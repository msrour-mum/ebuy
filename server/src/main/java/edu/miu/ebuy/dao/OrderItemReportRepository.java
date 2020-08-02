package edu.miu.ebuy.dao;


import edu.miu.ebuy.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemReportRepository extends JpaRepository<Order, Long> {
    @Query( value="SELECT * from Order WHERE user_id =?1" ,nativeQuery= true)
    public List<Order> findByUserId(int userId);
}
