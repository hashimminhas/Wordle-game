package game;

public class FeedbackProvider {


    private static final String RESET = "\u001B[0m";                   // ANSI color codes for console output
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String WHITE = "\u001B[37m";


    public String generateFeedback(String guess, String secretWord) {  // Generate feedback string with color coding
        StringBuilder feedback = new StringBuilder();

        boolean[] secretUsed = new boolean[5];                         // Track which letters in secret word have been matched


        for (int i = 0; i < 5; i++) {                                  // First pass: Mark exact matches (GREEN)
            if (guess.charAt(i) == secretWord.charAt(i)) {
                secretUsed[i] = true;
            }
        }


        for (int i = 0; i < 5; i++) {                                 // Second pass: Generate feedback for each letter
            char guessChar = guess.charAt(i);


            if (guessChar == secretWord.charAt(i)) {                               // Exact match - GREEN
                feedback.append(GREEN).append(guessChar).append(RESET);
            }

            else if (containsUnusedLetter(secretWord, guessChar, secretUsed)) {    // Letter exists elsewhere - YELLOW
                feedback.append(YELLOW).append(guessChar).append(RESET);

                markLetterAsUsed(secretWord, guessChar, secretUsed);               // Mark this letter as used in secret word
            }

            else {                                                                 // Letter not in word - WHITE
                feedback.append(WHITE).append(guessChar).append(RESET);
            }
        }

        return feedback.toString();
    }



    private boolean containsUnusedLetter(String secretWord, char letter, boolean[] used) {        //Check if a letter exists in the secret word and hasn't been matched yet
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter && !used[i]) {
                return true;
            }
        }
        return false;
    }

    private void markLetterAsUsed(String secretWord, char letter, boolean[] used) {               //Mark the first occurrence of a letter as used
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter && !used[i]) {
                used[i] = true;
                return;
            }
        }
    }
}