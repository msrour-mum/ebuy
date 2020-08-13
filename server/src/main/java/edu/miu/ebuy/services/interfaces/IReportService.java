package edu.miu.ebuy.services.interfaces;

import edu.miu.ebuy.common.http.ResponseResult;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;

public interface IReportService {
    public ResponseResult  OrderReport(int userId) throws FileNotFoundException, JRException;

    //public String  orderItemReport(int userId) throws FileNotFoundException, JRException;

    public ResponseResult profitReport(int userId) throws FileNotFoundException, JRException;
}