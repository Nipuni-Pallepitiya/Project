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
        result=nsd.calculation(10.00,10.00);
    }
    @Test(timeout = 1)
    public void amountleft()
    {

        assertEquals(9.990234375,result);

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
