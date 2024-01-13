/**
 * Main application class containing the main method to start the Hangman game.
 */
public class MainApp {

	/**
	 * Main method to start the Hangman game.
	 *
	 * @param args Command-line arguments (not used in this application).
	 */
	public static void main(String[] args) {
		// Create a new instance of HangmanGame
		HangmanGame game = new HangmanGame();

		// Start the Hangman game by calling the start method
		game.start();
	}
}
