package com.example.finalone;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class IT19005386 {

    static NShareDisplay nsd;
    double result;

    @BeforeClass
    public static void CreateObject()
    {
        nsd=new NShareDisplay();

    }
    @Before
    public void getValue()
    {

        result=0.00;
    }
    @Test(timeout = 1)
    public void amountleft() {
        result = nsd.calculation(10, 10);
        assertEquals(9.99,result,0.1);

    }
    @After
    public void cleardata()
    {

        result=0;
    }
    @AfterClass
    public static void deleteObj()
    {

        nsd=null;
    }
}
