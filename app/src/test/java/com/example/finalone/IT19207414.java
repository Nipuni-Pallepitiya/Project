package com.example.finalone;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IT19207414 {
    private static Ccreditcard2 cc2;
    boolean number;
    boolean name;
    //boolean date1;

    @BeforeClass
    public static void first(){
        cc2 = new Ccreditcard2();
    }

    @Before
    public void setup(){
        number = false;
        name = false;
        //date1 = false;
    }

    @Test
    public void nameInsert(){
        name = cc2.creditCardNameValidation("Abcdefg");
        assertTrue(name);
    }

    @Test
    public void numInsert(){
        number = cc2.creditCardNoValidation("1234567890355500");
        assertTrue(number);
    }

    @Test
    public void numInsert2(){
        number = cc2.validCard("1234567890355500");
        assertTrue(number);
    }

    @Test
    public void nameInsert1(){
        name = cc2.creditCardNameValidation("1234");
        assertFalse(name);
    }

    @Test
    public void numInsert1(){
        number = cc2.creditCardNoValidation("112233445566778899009900");
        assertFalse(number);
    }

    @Test
    public void numInsert4(){
        number = cc2.validCard("112233445566");
        assertFalse(number);
    }

    @After
    public void clear(){
        number = false;
        name = false;
    }

    @AfterClass
    public static void clearAll(){
        cc2 = null;
    }

}
