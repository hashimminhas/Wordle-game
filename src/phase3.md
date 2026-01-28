# Wordle Game - Phase 3

## Overview
Phase 3 represents a major milestone, transforming the basic Wordle game into a fully functional, feature-rich application with visual feedback, input validation, letter tracking, and complete game mechanics. This phase implements the core gameplay experience that makes Wordle engaging and challenging.

## Phase 3: Core Game Mechanics and Visual Feedback

### What We Implemented

#### 1. **FeedbackProvider Class (game/FeedbackProvider.java)**
The heart of the Wordle experience - provides color-coded visual feedback for each guess.

**Features:**
- **Green letters**: Correct letter in correct position
- **Yellow letters**: Correct letter in wrong position
- **White letters**: Letter not in the word
- **Smart duplicate handling**: Properly handles repeated letters in guesses
- **ANSI color codes**: Terminal-based color output for visual clarity

**Algorithm:**
- Two-pass approach for accurate feedback
- First pass: Mark exact matches (green)
- Second pass: Check for letters in wrong positions (yellow)
- Prevents over-marking duplicate letters

#### 2. **LetterTracker Class (game/LetterTracker.java)**
Tracks which letters have been used across all guesses, helping players strategize.

**Features:**
- Maintains a set of all letters used in guesses
- Displays remaining unused letters after each guess
- Automatic uppercase conversion for consistency
- Real-time alphabet tracking (A-Z)

**Benefits:**
- Players can see which letters haven't been tried yet
- Reduces accidental repetition of letters
- Improves strategic gameplay

#### 3. **WordValidator Class (game/WordValidator.java)**
Ensures all guesses meet game requirements before processing.

**Validation Rules:**
- Must be exactly 5 characters long
- Must contain only alphabetic characters
- No numbers or special characters allowed

**Features:**
- Pre-game validation prevents wasted attempts
- Invalid guesses don't count against the 6-attempt limit
- Clear error messages guide players

#### 4. **Enhanced Game Loop**
Major improvements to the core game flow in WordleGame.java.

**Improvements:**
- Integration of all three game classes (FeedbackProvider, LetterTracker, WordValidator)
- Proper attempt counting (invalid guesses don't count)
- Color-coded feedback display after each guess
- Letter tracking updates after each valid guess
- Cleaner game flow with better user experience

### Project Structure
```
KoodWordle/
├── WordleGame.java                 # Main game controller with enhanced loop
├── game/
│   ├── FeedbackProvider.java      # Color-coded feedback system
│   ├── LetterTracker.java         # Letter usage tracking
│   └── WordValidator.java         # Input validation
├── io/
│   └── WordReader.java            # File I/O handler
├── wordle-words.txt               # Word list
└── README.md                      # Phase 1 & 2 documentation
```

## Techniques Used

### 1. **ANSI Escape Codes for Terminal Colors**
```java
private static final String GREEN = "\u001B[32m";
private static final String YELLOW = "\u001B[33m";
private static final String WHITE = "\u001B[37m";
private static final String RESET = "\u001B[0m";
```
- Universal terminal color support
- No external libraries required
- Cross-platform compatibility

### 2. **Two-Pass Feedback Algorithm**
Essential for handling duplicate letters correctly (Wordle's trickiest logic).

**Example:** If secret word is "SPEED" and guess is "EERIE":
- First E: Yellow (exists, wrong position)
- Second E: Green (correct position, index 2)
- Third E: White (already matched two E's, no more available)

### 3. **HashSet for Letter Tracking**
```java
private Set<Character> usedLetters = new HashSet<>();
```
- O(1) lookup time
- Automatic duplicate prevention
- Efficient alphabet tracking

### 4. **StringBuilder for String Construction**
```java
StringBuilder feedback = new StringBuilder();
```
- More efficient than string concatenation
- Important for the 5-letter feedback string built character-by-character

### 5. **Boolean Array for State Tracking**
```java
boolean[] secretUsed = new boolean[5];
```
- Tracks which positions in secret word have been matched
- Prevents over-counting duplicate letters
- Key to Wordle's duplicate letter logic

## How to Run and Test

### Compilation
```bash
# Compile all game classes
javac game/FeedbackProvider.java game/LetterTracker.java game/WordValidator.java io/WordReader.java WordleGame.java
```

### Test Cases

#### **Test 1: Color Feedback - Exact Match**
```bash
java WordleGame 0
```
**Input:** CRANE (if secret word is CRANE)

**Expected Output:**
- All 5 letters in GREEN
- "You won in 1 attempts!"

#### **Test 2: Color Feedback - Mixed Results**
```bash
java WordleGame 1
```
**Guess:** CRANE (if secret word is SLATE)

**Expected Output:**
- C: WHITE (not in word)
- R: WHITE (not in word)
- A: YELLOW (in word, wrong position)
- N: WHITE (not in word)
- E: GREEN (correct position)

#### **Test 3: Letter Tracker**
```bash
java WordleGame 2
```
**After first guess:**
```
Remaining letters: B C D F G H I J K L M N O P Q R S T U V W X Y Z
```
*Only letters NOT in your guess appear*

#### **Test 4: Invalid Guess - Too Short**
```bash
java WordleGame 3
```
**Input:** CAT

**Expected Output:**
```
Invalid guess! Please enter exactly 5 letters.
Attempts remaining: 6
```
*Attempt counter doesn't decrease*

#### **Test 5: Invalid Guess - Numbers**
```bash
java WordleGame 4
```
**Input:** 12345

**Expected Output:**
```
Invalid guess! Please enter exactly 5 letters.
Attempts remaining: 6
```

#### **Test 6: Duplicate Letter Handling**
```bash
java WordleGame 5
```
**Secret:** SPEED  
**Guess:** EERIE

**Expected Output:**
- First E: YELLOW (in word at positions 2, 3)
- Second E: GREEN (correct at position 2)
- R: WHITE (not in word)
- Third E: GREEN (correct at position 3)
- Fourth E: WHITE (only 2 E's in SPEED)

#### **Test 7: Full Game Flow**
```bash
java WordleGame 0
```
**Sample interaction:**
```
Enter your name: 
Alice
Guess the word! You have 6 attempts.
Remaining letters: A B C D E F G H I J K L M N O P Q R S T U V W X Y Z

Enter your guess: AUDIO
A U D I O
Attempts remaining: 5
Remaining letters: B C E F G H J K L M N P Q R S T V W X Y Z

Enter your guess: CRANE
C R A N E
You won in 2 attempts!
```

### Visual Feedback Example
When playing in a terminal with color support:
- **GREEN** letters indicate correct position
- **YELLOW** letters indicate wrong position
- **WHITE** letters indicate not in word

## Key Improvements from Phase 2

| Feature | Phase 2 | Phase 3 |
|---------|---------|---------|
| **Feedback** | Plain text "You won" | Color-coded letter feedback |
| **Letter Tracking** | None | Full alphabet tracking |
| **Input Validation** | Basic length check | Comprehensive validation |
| **Duplicate Letters** | Not handled | Proper Wordle logic |
| **Game Experience** | Basic | Full Wordle experience |
| **Attempt Management** | All guesses count | Invalid guesses don't count |
| **Visual Clarity** | Text only | ANSI colors |
| **Code Organization** | Monolithic | Modular (3 game classes) |

## Technical Highlights

### FeedbackProvider Algorithm Complexity
- **Time Complexity:** O(n) where n = word length (always 5, so O(1))
- **Space Complexity:** O(n) for the boolean tracking array

### LetterTracker Efficiency
- **Add letter:** O(1) average
- **Check remaining:** O(26) = O(1) constant for alphabet
- **Total storage:** O(26) = O(1) constant

### WordValidator Performance
- **Length check:** O(1)
- **Character validation:** O(5) = O(1)

## Color Code Reference

| Color | Code | Meaning |
|-------|------|---------|
| GREEN | `\u001B[32m` | Correct letter, correct position |
| YELLOW | `\u001B[33m` | Correct letter, wrong position |
| WHITE | `\u001B[37m` | Letter not in word |
| RESET | `\u001B[0m` | Reset to default color |

## Game Rules Implemented

✅ **Exactly 6 attempts** to guess the word  
✅ **5-letter words only**  
✅ **Alphabetic characters only**  
✅ **Case-insensitive** input (auto-converted to uppercase)  
✅ **Color feedback** after each guess  
✅ **Letter tracking** for unused letters  
✅ **Proper duplicate letter** handling  
✅ **Invalid guesses don't count** against attempt limit  
✅ **Win detection** with attempt count  
✅ **Game over message** with secret word reveal

## Known Limitations (Future Enhancements)

1. **No dictionary validation**: Currently validates format only, not if word exists in dictionary
2. **Terminal color support**: Requires ANSI-compatible terminal
3. **No guess history display**: Previous guesses aren't shown in a list
4. **No statistics tracking**: Win/loss records not saved
5. **Single game session**: No replay without restarting program

## Dependencies

**Java Standard Library Only:**
- `java.util.HashSet` - Letter tracking
- `java.util.Set` - Letter storage interface
- `java.util.Scanner` - User input
- `java.io.*` - File reading

**No External Libraries Required**

## Testing Checklist

- [x] Green feedback for exact matches
- [x] Yellow feedback for wrong position
- [x] White feedback for non-existent letters
- [x] Letter tracker updates correctly
- [x] Invalid guesses rejected (too short/long)
- [x] Invalid guesses rejected (non-alphabetic)
- [x] Invalid attempts don't count
- [x] Win detection after correct guess
- [x] Game over after 6 failed attempts
- [x] Secret word revealed on loss
- [x] Duplicate letter handling

## Author
Hashim Ali

## Date
January 2026

---

**Phase 2 → Phase 3 Transition**: We evolved from a basic guess-and-check game to a fully-featured Wordle clone with sophisticated feedback algorithms, visual enhancements, and proper input validation. The game now provides the complete Wordle experience with color-coded feedback and strategic letter tracking.
