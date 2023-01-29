package perDay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day8Part2 {
	static List<List<Integer>> forestMap  = new ArrayList<>();
	
	// Calculating the scenic score for a position in the forest (which is not an edge)
	public static int scenicScore(int column, int line) {
		int treeHeight = forestMap.get(line).get(column);
		
		int scenicScore = 1;
		
		int viewingDistance = 1;
		for (int idx = column - 1; idx >= 0; idx--) { // Left
			if (forestMap.get(line).get(idx) >= treeHeight || idx == 0) {
				break;
			} else {
				viewingDistance += 1;
			}
		}
		scenicScore *= viewingDistance;
		
		viewingDistance = 1;
		for (int idx = column + 1; idx <= forestMap.get(line).size() - 1; idx++) { // Right
			if (forestMap.get(line).get(idx) >= treeHeight || idx == forestMap.get(line).size() - 1) {
				break;
			} else {
				viewingDistance += 1;
			}
		}
		scenicScore *= viewingDistance;
		
		viewingDistance = 1;
		for (int idx = line - 1; idx >= 0; idx--) { // Up
			if (forestMap.get(idx).get(column) >= treeHeight || idx == 0) {
				break;
			} else {
				viewingDistance += 1;
			}
		}
		scenicScore *= viewingDistance;
		
		viewingDistance = 1;
		for (int idx = line + 1; idx <= forestMap.size() - 1; idx++) { // Down
			if (forestMap.get(idx).get(column) >= treeHeight || idx == forestMap.size() - 1) {
				break;
			} else {
				viewingDistance += 1;
			}
		}
		scenicScore *= viewingDistance;
		
		return scenicScore;
	}
	
	public static boolean isNotEdge(int column, int line) {
		if (line == 0 || line == forestMap.size() - 1 ||
				column == 0 || column == forestMap.get(0).size() - 1) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in;   
        in = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\ressources\\InputDay8Of2022"));
        String str = in.readLine();
        
        // Initialize forestMap
        for (int n0Column = 0; n0Column < str.length(); n0Column++) { 
        	forestMap.add(new ArrayList<Integer>());
        }
        
        // Fill forestMap with height of all trees from input
        int line = 0;
        do {
        	for (int column = 0; column < str.length(); column++) {
        		int treeHeight = str.charAt(column) - 48; // '0'=48 and '9'=57 in ASCII table
        		forestMap.get(line).add(treeHeight);
        	}
        	line += 1;
        	
        	str = in.readLine();
        } while (str != null);
        
        // Finding the highest scenic score    
        int highestScenicScore = 0;  
        for (int y = 0; y < forestMap.size(); y++) {
        	for (int x = 0; x < forestMap.get(0).size(); x++) {
        		// Scenic score is equal to 0 if it is an edge
        		if (isNotEdge(x, y) && scenicScore(x, y) > highestScenicScore) {
        			highestScenicScore = scenicScore(x, y);
        		} 
        	}
        }
        
        System.out.println(forestMap);
        System.out.println(highestScenicScore);
	}

}
