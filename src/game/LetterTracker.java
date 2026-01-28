package game;

import java.util.HashSet;
import java.util.Set;

public class LetterTracker {
    
    private Set<Character> usedLetters;
    
    public LetterTracker() {
        this.usedLetters = new HashSet<>();
    }
    
   
    public void markLetterUsed(char letter) {                      //Mark a single letter as used
        usedLetters.add(Character.toUpperCase(letter));
    }
    
    public void markWordUsed(String word) {                        //Mark all letters in a word as used
        for (char c : word.toCharArray()) {
            markLetterUsed(c);
        }
    }
    
    // Update remaining letters - only remove letters that are NOT in the secret word
    public void updateLetters(String guess, String secretWord) {
        for (char c : guess.toCharArray()) {
            char upperC = Character.toUpperCase(c);
            // Only mark as used if the letter is NOT in the secret word
            if (secretWord.indexOf(upperC) == -1) {
                usedLetters.add(upperC);
            }
        }
    }
    
    public String getRemainingLetters() {                          //Get a string of all letters that have not been used yet
        StringBuilder remaining = new StringBuilder();
        
        for (char c = 'A'; c <= 'Z'; c++) {
            if (!usedLetters.contains(c)) {
                remaining.append(c).append(" ");
            }
        }
        
        return remaining.toString().trim();
    }

    public void displayRemainingLetters() {                         //Display remaining letters to console
        System.out.println("Remaining letters: " + getRemainingLetters());
    }
}