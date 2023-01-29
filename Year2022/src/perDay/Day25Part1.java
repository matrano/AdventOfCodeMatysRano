package perDay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class Day25Part1 {
	static Hashtable<String, Hashtable<String, String>> dictOutcomeSNAFU = outcomeSNAFU();
	
	public static Hashtable<String, Hashtable<String, String>> outcomeSNAFU() {
		Hashtable<String, Hashtable<String, String>> dictOutcomeSNAFU = new Hashtable<String, Hashtable<String, String>>();
		String[] listCharSNAFU = {"=", "-", "0", "1", "2"};
		String[] listOutcomeSNAFU = {"-1", "-2", "=", "-", "0", "1", "2", "1=", "1-"};
		
		for (int line = 0; line < listCharSNAFU.length; line++) {
			Hashtable<String, String> dictLineOutcomeSNAFU = new Hashtable<String, String>();
			for (int column = 0; column < listCharSNAFU.length; column++) {
				dictLineOutcomeSNAFU.put(listCharSNAFU[column], listOutcomeSNAFU[line + column]);
			}
			dictOutcomeSNAFU.put(listCharSNAFU[line], dictLineOutcomeSNAFU);
		}
		
		return dictOutcomeSNAFU;
	}
	
	public static String addSNAFU(String total, String number) {
		// Find which one is the longest
		String longest = total; 
		String shortest = number;
		if (total.length() < number.length()) {
			longest = number;
			shortest = total;
		}
		
		total = "";
		String carrying = null;
		for (int idx = 1; idx <= longest.length(); idx++) {
			String charAtIdxLongest = longest.charAt(longest.length() - idx) + "";
			String charAtIdxShortest = null;
			
			String outcomeSNAFU;
			
			try {
				// Find if index for shortest is still inside his length
				charAtIdxShortest = shortest.charAt(shortest.length() - idx) + "";
				
				// Will only be activated if index for shortest is not outside of length
				outcomeSNAFU = dictOutcomeSNAFU.get(charAtIdxLongest).get(charAtIdxShortest);
			} catch (Exception e) {
				outcomeSNAFU = charAtIdxLongest;
			}
			
			// If there's carrying add it to the outcome
			if (carrying != null) {
				outcomeSNAFU = addSNAFU(outcomeSNAFU, carrying);
				
				carrying = null;
			}
			
			// If the outcome has carrying separate it
			if (outcomeSNAFU.length() == 2) {
				carrying =  outcomeSNAFU.charAt(0) + "";
				outcomeSNAFU = outcomeSNAFU.substring(1);
			}
			
			// If it's the last iteration and there's still carrying add it
			if (carrying != null && idx == longest.length()) {
				outcomeSNAFU = carrying + outcomeSNAFU;
				
				carrying = null;
			}
			total = outcomeSNAFU + total;
		}
		return total;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in;   
        in = new BufferedReader(new FileReader("C:\\Users\\matys\\WorkspaceApplication\\AdventOfCode\\Year2022\\src\\ressources\\InputDay25Of2022"));
        String total = in.readLine();
        String fuelRequirement = in.readLine();
		
		do {
			total = addSNAFU(total, fuelRequirement);
			
			fuelRequirement = in.readLine();
		} while (fuelRequirement != null);
		
		System.out.println("SNAFU number for fuel requirement: " + total);
	}
}
