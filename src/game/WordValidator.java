package game;

import io.WordReader;
import java.util.List;

public class WordValidator {
    
    private WordReader wordReader;
    
    public WordValidator(WordReader wordReader) {
        this.wordReader = wordReader;
    }
    

    public boolean isValidGuess(String guess) {           // Check if guess is 5 letters and alphabetic
        
        if (guess.length() != 5) {                        // Check if exactly 5 letters
            return false;
        }
        
        
        for (char c : guess.toCharArray()) {              // Check if all characters are letters
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        
        return true;
    }
    
    
    public boolean isRealWord(String guess) {             // Check if guess exists in word list
        List<String> words = wordReader.readWords();
        
        if (words == null) {
            return true;                                  // If we can't read words, accept any valid format
        }
        
        return words.contains(guess.toUpperCase());       // Ensure case-insensitive comparison
    }
}