import game.FeedbackProvider;
import game.LetterTracker;
import game.WordValidator;
import io.StatsManager;
import io.WordReader;
import java.util.Scanner;
import model.GameStats;

public class WordleGame{

    public static void main(String[] args) {
        
    Scanner scanner = new Scanner(System.in);
    WordleGame game = new WordleGame();
    
    game.run(scanner, args);
    scanner.close();

}
     public void run( Scanner scanner, String[] args){

        if(args.length != 1){
            System.out.println("Please provide a number as command line argument");
            return;
        }
        int wordIndex;
        try {
            wordIndex = Integer.parseInt(args[0]);
            if (wordIndex < 0) {
                System.out.println("Press Enter to exit...");
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid command-line argument. Please launch with a valid number.");
            return;
        }

        WordReader wordReader = new WordReader("wordle-words.txt");
        String secretWord = wordReader.getWordByIndex(wordIndex);
        if (secretWord == null) {
            System.out.println("Error: Could not load word. Please check the word file.");
            return; 
        }
    
        FeedbackProvider feedbackProvider = new FeedbackProvider();
        WordValidator validator = new WordValidator(wordReader);
        LetterTracker letterTracker = new LetterTracker();

        System.out.print("Enter your username: ");
        String username = "";
        if(scanner.hasNextLine()){
            username = scanner.nextLine().trim();
        }else{
            return;
        }

        System.out.println("Welcome to Wordle! Guess the 5-letter word.");

        int maxAttempts = 6;
        int attemptsCount = 0;
        boolean hasWon = false;
        boolean inputEnded = false;

        while(attemptsCount < maxAttempts && !hasWon){
             attemptsCount++;

            System.out.print("Enter your guess: ");

            if(!scanner.hasNextLine()){
                  System.out.print(" ");
                  inputEnded = true;
                  break;
            }

            String guess = scanner.nextLine().trim().toLowerCase();

            // Check if guess is exactly 5 characters
            if (guess.length() != 5) {
               System.out.println(" Your guess must be exactly 5 letters long.");
               attemptsCount--;
               continue;
            }

            // Check if all characters are lowercase letters
            boolean allLetters = true;
            for (char c : guess.toCharArray()) {
                if (!Character.isLetter(c) || !Character.isLowerCase(c)) {
                    allLetters = false;
                    break;
                }
            }
            if (!allLetters) {
               System.out.println(" Your guess must only contain lowercase letters.");
               attemptsCount--;
               continue;
            }

            String guessUpper = guess.toUpperCase();

            // Check if word is in the word list
            if (!validator.isRealWord(guessUpper)) {
               System.out.println(" Word not in list. Please enter a valid word.");
               attemptsCount--;
               continue;
            }

            // Check if correct BEFORE showing feedback
            if (guessUpper.equals(secretWord)) {
                 hasWon = true;
                 System.out.println(" Congratulations! You've guessed the word correctly.");
            } else {
                String feedback = feedbackProvider.generateFeedback(guessUpper, secretWord);
                System.out.println(" Feedback: " + feedback);
            
                letterTracker.updateLetters(guessUpper, secretWord);
                letterTracker.displayRemainingLetters();
                System.out.println("Attempts remaining: " + (maxAttempts - attemptsCount));
            }
        }

            // If input ended prematurely, just exit without game over message
            if (inputEnded) {
                return;
            }

            if(!hasWon){
                System.out.println("Game over. The correct word was: " + secretWord.toLowerCase());
            }

            StatsManager statsManager = new StatsManager("stats.csv");
            String result = hasWon ? "win" : "loss";
            GameStats gameStats = new GameStats(username, secretWord, attemptsCount, result);
            statsManager.saveGameStats(gameStats);
    
            System.out.print("Do you want to see your stats? (yes/no): ");
    
            if (scanner.hasNextLine()) {
                String response = scanner.nextLine().trim().toLowerCase();
        
                if (response.equals("yes") || response.equals("y")) {
                    statsManager.displayUserStats(username);
                }
            }
        
    }
}
