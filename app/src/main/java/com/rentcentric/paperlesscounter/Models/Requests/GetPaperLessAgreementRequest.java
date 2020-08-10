package com.rentcentric.paperlesscounter.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPaperLessAgreementRequest {

    @SerializedName("PaperLessAdminLoginId")
    @Expose
    private Integer paperLessAdminLoginId;
    @SerializedName("AdminId")
    @Expose
    private String adminId;
    @SerializedName("LocationId")
    @Expose
    private Integer locationId;
    @SerializedName("MobileRequestId")
    @Expose
    private Integer mobileRequestId;

    public GetPaperLessAgreementRequest(Integer paperLessAdminLoginId, String adminId, Integer locationId, Integer mobileRequestId) {
        this.paperLessAdminLoginId = paperLessAdminLoginId;
        this.adminId = adminId;
        this.locationId = locationId;
        this.mobileRequestId = mobileRequestId;
    }

    public Integer getPaperLessAdminLoginId() {
        return paperLessAdminLoginId;
    }

    public void setPaperLessAdminLoginId(Integer paperLessAdminLoginId) {
        this.paperLessAdminLoginId = paperLessAdminLoginId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getMobileRequestId() {
        return mobileRequestId;
    }

    public void setMobileRequestId(Integer mobileRequestId) {
        this.mobileRequestId = mobileRequestId;
    }
}