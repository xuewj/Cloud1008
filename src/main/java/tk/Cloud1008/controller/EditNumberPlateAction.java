package tk.Cloud1008.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import tk.Cloud1008.entity.NumberPlateEntity;
import tk.Cloud1008.entity.NumberPlateFile;
import tk.Cloud1008.exceptions.InvalidFrontEndAccessException;
import tk.Cloud1008.service.NumberPlateManager;
import tk.Cloud1008.controller.LoginInterceptor;
import tk.Cloud1008.service.UserDetails;
import tk.Cloud1008.util.Base64;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class EditNumberPlateAction extends ActionSupport implements SessionAware
{
	/*
	 *  Public properties for subclasses
	 */
	//List of numberPlates; Setter and Getter are below
	public List<Integer> numberPlates;
	
	//NumberPlate object to be added; Setter and Getter are below
	public NumberPlateEntity numberPlate;
	
	//Result of addAction and deleteAction
	public List<String> result = new ArrayList<String>();
	
	public int Id;
	/*
	 *  Private properties for internal using
	 */
	//numberPlateFile to store informations about the uploaded file
	private NumberPlateFile numberPlateFile = new NumberPlateFile();
	
	private static final long serialVersionUID = 1L;
	
	//Logger configured using log4j
	private static final Logger logger = Logger.getLogger(EditNumberPlateAction.class);
	
	//NumberPlate manager injected by spring context; This is cool !!
	private NumberPlateManager numberPlateManager;

	//Http session created by Tomcat
	private Map session;
	
	/*
	 *  Methods for struts actions
	 */
	//This method return list of numberPlates in database
	public String listNumberPlates() {
		logger.info("listNumberPlates method called");
		if (getUsernameFromCurrentSession().equals("")) {
			numberPlates = null;
		} else {
			numberPlates = numberPlateManager.getAllNumberPlates(getUsernameFromCurrentSession());
		}
		result.add(SUCCESS);
		return SUCCESS;
	}

	//This method will be called when a numberPlate object is added
	public String addNumberPlate() {
		logger.info("addNumberPlate method called");
		if (numberPlateFile.getFile() != null){
			List<Object> idAndNumber = numberPlateManager.addNumberPlate(getUsernameFromCurrentSession(), numberPlateFile);
			Id = (Integer)idAndNumber.get(0);
			result.add((String)idAndNumber.get(1));
		} else {
			result.add(ERROR);
		}
		return SUCCESS;
	}	

	//Deletes a numberPlate by it's id passed in path parameter
	public String deleteNumberPlate() {
		logger.info("deleteNumberPlate method called");
		try {
			numberPlateManager.deleteNumberPlate(getUsernameFromCurrentSession(), numberPlate.getId());
			result.add(SUCCESS);
		} catch (InvalidFrontEndAccessException e) {
			result.add(ERROR);
		}
		return SUCCESS;
	}
	
	//Query entity of a numberPlate by it's id passed in path parameter
	public String queryNumberPlate() {
		logger.info("queryNumberPlate method called");
		try {
			numberPlate = Base64Decode(numberPlateManager.queryNumberPlate(getUsernameFromCurrentSession(), numberPlate.getId()));
			result.add(SUCCESS);
		} catch (InvalidFrontEndAccessException e) {
			result.add(ERROR);
		}
		return SUCCESS;
	}
	
	//Query entity of a numberPlate by it's id passed in path parameter
	public String queryNumberPlateDetails() {
		logger.info("queryNumberPlate method called");
		try {
			numberPlate = numberPlateManager.queryNumberPlateDetails(getUsernameFromCurrentSession(), numberPlate.getId());
			result.add(SUCCESS);
		} catch (InvalidFrontEndAccessException e) {
			result.add(ERROR);
		}
		return SUCCESS;
	}
	public String nullAction(){
		return SUCCESS;
	}
	
	
	/*
	 * Getters and setters
	 */

	public void setNumberPlateManager(NumberPlateManager numberPlateManager) {
		this.numberPlateManager = numberPlateManager;
	}

	
/*	public List<Integer> getNumberPlates() {
		return numberPlates;
	}

	public NumberPlateEntity getNumberPlate() throws InvalidFrontEndAccessException {
		return numberPlate;
	}*/
	

	public void setNumberPlate(NumberPlateEntity numberPlate) {
		this.numberPlate = numberPlate;
	}

	public void setUpload(File file) {
		this.numberPlateFile.setFile(file);
	}

	public void setUploadContentType(String contentType) {
	    this.numberPlateFile.setContentType(contentType);
	}

	public void setUploadFileName(String filename) {
		this.numberPlateFile.setFilename(filename);
	}
	
	@Override
	public void setSession(Map session) {
		this.session = session;
	}
	
	
	/*
	 * Useful methods for internal use
	 */
	private String getUsernameFromCurrentSession(){
		try {
			return ((UserDetails)this.session.get(LoginInterceptor.USER_SESSION_KEY)).getUsername();
		}
		catch (Exception e){
			return "";
		}
	}
	
	private String Base64Decode(String value) {
		try {
			return new String( Base64.decode(value.getBytes("UTF-8")), "UTF-8" ) ;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private NumberPlateEntity Base64Decode(NumberPlateEntity entity){
		try{
			entity.setFileName(Base64Decode(entity.getFileName()));
			return entity;
		} catch (Exception e){
			return null;
		}
	}
}
