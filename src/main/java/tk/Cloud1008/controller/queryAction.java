package tk.Cloud1008.controller;
import java.util.List;

import tk.Cloud1008.entity.NumberPlateEntity;

@SuppressWarnings("serial")
public class queryAction extends EditNumberPlateAction {
	
	public List<String> getResult() {
		return this.result;
	}
	
	public NumberPlateEntity getNumberPlate() {
		return this.numberPlate;
	}
	
}
