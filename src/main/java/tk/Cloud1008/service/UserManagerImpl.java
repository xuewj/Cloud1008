package tk.Cloud1008.service;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import tk.Cloud1008.dao.UserDAO;
import tk.Cloud1008.entity.UserEntity;
import tk.Cloud1008.exceptions.InvalidCookiesException;
import tk.Cloud1008.exceptions.InvalidDataAccessException;
import tk.Cloud1008.exceptions.InvalidUsernameOrPasswordException;
import tk.Cloud1008.util.Base64;

public class UserManagerImpl implements UserManager {

	private UserDetails userDetails;
	private UserDAO userDAO;
	private SecureRandom random = new SecureRandom();
    public static final int DEFAULT_SERIES_LENGTH = 16;
    public static final int DEFAULT_TOKEN_LENGTH = 16;

    private int seriesLength = DEFAULT_SERIES_LENGTH;
    private int tokenLength = DEFAULT_TOKEN_LENGTH;

		
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public UserDetails userLoginWithUsernameAndPassword(
			String username, String password) throws InvalidUsernameOrPasswordException, UnsupportedEncodingException {
		if (userDAO.getPasswordsByUsername(username).equals(password)){
			// Password correct, create series and token
			String series = generateSeriesData();
			String token = generateTokenData();
			userDAO.createPersistentLoginsEntity(username, series, token, (new Date()).toString());
			userDetails =  new UserDetails(username, Base64Encode(series + ":" + token));
			return userDetails;
		} else {
			throw new InvalidUsernameOrPasswordException();
		}
	}

	@Override
	@Transactional
	public UserDetails userLoginWithCookies(String value) throws UnsupportedEncodingException, InvalidCookiesException, InvalidDataAccessException {
		String[] split = Base64Decode(value).split(":");
		String series = split[0];
		String token = split[1];
		String username = userDAO.getUsernameBySeriesAndToken(series, token);
		if (username != null){
			token = generateTokenData();
			userDAO.setTokenBySeries(series, token);
			userDetails = new UserDetails(username, Base64Encode(series + ":" + token));
			return userDetails;
		} else {
			throw new InvalidCookiesException();
		}	
	}
	
	@Override
	@Transactional
	public void addUser(String username, String password) throws InvalidDataAccessException {
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(username);
		userEntity.setPassword(password);
		userDAO.addUserEntity(userEntity);
	}
	
	@Override
	@Transactional	
	public boolean usernameExist(String username) {
		try{
			userDAO.getPasswordsByUsername(username);
		} catch (Exception e){
			return false;
		}
		return true;
	}
	
	
	private String Base64Decode(String value) throws UnsupportedEncodingException{
		return new String( Base64.decode(value.getBytes("ASCII")), "ASCII" ) ;
	}
	
	private String Base64Encode(String value) throws UnsupportedEncodingException{
		return new String( Base64.encode(value.getBytes("ASCII")), "ASCII" ) ;
	}
	
	protected String generateSeriesData() {
		byte[] newSeries = new byte[seriesLength];
		random.nextBytes(newSeries);
		return new String(Base64.encode(newSeries));
	}

    protected String generateTokenData() {
        byte[] newToken = new byte[tokenLength];
        random.nextBytes(newToken);
        return new String(Base64.encode(newToken));
    }

	@Override
	public UserDetails getUserDetails() {
		// TODO Auto-generated method stub
		return null;
	}




}
