package com.example.dienthoai.Model;

import java.util.ArrayList;

public class Response<T> {
    private int EC;
    private String EM;
    private T DT;

    public Response() {
    }

    public Response(int EC, String EM, T DT) {
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

    public T getDT() {
        return DT;
    }

    public void setDT(T DT) {
        this.DT = DT;
    }
}
