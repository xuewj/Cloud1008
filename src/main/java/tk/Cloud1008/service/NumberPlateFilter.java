package tk.Cloud1008.service;

import java.io.File;
import java.util.Map;

// The type of Objects in ArrayList must be Mat or String
public interface NumberPlateFilter {
	public Map<String, Object> proc (File file);
	public Map<String, Object> getResult();
}
