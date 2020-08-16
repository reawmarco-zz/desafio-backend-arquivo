package com.salesman.service.sale;

import com.salesman.model.Sales;
import org.springframework.stereotype.Service;

@Service
public class SalesService {

    private final SalesData salesData;
    private final SalesDataAnalysis salesDataAnalysis;

    public SalesService(SalesData salesData, SalesDataAnalysis salesDataAnalysis) {
        this.salesData = salesData;
        this.salesDataAnalysis = salesDataAnalysis;
    }

    public Sales processLine(String[] line) {
        Sales sales = (Sales) salesDataAnalysis.processLine(line);
        addSales(sales);
        return sales;
    }

    public void addSales(Sales sales) {
        salesData.addSale(sales);
    }

    public long mostExpensiveSaleId() {
        return salesData.mostExpensiveSaleId();
    }

    public String worstSalesmanName() {
        return salesData.worstSalesmanName();
    }

    public void clearList() {
        salesData.clearList();
    }
}
