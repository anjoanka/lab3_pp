package game.droids;

import java.util.Random;

public class SupportDroid extends Droid {
    private int healCount;
    private static final int MAX_DAMAGE = 10;
    private int attackCount = 0;

    public SupportDroid(String name) {
        super(name, 80, MAX_DAMAGE);
        this.healCount = 3; // Максимум 3 використання heal
    }

    @Override
    public int getDamage() {
        attackCount++;

        if (attackCount % 3 == 0) {
            System.out.println("\u001B[31m" + getName() + " performs a critical hit!\u001B[0m");
            return MAX_DAMAGE;
        }

        // Випадковий урон у діапазоні від 0 до MAX_DAMAGE
        Random random = new Random();
        return random.nextInt(MAX_DAMAGE + 1); // Від 0 до 20
    }

    public void heal() {
        if (healCount > 0) {
            this.health += 10;
            healCount--;
            System.out.println(getName() + " heals for 10 health. Remaining heals: " + healCount);
        } else {
            System.out.println(getName() + " has no heals left.");
        }
    }

    public boolean canHeal() {
        return healCount > 0;
    }
}
