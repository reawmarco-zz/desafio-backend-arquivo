package com.salesman.service;

import com.salesman.model.Sales;
import com.salesman.model.Salesman;
import com.salesman.service.customer.CustomerData;
import com.salesman.service.customer.CustomerDataAnalysis;
import com.salesman.service.customer.CustomerService;
import com.salesman.service.sale.SalesData;
import com.salesman.service.sale.SalesDataAnalysis;
import com.salesman.service.sale.SalesService;
import com.salesman.service.salesman.SalesmanData;
import com.salesman.service.salesman.SalesmanDataAnalysis;
import com.salesman.service.salesman.SalesmanService;
import com.salesman.stub.SalesmanStub;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Before
    public void setUp() {

        salesmanData = mock(SalesmanData.class);
        salesmanDataAnalysis = mock(SalesmanDataAnalysis.class);
        salesmanService = new SalesmanService(salesmanData, salesmanDataAnalysis);

        customerData = mock(CustomerData.class);
        customerDataAnalysis = mock(CustomerDataAnalysis.class);
        customerService = new CustomerService(customerData, customerDataAnalysis);

        salesData = mock(SalesData.class);
        salesDataAnalysis = mock(SalesDataAnalysis.class);
        salesService = new SalesService(salesData, salesDataAnalysis);

        dataReaderService = new DataReaderService(customerService, salesmanService, salesService, SEPARATOR);
    }

    @Test
    public void processData() {
        String[] strSalesman = SalesmanStub.createOneLine();

        Salesman salesman = new Salesman("1234567891234", "Pedro", 40000.99);;

        when(salesmanDataAnalysis.processLine(strSalesman)).thenReturn(salesman);

        Salesman result = (Salesman) salesmanDataAnalysis.processLine(strSalesman);
        assertThat(result, instanceOf(Salesman.class));
        assertEquals(result, salesman);
    }
}