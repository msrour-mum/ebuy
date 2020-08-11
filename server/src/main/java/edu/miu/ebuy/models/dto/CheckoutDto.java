package edu.miu.ebuy.models.dto;

import edu.miu.ebuy.models.UserCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutDto {

    private List<CartIemDto> items;
    private UserCard card;
    private CheckoutOptionsDto checkoutOptions;
    private String address;
    private double shipping;
    private double tax;
}
