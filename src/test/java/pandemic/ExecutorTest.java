package pandemic;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

public class ExecutorTest {

	@Test
	public void mainTest() throws Exception {

	    String[] args = null;
	    final InputStream original = System.in;
	    
	    InputStream in = new ByteArrayInputStream("germany".getBytes());
	    System.setIn(in);
	    
	    Executor.main(args);
	    System.setIn(original);
	}
	
}
