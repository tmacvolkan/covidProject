package pandemic.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;


public class VaccineModelTest {

	private VaccineModel vaccineModel;
	
	@Before
	public void setUp() throws Exception {
		vaccineModel = new VaccineModel(1f);
	}
	
	@Test
	public void toStringTest() throws Exception {
		assertNotNull(vaccineModel.toString());
	}
	
}
