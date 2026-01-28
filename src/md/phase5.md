# Wordle Game - Phase 5

## Overview
Phase 5 is the final polish phase, focusing on enhancing user experience with improved output formatting, comprehensive documentation, and thorough testing. This phase transforms the functional game into a polished, submission-ready project.

## Phase 5: Final Polish & Documentation

### What We Implemented

#### 1. **Enhanced Stats Display Formatting**
Upgraded the stats display from plain text to a visually appealing format with Unicode box-drawing characters.

**Before (Phase 4):**
```
=== Stats for alice ===
alice played CRANE in 2 attempts - win

Summary:
Games played: 1
Wins: 1
Losses: 0
Average attempts to win: 2.00
```

**After (Phase 5):**
```
╔════════════════════════════════════════╗
║       Stats for alice                  ║
╚════════════════════════════════════════╝

Game History:
1. CRANE - 2 attempts - WIN

────────────────────────────────────────
Summary:
  Games played: 1
  Wins:         1
  Losses:       0
  Win rate:     100.0%
  Avg attempts: 2.00
────────────────────────────────────────

Thanks for playing!
```

**New Features:**
- Unicode box-drawing characters for visual appeal
- Numbered game history list
- Win rate percentage calculation
- Aligned summary with proper spacing
- Closing "Thanks for playing!" message
- Dynamic username padding for proper box alignment

#### 2. **Improved Game Exit Messages**
Added proper exit handling for all scenarios.

**Changes in WordleGame.java:**
- "Thanks for playing!" message when declining to view stats
- Consistent farewell message regardless of game outcome
- Graceful exit on EOF (Ctrl+D)

#### 3. **Enhanced .gitignore**
Comprehensive ignore file covering all common scenarios.

**Added entries:**
```gitignore
# IDE files
*.classpath
*.project
.settings/

# OS files
desktop.ini

# Build directories
bin/
out/
target/

# Logs
*.log

# Backup files
*.bak
*~
```

#### 4. **Win Rate Calculation**
New statistic added to player summary.

**Formula:**
```java
double winRate = ((double) wins / userStats.size()) * 100;
```

**Display:**
```
Win rate: 75.0%
```

#### 5. **Helper Method for String Padding**
Added utility method for proper box alignment.

```java
private String padRight(String s, int n) {
    return String.format("%-" + n + "s", s);
}
```

### Project Structure (Final)
```
KoodWordle/
├── WordleGame.java                 # Main game controller
├── game/
│   ├── FeedbackProvider.java      # Color-coded feedback system
│   ├── LetterTracker.java         # Letter usage tracking
│   └── WordValidator.java         # Input validation
├── io/
│   ├── WordReader.java            # Word file handler
│   └── StatsManager.java          # Enhanced stats manager
├── model/
│   └── GameStats.java             # Game data model
├── wordle-words.txt               # Word list (not in repo)
├── stats.csv                      # Auto-generated stats file
├── .gitignore                     # Comprehensive ignore rules
├── README.md                      # Main documentation
├── PHASE2_README.md               # Phase 2 documentation
├── PHASE3_README.md               # Phase 3 documentation
├── PHASE4_README.md               # Phase 4 documentation
└── PHASE5_README.md               # This file
```

## Techniques Used

### 1. **Unicode Box-Drawing Characters**
```java
System.out.println("╔════════════════════════════════════════╗");
System.out.println("║       Stats for " + username + "       ║");
System.out.println("╚════════════════════════════════════════╝");
```

**Characters Used:**
| Character | Unicode | Description |
|-----------|---------|-------------|
| ╔ | U+2554 | Box drawings double down and right |
| ═ | U+2550 | Box drawings double horizontal |
| ╗ | U+2557 | Box drawings double down and left |
| ║ | U+2551 | Box drawings double vertical |
| ╚ | U+255A | Box drawings double up and right |
| ╝ | U+255D | Box drawings double up and left |
| ─ | U+2500 | Box drawings light horizontal |

### 2. **String Formatting with printf**
```java
System.out.printf("%d. %s - %d attempts - %s\n", 
    i + 1, stats.getSecretWord(), stats.getAttempts(), 
    stats.getResult().toUpperCase());
```
- Clean, formatted output
- Proper alignment
- Numbered lists

### 3. **Percentage Calculation**
```java
double winRate = ((double) wins / userStats.size()) * 100;
System.out.printf("  Win rate:     %.1f%%\n", winRate);
```
- Cast to double for precision
- Format with one decimal place
- Escape `%` with `%%`

### 4. **Dynamic String Padding**
```java
private String padRight(String s, int n) {
    return String.format("%-" + n + "s", s);
}
```
- Left-aligned padding
- Maintains box integrity regardless of username length

## How to Compile and Run

### Compilation
```bash
# Clean compiled files (optional)
del /S *.class

# Compile all files
javac WordleGame.java
```

### Run
```bash
java WordleGame <word_index>
```

### Examples
```bash
java WordleGame 0    # Play with first word
java WordleGame 5    # Play with sixth word
java WordleGame 99   # Play with 100th word
```

## Test Cases

### Test 1: Complete Game with Stats Display
```bash
java WordleGame 0
```

**Expected Output:**
```
Enter your name: 
alice
Guess the word! You have 6 attempts.
Remaining letters: A B C D E F G H I J K L M N O P Q R S T U V W X Y Z

Enter your guess: crane
CRANE
You won in 1 attempts!

Would you like to see your stats? (yes/no): yes

╔════════════════════════════════════════╗
║       Stats for alice                  ║
╚════════════════════════════════════════╝

Game History:
1. CRANE - 1 attempts - WIN

────────────────────────────────────────
Summary:
  Games played: 1
  Wins:         1
  Losses:       0
  Win rate:     100.0%
  Avg attempts: 1.00
────────────────────────────────────────

Thanks for playing!
```

### Test 2: Decline Stats
```bash
java WordleGame 0
```

**Input:**
```
Would you like to see your stats? (yes/no): no
```

**Expected Output:**
```
Thanks for playing!
```

### Test 3: Multiple Games Statistics
After playing 4 games (3 wins, 1 loss):

**Expected Output:**
```
╔════════════════════════════════════════╗
║       Stats for alice                  ║
╚════════════════════════════════════════╝

Game History:
1. CRANE - 2 attempts - WIN
2. SLATE - 4 attempts - WIN
3. AUDIO - 6 attempts - LOSS
4. MOUNT - 3 attempts - WIN

────────────────────────────────────────
Summary:
  Games played: 4
  Wins:         3
  Losses:       1
  Win rate:     75.0%
  Avg attempts: 3.00
────────────────────────────────────────

Thanks for playing!
```

### Test 4: Invalid Input Handling
```bash
java WordleGame       # Missing argument
java WordleGame abc   # Invalid argument
java WordleGame -1    # Negative number
java WordleGame 999   # Out of range
```

All should display appropriate error messages without crashing.

### Test 5: EOF Handling
```bash
java WordleGame 0
# Press Ctrl+D or Ctrl+Z during input
```

Should exit gracefully without stack trace.

## Changes Summary

### WordleGame.java
| Line | Change |
|------|--------|
| Stats prompt section | Added "Thanks for playing!" for declined stats |

### StatsManager.java
| Method | Change |
|--------|--------|
| `displayUserStats()` | Complete rewrite with enhanced formatting |
| NEW: `padRight()` | Helper method for string padding |

### .gitignore
| Addition | Purpose |
|----------|---------|
| `*.classpath` | Eclipse IDE |
| `*.project` | Eclipse IDE |
| `.settings/` | Eclipse settings |
| `desktop.ini` | Windows folder config |
| `bin/`, `out/`, `target/` | Build directories |
| `*.log` | Log files |
| `*.bak`, `*~` | Backup files |

## Key Improvements from Phase 4

| Aspect | Phase 4 | Phase 5 |
|--------|---------|---------|
| **Stats Display** | Plain text | Unicode box formatting |
| **Win Rate** | Not shown | Percentage calculated |
| **Game History** | Simple list | Numbered, formatted list |
| **Exit Message** | None | "Thanks for playing!" |
| **Summary Alignment** | Basic | Proper spacing |
| **.gitignore** | Basic | Comprehensive |
| **Documentation** | Phase READMEs | Complete project README |

## Final Testing Checklist

### Gameplay Tests
- [x] Valid word index works
- [x] Invalid word index shows error
- [x] Missing argument shows usage
- [x] Invalid argument (non-number) shows error
- [x] Negative index shows error
- [x] Invalid guess rejected (not counted)
- [x] Valid guess processed correctly
- [x] Win detection works
- [x] Loss detection works (6 attempts)
- [x] Color feedback displays correctly
- [x] Letter tracker updates correctly

### Stats Tests
- [x] Stats saved after each game
- [x] Stats file created automatically
- [x] Stats display formatted correctly
- [x] Win rate calculated correctly
- [x] Average attempts correct (wins only)
- [x] Multiple players tracked separately
- [x] Case-insensitive username matching

### Output Tests
- [x] Unicode characters display correctly
- [x] Box alignment is correct
- [x] Summary is properly indented
- [x] "Thanks for playing!" shows when declining stats
- [x] "Thanks for playing!" shows after viewing stats

### Edge Case Tests
- [x] EOF (Ctrl+D) handled gracefully
- [x] Empty username handled
- [x] First game (empty stats file)
- [x] Large number of games

## Dependencies

**Java Standard Library Only:**
- No external dependencies
- Works with Java 8+
- Cross-platform compatible

## Known Terminal Requirements

For proper Unicode display:
- **Windows**: Use Windows Terminal or Git Bash
- **Linux/Mac**: Most terminals support Unicode
- **VS Code**: Built-in terminal works

## Files in Repository

**Committed:**
- ✅ WordleGame.java
- ✅ game/FeedbackProvider.java
- ✅ game/LetterTracker.java
- ✅ game/WordValidator.java
- ✅ io/WordReader.java
- ✅ io/StatsManager.java
- ✅ model/GameStats.java
- ✅ .gitignore
- ✅ README.md
- ✅ PHASE*_README.md files

**Not Committed (per .gitignore):**
- ❌ *.class files
- ❌ wordle-words.txt
- ❌ stats.csv
- ❌ IDE configurations

## Author
Hashim Ali

## Date
January 2026

---

**Phase 4 → Phase 5 Transition**: We transformed from a functional game to a polished, submission-ready project with enhanced visual formatting, comprehensive documentation, and thorough testing coverage. The game now provides a professional user experience worthy of submission.
