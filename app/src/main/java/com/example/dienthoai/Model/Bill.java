package com.example.dienthoai.Model;

import java.util.ArrayList;

public class Bill {
//    totalPrice: { type: Number, required: true },
//    saleDate: { type: Date, default: Date.now, require: true },
//    user: {
//        userId: { type: mongoose.Schema.Types.ObjectId, ref: 'User', required: true },
//        fullname: { type: String },
//        phone: {type: String},
//        address: { type: String }
//    },
//    paymentType: { type: String, require },
//    status: { type: String, require: true }
    private String _id, saleDate;
    private User user;
    private String paymentType;
    int totalPrice;
    private String status;
    private ArrayList<BillDetail> billDetails;

    public Bill() {
    }

    public Bill(int totalPrice, User user, String paymentType) {
        this.totalPrice = totalPrice;
        this.user = user;
        this.paymentType = paymentType;
    }

    public Bill(User user, String paymentType, String status, ArrayList<BillDetail> billDetails) {
        this.user = user;
        this.paymentType = paymentType;
        this.status = status;
        this.billDetails = billDetails;
        this.billDetails.forEach(items -> {
            totalPrice += Integer.valueOf(items.getPrice()) * Integer.valueOf(items.getQuantity());
        });
    }

    public Bill(User user, String paymentType, ArrayList<BillDetail> billDetails) {
        this.user = user;
        this.paymentType = paymentType;
        this.billDetails = billDetails;
        this.billDetails.forEach(items -> {
            totalPrice += Integer.valueOf(items.getPrice()) * Integer.valueOf(items.getQuantity());
        });
    }

    public ArrayList<BillDetail> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(ArrayList<BillDetail> billDetails) {
        this.billDetails = billDetails;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
