package com.coffee.database;

import java.util.List;

public interface DatabaseService {
    List<String> getMenu(String type);
    void saveOrder(String name, double price, String size, String type);
    List<String> getOrders();
}