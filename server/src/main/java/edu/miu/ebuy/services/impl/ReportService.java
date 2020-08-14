package edu.miu.ebuy.services.impl;

import edu.miu.ebuy.common.http.ResponseResult;
import edu.miu.ebuy.common.http.ResponseStatus;
import edu.miu.ebuy.dao.*;
import edu.miu.ebuy.models.Order;

import edu.miu.ebuy.models.Product;
import edu.miu.ebuy.models.dto.*;
import edu.miu.ebuy.services.interfaces.IReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
@Transactional

public class ReportService implements IReportService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    private   String  path ="C:\\uploads\\ebuy_uploads\\reports\\";

    public ResponseResult  OrderReport(int userId) throws FileNotFoundException, JRException {
        List<Order> orders = orderRepository.findByUserId(userId);
        if(orders==null || orders.size()==0)
        {
            return new ResponseResult(new ResponseStatus(400,""), "");
        }
        List<OrdersDto> lst = new ArrayList<>();
        for (Order or:orders)
        {
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
        String pdf= "profit"+new Random().nextInt(9500000) +".pdf";
        String fileName = path + pdf;
        String mappedFile = "http://localhost:8080/api/uploads/reports/"+pdf;
        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
        return new ResponseResult(new ResponseStatus(200,""), mappedFile);
    }


    public ResponseResult  profitReport(int vendorId) throws FileNotFoundException, JRException {

        List<Product> productList =  productRepository.reportProfit(vendorId);

        if(productList==null || productList.size()==0)
        {
            return new ResponseResult(new ResponseStatus(400,""), "");
        }
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
        String pdf= "orders"+new Random().nextInt(9500000) +".pdf";
        String fileName = path + pdf;
        String mappedFile = "http://localhost:8080/api/uploads/reports/"+pdf;
        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
        return new ResponseResult(new ResponseStatus(200,""), mappedFile);

    }
}
