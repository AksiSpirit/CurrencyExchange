package ru.aksi.repository;

import ru.aksi.Database;
import ru.aksi.model.CurrencyExchange;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyExchangeRepositorySqliteImpl implements CurrencyExchangeRepository{
    private static CurrencyExchangeRepositorySqliteImpl instance = new CurrencyExchangeRepositorySqliteImpl();
    private Database db;
    private CurrencyExchangeRepositorySqliteImpl() {
        db = Database.getInstance();
    }

    public static CurrencyExchangeRepositorySqliteImpl getInstance() {
        return instance;
    }


    @Override
    public CurrencyExchange findById(int id) {
        String query = "SELECT * FROM 'currency_exchange' WHERE id = ?;";
        CurrencyExchange result = null;
        Connection connection;
        try {
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CurrencyExchange currencyExchange = new CurrencyExchange();
                currencyExchange.setId(resultSet.getInt("id"));
                currencyExchange.setValue(resultSet.getDouble("value"));
                currencyExchange.setNominal(resultSet.getInt("nominal"));
                currencyExchange.setCurrencyName(resultSet.getString("currency_name"));
                currencyExchange.setCurrencyCode(resultSet.getString("currency_code"));
                currencyExchange.setDate(resultSet.getDate("date").toLocalDate());
                result = currencyExchange;
            }
        } catch (SQLException e) {
            System.err.printf("CurrencyExchangeSqlite - findById() error: " + e);
        }
        return result;
    }

    @Override
    public List<CurrencyExchange> findAll() {
        String query = "SELECT * FROM 'currency_exchange';";
        List<CurrencyExchange> resultList = new ArrayList<>();
        Connection connection;
        try {
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CurrencyExchange currencyExchange = new CurrencyExchange();
                currencyExchange.setId(resultSet.getInt("id"));
                currencyExchange.setValue(resultSet.getDouble("value"));
                currencyExchange.setNominal(resultSet.getInt("nominal"));
                currencyExchange.setCurrencyName(resultSet.getString("currency_name"));
                currencyExchange.setCurrencyCode(resultSet.getString("currency_code"));
                currencyExchange.setDate(resultSet.getDate("date").toLocalDate());
                resultList.add(currencyExchange);
            }
        } catch (SQLException e) {
            System.err.printf("CurrencyExchangeSqlite - findAll() error: " + e);
        }
        return resultList;
    }

    @Override
    public List<CurrencyExchange> findAllByCode(String currencyCode) {
        String query = "SELECT * FROM 'currency_exchange' WHERE 'currency_code' = ?;";
        List<CurrencyExchange> resultList = new ArrayList<>();
        Connection connection;
        try {
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, currencyCode);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CurrencyExchange currencyExchange = new CurrencyExchange();
                currencyExchange.setId(resultSet.getInt("id"));
                currencyExchange.setValue(resultSet.getDouble("value"));
                currencyExchange.setNominal(resultSet.getInt("nominal"));
                currencyExchange.setCurrencyName(resultSet.getString("currency_name"));
                currencyExchange.setCurrencyCode(resultSet.getString("currency_code"));
                currencyExchange.setDate(resultSet.getDate("date").toLocalDate());
                resultList.add(currencyExchange);
            }
        } catch (SQLException e) {
            System.err.printf("CurrencyExchangeSqlite - findAllByCode() error: " + e);
        }
        return resultList;
    }

    @Override
    public int delete(int id) {
        String query = "DELETE FROM 'currency_exchange' WHERE 'id' = ?;";
        int result = 0;
        Connection connection;
        try {
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.printf("CurrencyExchangeSqlite - delete() error: " + e);
        }
        return result;
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM 'currency_exchange';";
        String resetQuery = "DELETE FROM 'sqlite_sequence' WHERE name = 'currency_exchange'";
        Connection connection;
        try {
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            connection.prepareStatement(resetQuery).executeUpdate();
        } catch (SQLException e) {
            System.err.printf("CurrencyExchangeSqlite - delete() error: " + e);
        }
    }

    @Override
    public int insert(CurrencyExchange currency) {
        String query = "INSERT INTO 'currency_exchange' ('value', 'nominal', 'currency_name', 'currency_code', 'date') VALUES (?, ?, ?, ?, ?);";
        int result = 0;
        Connection connection;
        try {
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, currency.getValue());
            statement.setInt(2, currency.getNominal());
            statement.setString(3, currency.getCurrencyName());
            statement.setString(4, currency.getCurrencyCode());
            statement.setDate(5, Date.valueOf(currency.getDate()));
            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.printf("CurrencyExchangeSqlite - insert() error: " + e);
        }
        return result;
    }

    @Override
    public int update(CurrencyExchange currency) {
        String query = "UPDATE 'currency_exchange' SET 'value' = ?, 'nominal' = ?, 'date' = ? WHERE 'currency_code' = ?;";
        int result = 0;
        Connection connection;
        try {
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, currency.getValue());
            statement.setInt(2, currency.getNominal());
            statement.setDate(3, Date.valueOf(currency.getDate()));
            statement.setString(4, currency.getCurrencyCode());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.printf("CurrencyExchangeSqlite - update() error: " + e);
        }
        return result;
    }
}
