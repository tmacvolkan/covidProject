package pandemic;

import java.net.HttpURLConnection;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pandemic.model.VaccineModel;

public class VaccinesService extends CommonServices implements HttpCaller{
	
	public VaccinesService() throws Exception {
		super();
	}

	public String getData(String country) throws Exception {

		String url = util.vaccinesUrl;
		
		HttpResponse<String> response = getHttpResponse(country, url);
        
        // http servis response code
        int responseStatusCode = response.statusCode();
        
		if (responseStatusCode == HttpURLConnection.HTTP_OK) { // success
			
			// parsing message
			return messageParser(response.body()).toString();

		}
		
		return null;
		
	}

	public String messageParser(String message) throws Exception {
		
		// reading json message
		JsonNode jsonNode = new ObjectMapper().readTree(message);
		
		if (jsonNode.get("Global") == null){
			throw new Exception("Country vaccine data not found");
		}
		
		// just desired fields for calculation percentage of vaccinated people
		long vaccinated = jsonNode.get("Global").findValue("All").findValue("people_vaccinated").asLong();
		long population = jsonNode.get("Global").findValue("All").findValue("population").asLong();
		
		// calculating percentage of vaccinated people
		float vaccinatedLevel = (vaccinated * Util.percentage) / population;
		
		VaccineModel vaccineModel = new VaccineModel(vaccinatedLevel);
		
		return vaccineModel.toString();
	}
	

}
