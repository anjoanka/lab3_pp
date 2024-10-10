package game.droids;

import java.util.Random;

public class BattleDroid extends Droid {

    private static final int MAX_DAMAGE = 20;
    private int attackCount = 0;

    public BattleDroid(String name) {
        super(name, 100, MAX_DAMAGE);
    }

    @Override
    public int getDamage() {
        attackCount++;

        if (attackCount % 3 == 0) {
            System.out.println("\u001B[31m" + getName() + " performs a critical hit!\u001B[0m");
            return MAX_DAMAGE;
        }

        Random random = new Random();
        return random.nextInt(MAX_DAMAGE + 1);
    }
}
