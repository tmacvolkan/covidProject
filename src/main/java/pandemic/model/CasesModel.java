package pandemic.model;

import java.io.Serializable;

public class CasesModel implements Serializable{

	private static final long serialVersionUID = -1160826417063941531L;
    
	private long confirmed;
	private long recovered;
	private long deaths;
    
	public CasesModel(long confirmed, long recovered, long deaths) {
		this.confirmed = confirmed;
		this.recovered = recovered;
		this.deaths = deaths;
	}
	
	@Override
	public String toString() {
		return "Confirmed: " + this.confirmed + "\n" +
				"Recovered: " + this.recovered + "\n" +
				"Deaths: " + this.deaths + "\n" ;
	}
	
}
