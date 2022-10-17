package pandemic;

import java.net.HttpURLConnection;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pandemic.model.CasesModel;

public class CasesService extends CommonServices implements HttpCaller{
	
	public CasesService() throws Exception {
		super();
	}
	
	public String getData(String country) throws Exception {
		
		String url = util.caseUrl;
		
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
			
		if (jsonNode.get("All") == null){
			throw new Exception("Country data not found");
		}

		// just desired fields
		CasesModel casesModel = new CasesModel(jsonNode.get("All").findValue("confirmed").asLong(),
				jsonNode.get("All").findValue("recovered").asLong(),
				jsonNode.get("All").findValue("deaths").asLong());
		
		return casesModel.toString();

	}

}
