# Wordle Game - Phase 2

## Overview
Phase 2 builds upon the foundation established in Phase 1 by implementing file I/O operations, word loading functionality, and basic game loop mechanics. This phase transforms the project from a simple argument validator into a functional word-guessing game.

## Phase 2: File I/O and Game Loop Implementation

### What We Implemented

1. **WordReader Class (io/WordReader.java)**
    - Custom file reader for loading Wordle words from an external text file
    - Word validation and filtering (ensures 5-letter words only)
    - Index-based word retrieval system
    - Robust error handling for file operations

2. **Game Loop Mechanics**
    - Username input collection
    - 6-attempt game loop structure
    - User guess input handling
    - Win/loss condition checking
    - Attempt tracking and remaining attempts display

3. **Word List Management**
    - Created `wordle-words.txt` containing valid 5-letter words
    - Implemented index-based word selection for reproducible games
    - Added helper method to display all available words for testing

### Techniques Used

#### 1. **File I/O with Try-with-Resources**
```java
try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
    // Automatic resource management
}
```
- **BufferedReader**: Efficient file reading line-by-line
- **Try-with-resources**: Automatic file closure, prevents resource leaks
- **Exception Handling**: IOException handling with descriptive error messages

#### 2. **Data Validation**
- **Word Length Validation**: Filters words to ensure only 5-letter words are stored
- **Index Range Validation**: Checks if requested index is within valid range
- **Null Safety**: Comprehensive null checks throughout the code

#### 3. **Package Organization**
- Created `io` package for I/O operations
- Separation of concerns: file handling isolated from game logic
- Improved maintainability and code organization

#### 4. **Game State Management**
- Boolean flag (`hasWon`) to track victory condition
- Counter (`attemptsCount`) for tracking player attempts
- Loop control for 6-attempt limit

#### 5. **String Manipulation**
- `.trim()`: Removes leading/trailing whitespace
- `.toUpperCase()`: Normalizes input for case-insensitive comparison
- `.equals()`: String comparison for win condition

### Key Features

| Feature | Description | Implementation |
|---------|-------------|----------------|
| **File Reading** | Loads words from external file | WordReader class with BufferedReader |
| **Word Filtering** | Only accepts 5-letter words | Length validation in readWords() |
| **Index Selection** | Select word by index from list | getWordByIndex() method |
| **Game Loop** | 6 attempts to guess the word | While loop with attempt counter |
| **Win Detection** | Checks if guess matches secret word | String equality comparison |
| **Username Input** | Personalized game experience | Scanner.nextLine() with validation |
| **Error Handling** | Graceful handling of file/input errors | Try-catch blocks and null checks |

## Project Structure
```
KoodWordle/
├── WordleGame.java           # Main game controller
├── io/
│   └── WordReader.java       # File I/O handler
├── wordle-words.txt          # Word list (5-letter words)
├── game/                     # (Reserved for future game logic)
├── modle/                    # (Reserved for future data models)
└── README.md                 # Phase 1 documentation
```

## How to Run and Test

### Compilation
```bash
# Compile the WordReader class first
javac io/WordReader.java

# Compile the main game file
javac WordleGame.java
```

### Test Cases

#### **Test 1: Valid Word Index**
```bash
java WordleGame 0
```
**Expected behavior:**
- Loads word at index 0 (CRANE)
- Prompts for username
- Starts game loop with 6 attempts
- Accepts guesses and compares to secret word

**Sample interaction:**
```
Available words (20 total):
0: CRANE
1: SLATE
...
Word loaded successfully!
Enter your username: 
Alice
Guess the word! You have 6 attempts.
Enter your guess: CRANE
Your guess: CRANE
You won in 1 attempts!
```

#### **Test 2: Multiple Attempts**
```bash
java WordleGame 5
```
**Expected behavior:**
- Loads word at index 5 (SHORE)
- Allows up to 6 guesses
- Shows remaining attempts after each guess
- Declares game over if all attempts used

**Sample interaction:**
```
Word loaded successfully!
Enter your username: 
Bob
Guess the word! You have 6 attempts.
Enter your guess: CRANE
Your guess: CRANE
Attempts remaining: 5
Enter your guess: SHORE
Your guess: SHORE
You won in 2 attempts!
```

#### **Test 3: Index Out of Range**
```bash
java WordleGame 999
```
**Expected output:**
```
Error: Word index 999 is out of range.
Valid range: 0 to 19
Error: Could not load word. Please check the word file.
```

#### **Test 4: Missing Word File**
Rename or delete `wordle-words.txt` and run:
```bash
java WordleGame 0
```
**Expected output:**
```
Error reading file: wordle-words.txt (The system cannot find the file specified)
Error: Could not load word. Please check the word file.
```

#### **Test 5: View Available Words**
The game shows all available words at startup for testing purposes:
```
Available words (20 total):
0: CRANE
1: SLATE
2: AUDIO
...
```

### Verification Steps

1. **Verify Compilation:**
   ```bash
   javac io/WordReader.java WordleGame.java
   ```
   Should complete without errors.

2. **Verify Word File:**
   Check that `wordle-words.txt` contains 5-letter words (one per line).

3. **Test Word Loading:**
   ```bash
   java WordleGame 0
   ```
   Should display "Word loaded successfully!"

4. **Test Game Flow:**
    - Enter a username when prompted
    - Make guesses (5-letter words)
    - Verify attempt counter decrements
    - Test winning by entering the correct word

5. **Test Edge Cases:**
    - Invalid index (negative, too large)
    - Missing word file
    - Empty input

## Technical Highlights

### WordReader Class Design
```java
public class WordReader {
    private String filename;
    
    // Constructor
    public WordReader(String filename)
    
    // Read all words from file
    public List<String> readWords()
    
    // Get specific word by index
    public String getWordByIndex(int index)
    
    // Get total word count
    public int getWordCount()
}
```

### Error Handling Strategy
1. **File Not Found**: Returns null, game displays error message
2. **Invalid Index**: Prints error with valid range, returns null
3. **Empty Input**: hasNextLine() check prevents crashes
4. **Invalid Word Length**: Filtered during file reading

## Improvements from Phase 1

| Aspect | Phase 1 | Phase 2 |
|--------|---------|---------|
| **Functionality** | Argument validation only | Full game loop |
| **File I/O** | None | WordReader class with file handling |
| **User Interaction** | None | Username + guess input |
| **Game Logic** | None | 6 attempts, win/loss detection |
| **Code Organization** | Single file | Package structure (io/) |
| **Error Messages** | Enhanced error message for invalid word index | File I/O errors, index range errors |

## Known Limitations (To Be Addressed in Phase 3)

1. **No Color Feedback**: Game doesn't show which letters are correct/misplaced (green/yellow/gray)
2. **Basic Validation**: Doesn't validate guess length or format yet
3. **Game Over Logic Issue**: Game over message appears after each incorrect guess (needs fix)
4. **No Guess History**: Previous guesses are not tracked or displayed
5. **No Statistics**: No win/loss tracking across multiple games

## Next Steps (Phase 3 Preview)

- Implement color-coded feedback system (correct position, wrong position, not in word)
- Add guess validation (must be 5 letters, must be a valid word)
- Fix game over logic to only display after final attempt
- Create model classes for game state
- Add visual guess history display
- Implement proper endgame summary

## Dependencies

- **Java Standard Library**
    - `java.io.BufferedReader`
    - `java.io.FileReader`
    - `java.io.IOException`
    - `java.util.ArrayList`
    - `java.util.List`
    - `java.util.Scanner`

## Author
Hashim Ali

## Date
January 2026

---

**Phase 1 → Phase 2 Transition**: We moved from basic argument validation to a functional word-guessing game with file I/O, demonstrating proper separation of concerns and robust error handling.
