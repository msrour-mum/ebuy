package edu.miu.ebuy.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartIemDto {

    private int productId;
    private int quantity;
}
