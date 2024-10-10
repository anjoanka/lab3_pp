package game.menu;

import game.battle.Battle;
import game.battle.TeamBattle;
import game.droids.BattleDroid;
import game.droids.Droid;
import game.droids.SupportDroid;
import game.utils.BattleRecorder;
import game.utils.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final List<Droid> createdDroids = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);


    public void displayMenu() {
        while (true) {
            System.out.println("\n\u001B[34m========= Droid Battle Game Menu =========\u001B[0m");
            System.out.println("1. Create a Droid");
            System.out.println("2. Show Created Droids");
            System.out.println("3. Start 1v1 Battle");
            System.out.println("4. Start Team Battle");
            System.out.println("5. Replay Battle from Log");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = InputValidator.getValidatedIntegerInput(scanner);

            switch (choice) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    showCreatedDroids();
                    break;
                case 3:
                    start1v1Battle();
                    break;
                case 4:
                    startTeamBattle();
                    break;
                case 5:
                    replayBattle();
                    break;
                case 6:
                    System.out.println("Exiting the game. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void createDroid() {
        System.out.println("\u001B[34mChoose Droid Type:\u001B[0m");
        System.out.println("1. Battle Droid");
        System.out.println("2. Support Droid");
        int choice = InputValidator.getValidatedIntegerInput(scanner);

        System.out.print("Enter Droid Name: ");
        String name = scanner.next();

        switch (choice) {
            case 1:
                createdDroids.add(new BattleDroid(name));
                System.out.println("Battle Droid \"" + name + "\" created!");
                break;
            case 2:
                createdDroids.add(new SupportDroid(name));
                System.out.println("Support Droid \"" + name + "\" created!");
                break;
            default:
                System.out.println("Invalid droid type.");
        }
    }

    private void showCreatedDroids() {
        if (createdDroids.isEmpty()) {
            System.out.println("No droids have been created yet.");
            return;
        }
        System.out.println("\u001B[34mCreated Droids:\u001B[0m");
        for (int i = 0; i < createdDroids.size(); i++) {
            System.out.println((i + 1) + ". " + createdDroids.get(i));
        }
    }

    private void start1v1Battle() {
        if (createdDroids.size() < 2) {
            System.out.println("Not enough droids for 1v1 battle. Create more droids first.");
            return;
        }

        System.out.println("Select Droid 1:");
        Droid team1 = selectDroid();

        System.out.println("Select Droid 2:");
        Droid team2 = selectDroid();

        Battle battle = new Battle(team1, team2);
        battle.start();
    }

    private void startTeamBattle() {
        if (createdDroids.size() < 4) {
            System.out.println("Not enough droids for team battle. Create more droids first.");
            return;
        }

        System.out.println("Select Team 1:");
        List<Droid> team1 = selectTeam();

        System.out.println("Select Team 2:");
        List<Droid> team2 = selectTeam();

        TeamBattle teamBattle = new TeamBattle(team1, team2);
        teamBattle.start();
    }

    private void replayBattle() {
        List<String> battleLog = BattleRecorder.loadBattle();
        if (battleLog == null || battleLog.isEmpty()) {
            System.out.println("No battles recorded yet.");
        } else {
            System.out.println("\u001B[34mReplaying Battle:\u001B[0m");
            for (String log : battleLog) {
                System.out.println(log);
            }
        }
    }

    private Droid selectDroid() {
        showCreatedDroids();
        System.out.print("Enter droid number: ");
        int droidIndex = InputValidator.getValidatedIntegerInput(scanner, 1, createdDroids.size());
        return createdDroids.get(droidIndex - 1);
    }

    private List<Droid> selectTeam() {
        List<Droid> team = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            System.out.println("Select Droid for position " + (i + 1) + ":");
            Droid droid = selectDroid();
            if (!team.contains(droid)) {
                team.add(droid);
            } else {
                System.out.println("Droid already selected. Choose another.");
                i--;
            }
        }
        return team;
    }
}
