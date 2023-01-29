package perDay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day8Part1 {
	static List<List<Integer>> forestMap  = new ArrayList<>();
	
	public static int isVisible(int column, int line) {
		int treeHeight = forestMap.get(line).get(column);
		System.out.println("TreeHeight: " + treeHeight + " L/C: " + line + ", " + column);
		
		for (int idx = column - 1; idx >= 0; idx--) { // Left
			if (forestMap.get(line).get(idx) >= treeHeight) {
				break;
			} else if (idx == 0) {
				System.out.println("Visible");
				return 1;
			}
		}
		
		for (int idx = column + 1; idx <= forestMap.get(line).size() - 1; idx++) { // Right
			if (forestMap.get(line).get(idx) >= treeHeight) {
				break;
			} else if (idx == forestMap.get(line).size() - 1) {
				System.out.println("Visible");
				return 1;
			}
		}
		
		for (int idx = line - 1; idx >= 0; idx--) { // Up
			if (forestMap.get(idx).get(column) >= treeHeight) {
				break;
			} else if (idx == 0) {
				System.out.println("Visible");
				return 1;
			}
		}
		
		for (int idx = line + 1; idx <= forestMap.size() - 1; idx++) { // Down
			if (forestMap.get(idx).get(column) >= treeHeight) {
				break;
			} else if (idx == forestMap.size() - 1) {
				System.out.println("Visible");
				return 1;
			}
		}
		return 0;
	}
	
	public static boolean isEdge(int column, int line) {
		if (line == 0 || line == forestMap.size() - 1 ||
				column == 0 || column == forestMap.get(0).size() - 1) {
			return true;
		}
		return false;
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
        
        // Number of trees visible from outside of the grid     
        int visibleTreeCount = 0;  
        for (int y = 0; y < forestMap.size(); y++) {
        	for (int x = 0; x < forestMap.get(0).size(); x++) {
        		if (isEdge(x, y)) {
        			visibleTreeCount += 1;
        		} else {
        			visibleTreeCount += isVisible(x, y);
        		}
        	}
        }
        
        System.out.println(forestMap);
        System.out.println(visibleTreeCount);
	}

}
