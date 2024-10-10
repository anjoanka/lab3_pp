package game.menu;

public class Main {
    public static void main(String[] args) {
        System.out.println("\u001B[32mWelcome to the Droid Battle Game!\u001B[0m");

        Menu menu = new Menu();
        menu.displayMenu();

        System.out.println("Thank you for playing the Droid Battle Game!");
    }
}
