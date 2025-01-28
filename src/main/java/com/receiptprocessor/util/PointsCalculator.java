package com.receiptprocessor.util;

import com.receiptprocessor.model.Receipt;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class PointsCalculator {

        public int calculatePoints(Receipt receipt) {
            AtomicInteger points = new AtomicInteger(0);

            points.addAndGet(receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length());
            if (receipt.getTotal().matches("\\d+\\.00")) {
                points.addAndGet(50);
            }
            double total = Double.parseDouble(receipt.getTotal());
            if (total % 0.25 == 0) {
                points.addAndGet(25);
            }
            points.addAndGet((receipt.getItems().size() / 2) * 5);
            receipt.getItems().forEach(item -> {
                String trimmedDescription = item.getShortDescription().trim();
                if (trimmedDescription.length() % 3 == 0) {
                    double price = Double.parseDouble(item.getPrice());
                    points.addAndGet((int) Math.ceil(price * 0.2));
                }
            });
            String day = receipt.getPurchaseDate().split("-")[2];
            if (Integer.parseInt(day) % 2 != 0) {
                points.addAndGet(6);
            }
            String[] timeParts = receipt.getPurchaseTime().split(":");
            int hour = Integer.parseInt(timeParts[0]);
            int minute = Integer.parseInt(timeParts[1]);
            if (hour == 14 || (hour == 15 && minute == 0)) {
                points.addAndGet(10);
            }

            return points.get();
        }
    }

