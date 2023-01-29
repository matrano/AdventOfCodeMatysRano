package perDay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

public class Day9Part1 {
	static int[][] posHeadAroundTail = new int[3][3];
	
	static int curPosXHeadAroundTail;
	static int curPosYHeadAroundTail;
	
	static Dictionary<String, Integer> posTailOnGrid = new Hashtable<String, Integer>();  
	
	public static int isRopePulled(char dir) {
		int[] vector = {0, 0};
		
		switch (dir) {
		case 'R':
			vector[0] = 1;
			break;
		case 'L':
			vector[0] = -1;
			break;
		case 'U':
			vector[1] = 1;
			break;
		case 'D':
			vector[1] = -1;
			break;
		}
		
		try {
			posHeadAroundTail[curPosXHeadAroundTail][curPosYHeadAroundTail] = 0;
			
			curPosXHeadAroundTail += vector[0];
			curPosYHeadAroundTail += vector[1];
			
			posHeadAroundTail[curPosXHeadAroundTail][curPosYHeadAroundTail] = 1;
		} catch (Exception e) {
			if (vector[0] != 0) { // Moved on the X-axis
				posHeadAroundTail[curPosXHeadAroundTail][curPosYHeadAroundTail] = 0;
				curPosYHeadAroundTail = 1;
				posHeadAroundTail[curPosXHeadAroundTail][curPosYHeadAroundTail] = 1;
				
				
				posTailOnGrid.put(null, null);
			} else { // Moved on the Y-axis
				posHeadAroundTail[curPosXHeadAroundTail][curPosYHeadAroundTail] = 0;
				curPosXHeadAroundTail = 1;
				posHeadAroundTail[curPosXHeadAroundTail][curPosYHeadAroundTail] = 1;
			}
		}
		
		return 1;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		BufferedReader in;   
        in = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\ressources\\InputDay9Of2022"));
        String line = in.readLine();
        
        posHeadAroundTail[1][1] = 1;
        
        curPosXHeadAroundTail = 1;
        curPosYHeadAroundTail = 1;
        
        Integer qtDifferentPositions = 1;
        
        // Starting position for the tail and the head of the rope
        // Position of the tail is found with a string: 3:7
        posTailOnGrid.put("0:0", qtDifferentPositions);
        
//        String position = 0;
//        String valueAtPos = posTailOnGrid.put(position, qtDifferentPositions);
//        if (valueAtPos != null) {
//        	posTailOnGrid.put(position, valueAtPos);
//        }
        
        do {
        	// Separate both information
        	char direction = line.charAt(0);
        	int length = Integer.parseInt(line.substring(2));
        	
        	// One time
        	isRopePulled(direction);
        	
        	// N more times according to quantity
        	if (length > 1) {
        		
        	}
        	
        	line = in.readLine();
        } while (line != null);
	}

}
