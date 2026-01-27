import io.WordReader;
import java.util.List;
import java.util.Scanner;
public class WordleGame{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);  // Initialize scanner for user input

        WordleGame game = new WordleGame();    // Main method to start the game

        game.showAvailableWords(); // Show all available words for testing
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
            wordIndex = Integer.parseInt(args[0]); // Parse word index from command line argument

            if (wordIndex < 0) {
                System.out.println("Word index must be a postive integer.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Word index must be a number.");
            return;

        }

        WordReader wordReader = new WordReader("wordle-words.txt"); // Create WordReader instance
        String secretWord = wordReader.getWordByIndex(wordIndex); // Get secret word by


        if (secretWord == null) {  // Handle errors gracefully
            System.out.println("Error: Could not load word. Please check the word file.");
            return; // Exit gracefully
        }

        System.out.println("Word loaded successfully!"); // For testing, remove later


        System.out.println("Enter your username: "); // Prompt for username
        String username = "";
        if(scanner.hasNextLine()){
            username = scanner.nextLine().trim();
        }else{
            return;
        }

        System.out.println("\nGuess the word! You have 6 attempts."); // Game instructions

        int maxAttempts = 6;
        int attemptsCount = 0;
        boolean hasWon = false;

        while(attemptsCount < maxAttempts && !hasWon){
            attemptsCount++;

            System.out.print("Enter your guess: "); // Prompt for guess


            if(!scanner.hasNextLine()){
                break;
            }
            String guess = scanner.nextLine().trim().toUpperCase();

            System.out.println("Your guess: " + guess); // Display the guess


            if (guess.equals(secretWord)) {  // Check if correct
                hasWon = true;
                System.out.println("You won in " + attemptsCount + " attempts!");
            } else {
                System.out.println("Attempts remaining: " + (maxAttempts - attemptsCount));
            }

            if(!hasWon){
                System.out.print("\nGame over, The word was: " + secretWord + "\n" );
            }
        }
    }

    public void showAvailableWords() {    // Temporary helper to see all available words
        WordReader reader = new WordReader("wordle-words.txt");
        List<String> words = reader.readWords();

        if (words == null) {
            System.out.println("No words found!");
            return;
        }

        System.out.println("Available words (" + words.size() + " total):");
        for (int i = 0; i < words.size(); i++) {
            System.out.println(i + ": " + words.get(i));
        }
    }


}
