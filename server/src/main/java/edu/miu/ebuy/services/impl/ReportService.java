package edu.miu.ebuy.services.impl;

import edu.miu.ebuy.dao.OrderItemReportRepository;
import edu.miu.ebuy.dao.OrderRepository;
import edu.miu.ebuy.dao.ProductRepository;
import edu.miu.ebuy.models.Order;

import edu.miu.ebuy.models.Product;
import edu.miu.ebuy.models.dto.OrdersDto;
import edu.miu.ebuy.models.dto.ProductDto;
import edu.miu.ebuy.models.dto.ProfitDto;
import edu.miu.ebuy.services.interfaces.IReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class ReportService implements IReportService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private OrderItemReportRepository orderItemReportRepository;
    @Autowired
    private ProductRepository productRepository;
    private   String  path =System.getProperty("user.home")+"\\Downloads";

    public String  OrderReport(int userId) throws FileNotFoundException, JRException {

       List<Order> orders = orderRepository.findByUserId(userId);
       //load file and compile it

       List<OrdersDto> lst = new ArrayList<>();
       for (Order or:orders)
       {
           //OrdersDto ordersDto = new OrdersDto(or.getId(),or.getUser().getName(),or.getOrderDate(),or.getTotal(),or.getShipping())
           OrdersDto ordersDto =OrdersDto.read(or);
           lst.add(ordersDto);
       }
       File file1 = ResourceUtils.getFile("classpath:orderReport.jrxml");
       JasperReport jasperReport = JasperCompileManager.compileReport(file1.getAbsolutePath());
       JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lst);
       Map<String, Object> parameters = new HashMap<>();
       parameters.put("createdBy", "EBUY ECOMMERCE");
       JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
      //generate pdf
       String fileName = path + "\\orders"+new Random().nextInt(9500000) +".pdf";
       JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);

       //return "report generated in path : " + path;
       return fileName;

   }


    public String  profitReport(int userId) throws FileNotFoundException, JRException {

        List<Product> productList =  productRepository.reportProfit(userId);

        List<ProfitDto> lstProfit = new ArrayList<>();
        for (Product product : productList)
        {
            ProfitDto productDto =
                    new ProfitDto(product.getId(),product.getName(),
                            product.getUser().getId(),
                            product.getUser().getName(),
                            product.getCategory().getName(),product.getPrice());

            lstProfit.add(productDto);
        }



        //load file and compile it
        File file2 = ResourceUtils.getFile("classpath:orderdetailsReport.jrxml");
        JasperReport jasperReport2 = JasperCompileManager.compileReport(file2.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstProfit);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "EBUY ECOMMERCE");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport2, parameters, dataSource);

        //generate pdf
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\orderDetails.pdf");

        return "report generated in path : " + path;

    }
}
