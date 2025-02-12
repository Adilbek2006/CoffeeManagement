package com.coffee.main;

import com.coffee.database.DatabaseManager;
import com.coffee.services.OrderService;

import java.util.List;
import java.util.Scanner;

public class CoffeeManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderService orderService = new OrderService(DatabaseManager.getInstance());
        boolean running = true;

        while (running) {
            System.out.println("\nCoffee Management System");
            System.out.println("1. View Menu");
            System.out.println("2. Add Drink Order");
            System.out.println("3. Add Food Order");
            System.out.println("4. View Orders");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.nextLine();
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    orderService.viewMenu();
                    break;
                case 2:
                    List<String> drinks = orderService.getDrinksMenu();
                    if (drinks.isEmpty()) {
                        System.out.println("No drinks available.");
                    } else {
                        System.out.println("\nAvailable Drinks:");
                        for (int i = 0; i < drinks.size(); i++) {
                            System.out.println((i + 1) + ". " + drinks.get(i));
                        }
                        System.out.print("Choose a drink (1-" + drinks.size() + "): ");
                        int drinkChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (drinkChoice >= 1 && drinkChoice <= drinks.size()) {
                            orderService.addDrinkOrder(drinkChoice - 1);
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    }
                    break;
                case 3:
                    List<String> foods = orderService.getFoodsMenu();
                    if (foods.isEmpty()) {
                        System.out.println("No food available.");
                    } else {
                        System.out.println("\nAvailable Foods:");
                        for (int i = 0; i < foods.size(); i++) {
                            System.out.println((i + 1) + ". " + foods.get(i));
                        }
                        System.out.print("Choose a food item (1-" + foods.size() + "): ");
                        int foodChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (foodChoice >= 1 && foodChoice <= foods.size()) {
                            orderService.addFoodOrder(foodChoice - 1);
                        } else {
                            System.out.println("Invalid food selection.");
                        }
                    }
                    break;
                case 4:
                    List<String> orders = orderService.getOrders();
                    if (orders.isEmpty()) {
                        System.out.println("No orders found.");
                    } else {
                        System.out.println("Current Orders:");
                        for (String order : orders) {
                            System.out.println(order);
                        }
                    }
                    break;
                case 5:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a number between 1 and 5.");
                    break;
            }
        }
        scanner.close();
    }
}