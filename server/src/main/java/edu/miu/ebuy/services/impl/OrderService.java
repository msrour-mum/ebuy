package edu.miu.ebuy.services.impl;

import edu.miu.ebuy.dao.OrderRepository;
import edu.miu.ebuy.dao.ProductRepository;
import edu.miu.ebuy.models.Order;
import edu.miu.ebuy.models.Product;
import edu.miu.ebuy.models.dto.ProductDto;
import edu.miu.ebuy.services.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
    @Override
    public List<Order> getAll(int userId) {
        return orderRepository.findByUserId(userId);
    }



    @Override
    public Order get(long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return  orderRepository.save(order);
    }
}
