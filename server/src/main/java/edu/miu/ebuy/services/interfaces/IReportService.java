package edu.miu.ebuy.services.interfaces;

import edu.miu.ebuy.common.http.ResponseResult;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;

public interface IReportService {

    ResponseResult OrderReport(int userId) throws FileNotFoundException, JRException;

    ResponseResult profitReport(int userId) throws FileNotFoundException, JRException;
}
