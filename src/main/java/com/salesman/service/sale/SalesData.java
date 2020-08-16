package com.salesman.service.sale;

import com.salesman.model.Sales;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class SalesData {

    private final static int DEFAULT_ID_BIGGER_SALE = 0;

    private final List<Sales> sales;

    public SalesData() {
        this.sales = new ArrayList<>();
    }

    public void addSale(Sales sale) {
        sales.add(sale);
    }

    public int mostExpensiveSaleId() {
        return sales.stream()
                .max(Comparator.comparing(Sales::getAmount))
                .map(Sales::getId)
                .orElse(DEFAULT_ID_BIGGER_SALE);
    }

    public String worstSalesmanName() {
        String nomeVendedor = sales.isEmpty() ? null : sales.get(0).getSalesmanName();
        double valorMenorVenda = sales.isEmpty() ? 0.0 : sales.get(0).getAmount();

        for (Sales venda : sales) {
            if (venda.getAmount() < valorMenorVenda) {
                nomeVendedor = venda.getSalesmanName();
            }
        }
        return nomeVendedor;
    }

    public void clearList() {
        sales.clear();
    }
}