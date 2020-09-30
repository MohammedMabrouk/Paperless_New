
package com.rentcentric.paperlesscounter.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPaperLessAgreementResult {

    @SerializedName("AgreementUrl")
    @Expose
    private String agreementUrl;
    @SerializedName("AgreementSignId")
    @Expose
    private Integer agreementSignId;
    @SerializedName("AdminId")
    @Expose
    private String adminId;
    @SerializedName("AgreementId")
    @Expose
    private Integer agreementId;
    @SerializedName("ContractType")
    @Expose
    private String contractType;

    public String getAgreementUrl() {
        return agreementUrl;
    }

    public void setAgreementUrl(String agreementUrl) {
        this.agreementUrl = agreementUrl;
    }

    public Integer getAgreementSignId() {
        return agreementSignId;
    }

    public void setAgreementSignId(Integer agreementSignId) {
        this.agreementSignId = agreementSignId;
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

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

}
