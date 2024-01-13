import java.util.Random;

/**
 * Repository of words for the guessing game. Delivers a single random word from
 * the predefined array.
 */
public class WordRepository {

	// Predefined array of words
	private static final String[] wordBank = { "programming", "java", "class", "developer", "computer", "algorithm",
			"software", "hangman", "repository", "keyboard", "interface", "arraylist", "encryption" };

	// Private constructor to prevent instantiation
	private WordRepository() {
	}

	// Returns a random word from the predefined array
	public static String getRandomWord() {
		Random rnd = new Random();
		return wordBank[rnd.nextInt(wordBank.length)];
	}
}