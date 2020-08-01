package edu.miu.ebuy.controllers;

import edu.miu.ebuy.common.http.BaseResponse;
import edu.miu.ebuy.dao.OrderItemReportRepository;
import edu.miu.ebuy.models.OrderItemReport;
import edu.miu.ebuy.services.interfaces.IReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@BaseResponse
@RequestMapping(value = "/api/reports")
public class ReportsController {
    @Autowired
    private IReportService reportService;
    @Autowired
    private OrderItemReportRepository orderItemReportRepository;

    @GetMapping("/{userId}/orders")
    public String generateOrdersReport(@PathVariable int  userId) throws FileNotFoundException, JRException {
        return reportService.exportOrderReport(userId);



    }

    @GetMapping("/{userId}/test")
    public List<OrderItemReport> retriveOrderItemsbyUserID(@PathVariable int userId){
        return  orderItemReportRepository.findByUserId(userId);
    }
    @GetMapping("/{userId}/orderDetails")
    public String generateOrderDetailsReport(@PathVariable int  userId) throws FileNotFoundException, JRException {
        return reportService.exportOrderItemReport(userId);
    }
}
