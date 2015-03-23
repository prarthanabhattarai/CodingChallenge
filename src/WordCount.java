import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * WordCount Class reads text files and creates a text file with count for each of the words that appears in the files. 
 */

/**
 * @author Prarthana
 *
 */

public class WordCount {
	
	//linesArray stores each of the lines from text files as ArrayList elements
	private static ArrayList<String> linesArray;
	//wordsArray stores each of the words as ArrayList elements
	private static  ArrayList<String> wordsArray;
	//wordFrequency stores each of the words with their respective frequencies as map elements
	private static Map<String, Integer> wordFrequency; 
	//finalList stores the words to display in the output file
	private static  String[] finalList;
	//all the files in the folder wc_input are taken as input files
	//if using an IDE such as Eclipse to run the program, put ("wc_input") instead of ("../wc_input")
	private static final File dir = new File("../wc_input"); 
	private static FileWriter out; 
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		wordFrequency = new HashMap<String, Integer>();
		linesArray = new ArrayList<String>(); 
		
		//initialize file writer
		//if using an IDE such as Eclipse to run the program, put ("wc_output/wc_result.txt") instead of ("../wc_output/wc_result.txt")
		out = new FileWriter("../wc_output/wc_result.txt"); 
	
		//For each file in the directory, read the lines and create word count
		for (File file: dir.listFiles()) {
			
			//if it is a .txt file, then get the word count
			if (file.isFile() && file.getName().endsWith(".txt")) {
			
			Scanner fileScanner = new Scanner(file);

		    //while there is next line, add the line to linesArray
		    while (fileScanner.hasNextLine()){
		        linesArray.add(fileScanner.nextLine());
		    }	

		    fileScanner.close();
		}	
			
		}
		//once all the files are scanned, create the output file
		 createListOfWords();
		 createOutput();	
		out.close();	
	}

	/**
	 * Private method to create ArrayList of words 
	 * @return void
	 */
	private static void createListOfWords() {
		
		wordsArray = new ArrayList<String>();   
		
		//for each element in the LinesArray
		for (int i = 0; i< linesArray.size(); i++){
			//create a string Tokenizer to count words
			StringTokenizer stringTokenizer = new StringTokenizer(linesArray.get(i));
	    
			//while there are more words,
			while (stringTokenizer.hasMoreElements()) {
	    	String currentWord = ((String) stringTokenizer.nextElement()).toLowerCase();
	    	
	    	//remove hyphens, commas and fullstop
	    	currentWord= currentWord.replace("-","");
	    	currentWord = currentWord.replace(",","");
	    	currentWord= currentWord.replace(".","");
	    	
	    	//for words that are non-empty
	    	if (!currentWord.isEmpty()) {
	    		//if the word is contained in the map
                if (wordFrequency.containsKey(currentWord)) {
                    int count = wordFrequency.get(currentWord).intValue();
                    //increase its count
                    wordFrequency.put(currentWord, count + 1);
                // else, add it as new word in the map with count 1
                } else {
                	wordFrequency.put(currentWord, 1);
                }
            }
	    	
	    }
		}
	}
	
	/**
	 * Private method to create output text file
	 * @return void
	 */
	 private static void createOutput() throws IOException{	 
		 
	    //finalList is an Array that contains all the words in the map	
		finalList = new String[wordFrequency.size()];
	    int index = 0;
		 
	    //for each entry in the map
		 for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
	            String key = entry.getKey().toString();
	            Integer value = entry.getValue();
	            
	            //add the word as element of finalList at index
	            finalList[index]=key;
	            index++;
	        }
		 
		//sort finalList (to list words in alphabetical order) 
		Arrays.sort(finalList);
		
		//Set the output format
		String formatStr = "%-20s%s%n";
		
		//for each element of finalList,
		for (int i = 0; i<finalList.length; i++){
			
			//find corresponding entry in the map 
			for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()){

		        if(finalList[i].equals(entry.getKey().toString())){
		        	//get count of the word
		        	Integer	value = entry.getValue(); 
		            //write word and count in output file
		        	out.write(String.format(formatStr,finalList[i], value));
		        }		    	
		        
		    }
			
		}

	 }



}
