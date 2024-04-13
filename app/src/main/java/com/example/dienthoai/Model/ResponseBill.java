package com.example.dienthoai.Model;

public class ResponseBill {
    private int EC;
    private String EM;
    private String DT;

    public ResponseBill() {
    }

    public ResponseBill(int EC, String EM, String DT) {
        this.EC = EC;
        this.EM = EM;
        this.DT = DT;
    }

    public int getEC() {
        return EC;
    }

    public void setEC(int EC) {
        this.EC = EC;
    }

    public String getEM() {
        return EM;
    }

    public void setEM(String EM) {
        this.EM = EM;
    }

    public String getDT() {
        return DT;
    }

    public void setDT(String DT) {
        this.DT = DT;
    }
}
