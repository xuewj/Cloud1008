package tk.Cloud1008.controller;

import java.util.List;

@SuppressWarnings("serial")
public class nullAction extends EditNumberPlateAction {
	public List<String> getResult() {
		this.result.add(ERROR);
		return this.result;
	}
}
