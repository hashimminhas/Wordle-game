package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordReader {

    private String filename;

    public WordReader(String filename){  // Constructor to initialize filename
        this.filename = filename;
    }

    public List<String> readWords(){   // Method to read words from the file
        List<String> words = new ArrayList<>();  // List to store words read from file

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){  // Try-with-resources to ensure file is closed
            String line;
            while((line = reader.readLine()) !=null){  // Read each line

                String word = line.trim().toUpperCase();  // Trim whitespace and convert to uppercase

                if(word.length() == 5){
                    words.add(word);
                }
            }
            return words;
        }
        catch(IOException e){
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    public String getWordByIndex(int index){  // Method to get word by index
        List<String> words = readWords();     // Read all words

        if(words == null || words.isEmpty()){
            return null;
        }

        if(index < 0 || index >= words.size()){  // Check for valid index
            System.err.println("Error: Word index " + index + " is out of range.");
            System.err.println("Valid range: 0 to " + (words.size() - 1));
            return null;
        }
        return words.get(index);     // Return word at specified index
    }

    public int getWordCount(){   // Method to get total number of words
        List<String> words = readWords();
        return words != null ? words.size() : 0;   // Return size or 0 if null
    }
}