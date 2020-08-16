package com.salesman.service.data;

import com.salesman.model.Customer;

import java.util.HashMap;

public class CustomerData {

    private HashMap<String, Customer> customerHashMap;

    public CustomerData() {
        customerHashMap = new HashMap<>();
    }

    public void addCustomer(Customer customer) {
        customerHashMap.put(customer.getCnpj(), customer);
    }

    public int getTotalCustomers() {
        return customerHashMap.size();
    }

    public void clearList() {
        customerHashMap.clear();
    }
}
