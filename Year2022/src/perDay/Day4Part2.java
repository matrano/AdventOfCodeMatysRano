package perDay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day4Part2 {
	public static int contained(String line) {
		// Variables
		int xF, xI, yF, yI;
        String[] rangePair = new String[2];
        String[] rangeElf = new String[2];
        
        rangePair = line.split(",");
    	
    	// First elf : x
    	rangeElf = rangePair[0].split("-");
    	xI = Integer.parseInt(rangeElf[0]);
    	xF = Integer.parseInt(rangeElf[1]);
    	
    	// Second elf : y
    	rangeElf = rangePair[1].split("-");
    	yI = Integer.parseInt(rangeElf[0]);
    	yF = Integer.parseInt(rangeElf[1]);
		
    	// Finding overlap
    	if (xI <= yF) { 
    		if (xF >= yI) {
        		return 1;
        	}
    	}
    	return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in;
        in = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\ressources\\InputDay4Of2022"));
        String line = in.readLine();

        int nbrPairContained = 0;  
        do {
        	nbrPairContained += contained(line);      	
        	
        	line = in.readLine();
        } while (line != null);
        
        System.out.println("Total number of pair with an overlap: " + nbrPairContained);
	}

}
