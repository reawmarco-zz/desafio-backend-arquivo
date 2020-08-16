package com.salesman.service.salesman;

import com.salesman.model.Salesman;
import com.salesman.stub.SalesmanStub;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SalesmanServiceTest {
    private static final String SEPARATOR = "ç";
    private SalesmanService salesmanService;

    @Before
    public void setUp() {
        SalesmanDataAnalysis salesmanDataAnalysis = new SalesmanDataAnalysis();
        SalesmanData salesmanData = new SalesmanData();
        salesmanService = new SalesmanService(salesmanData, salesmanDataAnalysis);
    }

    @Test
    public void processLine() {
        String[] salesmanList = SalesmanStub.createOneLine();
        Salesman salesman = new Salesman("1234567891234", "Pedro", 40000.99);

        String[] strSalesman = salesmanList[0].split(SEPARATOR);
        salesmanService.processLine(strSalesman);

        Salesman result = salesmanService.getSalesmanByName(salesman.getName());
        int resultSize = salesmanService.getTotalSalesmen();

        assertEquals(salesman.getName(), result.getName());
        assertEquals(salesman.getCpf(), result.getCpf());
        assertEquals(1, resultSize);
    }
}