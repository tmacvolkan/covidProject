package pandemic;

import java.net.HttpURLConnection;
import java.net.http.HttpResponse;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pandemic.model.HistoryModel;

public class HistoryService extends CommonServices implements HttpCaller{
	
	public HistoryService() throws Exception {
		super();
	}
	
	public String getData(String country) throws Exception {
		
		String url = util.historyUrl;
		
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
			throw new Exception("Country history data not found");
		}
		
		// histirocal data
		HistoryModel historyModel = new HistoryModel(new ObjectMapper().convertValue(jsonNode.get("All").findValue("dates"), new TypeReference<Map<String, Long>>(){}));
		
		return historyModel.toString();
		
	}

}
