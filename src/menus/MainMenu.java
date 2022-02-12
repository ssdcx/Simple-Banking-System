package menus;

import card.Card;

import static services.CardGenerator.createCard;

public class MainMenu extends Menu {
    @Override
    protected void printChoices() {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
    }

    @Override
    protected int processChoices(int choice) {
        switch (choice) {
            case (1):
                createCardAndPutInDb();
                return choice;
            case (2):
                logIn();
                return choice;
            case (0):
                exit();
            default:
                System.out.println("There is no such choice!");
                return choice;
        }
    }

    private void createCardAndPutInDb() {
        Card card = createCard();
        db.addCard(card);

        System.out.println("Your card has been created");
        System.out.println("Your card number:");
        System.out.println(card.getCardNumber());
        System.out.println("Your card PIN:");
        System.out.println(card.getPin());
    }

    private void logIn() {
        Card card = new Card();
        System.out.println("Enter your card number:");
        card.setCardNumber(scanner.next());
        System.out.println("Enter your PIN:");
        card.setPin(scanner.next());

        if (db.isExist(card.getCardNumber(), card.getPin())) {
            System.out.println("You have successfully logged in!");
            new AccountMenu(card).printMenu();
        } else {
            System.out.println("Wrong card number or PIN!");
        }
    }
}
