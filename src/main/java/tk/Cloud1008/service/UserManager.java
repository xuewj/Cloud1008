package tk.Cloud1008.service;
import java.io.UnsupportedEncodingException;

import tk.Cloud1008.exceptions.InvalidCookiesException;
import tk.Cloud1008.exceptions.InvalidDataAccessException;
import tk.Cloud1008.exceptions.InvalidUsernameOrPasswordException;

public interface UserManager {
	public UserDetails userLoginWithUsernameAndPassword(String username,
			String password) throws InvalidUsernameOrPasswordException, UnsupportedEncodingException;
	public UserDetails userLoginWithCookies(String value) throws UnsupportedEncodingException, InvalidCookiesException, InvalidDataAccessException;
	public UserDetails getUserDetails();
	public void addUser(String username, String password) throws InvalidDataAccessException;
	public boolean usernameExist(String username);
}

