package tk.Cloud1008.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.json.JSONObject;

import tk.Cloud1008.util.Base64;

public class NumberPlateProcessing{
	static private Map<String, String> Type2Filter;
	private NumberPlateFilter filter;
	
	// Constructor
	public NumberPlateProcessing(File image) {	
		//Routine for processing
//		this.src = this.bufferedImage2Mat(image);
//		PreProcessing = new NumberPlateFiltersChain(this.src);
		
		Type2Filter = new HashMap<String, String>();
		Type2Filter.put("jpg","PictureFilter");
		
		//Apply the filter
		try {
			filter = (NumberPlateFilter)(
					 ( Class.forName("tk.Cloud1008.service.fliters." + Type2Filter.get("jpg")) )
//					 .getConstructor()
					 .newInstance() );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		filter.proc(image);
	}
	
//	public String calculateNumber(){
//		//Do the processing
//		return "fuck";
//	}

	public String bufferedImage2Base64String(BufferedImage bufferedImage) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {		
			ImageIO.write( bufferedImage, "jpg", baos );
			baos.flush();
			byte[] bytes = baos.toByteArray();
			baos.close();
			String base64 = new String(Base64.encode(bytes), "ASCII");
			base64 = "data:image/jpg;base64,"  + base64;
			return base64;
		} catch (Exception e) {
			return null;
		}
	}
	
	public String getThumbnail() {
		BufferedImage src = (BufferedImage)filter.getResult().get(0);
		if (src == null) return null;
		
		int w = src.getWidth();
		int h = src.getHeight();
		BufferedImage squareImage;
		if (w > h){
			squareImage = src.getSubimage((w - h) / 2,0, h, h);//(Mat)(new Crop((w - h) / 2,0, h, h)).proc(resultList).get(0);
		} else {
			squareImage = src.getSubimage(0, (h - w) / 2, w, w);//.proc(resultList).get(0);
		}
		BufferedImage result;
		result = (BufferedImage) squareImage.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
		return bufferedImage2Base64String(result);
	}
	
	public String getDetails(){
		JSONObject jSONObject = new JSONObject();
//		for (int i = 0; i < PreProcessing.size(); i++) {
//			jSONObject.put(options[0][i][0], "data:image/jpg;base64," + mat2Base64String((Mat)PreProcessing.get(i).getResult().get(0)));
//		}
		
		for (Map.Entry<String, Object> entry : filter.getResult().entrySet())
		{
			jSONObject.put( entry.getKey(),
					bufferedImage2Base64String((BufferedImage)entry.getValue()));
		}
		return jSONObject.toString();
	}
}
