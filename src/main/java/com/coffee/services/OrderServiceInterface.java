package com.coffee.services;

public interface OrderServiceInterface {
    void viewMenu();
    void addDrinkOrder(int index);
    void addFoodOrder(int index);
    void addDrink(String name, double price, String size);
    void addFood(String name, double price, String size); // ✅ Добавляем метод в интерфейс
}
