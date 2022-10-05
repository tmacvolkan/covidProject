package pandemic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class UtilTest {

	private Util util;
	float percentage = 100.0f;
	
	@Before
	public void setUp() throws Exception {
		util = new Util();
		util.readProperties();
	}
	
	@Test(expected = Exception.class)
	public void exceptionThrownTest() throws Exception {
	    util.errorManager(new Exception("unitTest"), "unitTestMessage");
	}
	
	@Test 
    public void urlNotNullTest() throws Exception {
		assertNotNull(util.caseUrl);
		assertNotNull(util.vaccinesUrl);
		assertNotNull(util.historyUrl);
		assertNotNull(util.percentage);
	}
    
	@Test 
    public void urlCorrectionTest() throws Exception {
		assertEquals(util.caseUrl, "https://covid-api.mmediagroup.fr/v1/cases?country={country}");
		assertEquals(util.historyUrl, "https://covid-api.mmediagroup.fr/v1/history?country={country}&status=confirmed");
		assertEquals(util.vaccinesUrl, "https://covid-api.mmediagroup.fr/v1/vaccines?country={country}");
	}
	
	
}
