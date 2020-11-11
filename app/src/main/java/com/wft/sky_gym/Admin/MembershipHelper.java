package com.wft.sky_gym.Admin;

public class MembershipHelper {
    String title,amount,description,sdate,edate;

    public MembershipHelper() {
    }

    public MembershipHelper(String title,String amount,String description, String sdate, String edate) {
        this.title = title;
        this.amount=amount;
        this.description=description;
        this.sdate= sdate;
        this.edate= edate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }
}
