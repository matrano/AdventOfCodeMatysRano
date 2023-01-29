package perDay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day6Part1 {
	final static int PACKET_LENGTH = 4;
	
	public static int isRepetitionSubstring(String str) {
		String tempStr;
		char initValue;
		for (int idx1 = 0; idx1 < str.length() - 1; idx1++) {
			initValue = str.charAt(idx1);
			tempStr = str.substring(idx1 + 1);
			for (int idx2 = 0; idx2 < tempStr.length(); idx2++) {
				if (initValue == tempStr.charAt(idx2)) {
					return 1;
				}
			}
			
		}
		return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in;   
        in = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\ressources\\InputDay6Of2022"));
        String line = in.readLine();
        System.out.println(line.length());
        
        for (int index = 0; index < line.length() - (PACKET_LENGTH - 1); index++) {
        	String subString = line.substring(index, index + PACKET_LENGTH);
        	System.out.println(subString);
        	if (isRepetitionSubstring(subString) == 0) {
        		System.out.println("End of marker position: " + (index + PACKET_LENGTH));
        		break;
        	}
        }
	}

}
