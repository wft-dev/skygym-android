package com.wft.sky_gym.Admin;

public class VisitorHelper {
    String fname, lname, email,address,visits,doj,dov,gender,contact;

    public VisitorHelper() {
    }

    public VisitorHelper(String fname, String lname, String email, String address, String visits, String doj,String dov, String gender, String  contact) {
        this.fname = fname;
        this.lname=lname;
        this.email=email;
        this.address=address;
        this.visits=visits;
        this.doj=doj;
        this.dov=dov;
        this.gender=gender;
        this.contact=contact;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVisits() {
        return visits;
    }

    public void setVisits(String visits) {
        this.visits = visits;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getDov() {
        return dov;
    }

    public void setDov(String dov) {
        this.dov = dov;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
