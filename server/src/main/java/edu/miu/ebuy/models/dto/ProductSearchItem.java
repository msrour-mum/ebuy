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


}
