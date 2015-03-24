package tk.Cloud1008.service.fliters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import tk.Cloud1008.service.NumberPlateFilter;

public class PictureFilter implements NumberPlateFilter {
	
	/*
	 * Return two objects:
	 * + BufferedImage ExtractedImage
	 */
    private Map<String, Object> resultList = new HashMap<String, Object>();
	private BufferedImage extractedImage;
	
//	PictureFilter (){}
		
	@Override
	public Map<String, Object> proc(File file) {
		try {
			extractedImage = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    resultList.put("extracted_image",extractedImage);
		return resultList;
	}

	@Override
	public Map<String, Object> getResult() {
		return resultList;
	}
}
