package com.wft.sky_gym;

public class Dataholder {
    String gymname,gymid,gymadd,firstname,lastname,gender,email,mobileno,password,dob;

    public Dataholder(String gymname,String gymid,String gymadd,String firstname,String lastname,String gender,String email,String mobileno,String password,String dob) {
        this.gymname = gymname;
        this.dob= dob;
        this.email= email;
        this.gender=gender;
        this.gymid=gymid;
        this.gymadd=gymadd;
        this.firstname=firstname;
        this.lastname=lastname;
        this.mobileno= mobileno;
        this.password=password;
    }

    public String getGymname() {
        return gymname;
    }

    public void setGymname(String gymname) {
        this.gymname = gymname;
    }

    public String getGymid() {
        return gymid;
    }

    public void setGymid(String gymid) {
        this.gymid = gymid;
    }

    public String getGymadd() {
        return gymadd;
    }

    public void setGymadd(String gymadd) {
        this.gymadd = gymadd;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
