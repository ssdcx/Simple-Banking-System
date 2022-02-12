package menus;

import card.Card;
import static algorithms.LuhnAlgorithm.luhnAlgorithmTest;

public class AccountMenu extends Menu {
    final private Card card;

    public AccountMenu(Card card) {
        this.card = card;
    }

    @Override
    protected void printChoices() {
        System.out.println("1. Balance");
        System.out.println("2. Add income");
        System.out.println("3. Do transfer");
        System.out.println("4. Close account");
        System.out.println("5. Log out");
        System.out.println("0. Exit");
    }

    @Override
    protected int processChoices(int choice) {
        switch (choice) {
            case (1):
                printBalance();
                return choice;
            case (2):
                addIncome();
                return choice;
            case (3):
                doTransfer();
                return choice;
            case (4):
                closeAccount();
            case (5):
                logOut();
                return 0;
            case (0):
                exit();
            default:
                System.out.println("There is no such choice!");
                return choice;
        }
    }

    private void printBalance() {
        System.out.println("Balance: " + db.checkBalance(card.getCardNumber()));
    }

    private void logOut() {
        System.out.println("You have successfully logged out!");
    }

    private void addIncome() {
        System.out.println("How much you want to deposit:");
        int income = scanner.nextInt();
        db.addIncomeToBalance(card, income);
        System.out.println(income + " successfully added to your card!");
    }

    private void closeAccount() {
        db.closeAccount(card);
        System.out.println("You account has been successfully deleted!");
    }

    private void doTransfer() {
        System.out.println("Write receiver's card number:");
        String receiversCardNumber = scanner.next();
        if (!luhnAlgorithmTest(receiversCardNumber)) {
            System.out.println("Probably you made a mistake in the card number. Please try again!");
        } else if (!db.isExist(receiversCardNumber)) {
            System.out.println("Such a card does not exist.");
        } else if (receiversCardNumber.equals(card.getCardNumber())) {
            System.out.println("You can't transfer money to the same account!");
        } else {
            System.out.println("How much to transfer:");
            int sum = scanner.nextInt();
            if (db.checkBalance(card.getCardNumber()) < sum) {
                System.out.println("Not enough money!");
            } else {
                Card receiver = new Card();
                receiver.setCardNumber(receiversCardNumber);
                db.doTransfer(card, receiver, sum);
                System.out.println("Successfully transferred!");
            }
        }
    }
}
