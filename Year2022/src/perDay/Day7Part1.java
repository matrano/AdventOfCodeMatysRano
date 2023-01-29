package perDay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
// Doesnt work, I cheated
public class Day7Part1 {
	static String presentDirectory;
	static ArrayList<String> filesystem = new ArrayList<String>();
	static ArrayList<String> directoryList = new ArrayList<String>();
	
	public static void readCommand(String instruction) {
//		if (instruction.charAt(0) == 'c') { // Change directory
			if (instruction.equals("cd /")) {
				presentDirectory = "/"; }
			else if (instruction.equals("cd ..")) {
				presentDirectory = presentDirectory.substring(0, presentDirectory.lastIndexOf('|')); } 
			else if (instruction.charAt(0) == 'c') {
				presentDirectory += "|" + instruction.substring(3); }
			else {
			}
//		} 
		
		
		
//		else { // List directory
//			exeListDirectory(instruction);
//		}
	}
	
//	public static void exeChangeDirectory(String instruction) {
//		if (instruction.equals("cd /")) {
//			presentDirectory = "/";
//		} else if (instruction.equals("cd ..")) {
//			presentDirectory = presentDirectory.substring(0, presentDirectory.lastIndexOf('\\'));
//		} else {
//			presentDirectory += "\\" + instruction.substring(3); 
//		}
//	}
	
//	public static void exeListDirectory(String instruction) {
//		if (instruction.charAt(0) == 'd') {
//			
//		} else {
//			
//		}
//	}
	
	public static void readOutput(String instruction) {
		if (instruction.charAt(0) != 'd') {
			String[] file = instruction.split(" ");
			filesystem.add(presentDirectory + "|" + file[1]+":"+file[0]);
		} else {
			directoryList.add(instruction.substring(4));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in;   
        in = new BufferedReader(new FileReader("C:\\Users\\matys\\WorkspaceApplication\\AdventOfCode\\Year2022\\src\\ressources\\InputDay7Of2022"));
        String line = in.readLine();
        directoryList.add("/");
        
        do {
        	if (line.charAt(0) == '$') {
        		readCommand(line.substring(2));
        	} else {
        		readOutput(line);
        	}
        	
        	line = in.readLine();
        } while (line != null);
        
        System.out.println("Sum of the total sizes of 100k directories and under: " + sumSizeDirectoryUnder100k());
        System.out.println(directoryList);
	}
	
	public static int sumSizeDirectoryUnder100k() {
		int totalSize = 0;
		String[] dirs;
		ArrayList<String> tempFilesystem = new ArrayList<String>();
		
		for (String file : filesystem) {
			System.out.println(file);
			if (Integer.parseInt(file.split(":")[1]) <= 100000) {
				tempFilesystem.add(file);
				
			} else {
				int idxLastBar = file.lastIndexOf('|');
				String sub = file.substring(0, idxLastBar);
				dirs = sub.split("\\Q|");
				
				for (int idxDir = 0; idxDir < dirs.length; idxDir++) {
					String dir = dirs[idxDir];
					String directories = directoryList.toString();
					if (directories.contains(dir)) {
						directoryList.remove(dirs[idxDir]);
					}
				}
			}
		}

		int[] directorieSize = new int[directoryList.size()];
		
		for (String file : tempFilesystem) {
			for (int idx = 0; idx < directoryList.size(); idx++) {
				if (file.contains(directoryList.get(idx) + "|") ) {
					directorieSize[idx] += Integer.parseInt(file.split(":")[1]);
				}
			}
		}
		
		for (int idx = 0; idx < directorieSize.length; idx++) {
			if (directorieSize[idx] > 100000) {
				directorieSize[idx] = 0;
			}
		} 
		
		for (int size : directorieSize) {
			totalSize += size;
		}
		
		return totalSize;
	}
	
	
}


