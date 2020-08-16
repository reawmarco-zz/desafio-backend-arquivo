package com.salesman.service.data;

import com.salesman.dto.SaleDTO;
import com.salesman.model.Salesman;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SalesData {

    private final static double DEFAULT_PRICE_VALUE = 0.0;
    private final static int DEFAULT_ID_BIGGER_SALE = 0;

    List<SaleDTO> sales;

    public SalesData() {
        sales = new ArrayList<>();
    }

    public void addSale(SaleDTO sale) {
        sales.add(sale);
    }

    /**
     * Buscar o id da maior venda computada no arquivo de entrada
     *
     * @return
     */
    public int getBiggerSale() {
        return sales.stream()
                .max(Comparator.comparing(SaleDTO::getAmount))
                .map(SaleDTO::getId)
                .orElse(DEFAULT_ID_BIGGER_SALE);

      /*  for (SaleDTO sale : sales) {
            if (sale.getAmount() > amount) {
                amount = sale.getAmount();
                id = sale.getId();
            }
        }
        return id;*/
    }

    /**
     * Buscar o nome do vendedor que tem o menor valor total das sales computadas
     * no arquivo de entrada
     *
     * @return
     */
    public String getNameSellerSmallSale() {
        //   String name = sales.stream().findFirst().map(SaleDTO::getSalesmanName).orElse(null);
        // double lowestSalesValue =  sales.stream().findFirst().map(SaleDTO::getAmount).orElse(DEFAULT_PRICE_VALUE);
        return sales.stream()
                .min(Comparator.comparing(SaleDTO::getAmount))
                .map(SaleDTO::getSalesman)
                .map(Salesman::getName)
                .orElse(null);

     /*   for (SaleDTO sale : sales) {
            if (sale.getAmount() < lowestSalesValue) {
                name = sale.getSalesmanName();
            }
        }
        return name;*/
    }

    public void clearList() {
        sales.clear();
    }
}
