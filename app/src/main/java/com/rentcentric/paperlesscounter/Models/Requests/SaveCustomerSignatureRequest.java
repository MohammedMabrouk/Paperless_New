package com.rentcentric.paperlesscounter.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveCustomerSignatureRequest {

    @SerializedName("AdminId")
    @Expose
    private String adminId;
    @SerializedName("AgreementId")
    @Expose
    private String agreementId;
    @SerializedName("AgreementSignId")
    @Expose
    private String agreementSignId;
    @SerializedName("AgreementUrl")
    @Expose
    private String agreementUrl;
    @SerializedName("LocationId")
    @Expose
    private String locationId;
    @SerializedName("PaperLessAdminLoginId")
    @Expose
    private String paperLessAdminLoginId;
    @SerializedName("SignautreImage")
    @Expose
    private String signautreImage;

    public SaveCustomerSignatureRequest(String adminId, String agreementId, String agreementSignId, String locationId, String paperLessAdminLoginId, String signautreImage) {
        this.adminId = adminId;
        this.agreementId = agreementId;
        this.agreementSignId = agreementSignId;
        this.locationId = locationId;
        this.paperLessAdminLoginId = paperLessAdminLoginId;
        this.signautreImage = signautreImage;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }

    public String getAgreementSignId() {
        return agreementSignId;
    }

    public void setAgreementSignId(String agreementSignId) {
        this.agreementSignId = agreementSignId;
    }

    public String getAgreementUrl() {
        return agreementUrl;
    }

    public void setAgreementUrl(String agreementUrl) {
        this.agreementUrl = agreementUrl;
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

    public String getSignautreImage() {
        return signautreImage;
    }

    public void setSignautreImage(String signautreImage) {
        this.signautreImage = signautreImage;
    }
}