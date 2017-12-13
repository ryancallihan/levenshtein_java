
public class LevTest {
	public static void main(String[] args){
		Levenshtein test = new Levenshtein("poop", "paoppooppoop");
		//test.runLevenshtein();
		test.getLevenshteinDist();
		test.showMatrix();
	}
}
