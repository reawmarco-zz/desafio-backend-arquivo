package com.salesman.stub;

import com.salesman.dto.ReportDTO;

public class ReportDTOStub {
    public static ReportDTO createOneResult() {
        return ReportDTO.builder()
                .worstSalesmanName("Pedro")
                .salesmanTotal(0)
                .mostExpensiveSaleId(0)
                .customerTotal(0)
                .build();
    }
}
