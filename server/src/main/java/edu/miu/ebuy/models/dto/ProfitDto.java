package edu.miu.ebuy.models.dto;

import lombok.Data;

@Data
public class ProfitDto {
    private Integer productId;
    private String productName;
    private Integer vendorId;
    private String vendorName;
    private String categoryName;
    private double profit;

    public ProfitDto(Integer productId, String productName, Integer vendorId, String vendorName, String categoryName, double profit) {
        this.productId = productId;
        this.productName = productName;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.categoryName = categoryName;
        this.profit = profit;
    }

    public Integer getProductId() {
        return productId;
    }

    public ProfitDto setProductId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public ProfitDto setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public ProfitDto setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
        return this;
    }

    public String getVendorName() {
        return vendorName;
    }

    public ProfitDto setVendorName(String vendorName) {
        this.vendorName = vendorName;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public ProfitDto setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public double getProfit() {
        return profit;
    }

    public ProfitDto setProfit(double profit) {
        this.profit = profit;
        return this;
    }
}
