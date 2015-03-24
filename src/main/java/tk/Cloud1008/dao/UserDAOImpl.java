package tk.Cloud1008.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import tk.Cloud1008.entity.PersistentLoginsEntity;
import tk.Cloud1008.entity.UserEntity;
import tk.Cloud1008.exceptions.InvalidCookiesException;
import tk.Cloud1008.exceptions.InvalidDataAccessException;

@Repository
public class UserDAOImpl implements UserDAO {
    private SessionFactory sessionFactory;
    
	//This setter will be used by Spring context to inject the sessionFactory instance
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked") 
	@Override
	public String getPasswordsByUsername(String username) {
		
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM UserEntity WHERE USERNAME = :username");
		query.setParameter("username", username);
		List <UserEntity> UserEntities = query.list();
		return UserEntities.get(0).getPassword();
	}

	@Override
	public void createPersistentLoginsEntity(String username, String series, String token, String LastUsed) {
		PersistentLoginsEntity persistentLoginsEntity = new PersistentLoginsEntity();
		persistentLoginsEntity.setUsername(username);
		persistentLoginsEntity.setSeries(series);
		persistentLoginsEntity.setToken(token);
		persistentLoginsEntity.setLastUsed(LastUsed);
		this.sessionFactory.getCurrentSession().save(persistentLoginsEntity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getUsernameBySeriesAndToken(String series, String token) throws InvalidCookiesException {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"FROM PersistentLoginsEntity WHERE SERIES = :series AND TOKEN = :token " );
			query.setParameter("series", series);
			query.setParameter("token", token);		
			List <PersistentLoginsEntity> PersistentLoginsEntities = query.list();
			return PersistentLoginsEntities.get(0).getUsername();
		} catch (Exception e) {
			throw new InvalidCookiesException();
		}
	}

	@Override
	public void setTokenBySeries(String series, String token) throws InvalidDataAccessException {
		try {
			Query query  = sessionFactory.getCurrentSession().createQuery(
				"UPDATE PersistentLoginsEntity SET TOKEN = :token WHERE SERIES = :series");
			query.setParameter("series", series);
			query.setParameter("token", token);
			query.executeUpdate();
		} catch (Exception e){
			throw new InvalidDataAccessException();
		}
	}

	@Override
	public void addUserEntity(UserEntity userEntity) throws InvalidDataAccessException {
		try {
			this.sessionFactory.getCurrentSession().save(userEntity);
		} catch (Exception e){
			throw new InvalidDataAccessException();
		}
	}
}