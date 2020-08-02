package edu.miu.ebuy.services.interfaces;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;

public interface IReportService {
    public String  exportOrderReport(int userId) throws FileNotFoundException, JRException;
    public String  exportOrderItemReport(int userId) throws FileNotFoundException, JRException;
}