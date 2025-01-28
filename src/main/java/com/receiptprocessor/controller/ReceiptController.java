package com.receiptprocessor.controller;


import com.receiptprocessor.dto.ReceiptDTO;
import com.receiptprocessor.model.Item;
import com.receiptprocessor.model.Receipt;
import com.receiptprocessor.service.ReceiptService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public ResponseEntity<?> processReceipt(@Valid @RequestBody ReceiptDTO receiptDTO) {
        String receiptId = receiptService.processReceipt(convertToReceipt(receiptDTO));
        return ResponseEntity.ok(Map.of("id", receiptId));
    }


    @GetMapping("/{id}/points")
    public ResponseEntity<?> getPoints(@PathVariable String id) {
        int points = receiptService.getPoints(id);
        return ResponseEntity.ok(Map.of("points", points));
    }


    private Receipt convertToReceipt(ReceiptDTO receiptDTO) {
        Receipt receipt = new Receipt();
        receipt.setRetailer(receiptDTO.getRetailer());
        receipt.setPurchaseDate(receiptDTO.getPurchaseDate());
        receipt.setPurchaseTime(receiptDTO.getPurchaseTime());
        receipt.setTotal(receiptDTO.getTotal());
        receipt.setItems(receiptDTO.getItems().stream().map(itemDTO -> {
            var item = new Item();
            item.setShortDescription(itemDTO.getShortDescription());
            item.setPrice(itemDTO.getPrice());
            return item;
        }).toList());
        return receipt;
    }
}
