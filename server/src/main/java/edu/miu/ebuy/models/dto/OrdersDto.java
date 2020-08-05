package edu.miu.ebuy.models.dto;

import edu.miu.ebuy.models.Order;
import lombok.Data;

import java.util.Date;
@Data
public class OrdersDto {

    private long id;
    private String  userName;
    private Date orderDate;
    private double total;
    private double shipping;
    private double netTotal;

    public OrdersDto(long id, String userName, Date orderDate, double total, double shipping) {
        this.id = id;
        this.userName = userName;
        this.orderDate = orderDate;
        this.total = total;
        this.shipping = shipping;
        this.netTotal =  total+shipping;
    }


    public static OrdersDto read(Order or)
    {
        OrdersDto ordersDto = new OrdersDto(or.getId(),or.getUser().getName(),or.getOrderDate(),or.getTotal(),or.getShipping());



        return ordersDto;
    }
}
