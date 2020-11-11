package com.wft.sky_gym.Admin;

public class TrainerHelper {
String gender, contact, dob, doj,email,fname, lname, salary, shiftdays,shifttimings,tid;

    public TrainerHelper() {
    }

    public TrainerHelper(String gender,String contact, String dob, String doj, String email, String fname, String lname, String salary, String shiftdays, String shifttimings, String tid) {
        this.gender = gender;
        this.contact= contact;
        this.dob= dob;
        this.doj= doj;
        this.email=email;
        this.fname=fname;
        this.lname=lname;
        this.salary= salary;
        this.shiftdays=shiftdays;
        this.shifttimings= shifttimings;
        this.tid=tid;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getShiftdays() {
        return shiftdays;
    }

    public void setShiftdays(String shiftdays) {
        this.shiftdays = shiftdays;
    }

    public String getShifttimings() {
        return shifttimings;
    }

    public void setShifttimings(String shifttimings) {
        this.shifttimings = shifttimings;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
