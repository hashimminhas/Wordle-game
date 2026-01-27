import java.util.Scanner;

public class WordleGame{

    public static void main(String[] args) {


        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Main method to start the game
        WordleGame game = new WordleGame();

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
            System.out.println("Invalid word index. Please provide a valid integer.");
            return;
        }

        System.out.println("Using word index: " + wordIndex );
    }
}