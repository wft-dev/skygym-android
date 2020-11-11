package com.wft.sky_gym.Admin;

public class UsersHelperClass {
    String gname,gid,gadd,fname1,lname1,dob,email,password,mobileno,gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UsersHelperClass() {
    }



    public UsersHelperClass(String gname, String gid, String gadd, String fname1,
                            String lname1, String dob, String email, String password,
                            String mobileno, String gender)
    {
        this.gname = gname;
        this.gid = gid;
        this.gadd = gadd;
        this.fname1 = fname1;
        this.lname1 = lname1;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.mobileno = mobileno;
        this.gender = gender;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGadd() {
        return gadd;
    }

    public void setGadd(String gadd) {
        this.gadd = gadd;
    }

    public String getFname1() {
        return fname1;
    }

    public void setFname1(String fname1) {
        this.fname1 = fname1;
    }

    public String getLname1() {
        return lname1;
    }

    public void setLname1(String lname1) {
        this.lname1 = lname1;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileno() {

        return mobileno;
    }

    public void setMobileno(String mobileno) {

        this.mobileno = mobileno;
    }

    public String getDob() {

        return dob;
    }

    public void setDob(String dob) {

        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }
}
