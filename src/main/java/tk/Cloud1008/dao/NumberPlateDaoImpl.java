package tk.Cloud1008.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import tk.Cloud1008.entity.NumberPlateEntity;
import tk.Cloud1008.exceptions.InvalidDataAccessException;

@Repository
public class NumberPlateDaoImpl implements NumberPlateDAO  
{
	//Session factory injected by spring context
    private SessionFactory sessionFactory;
	
    //This method will be called when a numberPlate object is added
	@Override
	public int addNumberPlate(NumberPlateEntity numberPlate) {
		this.sessionFactory.getCurrentSession().save(numberPlate);
		return numberPlate.getId();
	}

	//This method return list of numberPlates in database
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getAllNumberPlates(String username) throws InvalidDataAccessException {
		try{
			Query query = this.sessionFactory.getCurrentSession().createQuery("SELECT id from NumberPlateEntity WHERE OWNER = :owner");
			query.setParameter("owner", username);
			return query.list();
		} catch (Exception e){
			throw new InvalidDataAccessException();
		}
	}

	//Deletes a numberPlate by it's id
	@Override
	public void deleteNumberPlate(Integer numberPlateId) {
		NumberPlateEntity numberPlate = (NumberPlateEntity) sessionFactory.getCurrentSession()
										.load(NumberPlateEntity.class, numberPlateId);
        if (null != numberPlate) {
        	this.sessionFactory.getCurrentSession().delete(numberPlate);
        }
	}

	//This setter will be used by Spring context to inject the sessionFactory instance
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean authorizeUsernameAndId(String username, Integer numberPlateId) throws InvalidDataAccessException {
		try{
			Query query = this.sessionFactory.getCurrentSession().createQuery("SELECT Owner from NumberPlateEntity WHERE Id = :id");
			query.setParameter("id", numberPlateId);
			return query.list().get(0).equals(username);
		} catch (Exception e){
			throw new InvalidDataAccessException();
		}
	}

	@Override
	public NumberPlateEntity queryNumberPlate(Integer numberPlateId) throws InvalidDataAccessException {
		try{
			Query query = this.sessionFactory.getCurrentSession().createQuery("SELECT id,image,FileName,ContenType,number from NumberPlateEntity WHERE Id = :id");
			query.setParameter("id", numberPlateId);
			List<Object> list = query.list();
			Object[] objArray = (Object[])list.get(0);
			NumberPlateEntity entity = new NumberPlateEntity();
			entity.setId((Integer)objArray[0]);
			entity.setImage((String)objArray[1]);
			entity.setFileName((String)objArray[2]);
			entity.setContenType((String)objArray[3]);
			entity.setNumber((String)objArray[4]);
			return entity;
		} catch (Exception e){
			throw new InvalidDataAccessException();
		}
	}
	
	@Override
	public NumberPlateEntity queryNumberPlateDetails(Integer numberPlateId) throws InvalidDataAccessException {
		try{
			Query query = this.sessionFactory.getCurrentSession().createQuery("SELECT Details from NumberPlateEntity WHERE Id = :id");
			query.setParameter("id", numberPlateId);
			String details = (String)query.list().get(0);
			NumberPlateEntity entity = new NumberPlateEntity();
			entity.setDetails(details);
			return entity;
		} catch (Exception e){
			throw new InvalidDataAccessException();
		}
	}
}
