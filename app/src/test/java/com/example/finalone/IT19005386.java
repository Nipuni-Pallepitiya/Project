package com.example.finalone;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class IT19005386 {

    static NShareDisplay nsd;
    private static NShare_Main ns;
    boolean number;
    double result;

    @BeforeClass
    public static void CreateObject()
    {
        nsd=new NShareDisplay();
        ns=new NShare_Main();

    }
    @Before
    public void getValue()
    {
        number=false;
        result=0.00;
    }
    @Test(timeout = 1)
    public void amountleft() {
        result = nsd.calculation(10, 10);
        assertEquals(9.99,result,0.1);

    }
    @Test
    public void phonefrom()
    {
        number = ns.Phone("1234567890");
        assertTrue(number);
    }
    @After
    public void cleardata()
    {
        number=false;
        result=0;
    }
    @AfterClass
    public static void deleteObj()
    {

        nsd=null;
        ns=null;
    }
}
