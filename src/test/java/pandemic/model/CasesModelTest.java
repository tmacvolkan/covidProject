package pandemic.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;


public class CasesModelTest {

	private CasesModel casesModel;
	
	@Before
	public void setUp() throws Exception {
		casesModel = new CasesModel(1l, 1l, 1l);
	}
	
	@Test
	public void toStringTest() throws Exception {
		assertNotNull(casesModel.toString());
	}
	
}
