package com.example.dienthoai.Model;

public class BillDetail {
//    billId: { type: mongoose.Schema.Types.ObjectId, ref: 'Bill', required: true },
//    category: {
//        categoryId: {type: mongoose.Schema.Types.ObjectId, ref: 'Category', required: true },
//        name: { type: String },
//        image: { type: String }
//    },
//    price: { type: Number, required: true },
//    quantity: { type: Number, required: true },
    private Phone phone;
    private String _id;
    private int price, quantity;

    public BillDetail() {
    }

    public BillDetail(Phone phone, String _id, int price, int quantity) {
        this.phone = phone;
        this._id = _id;
        this.price = price;
        this.quantity = quantity;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
