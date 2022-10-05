package pandemic;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pandemic.model.CasesModel;
import pandemic.model.HistoryModel;
import pandemic.model.MessageTypes;
import pandemic.model.VaccineModel;

public class HttpCaller {
    
	Util util = new Util();
	 
	public HttpCaller() throws Exception {
		// loading properties from file
		util.readProperties();
	}
    
	public String call(String country) throws Exception {
		
		try {
			// Making uppercase the first letter and lowercase others
			String countryVal = country.substring(0, 1).toUpperCase() + country.toLowerCase().substring(1);
			
			// Calling 3 http get services for collect data
			CasesModel cases = (CasesModel) getData(countryVal, MessageTypes.CASES);
			VaccineModel vaccines = (VaccineModel) getData(countryVal, MessageTypes.VACCINES);
			HistoryModel history = (HistoryModel) getData(countryVal, MessageTypes.HISTORY);
			
			// append result
			return cases.toString() + vaccines.toString() + history;
			
		} catch (Exception e) {
			throw e;
		}
	 
	}
	
	private Object getData(String country, MessageTypes type) throws Exception  {
		
		String url = null;
		
		if (type == MessageTypes.CASES){
			
			url = util.caseUrl;
			
		} else if (type == MessageTypes.HISTORY){

			url = util.historyUrl;
			
		} else if (type == MessageTypes.VACCINES){
			
			url = util.vaccinesUrl;
		}
		
		// prepairing url with user input
		url = url.replace("{country}", country);
		
        HttpClient client = HttpClient.newHttpClient();
       
        HttpRequest request = HttpRequest.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .uri(URI.create(url))
            .timeout(Duration.ofSeconds(20))
            .build();
        
        // Calling http service
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        // http servis response body
        String responseBody = response.body();
        
        // http servis response code
        int responseStatusCode = response.statusCode();
        
		if (responseStatusCode == HttpURLConnection.HTTP_OK) { // success
			
			// parsing message
			return messageParser(type, responseBody);

		} else {
			util.errorManager(new Exception("Http responseCode: " + responseStatusCode), "Http get request got an error! ");
		}
		
		return null;
	}
	
	private Object messageParser(MessageTypes type, String message) throws Exception {
		
		// reading json message
		JsonNode jsonNode = new ObjectMapper().readTree(message);
		
		if (type == MessageTypes.CASES){
			
			if (jsonNode.get("All") == null){
				throw new Exception("Country data not found");
			}

			// just desired fields
			CasesModel casesModel = new CasesModel(jsonNode.get("All").findValue("confirmed").asLong(),
					jsonNode.get("All").findValue("recovered").asLong(),
					jsonNode.get("All").findValue("deaths").asLong());
			
			return casesModel;
			
		} else if (type == MessageTypes.HISTORY){
			
			if (jsonNode.get("All") == null){
				throw new Exception("Country history data not found");
			}
			
			// histirocal data
			HistoryModel historyModel = new HistoryModel(new ObjectMapper().convertValue(jsonNode.get("All").findValue("dates"), new TypeReference<Map<String, Long>>(){}));
			
			return historyModel;
			
		} else if (type == MessageTypes.VACCINES){
			
			if (jsonNode.get("Global") == null){
				throw new Exception("Country vaccine data not found");
			}
			
			// just desired fields for calculation percentage of vaccinated people
			long vaccinated = jsonNode.get("Global").findValue("All").findValue("people_vaccinated").asLong();
			long population = jsonNode.get("Global").findValue("All").findValue("population").asLong();
			
			// calculating percentage of vaccinated people
			float vaccinatedLevel = (vaccinated * Util.percentage) / population;
			
			VaccineModel vaccineModel = new VaccineModel(vaccinatedLevel);
			
			return vaccineModel;
		}
		
		return null;
			    
	}
	
}
