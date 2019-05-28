package com.rentcentric.paperlesscounter.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPaperLessAgreementResponse {

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
    @SerializedName("StatusInfo")
    @Expose
    private StatusInfo statusInfo;

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

    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(StatusInfo statusInfo) {
        this.statusInfo = statusInfo;
    }
}