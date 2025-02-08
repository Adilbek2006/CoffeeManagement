package com.coffee.models;

public class Food implements Item {
    private String name;
    private double price;
    private String size;

    public Food(String name, double price, String size) {
        this.name = name;
        this.price = price;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getSize() {
        return size;
    }

    @Override
    public String getType() {
        return "food";
    }

    @Override
    public String toString() {
        return size + " " + name + " ($" + price + ")";
    }
}