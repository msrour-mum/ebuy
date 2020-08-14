package edu.miu.ebuy.services.interfaces;

import edu.miu.ebuy.exceptions.ApplicationException;
import edu.miu.ebuy.exceptions.HttpException;
import edu.miu.ebuy.models.OrderItem;
import edu.miu.ebuy.models.Product;
import edu.miu.ebuy.models.User;
import edu.miu.ebuy.models.dto.CheckoutDto;
import edu.miu.ebuy.models.dto.OrdersDto;

import java.util.List;

public interface IShoppingService {

    boolean checkout(CheckoutDto checkout) throws ApplicationException;
    Product subscribeVendor(User user) throws HttpException;
    void addOrder(OrderItem orderItem, User user, double shipping);
    List<OrdersDto> userOrder(int userId);
}
