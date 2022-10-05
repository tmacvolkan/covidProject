package pandemic;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Executor {

	public static void main(String[] args) throws Exception {
		
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		
        System.out.println("Please enter country: ");
        
        //getting country from user
        String country = scanner.nextLine();
        
        HttpCaller caller = new HttpCaller();
        
        // execute program
        String result = caller.call(country);
        
        //print result
        System.out.println(result);

	}

}
