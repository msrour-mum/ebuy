package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
