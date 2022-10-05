package pandemic.model;

import java.io.Serializable;

public class VaccineModel implements Serializable{
    
	private static final long serialVersionUID = 510463177438621628L;
	private float vaccinatedLevel;
	
	public VaccineModel(float vaccinatedLevel) {
		this.vaccinatedLevel = vaccinatedLevel;
	}

	@Override
	public String toString() {
		return "Vaccinated level in %" + this.vaccinatedLevel + " of total population" + "\n";
	}
}
