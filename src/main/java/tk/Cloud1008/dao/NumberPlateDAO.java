package tk.Cloud1008.dao;

import java.util.List;

import tk.Cloud1008.entity.NumberPlateEntity;
import tk.Cloud1008.exceptions.InvalidDataAccessException;

public interface NumberPlateDAO 
{
	//This method will be called when a numberPlate object is added
    public int addNumberPlate(NumberPlateEntity numberPlate);
    //This method return list of numberPlates in database
    public List<Integer> getAllNumberPlates(String username) throws InvalidDataAccessException;
    //Deletes a numberPlate by it's id
    public void deleteNumberPlate(Integer numberPlateId);
    //Authorize the id match the corresponding username
	public boolean authorizeUsernameAndId(String username, Integer numberPlateId) throws InvalidDataAccessException;
	public NumberPlateEntity queryNumberPlate(Integer numberPlateId) throws InvalidDataAccessException;
	public NumberPlateEntity queryNumberPlateDetails(Integer numberPlateId) throws InvalidDataAccessException;
}