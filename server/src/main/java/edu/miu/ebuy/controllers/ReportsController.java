package edu.miu.ebuy.controllers;

import edu.miu.ebuy.common.http.BaseResponse;
import edu.miu.ebuy.dao.OrderItemReportRepository;
import edu.miu.ebuy.dao.OrderReportRepository;
import edu.miu.ebuy.dao.ProductRepository;
import edu.miu.ebuy.dao.ProfitDtoRepository;
import edu.miu.ebuy.models.Product;
import edu.miu.ebuy.models.dto.OrderItemReport;
import edu.miu.ebuy.models.dto.OrderReport;
import edu.miu.ebuy.models.dto.ProfitDto;
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



    @GetMapping("/{userId}/orders")
    public String generateOrdersReport(@PathVariable int  userId) throws FileNotFoundException, JRException {
        return reportService.OrderReport(userId);

    }


    @GetMapping("/{vendorId}/profits")
    public String generateProfitReport(@PathVariable int vendorId) throws FileNotFoundException, JRException {
        return reportService.profitReport(vendorId);
    }

    @GetMapping("/{userId}/orderDetails")
    public String generateOrderDetailsReport(@PathVariable int userId) throws FileNotFoundException, JRException {
        return reportService.orderItemReport(userId);

    }





}
