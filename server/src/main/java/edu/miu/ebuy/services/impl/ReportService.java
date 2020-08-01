package edu.miu.ebuy.services.impl;

import edu.miu.ebuy.dao.OrderItemReportRepository;
import edu.miu.ebuy.dao.OrderRepository;
import edu.miu.ebuy.models.Order;
import edu.miu.ebuy.models.OrderItemReport;
import edu.miu.ebuy.services.interfaces.IReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService implements IReportService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private OrderItemReportRepository orderItemReportRepository;


  private   String  path =System.getProperty("user.home")+"\\Downloads";

   public String  exportOrderReport(int userId) throws FileNotFoundException, JRException {


       List<Order> orders = orderRepository.findByUserId(userId);


       //load file and compile it
       File file1 = ResourceUtils.getFile("classpath:orderReport.jrxml");
       JasperReport jasperReport = JasperCompileManager.compileReport(file1.getAbsolutePath());

       JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orders);

       Map<String, Object> parameters = new HashMap<>();
       parameters.put("createdBy", "EBUY ECOMMERCE");

       JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

      //generate pdf
       JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\orders.pdf");

       return "report generated in path : " + path;

   }

    public String  exportOrderItemReport(int userId) throws FileNotFoundException, JRException {

        List<OrderItemReport> orderItems=  orderItemReportRepository.findByUserId(userId);

        //load file and compile it
        File file2 = ResourceUtils.getFile("classpath:orderdetailsReport.jrxml");
        JasperReport jasperReport2 = JasperCompileManager.compileReport(file2.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orderItems);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "EBUY ECOMMERCE");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport2, parameters, dataSource);

        //generate pdf
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\orderDetails.pdf");

        return "report generated in path : " + path;

    }
}
