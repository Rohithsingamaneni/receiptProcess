package com.receiptprocessor.repository;


import com.receiptprocessor.model.Receipt;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ReceiptRepository {
    private final ConcurrentHashMap<String, Receipt> receiptStore = new ConcurrentHashMap<>();

    public void save(Receipt receipt) {
        receiptStore.put(receipt.getId(), receipt);
    }

    public Receipt findById(String id) {
        return receiptStore.get(id);
    }

    public boolean existsById(String id) {
        return receiptStore.containsKey(id);
    }
}
