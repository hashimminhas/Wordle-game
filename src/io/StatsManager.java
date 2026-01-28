package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.GameStats;

public class StatsManager {
    
    private String filename;
    
    public StatsManager(String filename) {
        this.filename = filename;
    }
    
    /**
     * Save a game result to the stats file
     */
    public void saveGameStats(GameStats stats) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            // true = append mode
            writer.write(stats.toCSV());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving stats: " + e.getMessage());
        }
    }
    
    /**
     * Read all stats from the file
     */
    public List<GameStats> readAllStats() {
        List<GameStats> statsList = new ArrayList<>();
        File file = new File(filename);
        
        // If file doesn't exist, return empty list
        if (!file.exists()) {
            return statsList;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                GameStats stats = GameStats.fromCSV(line);
                if (stats != null) {
                    statsList.add(stats);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading stats: " + e.getMessage());
        }
        
        return statsList;
    }
    
    /**
     * Get stats for a specific user
     */
    public List<GameStats> getStatsForUser(String username) {
        List<GameStats> allStats = readAllStats();
        List<GameStats> userStats = new ArrayList<>();
        
        for (GameStats stats : allStats) {
            if (stats.getUsername().equalsIgnoreCase(username)) {
                userStats.add(stats);
            }
        }
        
        return userStats;
    }
    
    /**
     * Display all stats
     */
    public void displayAllStats() {
        List<GameStats> allStats = readAllStats();
        
        if (allStats.isEmpty()) {
            System.out.println("\nNo stats available yet.");
            return;
        }
        
        System.out.println("\n=== All Game Stats ===");
        for (GameStats stats : allStats) {
            System.out.println(stats);
        }
    }
    
    /**
     * Display stats for a specific user
     */
    public void displayUserStats(String username) {
        List<GameStats> userStats = getStatsForUser(username);
        
        if (userStats.isEmpty()) {
            System.out.println("No stats found for " + username);
            return;
        }
        
        int wins = 0;
        int totalAttempts = 0;
        
        for (GameStats stats : userStats) {
            if (stats.getResult().equals("win")) {
                wins++;
            }
            totalAttempts += stats.getAttempts();
        }
        
        double avgAttempts = (double) totalAttempts / userStats.size();
        
        System.out.println("Stats for " + username + ":");
        System.out.println("Games played: " + userStats.size());
        System.out.println("Games won: " + wins);
        System.out.println("Average attempts per game: " + avgAttempts);
    }
}