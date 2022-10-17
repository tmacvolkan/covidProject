package pandemic;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

public class CommonServices {

	Util util = new Util();
	 
	public CommonServices() throws Exception {
		// loading properties from file
		util.readProperties();
	}
	
	public HttpResponse<String> getHttpResponse(String country ,String url) throws Exception {
		
		// prepairing url with user input
		String capitalUpperCaseInput = util.capitalize(country);
		url = url.replace("{country}", capitalUpperCaseInput);
		
        HttpClient client = HttpClient.newHttpClient();
       
        // make request
        HttpRequest request = HttpRequest.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .uri(URI.create(url))
            .timeout(Duration.ofSeconds(20))
            .build();
        
        // Calling http service
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        // http servis response
        return response;
        
	}
}
