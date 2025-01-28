package com.receiptprocessor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


public class ItemDTO {
    @NotBlank(message = "Item description is required.")
    @Pattern(regexp = "^[\\w\\s\\-]+$", message = "Item description contains invalid characters.")
    private String shortDescription;

    @NotBlank(message = "Item price is required.")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Price must be in XX.XX format.")
    private String price;

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
