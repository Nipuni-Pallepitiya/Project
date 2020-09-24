package com.example.finalone.Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Bill {
    private String billno;
    private String billdateto;
    private String billdatefrom;
    private String billfixprice;
    private  String phone;

    public Bill() {
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getBilldateto() {
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String billdateto = dateFormat.format(cal.getTime());
        return billdateto;
    }

    public void setBilldateto(String billdateto) {
        this.billdateto = billdateto;
    }

    public String getBilldatefrom() {

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String billdatefrom = dateFormat.format(cal.getTime());
        return billdatefrom;
    }

    public void setBilldatefrom(String billdatefrom) {
        this.billdatefrom = billdatefrom;
    }

    public String getBillfixprice() {
        return billfixprice;
    }

    public void setBillfixprice(String billfixprice) {
        this.billfixprice = billfixprice;
    }

    public void setPhone(String phone){this.phone= phone;}

    public String getPhone(){return phone;}
}
