package com.salesman.service.salasman;

import com.salesman.model.Salesman;
import org.springframework.stereotype.Service;

@Service
public class SalesmanService {

    public final SalesmanData salesmanData;

    public final SalesmanDataAnalysis salesmanDataAnalysis;

    public SalesmanService(SalesmanData salesmanData, SalesmanDataAnalysis salesmanDataAnalysis) {
        this.salesmanData = salesmanData;
        this.salesmanDataAnalysis = salesmanDataAnalysis;
    }

    public Salesman processLine(String[] line) {
        Salesman salesman = (Salesman) salesmanDataAnalysis.processLine(line);
        addSalesman(salesman);
        return salesman;
    }

    private void addSalesman(Salesman salesman) {
        salesmanData.addSalesman(salesman);
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
