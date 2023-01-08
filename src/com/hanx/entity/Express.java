package com.hanx.entity;

public class Express {
    private Integer expressID;
    private Integer userID;
    private String expressName;
    private String expressPhone;
    private String expressAddress;

    public Integer getExpressID() {
        return expressID;
    }

    public void setExpressID(Integer expressID) {
        this.expressID = expressID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressPhone() {
        return expressPhone;
    }

    public void setExpressPhone(String expressPhone) {
        this.expressPhone = expressPhone;
    }

    public String getExpressAddress() {
        return expressAddress;
    }

    public void setExpressAddress(String expressAddress) {
        this.expressAddress = expressAddress;
    }
}
