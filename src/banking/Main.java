package banking;

import dao.CardDao;
import menus.MainMenu;

public class Main {
    public static void main(String[] args) {
        String dbName = (args[0].equals("-fileName")) ? args[1] : "";
        String tableName = "card";

        CardDao.initialize(dbName, tableName);

        new MainMenu().printMenu();
    }
}