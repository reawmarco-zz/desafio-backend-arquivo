package com.salesman.service.data;

import com.salesman.model.Salesman;

import java.util.HashMap;

public class SalesmanData {

    private HashMap<String, Salesman> salesmanHashMap;

    public SalesmanData() {
        salesmanHashMap = new HashMap<>();
    }

    public void addSalesman(Salesman salesman) {
        salesmanHashMap.put(salesman.getCpf(), salesman);
    }

    public Salesman getSalesmanByName(String name) {
        return salesmanHashMap.get(name);
    }

    public int getTotalSellers() {
        return salesmanHashMap.size();
    }

    public void clearList() {
        salesmanHashMap.clear();
    }
}
