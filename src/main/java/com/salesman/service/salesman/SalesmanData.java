package com.salesman.service.salesman;

import com.salesman.model.Salesman;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SalesmanData {

    private final HashMap<String, Salesman> salesmanHashMap;

    public SalesmanData() {
        salesmanHashMap = new HashMap<>();
    }

    public Salesman addSalesman(Salesman salesman) {
       return salesmanHashMap.put(salesman.getCpf(), salesman);
    }

    public Salesman getSalesmanByName(String name) {
        return salesmanHashMap.values()
                .stream()
                .filter(salesman -> salesman.getName().equals(name))
                .findFirst()
                .orElseGet(null);
    }

    public int getTotalSalesmen() {
        return salesmanHashMap.size();
    }

    public void clearList() {
        salesmanHashMap.clear();
    }
}
