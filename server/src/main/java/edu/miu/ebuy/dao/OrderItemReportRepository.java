//package edu.miu.ebuy.dao;
//
//
//
//import edu.miu.ebuy.models.dto.OrderItemReport;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//
//public interface OrderItemReportRepository extends JpaRepository<OrderItemReport, Long> {
//    @Query( value="SELECT * FROM vwOrderItemDetails WHERE userid=?1" ,nativeQuery= true)
//    List<OrderItemReport> findByUserId(int userId);
//}
