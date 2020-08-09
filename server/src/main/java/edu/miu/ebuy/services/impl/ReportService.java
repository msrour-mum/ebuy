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
    @Autowired
    OrderReportRepository orderReportRepository;
    @Autowired
    private OrderItemReportRepository orderItemReportRepository;

    @Autowired
    private ProfitDtoRepository profitDtoRepository;

    private   String  path =System.getProperty("user.home")+"\\Downloads";




    public String  OrderReport(int userId) throws FileNotFoundException, JRException {

        List<OrderReport> orders = orderReportRepository.findByUserId(userId);

        if(orders.isEmpty()){
            return "User Id doesnot exit";
        }
        //load file
        File file1 = ResourceUtils.getFile("classpath:orderReport.jrxml");

        //compile it
        JasperReport jasperReport = JasperCompileManager.compileReport(file1.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orders);

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("logo", ClassLoader.getSystemResource("cart.png").getPath());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

        //generate pdf
        String fileName = path + "\\orders"+new Random().nextInt(9500000) +".pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);

        return "report generated in path : " + path;
    }


    public String  profitReport(int vendorId) throws FileNotFoundException, JRException {

        List<ProfitDto> productList =  profitDtoRepository.findByVendorId(vendorId);
        System.out.print (productList);
        if(productList.isEmpty()){
            return "vendor Id doesn't exist";
        }

        //load file
        File fileProfit = ResourceUtils.getFile("classpath:profitsReport.jrxml");

        // compile it
        JasperReport jasperReportProfits = JasperCompileManager.compileReport(fileProfit.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(productList );

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("logo", ClassLoader.getSystemResource("cart.png").getPath());


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReportProfits, params, dataSource);

        //generate pdf
        String fileName = path + "\\VendorProfits"+new Random().nextInt(9500000) +".pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);

        return "report generated in path : " + path;

    }


    public String  orderItemReport(int userId) throws FileNotFoundException, JRException {

        List<OrderItemReport> orderItems =  orderItemReportRepository.findByUserId(userId);
        if(orderItems.isEmpty()){
            return "User Id doesnot exist";
        }

        //load file
        File fileOrderItem = ResourceUtils.getFile("classpath:orderItemsReport.jrxml");

        // compile it
        JasperReport jasperOrderItems = JasperCompileManager.compileReport(fileOrderItem.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orderItems);

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("logo", ClassLoader.getSystemResource("cart.png").getPath());



        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperOrderItems, params, dataSource);

        //generate pdf
        String fileName = path + "\\OrderItemsReport"+new Random().nextInt(9500000) +".pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);

        return "report generated in path : " + path;

    }
}
