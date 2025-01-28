package com.receiptprocessor.service;

import com.receiptprocessor.model.Receipt;
import com.receiptprocessor.repository.ReceiptRepository;
import com.receiptprocessor.util.PointsCalculator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final PointsCalculator pointsCalculator;

    public ReceiptService(ReceiptRepository receiptRepository, PointsCalculator pointsCalculator) {
        this.receiptRepository = receiptRepository;
        this.pointsCalculator = pointsCalculator;
    }

    public String processReceipt(Receipt receipt) {
        receipt.setId(UUID.randomUUID().toString());
        receipt.setPoints(pointsCalculator.calculatePoints(receipt));
        receiptRepository.save(receipt);
        return receipt.getId();
    }

    public int getPoints(String receiptId) {
        Receipt receipt = receiptRepository.findById(receiptId);
        if (receipt == null) {
            throw new IllegalArgumentException("Receipt not found");
        }
        return receipt.getPoints();
    }
}
