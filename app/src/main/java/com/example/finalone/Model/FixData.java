package com.example.finalone.Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FixData {

    private String fixname;
    private String fixdatefrom;
    private String fixdateto;
    private String fixprice;
    private String fixdata;

    public FixData() {
    }

    public String getFixname() {
        return fixname;
    }

    public void setFixname(String fixname) {
        this.fixname = fixname;
    }

    public String getFixdatefrom() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fixdatefrom1 = new Date();
        String fixdatefrom = dateFormat.format(fixdatefrom1);
        return fixdatefrom;
    }

    public void setFixdatefrom(String fixdatefrom) {
        this.fixdatefrom = fixdatefrom;
    }

    public String getFixdateto() {
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fixdateto = dateFormat.format(cal.getTime());
        return fixdateto;
    }

    public void setFixdateto(String fixdateto) {
        this.fixdateto = fixdateto;
    }

    public String getFixprice() {
        return fixprice;
    }

    public void setFixprice(String fixprice) {
        this.fixprice = fixprice;
    }

    public String getFixdata() {
        return fixdata;
    }

    public void setFixdata(String fixdata) {
        this.fixdata = fixdata;
    }
}
