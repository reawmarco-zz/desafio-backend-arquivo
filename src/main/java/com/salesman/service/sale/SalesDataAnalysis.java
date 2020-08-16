package com.salesman.service.sale;

import com.salesman.model.Sales;
import com.salesman.model.SalesItem;
import com.salesman.model.Salesman;
import com.salesman.service.IProcessLine;
import com.salesman.service.salesman.SalesmanService;
import org.springframework.stereotype.Component;

@Component
public class SalesDataAnalysis implements IProcessLine {

    private static final int SALE_POSITION = 1;
    private static final int SALESMAN_POSITION = 3;
    private static final int QUANTITY_POSITION = 1;
    private static final int ITEM_ID_POSITION = 0;
    private static final int ITEM_POSITION = 2;
    private static final int PRICE_POSITION = 2;

    private static final String ITEM_SEPARATOR = ",";
    private static final String ITEM_SEPARATOR_VALUES = "-";

    private final SalesmanService salesmanService;

    public SalesDataAnalysis(SalesmanService salesmanService) {
        this.salesmanService = salesmanService;
    }

    @Override
    public Object processLine(String[] line) {
        int id = Integer.parseInt(line[SALE_POSITION]);
        String item = line[ITEM_POSITION];
        item = item.substring(1, item.length() - 2);
        String salesmanName = line[SALESMAN_POSITION];

        Salesman salesman = salesmanService.getSalesmanByName(salesmanName);

        Sales sales = new Sales(id, salesman, salesmanName);

        String[] itens = item.split(ITEM_SEPARATOR);

        return processItens(sales, itens);
    }

    private Sales processItens(Sales sales, String[] itens) {
        for (String strItem : itens) {
            String[] texto = strItem.split(ITEM_SEPARATOR_VALUES);
            long id = Integer.parseInt(texto[ITEM_ID_POSITION]);
            double total = Double.parseDouble(texto[QUANTITY_POSITION]);
            double price = Double.parseDouble(texto[PRICE_POSITION]);
            sales.addSaleItem(new SalesItem(id, total, price));
        }
        return sales;
    }
}