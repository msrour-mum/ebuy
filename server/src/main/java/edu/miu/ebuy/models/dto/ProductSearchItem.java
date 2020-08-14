package edu.miu.ebuy.models.dto;

import lombok.Data;

@Data
public class ProductSearchItem {

    private String productName;
    private String vendorName;
    private String categoryName;
    private double priceFrom;
    private double priceTo;


    public void fitParameters()
    {
        if (priceTo==0)
            priceTo=10000000;

    }

    public String getProductName() {
        return productName;
    }

    public ProductSearchItem setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getVendorName() {
        return vendorName;
    }

    public ProductSearchItem setVendorName(String vendorName) {
        this.vendorName = vendorName;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public ProductSearchItem setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public double getPriceFrom() {
        return priceFrom;
    }

    public ProductSearchItem setPriceFrom(double priceFrom) {
        this.priceFrom = priceFrom;
        return this;
    }

    public double getPriceTo() {
        return priceTo;
    }

    public ProductSearchItem setPriceTo(double priceTo) {
        this.priceTo = priceTo;
        return this;
    }
}
