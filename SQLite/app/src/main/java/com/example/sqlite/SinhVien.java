package com.example.sqlite;

public class SinhVien {
    private int ID;
    private String Name;
    private String Contact;
    private String Address;
    private String Email;

    public SinhVien(int ID, String name, String contact, String address, String email) {
        this.ID = ID;
        Name = name;
        Contact = contact;
        Address = address;
        Email = email;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getContact() {
        return Contact;
    }

    public String getAddress() {
        return Address;
    }

    public String getEmail() {
        return Email;
    }
}
