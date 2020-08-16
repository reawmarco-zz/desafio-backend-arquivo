package com.salesman.service.sale;

import com.salesman.model.Sales;
import com.salesman.model.Salesman;
import com.salesman.stub.SalesStub;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SalesDataAnalysisTest {

    private SalesDataAnalysis salesDataAnalysis;

    @Before
    public void setUp() {
        salesDataAnalysis = mock(SalesDataAnalysis.class);
    }

    @Test
    public void processLine() {
        String[] strSales = SalesStub.createOneLine();
        Salesman salesman = new Salesman("3245678865434", "Paulo", 40000.99);
        Sales sales = new Sales(8, salesman, "Paulo");

        when(salesDataAnalysis.processLine(strSales)).thenReturn(sales);

        Sales result = (Sales) salesDataAnalysis.processLine(strSales);

        assertThat(result, instanceOf(Sales.class));
        assertEquals(result, sales);
    }
}
