package services;

import card.Card;
import dao.CardDao;


public class DbCardManager {
    private CardDao dao;

    public DbCardManager() {
        this.dao = CardDao.getInstance();
    }

    public int checkBalance(String cardNumber) {
        return dao.get(cardNumber).getBalance();
    }

    public void addCard(Card card) {
        dao.save(card);
    }

    public boolean isExist(String cardNumber, String pin) {
        boolean check = false;
        Card card = dao.get(cardNumber);

        if (card != null && card.getPin().equals(pin)) {
            check = true;
        }

        return check;
    }

    public boolean isExist(String cardNumber) {
        boolean check = false;
        Card card = dao.get(cardNumber);

        if (card != null) {
            check = true;
        }

        return check;
    }

    public void addIncomeToBalance(Card card, int income) {
        String param = "balance";
        dao.update(card, param, dao.get(card.getCardNumber()).getBalance() + income);
    }

    public void doTransfer(Card sender, Card receiver, int sum) {
        String param = "balance";
        dao.update(sender, param, dao.get(sender.getCardNumber()).getBalance() - sum);
        dao.update(receiver, param, dao.get(receiver.getCardNumber()).getBalance() + sum);
    }

    public void closeAccount(Card card) {
        dao.delete(card);
    }
}
