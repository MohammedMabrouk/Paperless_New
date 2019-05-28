package com.rentcentric.paperlesscounter.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaperLessAdminLoginRequest {

    @SerializedName("AdminId")
    @Expose
    private String adminId;
    @SerializedName("ClientID")
    @Expose
    private String clientID;
    @SerializedName("Password")
    @Expose
    private String password;

    public PaperLessAdminLoginRequest(String adminId, String clientID, String password) {
        this.adminId = adminId;
        this.clientID = clientID;
        this.password = password;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}