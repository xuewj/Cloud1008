package tk.Cloud1008.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.StrutsStatics;
import org.springframework.transaction.annotation.Transactional;

import tk.Cloud1008.exceptions.InvalidCookiesException;
import tk.Cloud1008.service.UserDetails;
import tk.Cloud1008.service.UserManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {
	private UserManager userManager;	
	public static final String USER_SESSION_KEY="wallet.session.user";
	public static final String COOKIE_REMEMBERME_KEY="wallet.cookie.rememberme";
	public static final String GOING_TO_URL_KEY="GOING_TO";

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);
		
		// Check if the current session is not a new one
		Map session = actionContext.getSession();
		if (session != null && session.get(USER_SESSION_KEY) != null){
			return invocation.invoke();
		}
		
		// Current session is a new one so login with cookies
		Cookie[] cookies = request.getCookies();
		try{
			if (cookies != null) {	
				for (Cookie cookie : cookies) {
					if (COOKIE_REMEMBERME_KEY.equals(cookie.getName())){
						UserDetails userDetails = userManager.userLoginWithCookies(cookie.getValue());
						if (userDetails != null){ 
							session.put(LoginInterceptor.USER_SESSION_KEY,userDetails);
							cookie.setValue(userDetails.getCookieString());
							response.addCookie(cookie);
							return invocation.invoke();
						}
					}
				}
			}
		} catch (InvalidCookiesException e){
			
		}
		
		// Login with cookies fail so login with password 
		setGoingToURL(session, invocation);
		return "login";
	}

	@SuppressWarnings("unchecked")
	private void setGoingToURL(Map session, ActionInvocation invocation){
		String url = "";
		String namespace = invocation.getProxy().getNamespace();
		if (StringUtils.isNotBlank(namespace) && !namespace.equals("/")){
			url = url + namespace;
		}
		String actionName = invocation.getProxy().getActionName();
		if (StringUtils.isNotBlank(actionName)){
			url = url + "/" + actionName + ".action";
		}
		session.put(GOING_TO_URL_KEY, url);
	}
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}