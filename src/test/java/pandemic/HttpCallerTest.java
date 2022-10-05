package pandemic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class HttpCallerTest {

	private HttpCaller httpCaller;
	private String httpCallerResponse;
	
	private Util util;
	
	@Before
	public void setUp() throws Exception {
		httpCaller = new HttpCaller();
		util = new Util();
		util.readProperties();
	}
	
	@Test 
    public void dataTest() throws Exception {
    	
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(util.caseUrl.replace("{country}", "Germany")))
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(response.statusCode(), HttpURLConnection.HTTP_OK);
       
    } 
	
	@Test 
    public void dataVaccineTest() throws Exception {
    	
        HttpRequest request = HttpRequest.newBuilder()
        		.uri(URI.create(util.vaccinesUrl.replace("{country}", "Germany")))
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(response.statusCode(), HttpURLConnection.HTTP_OK);
       
    } 
	
	@Test 
    public void dataHistoryTest() throws Exception {
     	
        HttpRequest request = HttpRequest.newBuilder()
        		.uri(URI.create(util.historyUrl.replace("{country}", "Germany")))
                .version(HttpClient.Version.HTTP_2)
                .GET() 
                .build();
        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(response.statusCode(), HttpURLConnection.HTTP_OK);
       
    } 

	@Test 
    public void httpCallerNotNullTest() throws Exception {
		httpCallerResponse = httpCaller.call("germany");
		assertNotEquals(httpCallerResponse, null);
	}
	
	@Test(expected = Exception.class)
	public void incorrectMessageExceptionThrownTest() throws Exception {
		httpCallerResponse = httpCaller.call("xxx");
	}
	
	@Test(expected = Exception.class)
	public void httpReqFailedTest() throws Exception {
		httpCaller = new HttpCaller();
		Mockito.when(util.caseUrl).thenReturn("https://aaaaaaaaa.tr");
		httpCallerResponse = httpCaller.call("germany");
	}
    
	
}
