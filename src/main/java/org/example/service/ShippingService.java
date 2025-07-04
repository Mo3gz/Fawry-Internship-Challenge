package org.example.service;

import org.example.model.Shippable;

import java.util.List;
import java.util.Map;

public class ShippingService {

    public static void ship(List<Map.Entry<Shippable, Integer>> shippableItems) {
        if (shippableItems.isEmpty()) {
            return;
        }

        System.out.println("** Shipment notice **");
        double totalWeight = 0;

        for (Map.Entry<Shippable, Integer> entry : shippableItems) {
            Shippable item = entry.getKey();
            int quantity = entry.getValue();
            double itemTotalWeight = item.getWeight() * quantity;
            totalWeight += itemTotalWeight;

            System.out.println(quantity + "x " + item.getName());
            System.out.println((int)(itemTotalWeight * 1000) + "g");
        }

        System.out.println("Total package weight " + totalWeight + "kg");
    }

    public static double calculateShippingFee(List<Map.Entry<Shippable, Integer>> shippableItems) {
        double totalWeight = 0;
        for (Map.Entry<Shippable, Integer> entry : shippableItems) {
            totalWeight += entry.getKey().getWeight() * entry.getValue();
        }
        return totalWeight * 30;
    }
}