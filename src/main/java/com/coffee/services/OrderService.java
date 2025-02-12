package com.coffee.services;

import com.coffee.database.DatabaseService;
import java.util.List;

public class OrderService implements OrderServiceInterface {
    private final DatabaseService databaseService;

    public OrderService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public void viewMenu() {
        System.out.println("\nDrink Menu:");
        List<String> drinks = getDrinksMenu();
        if (drinks.isEmpty()) {
            System.out.println("No drinks available.");
        } else {
            drinks.forEach(drink -> System.out.println((drinks.indexOf(drink) + 1) + ". " + drink));
        }

        System.out.println("\nFood Menu:");
        List<String> foods = getFoodsMenu();
        if (foods.isEmpty()) {
            System.out.println("No food available.");
        } else {
            foods.forEach(food -> System.out.println((foods.indexOf(food) + 1) + ". " + food));
        }
    }

    public List<String> getDrinksMenu() {
        return databaseService.getMenu("drink");
    }

    public List<String> getFoodsMenu() {
        return databaseService.getMenu("food");
    }

    @Override
    public void addDrinkOrder(int index) {
        List<String> drinks = getDrinksMenu();
        if (index >= 0 && index < drinks.size()) {
            String selectedDrink = drinks.get(index);
            System.out.println("Drink added to order: " + selectedDrink);
            saveOrder(selectedDrink, "drink");
        } else {
            System.out.println("Invalid drink selection.");
        }
    }

    @Override
    public void addFoodOrder(int index) {
        List<String> foods = getFoodsMenu();
        if (index >= 0 && index < foods.size()) {
            String selectedFood = foods.get(index);
            System.out.println("Food added to order: " + selectedFood);
            saveOrder(selectedFood, "food");
        } else {
            System.out.println("Invalid food selection.");
        }
    }

    @Override
    public void addDrink(String name, double price, String size) {
        System.out.println("Drink manually added: " + name + " (" + size + ") - $" + price);
        databaseService.saveOrder(name, price, size, "drink");
    }

    @Override
    public void addFood(String name, double price, String size) {
        System.out.println("Food manually added: " + name + " (" + size + ") - $" + price);
        databaseService.saveOrder(name, price, size, "food");
    }

    private void saveOrder(String item, String type) {
        String[] parts = item.split(" - ");
        if (parts.length < 3) {
            System.out.println("Error: Incorrect item format.");
            return;
        }

        String name = parts[0].trim();
        String size = parts[1].replace("(", "").replace(")", "").trim();
        double price;

        try {
            price = Double.parseDouble(parts[2].replace("$", "").trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid price format.");
            return;
        }

        databaseService.saveOrder(name, price, size, type);
    }

    public List<String> getOrders() {
        return databaseService.getOrders();
    }
}
