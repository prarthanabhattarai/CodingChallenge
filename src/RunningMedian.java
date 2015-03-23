import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
 * Running Median Class calculates the running median for number of words in a line from textfiles
 */

/**
 * @author Prarthana
 *
 */
public class RunningMedian {

	//linesArray stores each of the lines from text files as ArrayList elements
	private static ArrayList<String> linesArray;
	//wordsPerLine stores words per line for each of the lines as ArrayList elements
	private static ArrayList<Integer> wordsPerLine;
	//runningM stores runningMedian for lines as ArrayList elements
	private static ArrayList<Double> runningM;
	//all the files in the folder wc_input are taken as input files
	//if using an IDE such as Eclipse to run the program, put ("wc_input") instead of ("../wc_input")
	private static final File dir = new File("../wc_input"); 
	private static FileWriter out; 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		
		linesArray = new ArrayList<String>(); 
				
		//initialize file writer
		//if using an IDE such as Eclipse to run the program, put ("wc_output/med_result.txt") instead of ("../wc_output/med_result.txt")
		out = new FileWriter("../wc_output/med_result.txt"); 
		
		//To read input files in alphabetical order
		Arrays.sort(dir.listFiles());
		
		//For each file in the directory
		for (File file: dir.listFiles()) {
			
			//if it is a .txt file, then put each of its line into linesArray
			if (file.isFile() && file.getName().endsWith(".txt")) {
			
			Scanner fileScanner = new Scanner(file);

		    //while there is next line, add the line to linesArray
		    while (fileScanner.hasNextLine()){
		        linesArray.add(fileScanner.nextLine());
		    }	
		    	fileScanner.close();
			}	
			
		}
		//calculate running median from the lines in  linesArray
		calculateRunningMedian();	
	
		//write output in a text file
		createOutputFile();
		
		//close the output file
		out.close();
	}
	

	/**
	 * Private method to calcuate Running Median 
	 * @return void
	 */
	private static void calculateRunningMedian() {
		
		wordsPerLine = new ArrayList<Integer>();
		runningM = new ArrayList<Double>();
		
		//for each line (from linesArray)
		for (int i = 0; i< linesArray.size(); i++){
		int count = 0;
		
		//create string Tokenizer to count words
		StringTokenizer stringTokenizer = new StringTokenizer(linesArray.get(i));
	    
		//while there are more words
	    while (stringTokenizer.hasMoreElements()){  
	    	String currentWord = ((String) stringTokenizer.nextElement()).toLowerCase();
	    	
	    	//if it is non-empty word, increase the count
	    	if (!currentWord.isEmpty()) {   	
	    		count++;
	    	}
	    }
	    
	    //add 'count' as element of wordsPerLine 
	    wordsPerLine.add(count);
	    //find Median for elements upto i and add it to runningM 
	 	runningM.add(findMedian (i));
	    }
	   
	   	
	}
	
	/**
	 * Private method to find Median upto the line at 'index' in wordsPerLine
	 * @return void
	 */
	private static double findMedian(int index){

		//if it is the first line
		if (index==0){
			//median is the words per line in the first file
			return wordsPerLine.get(0);
		}
		
		//for subsequent lines
		//store the elements of wordsPerLine upto index in an array 
		int[] numWords = new int[index+1];
		double median= 0;
		
		for (int i = 0; i<wordsPerLine.size(); i++){ 
			numWords[i]= wordsPerLine.get(i);
		}
		
		//sort the array
		Arrays.sort(numWords);
		
		//calcualte median from elements in numWords
		int middle = ((numWords.length) / 2);
		
		//for even-numbered list, median is the mean if two elements at middle
		if(numWords.length % 2 == 0){
			double medianA = numWords[middle];
			double medianB = numWords[middle-1];
			median = (medianA + medianB) / 2;
		}
		
		//for odd-numbered list, median is the value of element in midpoint
		else{		
			median = numWords[middle];
		}
		
		return median;
	}
	
	/**
	 * Private method to write output as .txt file
	 * @return void
	 */
	private static void createOutputFile() throws IOException  {
		
		//set the format of output
		String formatStr = "%s%n";
		
		//for each of the lines, write its running median
		for (int i = 0; i<runningM.size();i++){
			out.write(String.format(formatStr, Double.toString(runningM.get(i))));
			}
		
	}
}	