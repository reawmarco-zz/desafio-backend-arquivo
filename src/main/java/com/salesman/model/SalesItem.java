package com.salesman.model;

import java.io.Serializable;

public class SalesItem implements Serializable {

    private static final long serialVersionUID = 2692001629933552503L;

    private long id;
    private double quantity;
    private double price;

    public SalesItem(long id, double quantity, double price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double total() {
        return (quantity * price);
    }
}
