package edu.miu.ebuy.controllers;

import edu.miu.ebuy.common.http.BaseResponse;
import edu.miu.ebuy.common.http.ResponseResult;
import edu.miu.ebuy.services.interfaces.IReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@BaseResponse
@RequestMapping(value = "/api/reports")
public class ReportsController {
    @Autowired
    private IReportService reportService;


    @GetMapping("/{userId}/orders")
    public ResponseResult generateOrdersReport(@PathVariable int  userId) throws FileNotFoundException, JRException {
        return reportService.OrderReport(userId);

    }
    @GetMapping("/{vendorId}/profits")
    public ResponseResult generateProfitReport(@PathVariable int vendorId) throws FileNotFoundException, JRException {
        return reportService.profitReport(vendorId);
    }
}
