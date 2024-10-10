package game.droids;

public abstract class Droid {
    private String name;
    protected int health;
    protected int damage;

    public Droid(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    public boolean isAlive() {
        return health > 0;
    }
    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return "Droid{name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                '}';
    }
}
