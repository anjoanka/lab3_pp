package game.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BattleRecorder {
    private static final String BATTLE_LOG_FILE = "battle_log.txt";
    public static void recordBattle(List<String> battleLog) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BATTLE_LOG_FILE))) {
            for (String log : battleLog) {
                writer.write(log);
                writer.newLine();
            }
            System.out.println("Battle recorded successfully.");
        } catch (IOException e) {
            System.out.println("Error saving the battle log: " + e.getMessage());
        }
    }

    public static List<String> loadBattle() {
        List<String> battleLog = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BATTLE_LOG_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                battleLog.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading the battle log: " + e.getMessage());
        }
        return battleLog;
    }
}
