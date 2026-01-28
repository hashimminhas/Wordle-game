# 🎮 Wordle Game - Command Line Implementation

A fully-featured Java implementation of the popular Wordle word-guessing game for the command line, complete with colored feedback, letter tracking, and persistent player statistics.

## 📋 Description

This is a command-line version of Wordle where players have **6 attempts** to guess a **5-letter word**. After each guess, the game provides colored feedback:

| Color | Meaning |
|-------|---------|
| 🟩 **Green** | Correct letter in the correct position |
| 🟨 **Yellow** | Correct letter in the wrong position |
| ⬜ **White** | Letter is not in the word |

## ✨ Features

- 🎮 **Interactive Gameplay**: 6 attempts to guess the secret word
- 🎨 **Colored Feedback**: ANSI color-coded letter feedback
- 🔤 **Letter Tracking**: Shows remaining unused letters
- 📊 **Statistics Tracking**: Win/loss records saved to CSV
- 👤 **Multi-User Support**: Track multiple players
- 📈 **Analytics**: Win rate and average attempts calculations
- 💾 **Persistent Data**: Stats saved across game sessions
- ✅ **Input Validation**: Rejects invalid guesses without penalty

## 🛠️ Prerequisites

- Java Development Kit (JDK) 8 or higher
- Terminal with ANSI color support:
  - **Windows**: Windows Terminal, Git Bash, or PowerShell
  - **Linux/Mac**: Most terminals work out of the box

## 📁 Project Structure

```
KoodWordle/
├── WordleGame.java              # Main game controller
├── game/
│   ├── FeedbackProvider.java    # Color-coded feedback logic
│   ├── WordValidator.java       # Input validation
│   └── LetterTracker.java       # Tracks used letters
├── io/
│   ├── WordReader.java          # Reads word list from file
│   └── StatsManager.java        # Manages statistics CSV
├── model/
│   └── GameStats.java           # Game statistics data model
├── wordle-words.txt             # Word list (user-provided)
├── stats.csv                    # Generated statistics file
├── .gitignore                   # Git ignore configuration
└── README.md                    # This file
```

## 🚀 Installation

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd KoodWordle
   ```

2. **Add a word list file:**
   Create `wordle-words.txt` with 5-letter words (one per line):
   ```
   CRANE
   SLATE
   AUDIO
   MOUNT
   CRISP
   ```

3. **Compile the project:**
   ```bash
   javac WordleGame.java
   ```

## 💻 Usage

### Basic Command
```bash
java WordleGame <word_index>
```

Where `<word_index>` is the 0-based index of the word in your word list.

### Examples
```bash
java WordleGame 0     # Play with the first word
java WordleGame 9     # Play with the 10th word
java WordleGame 42    # Play with the 43rd word
```

### Error Cases
```bash
java WordleGame           # Error: Missing argument
java WordleGame abc       # Error: Invalid number
java WordleGame -1        # Error: Negative index
java WordleGame 999       # Error: Index out of range
```

## 🎯 How to Play

1. **Start the game** with a word index
2. **Enter your name** when prompted
3. **Make guesses** - enter 5-letter words
4. **Read the feedback**:
   - Green letters are in the correct spot
   - Yellow letters are in the word but wrong spot
   - White letters are not in the word
5. **Win** by guessing the word within 6 attempts
6. **View your stats** after the game

## 📺 Example Gameplay

```
Enter your name: 
alice
Guess the word! You have 6 attempts.
Remaining letters: A B C D E F G H I J K L M N O P Q R S T U V W X Y Z

Enter your guess: stone
STONE
Attempts remaining: 5
Remaining letters: B C D F G H I J K L M P Q R U V W X Y Z

Enter your guess: crane
CRANE
You won in 2 attempts!

Would you like to see your stats? (yes/no): yes

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

## 📊 Statistics System

### Tracked Metrics
- ✅ Total games played
- ✅ Total wins and losses
- ✅ Win rate percentage
- ✅ Average attempts to win
- ✅ Complete game history

### Stats File Format
Statistics are saved to `stats.csv` in CSV format:
```
username,secretWord,attempts,result
alice,CRANE,2,win
bob,SLATE,6,loss
alice,AUDIO,4,win
```

## 🔧 Technical Details

### Architecture
- **MVC-like Pattern**: Model (GameStats), View (console output), Controller (WordleGame)
- **Package Organization**: `game/`, `io/`, `model/`
- **Single Responsibility**: Each class has one purpose

### Key Algorithms
- **Two-Pass Feedback Algorithm**: Correctly handles duplicate letters
- **HashSet for Letter Tracking**: O(1) lookup for remaining letters
- **CSV Serialization**: Simple, portable data format

### Color Implementation
```java
private static final String GREEN  = "\u001B[32m";
private static final String YELLOW = "\u001B[33m";
private static final String WHITE  = "\u001B[37m";
private static final String RESET  = "\u001B[0m";
```

## 📝 Development Phases

| Phase | Description | Key Features |
|-------|-------------|--------------|
| **1** | Basic Structure | CLI argument validation |
| **2** | File Operations | Word file reading, WordReader class |
| **3** | Game Logic | Colored feedback, letter tracking, validation |
| **4** | Statistics | Stats persistence, CSV storage, analytics |
| **5** | Final Polish | Enhanced formatting, documentation, testing |

## 🧪 Testing

### Run All Tests
```bash
# Valid gameplay
java WordleGame 0

# Edge cases
java WordleGame           # Missing argument
java WordleGame abc       # Non-numeric
java WordleGame -1        # Negative
java WordleGame 999       # Out of range

# Input validation (during game)
# Enter: AB (too short)
# Enter: ABCDEF (too long)
# Enter: 12345 (not letters)
```

### Expected Behaviors
- ✅ Invalid arguments show helpful error messages
- ✅ Invalid guesses don't count against attempts
- ✅ Stats persist across multiple games
- ✅ Multiple users tracked separately
- ✅ EOF (Ctrl+D) exits gracefully

## 📄 Files Not in Repository

Per `.gitignore`, these files are not committed:
- `wordle-words.txt` - Word list (user must provide)
- `stats.csv` - Generated during gameplay
- `*.class` - Compiled Java files
- IDE configuration files

## 🎓 Requirements Met

- ✅ **No static methods** (except `main()`)
- ✅ **Single Scanner** passed through methods
- ✅ **Proper packages** for organization
- ✅ **Error handling** for all edge cases
- ✅ **No external dependencies** - Java standard library only

## 🔮 Possible Enhancements

- [ ] Dictionary validation (check if guess is a real word)
- [ ] Streak tracking (consecutive wins)
- [ ] Time-based statistics
- [ ] Hint system
- [ ] Multiplayer mode
- [ ] Web interface

## 👨‍💻 Author

**Hashim Ali**

## 📅 Date

January 2026

## 🏫 Acknowledgments

- Inspired by [Wordle](https://www.nytimes.com/games/wordle/index.html) by Josh Wardle
- Created as part of the Kood/Jõhvi curriculum

---

**Happy Wordling! 🎮**
