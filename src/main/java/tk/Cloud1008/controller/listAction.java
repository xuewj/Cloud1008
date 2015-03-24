package tk.Cloud1008.controller;

import java.util.List;

@SuppressWarnings("serial")
public class listAction extends EditNumberPlateAction {
	public List<String> getResult() {
		return this.result;
	}
	
	public List<Integer> getNumberPlates() {
		return this.numberPlates;
	}
}
