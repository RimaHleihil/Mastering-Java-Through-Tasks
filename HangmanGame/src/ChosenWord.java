import java.util.Random;

/**
 * Represents the word to be guessed in the game. Tracks guessed characters,
 * number of guesses, and provides interaction methods.
 */
public class ChosenWord {

    private static final int TOTAL_LETTERS = 26;
    
    private String secretWord; // The word to be guessed
    private boolean[] correctlyGuessedLetters; // Flags for correctly guessed letters
    private boolean[] attemptedLetters; // Flags for all attempted letters
    private String unguessedChars; // String of unguessed characters for user reference

    /**
     * Constructor to initialize a new word.
     *
     * @param secretWord The word to be guessed
     */
    public ChosenWord(String secretWord) {
        initializeWord(secretWord);
    }

    /**
     * Initialize the word and related attributes.
     *
     * @param secretWord The word to be guessed
     */
    public void initializeWord(String secretWord) {
        this.secretWord = secretWord;
        this.correctlyGuessedLetters = new boolean[secretWord.length()];
        this.attemptedLetters = new boolean[TOTAL_LETTERS];
        this.unguessedChars = generateUnguessedChars();
    }

    /**
     * Handles a character guess and updates the game state.
     *
     * @param letter The guessed character
     * @return True if the guessed letter is correct and the entire word is guessed, false otherwise
     */
    /**
     * Handles a character guess and updates the game state.
     *
     * @param letter The guessed character
     * @return True if the guessed letter is correct and the entire word is guessed, false otherwise
     */
    public boolean makeGuess(char letter) {
        // Convert the guessed letter to an index ('a' has index 0, 'b' has index 1, and so on)
        int letterIndex = (int) (letter - 'a');

        // Check if the letter has already been attempted
        if (attemptedLetters[letterIndex]) {
            return false; // Letter already attempted
        }

        // Mark the letter as attempted
        attemptedLetters[letterIndex] = true;

        // Flags to track if the guessed letter is correct and if all letters are guessed
        boolean letterGuessed = false;
        boolean allLettersGuessed = true;

        // Iterate through each letter in the secret word
        for (int i = 0; i < secretWord.length(); i++) {
            // Check if the guessed letter matches and has not been guessed before
            if (secretWord.charAt(i) == letter && !correctlyGuessedLetters[i]) {
                // Mark the letter as correctly guessed
                correctlyGuessedLetters[i] = true;
                letterGuessed = true;
            }

            // Check if all letters are guessed
            if (!correctlyGuessedLetters[i]) {
                allLettersGuessed = false;
            }
        }

        // Update the string of unguessed characters after a correct guess
        if (letterGuessed) {
            updateUnguessedChars(letter);
        }

        // Return true if the guessed letter is correct and all letters are guessed
        return letterGuessed && allLettersGuessed;
    }


    /**
     * Returns the word.
     *
     * @return The secret word
     */
    public String getWord() {
        return secretWord;
    }

    /**
     * Returns the number of guesses.
     *
     * @return The number of guesses
     */
    public int getNumberOfGuesses() {
        // Calculate the number of guesses based on the length of unguessedChars
        return TOTAL_LETTERS - unguessedChars.length();
    }

    /**
     * Returns the word with underscores for unrevealed characters.
     *
     * @return The word with underscores
     */
    public String getWordStatus() {
        StringBuilder wordStatus = new StringBuilder();
        for (int i = 0; i < correctlyGuessedLetters.length; i++) {
            if (correctlyGuessedLetters[i]) {
                wordStatus.append(secretWord.charAt(i));
            } else {
                wordStatus.append("_");
            }

            if (i < correctlyGuessedLetters.length - 1) {
                wordStatus.append(" ");
            }
        }
        return wordStatus.toString();
    }

    /**
     * Returns a string of available characters.
     *
     * @return A string of available characters
     */
    public String getUnusedLetters() {
        return unguessedChars;
    }

    /**
     * Helper method to generate a string of unguessed characters.
     *
     * @return A string containing all available characters
     */
    private String generateUnguessedChars() {
        StringBuilder chars = new StringBuilder();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            chars.append(ch);
        }
        return chars.toString();
    }

    /**
     * Helper method to update unguessedChars after a correct guess.
     *
     * @param guessedLetter The correctly guessed letter
     */
    private void updateUnguessedChars(char guessedLetter) {
        unguessedChars = unguessedChars.replace(String.valueOf(guessedLetter), "");
    }
}
