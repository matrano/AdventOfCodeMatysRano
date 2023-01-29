package perDay;

import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

public class Day2Part2 {

	public static void main(String[] args) throws IOException {
		BufferedReader in;
        int totalScore = 0;
        
        String line;
        char opponentChoice;
        char outcome;
     
        in = new BufferedReader(new FileReader("C:\\Users\\matys\\WorkspaceApplication\\AdventOfCode\\Year2022\\src\\ressources\\InputDay2Of2022"));
        line = in.readLine();
        
        String[] guide = new String[2];
        do {
        	guide = line.split(" ", 2);
        	
        	opponentChoice = guide[0].charAt(0);
        	outcome = guide[1].charAt(0);
        	
        	if (outcome == 'X') {
        		if (opponentChoice == 'A') {
        			totalScore += 3;
        		} else if (opponentChoice == 'B') {
        			totalScore += 1;
        		} else {
        			totalScore += 2;
        		}

        	} else if (outcome == 'Y') {
        		totalScore += 3;
        		if (opponentChoice == 'A') {
        			totalScore += 1;
        		} else if (opponentChoice == 'B') {
        			totalScore += 2;
        		} else {
        			totalScore += 3;
        		}

        	} else if (outcome == 'Z') {
        		totalScore += 6;
        		if (opponentChoice == 'A') {
        			totalScore += 2;
        		} else if (opponentChoice == 'B') {
        			totalScore += 3;
        		} else {
        			totalScore += 1;
        		}

        	} else {
        		System.out.println("--- Error : MyChoice isnt expected ---");
        	}
        	
        	line = in.readLine();
        } while (line != null);
        
        System.out.println("Total score: " + totalScore);

	}

}
