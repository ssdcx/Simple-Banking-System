package services;

import card.Card;
import java.util.Random;

import static algorithms.LuhnAlgorithm.luhnAlgorithm;

public class CardGenerator {
    public static Card createCard() {
        Random random = new Random();

        String bin =  "400000";
        String pin = String.valueOf(random.nextInt(9999 - 1000 + 1) + 1000);
        String accountIdentifier = String.valueOf(random.nextInt(999999999 - 100000000 + 1) + 100000000);
        String checksum = luhnAlgorithm(bin, accountIdentifier);;

        Card card = new Card(bin + accountIdentifier + checksum, pin, 0);

        return card;
    }
}
