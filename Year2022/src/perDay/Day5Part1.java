package perDay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Day5Part1 {
	public static Stack<String> reverse(Stack<String> arr){
	    Queue<String> arrCopy = new LinkedList<>();
	    while(! arr.empty()){
	        arrCopy.add(arr.pop());
	    }
	    while(arrCopy.peek() != null){
	        arr.add(arrCopy.remove());
	    }
	    return arr;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in;
        in = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\ressources\\InputDay5Of2022"));
        String line = in.readLine();
        
        int numberColumns = (line.length() - 3) / 4 + 1;
        String valueAt;
        
        List<Stack<String>> listColumns = new ArrayList<>();
        for (int n0Stack = 0; n0Stack < numberColumns; n0Stack++) {
        	listColumns.add(n0Stack, new Stack<String>());
        }
        
        String totalRearrangement = "";
        
        String[] oneInstruction;
        int quantity, from, to;

        do {
        	try {
        		if (line.contains("[")) {
        			for (int i = 0; i < numberColumns; i++) {
//        				try {
        				valueAt = line.charAt(4 * i + 1) + "";
        				if (! valueAt.isBlank()) {
        					listColumns.get(i).push(valueAt);
        				}
//        				} catch(Exception e) {
//        					System.out.println(line + " -- Problem : " + e);
//        				}
        			}
            	} 
        		
        		// Reverse stacks
        		if (line.charAt(0) == ' ') {
            		for (int i = 0; i < numberColumns; i++) {
            			Stack<String> reversedStack = reverse(listColumns.get(i));
            			listColumns.set(i, reversedStack);
            		}
            	}

            	if (line.charAt(0) == 'm') {
//            		System.out.println(line);
            		
            		oneInstruction = line.split(" ");
            		quantity = Integer.parseInt(oneInstruction[1]);
            		from = Integer.parseInt(oneInstruction[3]);
            		to = Integer.parseInt(oneInstruction[5]);
            		
            		for (int count = 0; count < quantity; count++) {
            			Stack<String> getFrom = listColumns.get(from - 1);
            			Stack<String> getTo = listColumns.get(to - 1);
            			getTo.push(getFrom.pop());
            		}
            		
//            		System.out.println(to);
            	}
            } catch(Exception e) {
//            	System.out.println(e);
            }
        	
        	line = in.readLine();
        } while (line != null);
        
        System.out.println(listColumns);
        
        for (Stack<String> column : listColumns) {
        	totalRearrangement += column.peek();
        }
        
        System.out.println(totalRearrangement);
	}

}
