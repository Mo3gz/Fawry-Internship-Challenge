package org.example.model;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Product, Integer> items;

    public ShoppingCart() {
        this.items = new HashMap<>();
    }

    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Not enough stock for " + product.getName() +
                    ". Available: " + product.getQuantity() + ", Requested: " + quantity);
        }

        if (items.containsKey(product)) {
            int totalQuantity = items.get(product) + quantity;
            if (totalQuantity > product.getQuantity()) {
                throw new IllegalArgumentException("Not enough stock for " + product.getName() +
                        ". Available: " + product.getQuantity() + ", Total requested: " + totalQuantity);
            }
            items.put(product, totalQuantity);
        } else {
            items.put(product, quantity);
        }
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            subtotal += entry.getKey().getPrice() * entry.getValue();
        }
        return subtotal;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}

