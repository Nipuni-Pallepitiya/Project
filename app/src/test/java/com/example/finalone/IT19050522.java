package com.example.finalone;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class IT19050522 {
    private static MRegister mRegister;
    boolean phone;
    boolean password;
    boolean confirm;

    @BeforeClass
    public static void first(){
        mRegister = new MRegister();
    }

    @Before
    public void setup(){
        phone = false;
        password = false;
        confirm = false;

    }

    @Test
    public void validPhone(){
        phone = mRegister.isValidatePhone("1234567890");
        assertTrue(phone);
    }
    @Test
    public void validPhone1(){
        phone = mRegister.isValidatePhone("12345");
        assertFalse(phone);
    }
    @Test
    public  void validPhone2(){
        phone = mRegister.isValidatePhone("qwert");
        assertFalse(phone);
    }
    @Test
    public void validPhone3(){
        phone = mRegister.isValidatePhone("");
        assertFalse(phone);
    }
    @Test
    public void validPassword(){
        password = mRegister.isValidatePassword("12345");
        assertFalse(password);
    }
    @Test
    public void validPassword1(){
        password = mRegister.isValidatePassword("1234567890");
        assertTrue(password);
    }
    @Test
    public void validPassword2(){
        password = mRegister.isValidatePassword("qwerty");
        assertFalse(password);
    }
    @Test
    public void validPassword3(){
        password = mRegister.isValidatePassword("");
        assertFalse(password);
    }
    @Test
    public void validConfirm(){
        confirm = mRegister.isValidateConfirm("1234567","123456");
        assertFalse(confirm);
    }
    @Test
    public void validConfirm1(){
        confirm = mRegister.isValidateConfirm("1234567","1234567");
        assertTrue(confirm);
    }
    @Test
    public void validConfirm2(){
        confirm = mRegister.isValidateConfirm("","");
        assertTrue(confirm);
    }


    @After
    public void clear(){
        phone = false;
        password = false;

    }

    @AfterClass
    public static void clearAll(){
        mRegister = null;
    }





}
