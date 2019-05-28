package com.rentcentric.paperlesscounter.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveCustomerSignatureResponse {

    @SerializedName("StatusInfo")
    @Expose
    private StatusInfo statusInfo;

    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(StatusInfo statusInfo) {
        this.statusInfo = statusInfo;
    }
}