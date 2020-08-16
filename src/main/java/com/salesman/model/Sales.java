package com.salesman.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Sales implements Serializable {

    private static final long serialVersionUID = 8447199076814821763L;

    private long id;
    private List<SalesItem> saleItems;
    private double amount;
    private Salesman salesman;
    private String salesmanName;

    public Sales(long id, Salesman salesman, String salesmanName) {
        this.id = id;
        this.salesman = salesman;
        this.salesmanName = salesmanName;
        saleItems = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<SalesItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SalesItem> saleItems) {
        this.saleItems = saleItems;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public void addSaleItem(SalesItem item) {
        saleItems.add(item);
        amount += item.total();
    }
}
