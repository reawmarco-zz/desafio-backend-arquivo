package com.salesman.service;

import com.salesman.dto.ReportDTO;
import com.salesman.service.customer.CustomerData;
import com.salesman.service.customer.CustomerDataAnalysis;
import com.salesman.service.customer.CustomerService;
import com.salesman.service.sale.SalesData;
import com.salesman.service.sale.SalesDataAnalysis;
import com.salesman.service.sale.SalesService;
import com.salesman.service.salesman.SalesmanData;
import com.salesman.service.salesman.SalesmanDataAnalysis;
import com.salesman.service.salesman.SalesmanService;
import com.salesman.stub.CustomerStub;
import com.salesman.stub.SalesStub;
import com.salesman.stub.SalesmanStub;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DataReaderServiceTest {

    private static final String SEPARATOR = "ç";

    private SalesmanData salesmanData;
    private SalesmanDataAnalysis salesmanDataAnalysis;
    private SalesmanService salesmanService;

    private CustomerData customerData;
    private CustomerDataAnalysis customerDataAnalysis;
    private CustomerService customerService;

    private SalesData salesData;
    private SalesDataAnalysis salesDataAnalysis;
    private SalesService salesService;

    private DataReaderService dataReaderService;
    private DataResultService dataResultService;

    @Before
    public void setUp() {

        salesmanData = new SalesmanData();
        salesmanDataAnalysis = new SalesmanDataAnalysis();
        salesmanService = new SalesmanService(salesmanData, salesmanDataAnalysis);

        customerData = new CustomerData();
        customerDataAnalysis = new CustomerDataAnalysis();
        customerService = new CustomerService(customerData, customerDataAnalysis);

        salesData = new SalesData();
        salesDataAnalysis = new SalesDataAnalysis(salesmanService);
        salesService = new SalesService(salesData, salesDataAnalysis);

        dataReaderService = new DataReaderService(customerService, salesmanService, salesService, SEPARATOR);
        dataResultService = new DataResultService();
    }

    @Test
    public void processData() {
        String[] salesmanList = SalesmanStub.createTwoLines();
        String[] customerList = CustomerStub.createTwoLines();
        String[] salesList = SalesStub.createTwoLines();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(salesmanList[0])
                .append("\n")
                .append(salesmanList[1])
                .append("\n")
                .append(customerList[0])
                .append("\n")
                .append(customerList[1])
                .append("\n")
                .append(salesList[0])
                .append("\n")
                .append(salesList[1]);

        ReportDTO reportDTO = dataReaderService.processData(stringBuilder.toString());

       String resultReport = dataResultService.saveResult(reportDTO);

        assertEquals("Paulo", reportDTO.getWorstSalesmanName());
        assertEquals(2, reportDTO.getCustomerTotal());
        assertEquals(8, reportDTO.getMostExpensiveSaleId());
        assertEquals(2, reportDTO.getSalesmanTotal());
        assertEquals(reportDTO.formattedReport(), resultReport);
    }

    @Test
    public void clearLists(){
        String[] salesmanList = SalesmanStub.createTwoLines();
        String[] customerList = CustomerStub.createTwoLines();
        String[] salesList = SalesStub.createTwoLines();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(salesmanList[0])
                .append("\n")
                .append(salesmanList[1])
                .append("\n")
                .append(customerList[0])
                .append("\n")
                .append(customerList[1])
                .append("\n")
                .append(salesList[0])
                .append("\n")
                .append(salesList[1]);

        dataReaderService.processData(stringBuilder.toString());

        salesmanService.clearList();
        salesService.clearList();
        customerService.clearList();

        assertEquals("Not Found",salesService.worstSalesmanName());
        assertEquals(0, salesService.mostExpensiveSaleId());
        assertEquals(0, customerService.getTotalCustomers());
        assertEquals(0, salesmanService.getTotalSalesmen());
    }
}