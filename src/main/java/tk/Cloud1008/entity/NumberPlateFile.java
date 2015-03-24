package tk.Cloud1008.entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import tk.Cloud1008.exceptions.InvalidFileException;

public class NumberPlateFile {
	private static final Logger logger = Logger.getLogger(NumberPlateFile.class);
	private File file;
	private String contentType;
	private String filename;
//	private BufferedImage image;


	// Getter and setter
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
//	public BufferedImage getImage() {
//		return image;
//	}
//	
//	// This method will validate whether this file is a image using java library and then store into memory
//	public Boolean storeImageFile() {
//		try {
//			image = ImageIO.read(this.file);
//		    if (image == null) {
//		        throw new InvalidFileException();
//		    }
//		} catch (InvalidFileException ex){
//			logger.info("The file " + this.filename + " could not be opened, not a image file.");
//			return false;
//		} catch(IOException ex) {
//		    logger.info("The file " + this.filename + " could not be opened, an error occurred.");
//		    return false;
//		}
//		return true;
//	}
}
