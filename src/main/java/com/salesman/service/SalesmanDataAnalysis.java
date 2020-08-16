package com.salesman.service;

import com.salesman.model.Salesman;

public class SalesmanDataAnalysis implements IProcessLine {

    private static final int CPF_POSITION = 1;
    private static final int NAME_POSITION = 2;
    private static final int SALARY = 3;

    @Override
    public Object processLine(String[] line) {
        String cpf = line[CPF_POSITION];
        String name = line[NAME_POSITION];
        Double salary = Double.parseDouble(line[SALARY]);
        return new Salesman(cpf, name, salary);
    }
}
