package com.rentcentric.paperlesscounter.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPaperLessTempChargeSummeryRequest {
    @SerializedName("adminid")
    @Expose
    private String admiId;

    public GetPaperLessTempChargeSummeryRequest(String admiId) {
        this.admiId = admiId;
    }

    public String getAdmiId() {
        return admiId;
    }

    public void setAdmiId(String admiId) {
        this.admiId = admiId;
    }


}
