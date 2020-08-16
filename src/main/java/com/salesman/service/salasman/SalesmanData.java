package com.salesman.service.salasman;

import com.salesman.model.Salesman;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SalesmanData {

    private final HashMap<String, Salesman> salesmanHashMap;

    public SalesmanData() {
        salesmanHashMap = new HashMap<>();
    }

    public void addSalesman(Salesman salesman) {
        salesmanHashMap.put(salesman.getCpf(), salesman);
    }

    public Salesman getSalesmanByName(String name) {
        return salesmanHashMap.get(name);
    }

    public int getTotalSalesmen() {
        return salesmanHashMap.size();
    }

    public void clearList() {
        salesmanHashMap.clear();
    }
}
