package com.salesman.service.salesman;

import com.salesman.model.Salesman;
import org.springframework.stereotype.Service;

@Service
public class SalesmanService {

    private final SalesmanData salesmanData;
    private final SalesmanDataAnalysis salesmanDataAnalysis;

    public SalesmanService(SalesmanData salesmanData, SalesmanDataAnalysis salesmanDataAnalysis) {
        this.salesmanData = salesmanData;
        this.salesmanDataAnalysis = salesmanDataAnalysis;
    }

    public Salesman processLine(String[] line) {
        Salesman salesman = (Salesman) salesmanDataAnalysis.processLine(line);
        return addSalesman(salesman);
    }

    private Salesman addSalesman(Salesman salesman) {
        return salesmanData.addSalesman(salesman);
    }

    public int getTotalSalesmen() {
        return salesmanData.getTotalSalesmen();
    }

    public Salesman getSalesmanByName(String name) {
        return salesmanData.getSalesmanByName(name);
    }

    public void clearList() {
        salesmanData.clearList();
    }
}
