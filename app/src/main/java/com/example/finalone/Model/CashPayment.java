package com.example.finalone.Model;

public class CashPayment {
    String cBillNo;
    String pBillNo;
    String date;
    String phone;

    public CashPayment() {
    }

    public String getcBillNo() {
        return cBillNo;
    }

    public void setcBillNo(String cBillNo) {
        this.cBillNo = cBillNo;
    }

    public String getpBillNo() {
        return pBillNo;
    }

    public void setpBillNo(String pBillNo) {
        this.pBillNo = pBillNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPhone(String phone){this.phone=phone;}

    public String getPhone(){return phone;}
}
