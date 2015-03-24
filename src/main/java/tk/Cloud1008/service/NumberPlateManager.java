package tk.Cloud1008.service;

import java.util.List;

import tk.Cloud1008.entity.NumberPlateEntity;
import tk.Cloud1008.entity.NumberPlateFile;
import tk.Cloud1008.exceptions.InvalidDataAccessException;
import tk.Cloud1008.exceptions.InvalidFrontEndAccessException;

public interface NumberPlateManager {
	//This method will be called when a numberPlate object is added
    public List<Object> addNumberPlate(String username, NumberPlateFile numberPlateFile);
    //This method return list of numberPlates in database
    public List<Integer> getAllNumberPlates(String username);
    //Deletes a numberPlate by it's id
    public void deleteNumberPlate(String username, Integer numberPlateId) throws InvalidFrontEndAccessException;
	public NumberPlateEntity queryNumberPlate(String username,Integer numberPlateId) throws InvalidFrontEndAccessException;
	public NumberPlateEntity queryNumberPlateDetails(String username,Integer numberPlateId) throws InvalidFrontEndAccessException;
}
