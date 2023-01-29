package perDay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day1Part2 {
	public static int maxIndex(ArrayList<Integer> list){
		int position = 0;
		int max = 0;
        for(int pos = 0; pos < list.size(); pos++){
            if(list.get(pos) > max){
            	max = list.get(pos);
                position = pos;
            }
        }
        return position;
    }

	public static void main(String[] args) throws IOException {
        BufferedReader in;
        int input;
        int elfCursor = 0;
        int valueCalForElf;
        
        ArrayList<Integer> listOfElfs = new ArrayList<Integer>(Collections.nCopies(10, 0));

        in = new BufferedReader(new FileReader("C:\\Users\\matys\\WorkspaceApplication\\AdventOfCode\\Year2022\\src\\ressources\\InputDay1Of2022"));
        String line = in.readLine(); 

        do {	
        	try {
        		listOfElfs.set(elfCursor, listOfElfs.get(elfCursor) + Integer.parseInt(line, 10));
            } catch(Exception e) {
            	elfCursor += 1;	
            	listOfElfs.add(elfCursor, 0);
            }
        	line = in.readLine(); 
        } while (line != null);
        
        int topX = 3;
        int posMax;
        int total = 0;
        for (int i = 0; i < topX; i++) {
        	posMax = maxIndex(listOfElfs);
        	// System.out.println(listOfElfs.get(posMax));
        	total += listOfElfs.get(posMax);
        	
        	listOfElfs.remove(posMax);
        }
        
        System.out.println("Total: " + total);
	}
}