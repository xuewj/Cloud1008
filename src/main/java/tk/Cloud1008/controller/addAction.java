package tk.Cloud1008.controller;

import java.util.List;

@SuppressWarnings("serial")
public class addAction extends EditNumberPlateAction {
	public List<String> getResult() {
		return this.result;
	}
	
	public int getId(){
		return this.Id;
	}
}
