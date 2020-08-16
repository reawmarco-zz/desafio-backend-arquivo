package com.salesman.stub;

import com.salesman.model.Salesman;

import java.util.ArrayList;
import java.util.List;

public class SalesmanStub {

    public static String[] createOneLine(){
        return new String[]{"001ç1234567891234çPedroç40000.99"};
    }

    public static String[] createStringList(){
        return new String[]{"001ç1234567891234çPedroç40000.99",
                            "001ç1234567891234çNoahç50000",
                            "001ç1234567891224çMarcoç42300.99",
                            "001ç1234567891234çJoaoç2300.99"};
    }

    public static Object createOne(){
        return new Salesman("1234567891234", "Pedro", 40000.99);
    }

    public static List<Salesman> createList(){
        Salesman salesman1 = new Salesman("1234567891234", "Pedro", 50000);
        Salesman salesman2 = new Salesman("3245678865434", "Paulo", 40000.99);
        Salesman salesman3 = new Salesman("1222567891234", "Marco", 50000);
        Salesman salesman4 = new Salesman("1234561291234", "Joao", 20.0);
        Salesman salesman5 = new Salesman("1234567291234", "Noah", 500.22);

        List<Salesman> list = new ArrayList<>();
        list.add(salesman1);
        list.add(salesman2);
        list.add(salesman3);
        list.add(salesman4);
        list.add(salesman5);

        return list;

    }
}
