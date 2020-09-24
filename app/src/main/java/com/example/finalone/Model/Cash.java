package com.example.finalone.Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Cash {
    String phoneNo;
    String branchName;
    String branchNo;
    String Cashno;
    String date;
    String time;

    public Cash() {
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getCashno() {
        return Cashno;
    }

    public void setCashno(String cashno) {
        Cashno = cashno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
