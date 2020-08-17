package com.salesman.service.customer;

import com.salesman.model.Customer;
import com.salesman.service.sale.SalesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerData customerData;
    private final CustomerDataAnalysis customerDataAnalysis;
    private Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public CustomerService(CustomerData customerData, CustomerDataAnalysis customerDataAnalysis) {
        this.customerData = customerData;
        this.customerDataAnalysis = customerDataAnalysis;
    }

    public Customer processLine(String[] line) {
        Customer customer = (Customer) customerDataAnalysis.processLine(line);
        addCustomer(customer);
        return customer;
    }

    private Customer addCustomer(Customer customer) {
        return customerData.addCustomer(customer);
    }

    public int getTotalCustomers() {
        return customerData.getTotalCustomers();
    }

    public void clearList() {
        logger.info("Clearing list  Customer");
        customerData.clearList();
    }
}
