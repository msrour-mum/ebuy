package edu.miu.ebuy.services.interfaces;

import edu.miu.ebuy.models.Order;

import java.util.List;

public interface IOrderService {

    List<Order> getAll();

    List<Order> getAll(int userId);

    Order get(long orderId);

    Order create(Order order);

    Order update(Order order);
}
