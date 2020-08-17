package com.salesman.service.sale;

import com.salesman.model.Sales;
import com.salesman.model.Salesman;
import com.salesman.service.salesman.SalesmanData;
import com.salesman.service.salesman.SalesmanDataAnalysis;
import com.salesman.service.salesman.SalesmanService;
import com.salesman.stub.SalesStub;
import com.salesman.stub.SalesmanStub;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SalesServiceTest {
    private static final String SEPARATOR = "ç";
    private SalesmanData salesmanData;
    private SalesmanDataAnalysis salesmanDataAnalysis;
    private SalesData salesData;
    private SalesDataAnalysis salesDataAnalysis;
    private SalesService salesService;
    private SalesmanService salesmanService;

    @Before
    public void setUp() {
        salesmanData = new SalesmanData();
        salesmanDataAnalysis = new SalesmanDataAnalysis();
        salesmanService = new SalesmanService(salesmanData, salesmanDataAnalysis);
        salesData = new SalesData();
        salesDataAnalysis = new SalesDataAnalysis(salesmanService);
        salesService = new SalesService(salesData, salesDataAnalysis);
    }

    @Test
    public void processLine() {
        String[] salesList = SalesStub.createTwoLines();
        Salesman salesman = new Salesman("1234567891234", "Pedro", 40000.99);
        Salesman salesman2 = new Salesman("3245678865434", "Paulo", 40000.99);
        Sales sales = new Sales(8, salesman, "Pedro");

        String[] salesmanList = SalesmanStub.createTwoLines();

        salesmanService.processLine(salesmanList[0].split(SEPARATOR));
        salesmanService.processLine(salesmanList[1].split(SEPARATOR));

        Sales result = salesService.processLine(salesList[0].split(SEPARATOR));
        salesService.processLine(salesList[1].split(SEPARATOR));

        long resultMostExpansive = salesService.mostExpensiveSaleId();
        String resultWorsSalesman =  salesService.worstSalesmanName();

        assertThat(result, instanceOf(Sales.class));
        assertEquals(sales.getId(), result.getId());
        assertEquals(sales.getSalesmanName(), result.getSalesmanName());
        assertEquals(8,resultMostExpansive);
        assertEquals("Paulo",resultWorsSalesman);

        assertThat(result, instanceOf(Sales.class));
    }
}