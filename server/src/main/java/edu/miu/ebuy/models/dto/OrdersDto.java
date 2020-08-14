package edu.miu.ebuy.models.dto;

import edu.miu.ebuy.models.Order;
import edu.miu.ebuy.models.OrderItem;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrdersDto {

    private long id;
    private String  userName;
    private Date orderDate;
    private double total;
    private double shipping;
    private double netTotal;
    private String userAddress;


    List<OrdersItemsDto> lstItems = new ArrayList<>();

    public OrdersDto(long id, String userName, Date orderDate, double total, double shipping , String userAddress) {
        this.id = id;
        this.userName = userName;
        this.orderDate = orderDate;
        this.total = total;
        this.shipping = shipping;
        this.netTotal =  total+shipping;
        this.userAddress=userAddress;
    }


    public static OrdersDto read(Order or)
    {
        OrdersDto ordersDto = new OrdersDto(or.getId(),or.getUser().getName(),
                or.getOrderDate(),or.getTotal(),or.getShipping(),or.getUser().getAddress());
        return ordersDto;
    }


    public void addItem(OrdersItemsDto item)    { if (lstItems!=null)    lstItems.add(item);    }
    public void removeItem(OrdersItemsDto item)
    {
        lstItems.remove(item);
    }

    public static OrdersDto readFull(Order or)
    {
        OrdersDto ordersDto = new OrdersDto(or.getId(),or.getUser().getName(),
                or.getOrderDate(),or.getTotal(),or.getShipping(),or.getUser().getAddress());

        for (OrderItem i : or.getItems())
        {
            ordersDto.addItem(OrdersItemsDto.read(i));
        }
        return ordersDto;
    }
}
