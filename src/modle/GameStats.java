package model;

public class GameStats {

    private String username;
    private String secretWord;
    private int attempts;
    private String result; // "win" or "loss"

    public GameStats(String username, String secretWord, int attempts, String result) {
        this.username = username;
        this.secretWord = secretWord;
        this.attempts = attempts;
        this.result = result;
    }

    public String getUsername() {
        return username;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public int getAttempts() {
        return attempts;
    }

    public String getResult() {
        return result;
    }

    /**
     * Convert to CSV format
     * Format: username,secretWord,attempts,result
     */
    public String toCSV() {
        return username + "," + secretWord + "," + attempts + "," + result;
    }

    /**
     * Create GameStats from CSV line
     */
    public static GameStats fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");

        if (parts.length != 4) {
            return null; // Invalid format
        }

        try {
            String username = parts[0];
            String secretWord = parts[1];
            int attempts = Integer.parseInt(parts[2]);
            String result = parts[3];

            return new GameStats(username, secretWord, attempts, result);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("%s played %s in %d attempts - %s",
                username, secretWord, attempts, result);
    }
}