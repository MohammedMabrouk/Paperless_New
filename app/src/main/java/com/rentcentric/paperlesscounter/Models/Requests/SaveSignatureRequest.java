package com.rentcentric.paperlesscounter.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SaveSignatureRequest {
    @SerializedName("AgreementId")
    @Expose
    private Integer agreementID;
    @SerializedName("SignatureList")
    @Expose
    private List<Signature> signature = null;

    public SaveSignatureRequest(Integer agreementID, List<Signature> signature) {
        this.agreementID = agreementID;
        this.signature = signature;
    }

    public Integer getAgreementID() {
        return agreementID;
    }

    public void setAgreementID(Integer agreementID) {
        this.agreementID = agreementID;
    }

    public List<Signature> getSignature() {
        return signature;
    }

    public void setSignature(List<Signature> signature) {
        this.signature = signature;
    }
}
