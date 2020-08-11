//package edu.miu.ebuy.dao;
//
//
//import edu.miu.ebuy.models.dto.OrderReport;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//
//public interface OrderReportRepository extends JpaRepository<OrderReport, Long> {
//    @Query( value="SELECT * FROM vwOrders WHERE userId=?1" ,nativeQuery= true)
//    List<OrderReport> findByUserId(int userId);
//}
