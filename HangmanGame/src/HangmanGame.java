import java.util.Scanner;

/**
 * Represents the Hangman game logic and user interface, where players attempt
 * to guess a hidden word.
 */
public class HangmanGame {

	private static final Scanner scanner = new Scanner(System.in);

	private boolean isGameWon; // Flag to track whether the game is won
	private ChosenWord currentWord; // The current word to be guessed
	private String playerInput; // User input for each round

	// Constructor
	public HangmanGame() {
		this.currentWord = new ChosenWord(WordRepository.getRandomWord());
		this.isGameWon = false;
		this.playerInput = "";
	}

	// Start the Hangman game
	public void start() {
		System.out.print("Welcome to the Hangman game!\n");
		System.out.print("Let the guessing begin!\n");
		System.out.print("Try to guess it in a minimun attempts number;)\n");
		System.out.print("Each step you type only 1 small english letter (a-z)\n");
		System.out.print("If you would like to exit, just type 'exit'\n");

		do {
			playGame();
			System.out.print("\nWould you like to play another round? (yes/no): ");
			playerInput = scanner.nextLine().toLowerCase();
		} while (playerInput.equals("yes"));

		System.out.println("Thanks for playing Hangman! Goodbye.");
		scanner.close();
	}

	/**
	 * Main game loop for Hangman. Continues until the game is won or exited by the
	 * player.
	 */
	private void playGame() {
		while (true) {
			// Check if the game is won
			if (isGameWon) {
				// Display a message of success and reset for a new round
				System.out.print("WOW! You successfully guessed the word: " + currentWord.getWord() + ". \nIt took you "
						+ currentWord.getNumberOfGuesses() + " guesses.\n");
				currentWord.initializeWord(WordRepository.getRandomWord());
				isGameWon = false;
				break;
			}

			// Retrieve the remaining unchosen letters
			String remainingLetters = currentWord.getUnusedLetters();

			// Display the current word status and remaining unchosen letters
			System.out.println("\nCurrent word status:" + currentWord.getWordStatus());
			System.out.println("Remaining unchosen letters: " + remainingLetters);

			// Prompt the player to enter a letter
			System.out.print("\nPlease enter a new valid letter(a-z): ");
			playerInput = scanner.nextLine();

			// Check if the player wants to exit the game
			if (playerInput.equals("exit")) {
				System.out.println("Hope to see you soon again!");
				System.exit(0);
			}

			// Validate the entered letter
			if (playerInput.length() != 1 || !Character.isLowerCase(playerInput.charAt(0))
					|| remainingLetters.indexOf(playerInput) == -1) {
				// Display an error message for invalid input and continue the loop
				System.out.println("Invalid! It's either a letter you already had tried, or invalid input."
						+ "\nPlease choose a single letter (a-z)!");
				continue;
			}

			// Make a guess based on the entered letter
			isGameWon = currentWord.makeGuess(playerInput.charAt(0));
		}
	}

}