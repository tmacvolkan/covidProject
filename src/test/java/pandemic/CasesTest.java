package pandemic;

import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class CasesTest {

	private CasesService cases;
	private String casesResponse;
	
	private Util util;
	
	@Before
	public void setUp() throws Exception {
		cases = new CasesService();
		util = new Util();
		util.readProperties();
	}

	@Test 
    public void casesHttpCallerNotNullTest() throws Exception {
		casesResponse = cases.getData("germany");
		assertNotEquals(casesResponse, null);
	}
	
	@Test(expected = Exception.class)
	public void casesHttpReqFailedTest() throws Exception {
		cases = new CasesService();
		Mockito.when(util.caseUrl).thenReturn("https://aaaaaaaaa.tr");
		casesResponse = cases.getData("germany");
	}
    
	
}
