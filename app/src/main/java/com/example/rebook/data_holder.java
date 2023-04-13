package com.example.rebook;

public class data_holder {

    String user_name,Email,phone,address;

    public data_holder(){

    }

    public data_holder(String user_name, String email, String phone, String address) {
        this.user_name = user_name;
        Email = email;
        this.phone = phone;
        this.address = address;

    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
