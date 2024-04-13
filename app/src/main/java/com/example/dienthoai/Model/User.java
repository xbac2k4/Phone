package com.example.dienthoai.Model;

public class User {
//    email: { type: String, required: true },
//    password: { type: String, required: true },
//    fullname: { type: String },
//    address: { type: String },
//    sex: { type: String },
//    phone: { type: String },
//    group: { type: String }
    private String _id, email, password, fullname, address, sex, phone, group;
//    private String userId;

    public User() {
    }

    public User(String email, String password, String fullname, String address, String sex, String phone) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.sex = sex;
        this.phone = phone;
    }

    public User(String _id, String fullname, String address, String phone) {
        this._id = _id;
        this.fullname = fullname;
        this.address = address;
        this.phone = phone;
//        this.userId = _id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
