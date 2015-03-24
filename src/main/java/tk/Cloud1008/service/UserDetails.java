package tk.Cloud1008.service;

public class UserDetails {


	private String username;
    private String CookieString;
    
    public UserDetails(String username, String CookiesString) {
        this.username = username;
        this.CookieString = CookiesString;
    }
   
    public void setUsername(String username) {
		this.username = username;
	}

	public void setCookiesString(String cookieString) {
		CookieString = cookieString;
	}

	public String getUsername() {
        return username;
    }
	
	public String getCookieString() {
		return CookieString;
	}
	
}