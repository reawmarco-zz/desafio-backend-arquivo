package com.salesman.service.sale;

import com.salesman.model.Sales;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Component
public class SalesData {

    private final static long DEFAULT_ID_BIGGER_SALE = 0;
    private final static String DEFAULT_WORST_SALESMAN_NAME ="Not Found";

    private final List<Sales> sales;

    public SalesData() {
        this.sales = new ArrayList<>();
    }

    public Sales addSale(Sales sale) {
        sales.add(sale);
        return sale;
    }

    public long mostExpensiveSaleId() {
        return sales.stream()
                .max(Comparator.comparing(Sales::getAmount))
                .map(Sales::getId)
                .orElse(DEFAULT_ID_BIGGER_SALE);
    }

    public String worstSalesmanName() {
        double firstValue = sales.stream()
                .filter(Objects::nonNull)
                .filter(s -> s.getAmount() != 0)
                .findFirst()
                .map(Sales::getAmount)
                .orElse(0.0);

        return sales.stream()
                .filter(Objects::nonNull)
                .filter(s -> s.getAmount() != 0 && s.getSalesmanName() != null)
                .filter(s -> s.getAmount() < firstValue)
                .findFirst()
                .map(Sales::getSalesmanName)
                .orElse(DEFAULT_WORST_SALESMAN_NAME);
    }

    public void clearList() {
        sales.clear();
    }
}