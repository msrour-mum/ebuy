package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.OrderItemReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemReportRepository extends JpaRepository<OrderItemReport, Long> {
    @Query( value="SELECT * from vworderitemdetails WHERE user_id =?1" ,nativeQuery= true)
    public List<OrderItemReport> findByUserId(int userId);
}
