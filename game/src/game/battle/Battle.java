package game.battle;

import game.droids.Droid;
import game.utils.BattleRecorder;
import game.droids.SupportDroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Battle {

    private Droid attacker;
    private Droid defender;
    private List<String> battleLog = new ArrayList<>();

    public Battle(Droid attacker, Droid defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    public void start() {
        System.out.println("\u001B[32mBattle Begins!\u001B[0m");

        while (attacker.isAlive() && defender.isAlive()) {
            battleRound(attacker, defender);

            if (!defender.isAlive()) {
                System.out.println("\u001B[33m" + defender.getName() + " has been destroyed!\u001B[0m");
                battleLog.add(defender.getName() + " has been destroyed.");
                break;
            }

            battleRound(defender, attacker);

            if (!attacker.isAlive()) {
                System.out.println("\u001B[31m" + attacker.getName() + " has been destroyed!\u001B[0m");
                battleLog.add(attacker.getName() + " has been destroyed.");
                break;
            }
        }

        BattleRecorder.recordBattle(battleLog);
    }

    private void battleRound(Droid attacker, Droid defender) {
        Random random = new Random();

        if (attacker instanceof SupportDroid && ((SupportDroid) attacker).canHeal()) {
            if (random.nextBoolean()) {
                ((SupportDroid) attacker).heal(); // Лікуємо дроїда
                battleLog.add(attacker.getName() + " heals itself.");
                System.out.println("\u001B[36m" + attacker.getName() + " heals for some health!\u001B[0m");
                return;
            }
        }

        int damage = attacker.getDamage();
        defender.takeDamage(damage);
        battleLog.add(attacker.getName() + " attacks " + defender.getName() + " and deals " + damage + " damage.");

        displayAttack(attacker, defender, damage);
    }

    private void displayAttack(Droid attacker, Droid defender, int damage) {
        System.out.println("\u001B[35m" + attacker.getName() + " attacks " + defender.getName() + " \u001B[0m");
        System.out.println("\u001B[34m" + defender.getName() + " takes " + damage + " damage!\u001B[0m");
        System.out.println("\u001B[33m" + defender.getName() + "'s remaining health: " + defender.getHealth() + "\u001B[0m");
        System.out.println("---------------------------------------------------");
    }
}
