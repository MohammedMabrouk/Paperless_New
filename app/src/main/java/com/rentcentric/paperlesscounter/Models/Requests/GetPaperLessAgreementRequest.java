package com.rentcentric.paperlesscounter.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPaperLessAgreementRequest {

    @SerializedName("AdminID")
    @Expose
    private String adminID;
    @SerializedName("LocationId")
    @Expose
    private String locationId;
    @SerializedName("PaperLessAdminLoginId")
    @Expose
    private String paperLessAdminLoginId;

    public GetPaperLessAgreementRequest(String adminID, String locationId, String paperLessAdminLoginId) {
        this.adminID = adminID;
        this.locationId = locationId;
        this.paperLessAdminLoginId = paperLessAdminLoginId;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
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
}