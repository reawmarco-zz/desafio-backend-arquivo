package com.salesman.dto;

import com.salesman.model.Salesman;

import java.util.ArrayList;
import java.util.List;

public class SaleDTO {

    private Integer id;
    private List<SaleItemDTO> saleItems;
    private double amount;
    private Salesman salesman;
    private String salesmanName;

    public SaleDTO(Integer id, Salesman salesman, String salesmanName) {
        this.id = id;
        this.salesman = salesman;
        this.salesmanName = salesmanName;
        saleItems = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<SaleItemDTO> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItemDTO> saleItems) {
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

    public void addSaleItem(SaleItemDTO item) {
        saleItems.add(item);
        amount += item.total();
    }
}
