package menus;

import services.DbCardManager;
import java.util.Scanner;

abstract class Menu {
    protected DbCardManager db = new DbCardManager();
    protected Scanner scanner = new Scanner(System.in);

    public void printMenu() {
        int choice;

        do {
            printChoices();
            choice = scanner.nextInt();
            choice = processChoices(choice);
        } while (choice != 0);
    }

    protected abstract void printChoices();
    protected abstract int processChoices(int choice);
    protected void exit() {
        System.out.println("Bye!");
        System.exit(0);
    }
}
