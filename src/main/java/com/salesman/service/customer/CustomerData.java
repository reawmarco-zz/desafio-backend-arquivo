package com.salesman.service.customer;

import com.salesman.model.Customer;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CustomerData {

    private final HashMap<String, Customer> customerHashMap;

    public CustomerData() {
        customerHashMap = new HashMap<>();
    }

    public Customer addCustomer(Customer customer) {
        return customerHashMap.put(customer.getCnpj(), customer);
    }

    public int getTotalCustomers() {
        return customerHashMap.size();
    }

    public void clearList() {
        customerHashMap.clear();
    }
}
