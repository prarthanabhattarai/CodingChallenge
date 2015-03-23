# CodingChallenge

This is my submission for Insight Data Engineering Coding Challenge.
The source file contains two programs, written in Java, one for word count and another for running median. Both of this programs use .txt files as input files and create .txt files as output.

# Word Count

The program WordCount.java calculates the word count for each of the words that appear in the text files located in 'wc_input' folder. It prints out the result in alphabetical oder, with each word placed next to its frequnecy. It creates the output file 'wc_result.txt'.

# Running Median

The program RunningMedian.java calculates the running median for each line of text in the text files located in 'wc_input' folder. The text files are read in alphabetical order. Hence the files are read in the order 'ATestFile.txt','BTestFile.txt' and 'Dictionary.txt'. It creates the output file 'med_result.txt' where each line represents the running median for lines upto that point. 

#Script File

To run the programs use the shell script file. Firstofall, download the folder. Using terminal, open the downloaded folder. Once you are inside the folder 'CodingChallenge' in terminal, run the shell script file by typing in the command 'sh run.sh'. The output files will be stored in 'wc_output' folder. After running the scrpit file, check to make sure that the 'wc_output' folder now contains two text files, 'wc_result.txt' and 'med_result.txt'.
