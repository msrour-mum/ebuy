package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(int userId);

}
