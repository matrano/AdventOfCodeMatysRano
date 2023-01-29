package perDay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day3Part2 {
	public static char commonChar(String line1, String line2, String line3) {
		char common;
		for (int i = 0; i < line1.length(); i++) {
    		for (int j = 0; j < line2.length(); j++) {
    			if (line1.charAt(i) == line2.charAt(j)) {
    				for (int k = 0; k < line3.length(); k++) {
    					if (line1.charAt(i) == line3.charAt(k)) {
    						common = line3.charAt(k);
    						System.out.println("Common letter: " + common);
    						
    						return common;
    					}
    				}
    			}
    		}
    	}
		return ' ';
	}	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in;
        int totalScore = 0;
        
        String line1;
        String line2;
        String line3;
     
        in = new BufferedReader(new FileReader("C:\\Users\\matys\\WorkspaceApplication\\AdventOfCode\\Year2022\\src\\ressources\\InputDay3Of2022"));
        String line = in.readLine();

        char common;
        do { 
        	line1 = line;
        	line2 = in.readLine();
        	line3 = in.readLine();
        	
        	common = commonChar(line1, line2, line3);
        						
        	if (common <= 90) {
        		totalScore += common - 38;
        	} else {
        		totalScore += common - 96;
        	}
        	
        	line = in.readLine();
        } while (line != null);
        
        System.out.println("Score total: " + totalScore);
	}

}
