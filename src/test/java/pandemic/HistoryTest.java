package pandemic;

import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class HistoryTest {

	private HistoryService history;
	private String historyResponse;
	
	private Util util;
	
	@Before
	public void setUp() throws Exception {
		history = new HistoryService();
		util = new Util();
		util.readProperties();
	}

	@Test 
    public void casesHttpCallerNotNullTest() throws Exception {
		historyResponse = history.getData("france");
		assertNotEquals(historyResponse, null);
	}
	
	@Test(expected = Exception.class)
	public void casesHttpReqFailedTest() throws Exception {
		history = new HistoryService();
		Mockito.when(util.caseUrl).thenReturn("https://aaaaaaaaa.tr");
		historyResponse = history.getData("germany");
	}
    
	
}
