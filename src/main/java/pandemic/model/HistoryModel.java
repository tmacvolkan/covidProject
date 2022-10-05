package pandemic.model;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class HistoryModel implements Serializable{

	private static final long serialVersionUID = -1160826417063941531L;
    
	private Map<String, Long> history = new TreeMap<String, Long>();
	
	public HistoryModel(Map<String, Long> history) {
		this.history = history;
	}
	
	@Override
	public String toString() {
		return "Confirmed Historical data: " + this.history;
	}
	
	
	
}
