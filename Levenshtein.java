import java.util.*;
public class Levenshtein {

	private String word1 = "";
	private String word2 = "";
	private int length1;
	private int length2;
	private int levDist;
	private int[][] matrix;
	
	public static void main(String[] args){

		Levenshtein test =  new Levenshtein();

		test.runLevenshtein();

	}
	
	/**
	 * Constructor for Levenshtein algorithm
	 * @param word1
	 * @param word2
	 */
	public Levenshtein(String word1, String word2){
		this.word1 = word1.toLowerCase();
		this.word2 = word2.toLowerCase();
		this.length1 = word1.length()+1;
		this.length2 = word2.length()+1;
		makeLevenshtein();
	}
	
	/**
	 * Constructor for Levenshtein algorithm
	 */
	public Levenshtein(){
		this.word1 = "";
		this.word2 = "";
		this.length1 = word1.length()+1;
		this.length2 = word2.length()+1;
	}
	
	/**
	 * Prints out matrix
	 */
	public void printMatrix(){
		
		makeLevenshtein();
		
		System.out.print("    ");
        for (int i = 1; i < length2; i++) {
            System.out.print(word2.charAt(i-1) + " ");
        }
        System.out.println();
        for (int row = 0; row < length1; row++) {
            if (row > 0) {
                System.out.print(word1.charAt(row - 1) + " ");
            } else {
                System.out.print("  ");
            }
            for (int col = 0; col < length2; col++) {
                
            	System.out.print(matrix[row][col] + " ");
            	
                if (row == length1-1 && col == length2-1){
                	System.out.print("<--");
                }
                
            }
            System.out.println();
        }
        System.out.println();
	}
	
	/**
	 * Creates the matrix using the Levenshtein algorithm
	 */
	public void makeLevenshtein(){
		
		matrix = new int[length1][length2];
		
		// Sets up first row and column
		for (int row = 0; row < length1; row++){
			matrix[row][0] = row;
		}
		for (int col = 0; col < length2; col++){
			matrix[0][col] = col;
		}
		
		// Fills in rest of matrix
		for (int row = 1; row <= (length1-1); row++){
			for (int col = 1; col <= (length2-1); col++){	
				int differenceCost = 0;
				if (isDifferent(row,col)){
					differenceCost = 1;
				}					
				matrix[row][col] = Math.min(Math.min(matrix[row-1][col-1]+differenceCost, matrix[row][col-1]+1) , 
						matrix[row-1][col]+1);
			}
		}
		levDist = matrix[length1-1][length2-1];
		
	}
	
	/**
	 * Compares two chars to see if they are equal
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean isDifferent(int i, int j){
		boolean result = true;
		i -= 1;
		j -= 1;
		char c1 = word1.charAt(i);
		char c2 = word2.charAt(j);
		if (c1 == c2){
			result = false;
		}
		return result;	
	}
	
	/**
	 * Runs an interactive program
	 */
	public void runLevenshtein(){
		Scanner in = new Scanner(System.in);
		System.out.println("Hello, I am the ghost of Vladimir Iosifovich Levenshtein." +
				"\nI am here to calculate how different two words are for you.");
		System.out.println("Please give me the first word...");
		String w1 = in.next();
		System.out.println("\nExcellent. Now please give me a second word...\n");
		String w2 = in.next();
		setWords(w1, w2);
		makeLevenshtein();
		System.out.print(w1 + " and " + w2 + " have a distance of: " + levDist + ".\n\n");
		System.out.println("Would you like to see a matrix to illustrate this?\n" +
				"If so, type \"yes\" and if not, type \"no\"\n");
		String answer = in.next();
		if (answer.equalsIgnoreCase("no")){
			System.out.println("Ok, no problem.");
		}
		else{
			System.out.println("Ok, here you are:");
			printMatrix();
			System.out.println();
		}
		System.out.println("Would you like to do this again?");
		String answer2 = in.next();
		if (answer2.equalsIgnoreCase("no")){
			System.out.println("Ok, have a good day!");
			System.exit(0);
		}
		else{
			runLevenshtein();
		}
	}
	
	/**
	 * calculates the levenshtein distance
	 * 
	 * @return int levenshtein distance
	 */
	public int getLevenshteinDist(){

		return levDist;
		
	}
	
	/**
	 * Sets word1
	 * 
	 * @param word1
	 */
	public void setWord1(String word1){
		this.word1 = word1.toLowerCase();
		this.length1 = word1.length()+1;
	}
	
	/**
	 * Sets word2
	 * 
	 * @param word2
	 */
	public void setWord2(String word2){
		this.word1 = word2.toLowerCase();
		this.length2 = word2.length()+1;
	}
	
	/**
	 * Sets both words
	 * @param word1
	 * @param word2
	 */
	public void setWords(String word1, String word2){
		this.word1 = word1.toLowerCase();
		this.word2 = word2.toLowerCase();
		this.length1 = word1.length()+1;
		this.length2 = word2.length()+1;
	}
	
	/**
	 * Returns first word that was entered
	 * 
	 * @return String word1
	 */
	public String getWord1(){
		return word1;
	}
	
	/**
	 * Returns second word that was entered
	 * 
	 * @return String word2
	 */
	public String getWord2(){
		return word2;
	}
	
	/**
	 * Returns the length of word 1
	 * 
	 * @return int length1
	 */
	public int getLength1(){
		return length1;
	}
	
	/**
	 * Returns the length of word 2
	 * 
	 * @return int length2
	 */
	public int getLength2(){
		return length2;
	}
}

