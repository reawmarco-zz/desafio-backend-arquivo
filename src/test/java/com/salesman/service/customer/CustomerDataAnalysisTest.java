package com.salesman.service.customer;

import com.salesman.model.Customer;
import com.salesman.stub.CustomerStub;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerDataAnalysisTest {

    private CustomerDataAnalysis customerDataAnalysis;

    @Before
    public void setUp() {
        customerDataAnalysis = mock(CustomerDataAnalysis.class);
    }

    @Test
    public void processLine() {
        String[] strCustomer = CustomerStub.createOneLine();
        Customer customer = new Customer("2345675434544345", "Jose da Silva", "Rural");

        when(customerDataAnalysis.processLine(strCustomer)).thenReturn(customer);

        Customer result = (Customer) customerDataAnalysis.processLine(strCustomer);

        assertThat(result, instanceOf(Customer.class));
        assertEquals(result, customer);
    }
}
