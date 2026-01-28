# Wordle Game - Phase 1

## Overview
This is a Java implementation of a Wordle game. Phase 1 focuses on setting up the basic project structure and implementing command-line argument validation.

## Phase 1: Command-Line Argument Validation

### What We Implemented
Phase 1 establishes the foundation of the game by implementing robust command-line argument handling. The program accepts a word index as a command-line argument and validates it before proceeding.

### Techniques Used

1. **Command-Line Argument Parsing**
    - Implemented argument count validation using `args.length`
    - Used `Integer.parseInt()` to convert string arguments to integers
    - Applied defensive programming to handle invalid inputs

2. **Error Handling**
    - `NumberFormatException` handling for non-numeric inputs
    - Boundary validation for negative numbers
    - Argument count validation (exactly 1 argument required)

3. **Object-Oriented Design**
    - Separated concerns with a `run()` method
    - Used instance-based approach for game logic
    - Scanner resource management with proper cleanup

### Validation Rules

The program validates the following scenarios:

| Scenario | Input | Expected Output | Status |
|----------|-------|----------------|--------|
| Valid index | `java WordleGame 42` | `Using word index: 42` | ✅ |
| Missing argument | `java WordleGame` | `Usage: java WordleGame <word_index>` | ✅ |
| Invalid argument | `java WordleGame abc` | `Invalid word index. Please provide a valid integer.` | ✅ |
| Multiple arguments | `java WordleGame 1 2 3` | `Usage: java WordleGame <word_index>` | ✅ |
| Negative number | `java WordleGame -5` | `Word index must be a postive integer.` | ✅ |

## How to Run and Test

### Compilation
```bash
javac WordleGame.java
```

### Test Cases

**1. Test with valid index:**
```bash
java WordleGame 42
```
Expected output: `Using word index: 42`

**2. Test with missing argument:**
```bash
java WordleGame
```
Expected output: `Usage: java WordleGame <word_index>`

**3. Test with invalid (non-numeric) argument:**
```bash
java WordleGame abc
```
Expected output: `Invalid word index. Please provide a valid integer.`

**4. Test with multiple arguments:**
```bash
java WordleGame 1 2 3
```
Expected output: `Usage: java WordleGame <word_index>`

**5. Test with negative number:**
```bash
java WordleGame -5
```
Expected output: `Word index must be a postive integer.`

## Project Structure
```
KoodWordle/
├── WordleGame.java    # Main game file with argument validation
├── game/              # (Future: Game logic)
├── io/                # (Future: Input/Output handling)
├── model/             # (Future: Data models)
└── README.md          # Project documentation
```

## Next Steps (Future Phases)
- Implement word list loading and management
- Add game logic for guess validation
- Implement color-coded feedback system (green/yellow/gray)
- Add attempt tracking (6 attempts limit)
- Implement win/lose conditions

## Author
Hashim Ali

## Date
January 2026
