package com.salesman.service.salesman;

import com.salesman.model.Salesman;
import com.salesman.service.DataReaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SalesmanService {

    private final SalesmanData salesmanData;
    private final SalesmanDataAnalysis salesmanDataAnalysis;
    private Logger logger = LoggerFactory.getLogger(SalesmanService.class);
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
        logger.info("Getting Total Salesmen");
        return salesmanData.getTotalSalesmen();
    }

    public Salesman getSalesmanByName(String name) {
        return salesmanData.getSalesmanByName(name);
    }

    public void clearList() {
        logger.info("Clearing list  Salesman");
        salesmanData.clearList();
    }
}
