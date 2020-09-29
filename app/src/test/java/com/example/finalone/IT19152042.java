package com.example.finalone;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IT19152042 {

    private  static  Pdata_package_details datapackdetail;
    int tot;

    @BeforeClass
    public static  void initmain(){
        datapackdetail = new Pdata_package_details();
    }

    @Before
    public void setup(){
        tot=0;
    }

    @Test
    public void calculateTotal() {
        tot = datapackdetail.totalAmount(150, 790);
        Assert.assertEquals(940,tot);
    }

    @After
    public void clear(){
        tot=0;
    }

    @AfterClass
    public static void clearAll(){
        datapackdetail = null;
    }

}
