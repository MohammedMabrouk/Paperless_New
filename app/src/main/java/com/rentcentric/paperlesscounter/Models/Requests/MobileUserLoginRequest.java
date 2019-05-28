package com.rentcentric.paperlesscounter.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MobileUserLoginRequest {

    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("MobileServicePassword")
    @Expose
    private String mobileServicePassword = "dr0w$$ap";
    @SerializedName("MobileServiceUserName")
    @Expose
    private String mobileServiceUserName = "dr0w$$ap";
    @SerializedName("Password")
    @Expose
    private String password;

    public MobileUserLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileServicePassword() {
        return mobileServicePassword;
    }

    public void setMobileServicePassword(String mobileServicePassword) {
        this.mobileServicePassword = mobileServicePassword;
    }

    public String getMobileServiceUserName() {
        return mobileServiceUserName;
    }

    public void setMobileServiceUserName(String mobileServiceUserName) {
        this.mobileServiceUserName = mobileServiceUserName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}