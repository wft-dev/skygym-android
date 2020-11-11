package com.wft.sky_gym.Admin;

public class MemberHelper {
    String memname, memid,doj,gender,password,tname,email,address,contact,dob;

    public MemberHelper() {
    }

    public MemberHelper(String memname,String memid, String doj, String gender, String password, String tname, String email, String address, String contact, String dob) {
        this.memname=memname;
        this.memid = memid;
        this.doj=doj;
        this.gender=gender;
        this.password=password;

        this.tname=tname;
        this.email=email;
        this.address=address;
        this.contact=contact;
        this.dob=dob;

    }

    public String getMemid() {
        return memid;
    }

    public String getMemname() {
        return memname;
    }

    public void setMemname(String memname) {
        this.memname = memname;
    }

    public void setMemid(String memberid) {
        this.memid = memberid;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
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

   
}
