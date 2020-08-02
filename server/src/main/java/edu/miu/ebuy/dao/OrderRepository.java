package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Query( value="SELECT * FROM orders WHERE userid=?1" ,nativeQuery= true)
    public List<Orders> findByUserId(int userId);
}
