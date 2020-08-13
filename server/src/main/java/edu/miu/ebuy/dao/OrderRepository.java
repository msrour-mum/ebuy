package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query( value="SELECT * FROM orders WHERE userid=?1" ,nativeQuery= true)
    public List<Order> findByUserId(int userId);
}
