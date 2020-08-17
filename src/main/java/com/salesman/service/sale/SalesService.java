package com.salesman.service.sale;

import com.salesman.model.Sales;
import com.salesman.service.salesman.SalesmanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SalesService {

    private final SalesData salesData;
    private final SalesDataAnalysis salesDataAnalysis;
    private Logger logger = LoggerFactory.getLogger(SalesService.class);
    public SalesService(SalesData salesData, SalesDataAnalysis salesDataAnalysis) {
        this.salesData = salesData;
        this.salesDataAnalysis = salesDataAnalysis;
    }

    public Sales processLine(String[] line) {
        Sales sales = (Sales) salesDataAnalysis.processLine(line);
        return addSales(sales);
    }

    public Sales addSales(Sales sales) {
        return salesData.addSale(sales);
    }

    public long mostExpensiveSaleId() {
        logger.info("Getting the most expensive sale id");
        return salesData.mostExpensiveSaleId();
    }

    public String worstSalesmanName() {
        logger.info("Getting the worst salesman");
        return salesData.worstSalesmanName();
    }

    public void clearList() {
        logger.info("Clearing list  Sales");
        salesData.clearList();
    }
}
