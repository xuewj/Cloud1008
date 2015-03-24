package tk.Cloud1008.dao;

import tk.Cloud1008.entity.UserEntity;
import tk.Cloud1008.exceptions.InvalidCookiesException;
import tk.Cloud1008.exceptions.InvalidDataAccessException;

public interface UserDAO {

	String getPasswordsByUsername(String username);

	void createPersistentLoginsEntity(String username, String series, String token, String LastUsed);

	String getUsernameBySeriesAndToken(String series, String token) throws InvalidCookiesException;

	void setTokenBySeries(String series, String token) throws InvalidDataAccessException;

	void addUserEntity(UserEntity userEntity) throws InvalidDataAccessException;

}
