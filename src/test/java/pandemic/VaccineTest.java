package pandemic;

import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class VaccineTest {

	private VaccinesService vaccines;
	private String vaccinesResponse;
	
	private Util util;
	
	@Before
	public void setUp() throws Exception {
		vaccines = new VaccinesService();
		util = new Util();
		util.readProperties();
	}

	@Test 
    public void vaccinesHttpCallerNotNullTest() throws Exception {
		vaccinesResponse = vaccines.getData("france");
		assertNotEquals(vaccinesResponse, null);
	}
	
	@Test(expected = Exception.class)
	public void vaccinesHttpReqFailedTest() throws Exception {
		vaccines = new VaccinesService();
		Mockito.when(util.caseUrl).thenReturn("https://aaaaaaaaa.tr");
		vaccinesResponse = vaccines.getData("germany");
	}
    
	
}
