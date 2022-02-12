package dao;

import card.Card;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CardDao implements Dao<Card> {
    final private SQLiteDataSource dataSource = new SQLiteDataSource();
    private String tableName;
    private static CardDao instance;

    private CardDao(String dbName, String tableName) {
        dataSource.setUrl("jdbc:sqlite:" + dbName);
        this.tableName = tableName;
        createTableIfNotExists(tableName);
    }

    public static CardDao getInstance() {
        return instance;
    }

    public static CardDao initialize(String dbName, String tableName) {
        if (instance == null) {
            instance = new CardDao(dbName, tableName);
        }
        return instance;
    }

    private void createTableIfNotExists(String tableName) {
        String query = "CREATE TABLE IF NOT EXISTS " + tableName + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "number TEXT," +
                "pin TEXT," +
                "balance INTEGER DEFAULT 0)";

        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    @Override
    public Card get(String cardNumber) {
        Card card = null;
        String query = "SELECT number, pin, balance FROM " + tableName + " WHERE number=" + cardNumber;

        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    if (resultSet.isBeforeFirst()) {
                        resultSet.next();
                        card = new Card(resultSet.getString("number"), resultSet.getString("pin"), resultSet.getInt("balance"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return card;
    }

    @Override
    public void save(Card card) {
        String query = "INSERT INTO " + tableName + " (number, pin, balance) VALUES " +
                "(" + card.getCardNumber() + ", "+ card.getPin() + ", " + card.getBalance() + ")";

        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Card card, String param, int value) {
        String query = "UPDATE " + tableName +
                " SET " + param + " = " + value +
                " WHERE number = " + card.getCardNumber();

        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Card card) {
        String query = "DELETE FROM " + tableName +
                " WHERE number = " + card.getCardNumber();

        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
