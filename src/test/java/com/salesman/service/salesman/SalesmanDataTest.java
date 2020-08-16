package com.salesman.service.salesman;

import com.salesman.model.Salesman;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SalesmanDataTest {

    private SalesmanData salesmanData;

    @Before
    public void setUp() {
        salesmanData = new SalesmanData();
    }

    @Test
    public void addSalesman() {
        Salesman salesman = new Salesman("1234567891234", "Pedro", 40000.99);

        salesmanData.addSalesman(salesman);

        int result = salesmanData.getTotalSalesmen();
        Salesman salesmanResult = salesmanData.getSalesmanByName("Pedro");

        assertEquals(1, result);
        assertEquals("Pedro", salesmanResult.getName());
        assertEquals("1234567891234", salesmanResult.getCpf());
    }

    @Test
    public void getSalesmanByName() {
        Salesman salesman = new Salesman("1234567891234", "Pedro", 40000.99);

        salesmanData.addSalesman(salesman);

        Salesman salesmanResult = salesmanData.getSalesmanByName("Pedro");

        assertEquals("Pedro", salesmanResult.getName());
        assertEquals("1234567891234", salesmanResult.getCpf());
    }

    @Test
    public void getTotalSalesmen() {
        Salesman salesman = new Salesman("1234567891234", "Pedro", 40000.99);
        Salesman salesman2 = new Salesman("1234567891222", "Marco", 5000);
        Salesman salesman3 = new Salesman("1234567891221", "Joao", 1000);

        salesmanData.addSalesman(salesman);
        salesmanData.addSalesman(salesman2);
        salesmanData.addSalesman(salesman3);

        int result = salesmanData.getTotalSalesmen();

        assertEquals(3, result);
    }

    @Test
    public void clearList() {
        Salesman salesman = new Salesman("1234567891234", "Pedro", 40000.99);
        salesmanData.addSalesman(salesman);
        salesmanData.clearList();
        int result = salesmanData.getTotalSalesmen();
        assertEquals(0, result);
    }
}