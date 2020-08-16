package com.salesman.dto;

public class ReportDTO {

    private long salesmanTotal;
    private long customerTotal;
    private String mostExpensiveSaleId;
    private String worstSalesmanName;

    public long getSalesmanTotal() {
        return salesmanTotal;
    }

    public void setSalesmanTotal(long salesmanTotal) {
        this.salesmanTotal = salesmanTotal;
    }

    public long getCustomerTotal() {
        return customerTotal;
    }

    public void setCustomerTotal(long customerTotal) {
        this.customerTotal = customerTotal;
    }

    public String getMostExpensiveSaleId() {
        return mostExpensiveSaleId;
    }

    public void setMostExpensiveSaleId(String mostExpensiveSaleId) {
        this.mostExpensiveSaleId = mostExpensiveSaleId;
    }

    public String getWorstSalesmanName() {
        return worstSalesmanName;
    }

    public void setWorstSalesmanName(String worstSalesmanName) {
        this.worstSalesmanName = worstSalesmanName;
    }
}
