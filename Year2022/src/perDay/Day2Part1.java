package perDay;

import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

public class Day2Part1 {

	public static void main(String[] args) throws IOException {
		BufferedReader in;
        int totalScore = 0;
        
        String line;
        char opponentChoice;
        char myChoice;
     
        in = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\ressources\\InputDay2Of2022"));
        line = in.readLine();
        
        String[] outcome = new String[2];
        do {
        	outcome = line.split(" ", 2);
        	
        	opponentChoice = outcome[0].charAt(0);
        	myChoice = outcome[1].charAt(0);
        	
        	if (opponentChoice == 'A') {
        		opponentChoice = 'X';
        	} else if (opponentChoice == 'B') {
        		opponentChoice = 'Y';
        	} else if (opponentChoice == 'C') {
        		opponentChoice = 'Z';
        	} else {
        		System.out.println("--- Error : OpponentChoice isnt expected ---");
        	}
        	
        	if (myChoice == 'X') {
        		totalScore += 1;
        		if (myChoice == opponentChoice) {
        			totalScore += 3;
        		} else if (opponentChoice == 'Z') {
        			totalScore += 6;
        		} else {
        			
        		}

        	} else if (myChoice == 'Y') {
        		totalScore += 2;
        		if (myChoice == opponentChoice) {
        			totalScore += 3;
        		} else if (opponentChoice == 'X') {
        			totalScore += 6;
        		} else {
        			
        		}

        	} else if (myChoice == 'Z') {
        		totalScore += 3;
        		if (myChoice == opponentChoice) {
        			totalScore += 3;
        		} else if (opponentChoice == 'Y') {
        			totalScore += 6;
        		} else {
        			
        		}

        	} else {
        		System.out.println("--- Error : MyChoice isnt expected ---");
        	}
        	
        	line = in.readLine();
        } while (line != null);
        
        System.out.println("Total score: " + totalScore);
	}

}
