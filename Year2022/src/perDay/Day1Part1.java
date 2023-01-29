package perDay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day1Part1 {

	public static void main(String[] args) throws IOException {
        BufferedReader in;
        int input;
        int elfCursor = 0;
        int valueCalForElf;
        
        ArrayList<Integer> listOfElfs = new ArrayList<Integer>(Collections.nCopies(10, 0));

        in = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\ressources\\InputDay1Of2022"));
        String line = in.readLine(); 

        do {	
        	try {
        		input = Integer.parseInt(line, 10);
        		valueCalForElf = listOfElfs.get(elfCursor) + input;
        		listOfElfs.set(elfCursor, valueCalForElf);
            } catch(Exception e) {
            	elfCursor += 1;	
            	listOfElfs.add(elfCursor, 0);
            }
        	line = in.readLine(); 
        } while (line != null);
        System.out.println(max(listOfElfs));
        System.out.println(listOfElfs);
	}
	
	public static int max(ArrayList<Integer> list){
        int max = 0;
        for(Integer integer : list){
            if(integer > max){
                max = integer;
            }
        }
        return max;
    }

}
