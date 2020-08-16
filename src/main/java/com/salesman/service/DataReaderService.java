package com.salesman.service;

import com.salesman.dto.ReportDTO;
import com.salesman.model.Customer;
import com.salesman.model.Sales;
import com.salesman.model.Salesman;
import com.salesman.service.customer.CustomerService;
import com.salesman.service.salasman.SalesmanService;
import com.salesman.service.sale.SalesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DataReaderService {

    private final static int TYPE_POSITION = 0;
    private final CustomerService customerService;
    private final SalesmanService salesmanService;
    private final SalesService salesService;
    private final String separator;

    public DataReaderService(CustomerService customerService,
                             SalesmanService salesmanService,
                             SalesService salesService,
                             @Value("${separator}") String separator) {
        this.customerService = customerService;
        this.salesmanService = salesmanService;
        this.salesService = salesService;
        this.separator = separator;
    }

    public ReportDTO processData(String source) {
        List<String> list = Arrays.asList(source.split(System.lineSeparator()));

        list.forEach(this::processLine);

        return ReportDTO
                .builder()
                .customerTotal(customerService.getTotalCustomers())
                .mostExpensiveSaleId(salesService.mostExpensiveSaleId())
                .salesmanTotal(salesmanService.getTotalSalesmen())
                .worstSalesmanName(salesService.worstSalesmanName())
                .build();
    }

    private void processLine(String line) {
        String[] str = line.split(separator);
        String type = str[TYPE_POSITION];

        switch (type) {
            case "001":
                processSalesmanLine(str);
                break;
            case "002":
                processCustomerLine(str);
                break;
            case "003":
                processSalesLine(str);
                break;
        }
    }

    private Salesman processSalesmanLine(String[] line) {
        return salesmanService.processLine(line);
    }

    private Customer processCustomerLine(String[] line) {
        return customerService.processLine(line);
    }

    private Sales processSalesLine(String[] line) {
        return salesService.processLine(line);
    }
}
