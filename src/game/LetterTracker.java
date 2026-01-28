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