package com.salesman.service.salesman;

import com.salesman.model.Salesman;
import com.salesman.stub.SalesmanStub;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SalesmanDataAnalysisTest {

    private SalesmanDataAnalysis salesmanDataAnalysis;

    @Before
    public void setUp() {
        salesmanDataAnalysis = mock(SalesmanDataAnalysis.class);
    }

    @Test
    public void processLine() {
        String[] strSalesman = SalesmanStub.createOneLine();
        Salesman salesman = new Salesman("1234567891234", "Pedro", 40000.99);

        when(salesmanDataAnalysis.processLine(strSalesman)).thenReturn(salesman);

        Salesman result = (Salesman) salesmanDataAnalysis.processLine(strSalesman);
        assertThat(result, instanceOf(Salesman.class));
        assertEquals(result, salesman);
    }
}