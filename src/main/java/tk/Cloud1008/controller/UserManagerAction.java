package tk.Cloud1008.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import tk.Cloud1008.exceptions.InvalidUsernameOrPasswordException;
import tk.Cloud1008.service.UserDetails;
import tk.Cloud1008.service.UserManager;

import com.opensymphony.xwork2.ActionSupport;


public class UserManagerAction extends ActionSupport implements ServletResponseAware, ServletRequestAware, SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//userManager injected by spring context; This is cool !!
	private UserManager userManager;
	private String username;
	private String password;
	private String confirmPassword;
	private boolean rememberMe;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private Map session;
	private String goingToURL;
	private String result;

	@SuppressWarnings("unchecked")
	public String login() throws Exception{
		try {
			UserDetails userDetails = userManager.userLoginWithUsernameAndPassword(username, password);
			if (rememberMe) {
				Cookie cookie = new Cookie(LoginInterceptor.COOKIE_REMEMBERME_KEY, userDetails.getCookieString());
				cookie.setMaxAge(60 * 60 * 24 * 14);
				response.addCookie(cookie);
				rememberMe = false;
			}
			session.put(LoginInterceptor.USER_SESSION_KEY, userDetails);
			String goingToURL = (String) session.get(LoginInterceptor.GOING_TO_URL_KEY);
			if (StringUtils.isNotBlank(goingToURL)){
				setGoingToURL(goingToURL);
				session.remove(LoginInterceptor.GOING_TO_URL_KEY);
			} else {
				setGoingToURL("index.action");
			}
			result = SUCCESS;
			return SUCCESS;
		} catch (InvalidUsernameOrPasswordException e) {
			result = "User name or password is not corrected.";
			return SUCCESS;
		} catch (Exception e){
			result = "BMW.";
			return SUCCESS;
		}
	}
	
	public String logout() throws Exception{
		HttpSession session = request.getSession(false);
		if (session!=null)
			session.removeAttribute(LoginInterceptor.USER_SESSION_KEY);
		
		Cookie[] cookies = request.getCookies();
		if (cookies!=null) {
			for (Cookie cookie : cookies) {
				if (LoginInterceptor.COOKIE_REMEMBERME_KEY.equals(cookie
						.getName())) {
					cookie.setValue("");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					result = SUCCESS;
					return SUCCESS;
				}
			}
		}
		result = SUCCESS;
		return SUCCESS;
	}

	public String register() throws Exception{
		try {
			if(username.equals("") || password.equals("")){
				result = "Username or password is empty.";
				return SUCCESS;
			} else if(!password.equals(confirmPassword)){
				result = "Confirm password not matchs the password.";
				return SUCCESS;
			} else if (userManager.usernameExist(username)){
				result = "Username exist.";
				return SUCCESS;			
			} else {
				userManager.addUser(username, password);
				session.put(LoginInterceptor.USER_SESSION_KEY, (UserDetails)userManager.userLoginWithUsernameAndPassword(username, password));
				
				result = SUCCESS;
				return SUCCESS;
			}
		} catch (Exception e){
			result = "BMW";
			return SUCCESS;
		}
	}
	
	public String checkUsername(){
		result = SUCCESS;
		return SUCCESS;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setSession(Map session) {
		this.session = session;
	}	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	public void setGoingToURL(String goingToURL) {
		this.goingToURL = goingToURL;
	}
/*	public boolean isRememberMe() {
		return rememberMe;
	}*/
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	public void setLoginName(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	// public getters
	public List<String> getResult(){
		List<String> resultList = new ArrayList<String>();
		resultList.add(this.result);
		resultList.add(this.getUsername());
		return resultList;
	}
	
	private String getUsername(){
		try {
			return ((UserDetails)this.session.get(LoginInterceptor.USER_SESSION_KEY)).getUsername();
		}
		catch (Exception e){
			return "";
		}
	}
}
