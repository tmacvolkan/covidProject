package pandemic.model;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;


public class HistoryModelTest {

	private HistoryModel historyModel;
	
	@Before
	public void setUp() throws Exception {
		Map<String, Long> historyMap = new HashMap<String, Long>(){{put("Test", 1l);}};
		historyModel = new HistoryModel(historyMap);
	}
	
	@Test
	public void toStringTest() throws Exception {
		assertNotNull(historyModel.toString());
	}
	
}
