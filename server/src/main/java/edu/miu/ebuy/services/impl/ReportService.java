package edu.miu.ebuy.services.impl;

import edu.miu.ebuy.dao.*;
import edu.miu.ebuy.models.Order;

import edu.miu.ebuy.models.Product;
import edu.miu.ebuy.models.dto.*;
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
    //@Autowired
    //OrderReportRepository orderReportRepository;
//    @Autowired
//    private OrderItemReportRepository orderItemReportRepository;

//    @Autowired
//    private ProfitDtoRepository profitDtoRepository;


    @Autowired
    OrderRepository orderRepository;
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


    public String  profitReport(int vendorId) throws FileNotFoundException, JRException {

        List<Product> productList =  productRepository.reportProfit(vendorId);

        List<ProfitDto> lstProfit = new ArrayList<>();
        for (Product product : productList)
        {
            ProfitDto productDto =
                    new ProfitDto(product.getId(),product.getName(),
                            product.getUser().getId(),
                            product.getUser().getName(),
                            product.getCategory().getName(),product.getPrice(),
                            product.getCost(),
            product.getPrice());

            lstProfit.add(productDto);
        }

        //load file
        File fileProfit = ResourceUtils.getFile("classpath:profitsReport.jrxml");

        // compile it
        JasperReport jasperReportProfits = JasperCompileManager.compileReport(fileProfit.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstProfit );

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("logo", ClassLoader.getSystemResource("cart.png").getPath());


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReportProfits, params, dataSource);

        //generate pdf
        String fileName = path + "\\VendorProfits"+new Random().nextInt(9500000) +".pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);

        return "report generated in path : " + path;

    }
    public String  orderItemReport(int userId) throws FileNotFoundException, JRException{
        return  "not impplemneted";
    }

//    public String  orderItemReport(int userId) throws FileNotFoundException, JRException {
//
//        List<OrderItemReport> orderItems =  orderItemReportRepository.findByUserId(userId);
//        if(orderItems.isEmpty()){
//            return "User Id doesnot exist";
//        }
//
//        //load file
//        File fileOrderItem = ResourceUtils.getFile("classpath:orderItemsReport.jrxml");
//
//        // compile it
//        JasperReport jasperOrderItems = JasperCompileManager.compileReport(fileOrderItem.getAbsolutePath());
//
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orderItems);
//
//        HashMap<String, Object> params = new HashMap<String, Object>();
//        params.put("logo", ClassLoader.getSystemResource("cart.png").getPath());
//
//
//
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperOrderItems, params, dataSource);
//
//        //generate pdf
//        String fileName = path + "\\OrderItemsReport"+new Random().nextInt(9500000) +".pdf";
//        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
//
//        return "report generated in path : " + path;
//
//    }
}
