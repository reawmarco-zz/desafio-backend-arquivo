package com.salesman.service.customer;

import com.salesman.model.Customer;
import com.salesman.stub.CustomerStub;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CustomerServiceTest {

    private static final String SEPARATOR = "ç";
    private CustomerService customerService;

    @Before
    public void setUp() {
        CustomerData customerData = new CustomerData();
        CustomerDataAnalysis customerDataAnalysis = new CustomerDataAnalysis();
        customerService = new CustomerService(customerData, customerDataAnalysis);
    }

    @Test
    public void processLine() {
        String[] customerList = CustomerStub.createOneLine();
        Customer customer = new Customer("2345675434544345", "Jose da Silva", "Rural");

        String[] strCustomer = customerList[0].split(SEPARATOR);
        Customer result = customerService.processLine(strCustomer);
        int resultSize = customerService.getTotalCustomers();

        assertThat(result, instanceOf(Customer.class));
        assertEquals(customer.getName(), result.getName());
        assertEquals(customer.getBusinessArea(), result.getBusinessArea());
        assertEquals(customer.getCnpj(), result.getCnpj());
        assertEquals(1, resultSize);
    }
}