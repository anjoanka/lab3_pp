package game.battle;

import game.droids.Droid;
import game.droids.SupportDroid;
import game.utils.BattleRecorder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeamBattle {

    private List<Droid> team1;
    private List<Droid> team2;
    private List<String> battleLog = new ArrayList<>();

    public TeamBattle(List<Droid> team1, List<Droid> team2) {
        this.team1 = new ArrayList<>(team1);
        this.team2 = new ArrayList<>(team2);
    }

    public void start() {
        System.out.println("\u001B[32mTeam Battle Begins!\u001B[0m");

        while (!team1.isEmpty() && !team2.isEmpty()) {
            Droid attacker1 = team1.get(0);
            Droid attacker2 = team2.get(0);

            battleRound(attacker1, attacker2, team2);

            if (team2.isEmpty()) {
                System.out.println("\u001B[33mTeam 1 Wins!\u001B[0m");
                battleLog.add("Team 1 wins the battle.");
                break;
            }

            battleRound(attacker2, attacker1, team1);

            if (team1.isEmpty()) {
                System.out.println("\u001B[31mTeam 2 Wins!\u001B[0m");
                battleLog.add("Team 2 wins the battle.");
                break;
            }
        }

        BattleRecorder.recordBattle(battleLog);
    }

    private void battleRound(Droid attacker, Droid defender, List<Droid> defenderTeam) {
        int damage = attacker.getDamage();
        defender.takeDamage(damage);
        Random random = new Random();

        if (attacker instanceof SupportDroid && ((SupportDroid) attacker).canHeal()) {
            if (random.nextBoolean()) {
                ((SupportDroid) attacker).heal();
                battleLog.add(attacker.getName() + " heals itself.");
                System.out.println("\u001B[36m" + attacker.getName() + " heals for some health!\u001B[0m");
                return;
            }
        }
        battleLog.add(attacker.getName() + " attacks " + defender.getName() + " and deals " + damage + " damage.");


        displayAttack(attacker, defender, damage);

        if (!defender.isAlive()) {
            System.out.println("\u001B[31m" + defender.getName() + " has been destroyed!\u001B[0m");
            battleLog.add(defender.getName() + " has been destroyed.");
            defenderTeam.remove(defender); // Видаляємо дроїда з команди, якщо він знищений
        }
    }


    private void displayAttack(Droid attacker, Droid defender, int damage) {
        System.out.println("\u001B[35m" + attacker.getName() + " attacks " + defender.getName() + " \u001B[0m");
        System.out.println("\u001B[34m" + defender.getName() + " takes " + damage + " damage!\u001B[0m");
        System.out.println("\u001B[33m" + defender.getName() + "'s remaining health: " + defender.getHealth() + "\u001B[0m");
        System.out.println("---------------------------------------------------");
    }
}
