package com.rentcentric.paperlesscounter.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveCustomerSignatureRequest {

    @SerializedName("AdminId")
    @Expose
    private String adminId;
    @SerializedName("AgreementId")
    @Expose
    private Integer agreementId;
    @SerializedName("AgreementSignId")
    @Expose
    private Integer agreementSignId;
    @SerializedName("AgreementUrl")
    @Expose
    private String agreementUrl;
    @SerializedName("LocationId")
    @Expose
    private Integer locationId;
    @SerializedName("PaperLessAdminLoginId")
    @Expose
    private Integer paperLessAdminLoginId;
    @SerializedName("SignatureImage")
    @Expose
    private String signautreImage;
    @SerializedName("MobileRequestId")
    @Expose
    private Integer mobileRequestId;

    public SaveCustomerSignatureRequest(
            String adminId,
            Integer agreementId,
            Integer agreementSignId,
            Integer locationId,
            Integer paperLessAdminLoginId,
            String signautreImage,
            Integer mobileRequestId,
            String agreementUrl) {
        this.adminId = adminId;
        this.agreementId = agreementId;
        this.agreementSignId = agreementSignId;
        this.locationId = locationId;
        this.paperLessAdminLoginId = paperLessAdminLoginId;
        this.signautreImage = signautreImage;
        this.mobileRequestId = mobileRequestId;
        this.agreementUrl = agreementUrl;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Integer getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Integer agreementId) {
        this.agreementId = agreementId;
    }

    public Integer getAgreementSignId() {
        return agreementSignId;
    }

    public void setAgreementSignId(Integer agreementSignId) {
        this.agreementSignId = agreementSignId;
    }

    public String getAgreementUrl() {
        return agreementUrl;
    }

    public void setAgreementUrl(String agreementUrl) {
        this.agreementUrl = agreementUrl;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getPaperLessAdminLoginId() {
        return paperLessAdminLoginId;
    }

    public void setPaperLessAdminLoginId(Integer paperLessAdminLoginId) {
        this.paperLessAdminLoginId = paperLessAdminLoginId;
    }

    public String getSignautreImage() {
        return signautreImage;
    }

    public void setSignautreImage(String signautreImage) {
        this.signautreImage = signautreImage;
    }

    public Integer getMobileRequestId() {
        return mobileRequestId;
    }

    public void setMobileRequestId(Integer mobileRequestId) {
        this.mobileRequestId = mobileRequestId;
    }
}