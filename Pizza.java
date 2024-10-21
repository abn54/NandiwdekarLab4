/**
 * Project: Lab4 Pizza Shop
 * Purpose Details: Represents a pizza object.
 * Course: IST 242
 * Author: Aayudh Nandiwdekar
 * Date Developed: 10/21/2024
 * Last Date Changed:
 * Revision:
 */

public class Pizza {
    private String size;
    private String crust;
    private String[] toppings;
    private double price;

    // Default constructor for Jackson
    public Pizza() {
    }

    public Pizza(String size, String crust, String[] toppings, double price) {
        this.size = size;
        this.crust = crust;
        this.toppings = toppings;
        this.price = price;
    }

    // Getters
    public String getSize() {
        return size;
    }

    public String getCrust() {
        return crust;
    }

    public String[] getToppings() {
        return toppings;
    }

    public double getPrice() {
        return price;
    }
}
