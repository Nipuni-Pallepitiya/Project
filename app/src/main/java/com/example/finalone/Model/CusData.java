package com.example.finalone.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CusData {

    private String dataid;
    private String phone;
    private String datadatefrom;
    private String datavalidity;
    private String dataprice;
    private String dataname;

    public CusData() {
    }

    public String getDataid() {
        return dataid;
    }

    public void setDataid(String dataid) {
        this.dataid = dataid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDatadatefrom() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date cusdatefrom1 = new Date();
        String datadatefrom = dateFormat.format(cusdatefrom1);

        return datadatefrom;
    }

    public void setDatadatefrom(String datadatefrom) {
        this.datadatefrom = datadatefrom;
    }

    public String getDatavalidity() {
        return datavalidity;
    }

    public void setDatavalidity(String datavalidity) {
        this.datavalidity = datavalidity;
    }

    public String getDataprice() {
        return dataprice;
    }

    public void setDataprice(String dataprice) {
        this.dataprice = dataprice;
    }

    public String getDataname() {
        return dataname;
    }

    public void setDataname(String dataname) {
        this.dataname = dataname;
    }
}
