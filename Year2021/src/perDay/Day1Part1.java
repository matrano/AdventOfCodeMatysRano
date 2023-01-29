//package perDay;
//
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//
//public class Day1Part1 {
//
//	public static void main(String[] args) throws IOException {
//		String line;
//        BufferedReader in;
//        
//        ArrayList<String> input = new ArrayList<String>();
//        
//        int counter = 0;
//        
//        in = new BufferedReader(new FileReader("C:\\Users\\matys\\WorkspaceApplication\\AdventOfCode\\Year2021\\src\\ressources\\InputDay1Of2021"));
//        line = in.readLine(); 
//        int curValue = Integer.parseInt(line);
//
//        
//        
//        do {
//        	input.add(line);
//            line = in.readLine();
//            
//            try {
//            nextValue = Integer.parseInt(line);
//            } catch (Exception e) {
//         	   
//            }
//            
//            if (curValue == nextValue) {
//         	   continue;
//            } else if (nextValue > curValue) {
//         	   counter += 1;
//            } else {
//         	   continue;
//            }
//            
//            curValue = nextValue;
//        } while (line != null);
//        
//        System.out.println(input);
//        System.out.println(curValue);
//        System.out.println(nextValue);
//        System.out.println(counter);
//	}
//
//}
