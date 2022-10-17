package pandemic;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Executor {

	public static void main(String[] args) throws Exception {
		
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		
        System.out.println("Please enter country: ");
        
        //getting country from user
        String country = scanner.nextLine();
                
        //find datas
        CasesService cases = new CasesService();
        
        VaccinesService vaccines = new VaccinesService();
        
        HistoryService history = new HistoryService();
        
        // execute program
        String casesData = cases.getData(country).toString();
        
        String vaccinesData = vaccines.getData(country).toString();
        
        String historyData = history.getData(country).toString();
        
        //print result
        System.out.println(casesData + vaccinesData + historyData);

	}

}
