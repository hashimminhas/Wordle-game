import game.FeedbackProvider;
import game.LetterTracker;
import game.WordValidator;
import io.WordReader;
import java.util.Scanner;

public class WordleGame{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);                                                // Initialize scanner for user input
        WordleGame game = new WordleGame();                                                      // Main method to start the game

        game.run(scanner, args);
        scanner.close();

    }
    public void run( Scanner scanner, String[] args){

        if(args.length != 1){
            System.out.println("Usage: java WordleGame <word_index>");
            return;
        }
        int wordIndex;
        try {
            wordIndex = Integer.parseInt(args[0]);                                              // Parse word index from command line argument
            if (wordIndex < 0) {
                System.out.println("Error: Word index must be a positive integer.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Word index must be a number.");
            return;
        }

        WordReader wordReader = new WordReader("wordle-words.txt");                           // Create WordReader instance
        String secretWord = wordReader.getWordByIndex(wordIndex);                             // Get secret word by
        if (secretWord == null) {                                                             // Handle errors gracefully
            System.out.println("Error: Could not load word. Please check the word file.");
            return;
        }

        FeedbackProvider feedbackProvider = new FeedbackProvider();
        WordValidator validator = new WordValidator(wordReader);
        LetterTracker letterTracker = new LetterTracker();

        System.out.println("Enter your name: ");                                          // Prompt for username
        String username = "";
        if(scanner.hasNextLine()){
            username = scanner.nextLine().trim();
        }else{
            return;
        }

        System.out.println("Guess the word! You have 6 attempts.");                         // Game instructions
        letterTracker.displayRemainingLetters();

        int maxAttempts = 6;
        int attemptsCount = 0;
        boolean hasWon = false;

        while(attemptsCount < maxAttempts && !hasWon){
            attemptsCount++;

            System.out.print("\nEnter your guess: ");

            if(!scanner.hasNextLine()){
                break;
            }

            String guess = scanner.nextLine().trim().toUpperCase();


            if (!validator.isValidGuess(guess)) {                                            // System.out.println("Your guess: " + guess); // Display the guess
                System.out.println("Invalid guess! Please enter exactly 5 letters.");
                attemptsCount--;                                                              // Don't count invalid attempts
                continue;
            }


            String feedback = feedbackProvider.generateFeedback(guess, secretWord);          // Generate and display feedback
            System.out.println(feedback);


            letterTracker.markWordUsed(guess);                                                // Update letter tracker

            if (guess.equals(secretWord)) {                                                   // Check if correct
                hasWon = true;
                System.out.println("You won in " + attemptsCount + " attempts!");
            } else {
                System.out.println("Attempts remaining: " + (maxAttempts - attemptsCount));
                letterTracker.displayRemainingLetters();
            }
        }

        if(!hasWon){
            System.out.print("\nGame over, The word was: " + secretWord + "\n" );
        }

    }
}
