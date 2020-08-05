package edu.miu.ebuy.models.dto;

import edu.miu.ebuy.models.OrderItem;
import lombok.Data;

@Data
public class OrdersItemsDto {

    private long id;

//    private long orderId;
//    private String  orderUserName;
//    private Date orderDate;
//    private double orderTotal;
//    private double orderShipping;
//    private double orderNetTotal;

    private String  productName;
    private double  productPrice;
    private double  productCost;
    private String  productVendor;
    private String  productDescription;


    private int quantity;
    private double itemTotal;

    private double ItemProfit;

    public static OrdersItemsDto read(OrderItem item)
    {
        OrdersItemsDto o = new OrdersItemsDto();
        o.id=item.getId();
        o.ItemProfit=item.getQuantity() * ( item.getProduct().getPrice()-item.getProduct().getCost());
        o.itemTotal=item.getQuantity() * item.getProduct().getPrice();
        o.productCost=item.getProduct().getCost();
        o.productDescription=item.getProduct().getDescription();
        o.productName=item.getProduct().getName();
        o.productPrice=item.getProduct().getPrice();
        o.productVendor=item.getProduct().getUser().getName();
        o.quantity=item.getQuantity();
        return o;
    }

}
