package pandemic;

public interface HttpCaller {
	
	public String getData(String country) throws Exception;
	
	public String messageParser(String message) throws Exception;
	
}
