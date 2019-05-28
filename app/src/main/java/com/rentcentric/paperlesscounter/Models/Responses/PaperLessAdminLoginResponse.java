package com.rentcentric.paperlesscounter.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaperLessAdminLoginResponse {

    @SerializedName("AdminId")
    @Expose
    private String adminId;
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("LocationId")
    @Expose
    private String locationId;
    @SerializedName("PaperLessAdminLoginId")
    @Expose
    private String paperLessAdminLoginId;
    @SerializedName("StatusInfo")
    @Expose
    private StatusInfo statusInfo;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getPaperLessAdminLoginId() {
        return paperLessAdminLoginId;
    }

    public void setPaperLessAdminLoginId(String paperLessAdminLoginId) {
        this.paperLessAdminLoginId = paperLessAdminLoginId;
    }

    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(StatusInfo statusInfo) {
        this.statusInfo = statusInfo;
    }
}