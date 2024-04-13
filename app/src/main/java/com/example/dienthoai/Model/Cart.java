package com.example.dienthoai.Model;

public class Cart {
//    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            "tensp TEXT NOT NULL, " +
//            "giasp INTEGER NOT NULL, " +
//            "soluong INTEGER NOT NULL, " +
//            "tongtien INTEGER NOT NULL, " +
//            "hinhanh TEXT NOT NULL, " +
//            "iduser TEXT NOT NULL)";
    private int _id, price, quantity, totalprice;
    private String name, image, iduser, idPhone;

    public Cart() {
    }

    public Cart(int price, int quantity, int totalprice, String name, String image, String iduser, String idPhone) {
        this.price = price;
        this.quantity = quantity;
        this.totalprice = totalprice;
        this.name = name;
        this.image = image;
        this.iduser = iduser;
        this.idPhone = idPhone;
    }

    public String getIdPhone() {
        return idPhone;
    }

    public void setIdPhone(String idPhone) {
        this.idPhone = idPhone;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
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

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }
}
