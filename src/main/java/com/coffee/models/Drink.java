package com.coffee.models;

public class Drink implements Item {
    private String name;
    private double price;
    private String size;

    public Drink(String name, double price, String size) {
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
        return "drink";
    }

    @Override
    public String toString() {
        return size + " " + name + " ($" + price + ")";
    }
}