package com.salesman.service;

import com.salesman.dto.SaleDTO;
import com.salesman.model.Salesman;

public class SalesDataAnalysis implements IProcessLine {

    @Override
    public Object processLine(String[] line) {
        int id = Integer.parseInt(line[1]);
        String item = line[2];
        item = item.substring(1, item.length() - 2);
        String nomeVendedor = line[3];

        Salesman vendedor = VendedorService.buscarVendedorPeloNome(nomeVendedor);
        SaleDTO venda = new SaleDTO(id, vendedor, nomeVendedor);

        String[] itens = item.split(",");

        for (String strItem : itens) {
            String[] texto = strItem.split("-");
            int itemID = Integer.valueOf(texto[0]);
            double total = Double.valueOf(texto[1]);
            double valor = Double.valueOf(texto[2]);
            venda.adicionaItem(new Item(itemID, total, valor));
        }
        return venda;
    }
}
