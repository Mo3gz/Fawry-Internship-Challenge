package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import org.example.model.*;
import org.example.service.ShippingService;

import javax.lang.model.type.NullType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void checkout(Customer customer, ShoppingCart cart) {
        // Check if cart is empty
        if (cart.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        // Check for expired or out of stock products
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product.isExpired()) {
                throw new IllegalArgumentException("Product " + product.getName() + " has expired");
            }
            if (quantity > product.getQuantity()) {
                throw new IllegalArgumentException("Product " + product.getName() + " is out of stock. Available: "
                        + product.getQuantity() + ", Requested: " + quantity);
            }
        }

        // Calculate subtotal
        double subtotal = cart.getSubtotal();

        // Collect shippable items
        List<Map.Entry<Shippable, Integer>> shippableItems = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            if (entry.getKey() instanceof Shippable) {
                shippableItems.add(Map.entry((Shippable) entry.getKey(), entry.getValue()));
            }
        }

        // Calculate shipping fee
        double shippingFee = ShippingService.calculateShippingFee(shippableItems);
        double totalAmount = subtotal + shippingFee;

        // Check if customer has sufficient balance
        if (customer.getBalance() < totalAmount) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        // Process payment
        customer.deductBalance(totalAmount);

        // Update product quantities
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            product.setQuantity(product.getQuantity() - entry.getValue());
        }

        // Ship items if any
        if (!shippableItems.isEmpty()) {
            ShippingService.ship(shippableItems);
        }

        // Print checkout receipt
        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double totalPrice = product.getPrice() * quantity;
            System.out.println(quantity + "x " + product.getName());
            System.out.println((int) totalPrice);
        }

        System.out.println("---------------------");
        System.out.println("Subtotal\t" + (int) subtotal);
        System.out.println("Shipping\t" + (int) shippingFee);
        System.out.println("Amount\t\t" + (int) totalAmount);
    }

    public static void main(String[] args) {
        System.out.println("=== E-Commerce System Demo ===\n");

        // Create products matching the expected output
        ExpirableProduct cheese = new ExpirableProduct("Cheese", 100, 10, LocalDate.now().plusDays(7), 0.2);
        ExpirableProduct biscuits = new ExpirableProduct("Biscuits", 150, 5, LocalDate.now().plusDays(30), 0.7);
        NonExpirableProduct tv = new NonExpirableProduct("TV", 5000, 3, 15.0);
        NonExpirableProduct scratchCard = new NonExpirableProduct("Scratch Card", 50, 20, 0);

        // Create customer
        Customer customer = new Customer("John Doe", 10000);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Initial balance: $" + customer.getBalance() + "\n");

        // Create shopping cart
        ShoppingCart cart = new ShoppingCart();

        try {
            // Add items to cart following the example from PDF
            cart.add(cheese, 2);
            System.out.println("Added 2x Cheese to cart");

            cart.add(biscuits, 1);  // Adding biscuits to match expected output
            System.out.println("Added 1x Biscuits to cart");

            cart.add(scratchCard, 1);
            System.out.println("Added 1x Scratch Card to cart");

            System.out.println("\nCart subtotal: $" + cart.getSubtotal());

            // Perform checkout
            System.out.println("\n=== Checkout Process ===");
            checkout(customer, cart);

            System.out.println("\nTransaction completed successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test edge cases
        testEdgeCases();
    }

    private static void testEdgeCases() {
        System.out.println("\n\n=== Testing Edge Cases ===\n");

        // Test 1: Empty cart
        System.out.println("Test 1: Empty cart checkout");
        try {
            ShoppingCart emptyCart = new ShoppingCart();
            Customer testCustomer = new Customer("Test Customer", 1000);
            checkout(testCustomer, emptyCart);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Correctly caught error: " + e.getMessage() + "\n");
        }

        // Test 2: Insufficient balance
        System.out.println("Test 2: Insufficient balance");
        try {
            ShoppingCart cart = new ShoppingCart();
            NonExpirableProduct expensiveTV = new NonExpirableProduct("Expensive TV", 10000, 1, 20.0);
            cart.add(expensiveTV, 1);
            Customer poorCustomer = new Customer("Poor Customer", 100);
            checkout(poorCustomer, cart);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Correctly caught error: " + e.getMessage() + "\n");
        }

        // Test 3: Expired product
        System.out.println("Test 3: Expired product");
        try {
            ShoppingCart cart = new ShoppingCart();
            ExpirableProduct expiredCheese = new ExpirableProduct("Expired Cheese", 100, 5, LocalDate.now().minusDays(1), 0.2);
            cart.add(expiredCheese, 1);
            Customer testCustomer = new Customer("Test Customer", 1000);
            checkout(testCustomer, cart);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Correctly caught error: " + e.getMessage() + "\n");
        }

        // Test 4: Out of stock
        System.out.println("Test 4: Out of stock");
        try {
            ShoppingCart cart = new ShoppingCart();
            NonExpirableProduct limitedMobile = new NonExpirableProduct("Limited Mobile", 1000, 2, 0);
            cart.add(limitedMobile, 5);  // Trying to add more than available
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Correctly caught error: " + e.getMessage() + "\n");
        }
    }
}