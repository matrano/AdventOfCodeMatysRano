package perDay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day3Part1 {
	static String firstComp;
	static String secondComp;
	
	public static char commonChar(String line) {
    	firstComp = line.substring(0, line.length() / 2);
    	secondComp = line.substring(line.length() / 2);
    	
    	for (int i = 0; i < firstComp.length(); i++) {
    		for (int j = 0; j < secondComp.length(); j++) {
    			if (firstComp.charAt(i) == secondComp.charAt(j)) {
    				return firstComp.charAt(i);
    			}
    		}
    	}
		return ' ';
	}	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in;
        int totalScore = 0;
     
        in = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\ressources\\InputDay3Of2022"));
        String line = in.readLine();

        char common;
        do { 
        	common = commonChar(line);
        	
        	if (common <= 90) {
        		totalScore += common - 38;
        	} else {
        		totalScore += common - 96;
        	}
        	
        	line = in.readLine();
        } while (line != null);
        System.out.println("Total score: " + totalScore);
	}
}
