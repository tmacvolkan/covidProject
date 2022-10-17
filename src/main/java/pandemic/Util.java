package pandemic;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Util {

	private static final String propertyFile = "properties.json";
	public String caseUrl;
	public String historyUrl;
	public String vaccinesUrl;
	
	public static final float percentage = 100.0f;
	
	// manage the errors
	public void errorManager(Exception e, String message) throws Exception {
		
    	System.err.println(message + " : " + e.getMessage());
    	e.printStackTrace();
    	throw new Exception(e.getMessage());
    	
	}
	
	public String capitalize(String str) {
	    if(str == null || str.isEmpty()) {
	        return str;
	    }

	    return str.substring(0, 1).toUpperCase() + str.toLowerCase().substring(1);
	}

	// read urls from property
	public void readProperties() throws Exception {

		// property file loader
        InputStream is = getFileFromResourceAsStream(propertyFile);
        
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        
        StringBuilder stringBuilder = new StringBuilder();
        
        // read all lines
        for (String line; (line = reader.readLine()) != null;) {
        	stringBuilder.append(line);
        }
        
        // get all json objects
		JsonNode jsonNode = new ObjectMapper().readTree(stringBuilder.toString());
		
		caseUrl = jsonNode.findValue("caseUrl").textValue();
		historyUrl = jsonNode.findValue("historyUrl").textValue();
		vaccinesUrl = jsonNode.findValue("vaccinesUrl").textValue();
	}
	
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
	
}
