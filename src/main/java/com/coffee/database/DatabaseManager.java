package com.coffee.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager implements DatabaseService {
    private static final String URL = "jdbc:postgresql://localhost:5432/CoffeeDB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "0000";

    private static DatabaseManager instance;
    private Connection connection;

    private DatabaseManager() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseManager.class) {
                if (instance == null) {
                    instance = new DatabaseManager();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public List<String> getMenu(String type) {
        List<String> menu = new ArrayList<>();
        String query = "SELECT name, price, size FROM menu WHERE type = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, type);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String item = type + " - " + rs.getString("name") + " (" + rs.getString("size") + ") - $" + rs.getDouble("price");
                menu.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public void saveOrder(String name, double price, String size, String type) {
        String query = "INSERT INTO orders (name, price, size, type) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setString(3, size);
            pstmt.setString(4, type);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getOrders() {
        List<String> orders = new ArrayList<>();
        String query = "SELECT name, price, size, type FROM orders";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String order = rs.getString("type") + " - " + rs.getString("name") +
                        " (" + rs.getString("size") + ") - $" + rs.getDouble("price");
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}