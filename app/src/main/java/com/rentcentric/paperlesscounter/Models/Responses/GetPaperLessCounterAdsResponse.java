package com.rentcentric.paperlesscounter.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPaperLessCounterAdsResponse {

    @SerializedName("PaperLessCounterAdsList")
    @Expose
    private List<PaperLessCounterAdsList> paperLessCounterAdsList = null;
    @SerializedName("StatusInfo")
    @Expose
    private StatusInfo statusInfo;

    public List<PaperLessCounterAdsList> getPaperLessCounterAdsList() {
        return paperLessCounterAdsList;
    }

    public void setPaperLessCounterAdsList(List<PaperLessCounterAdsList> paperLessCounterAdsList) {
        this.paperLessCounterAdsList = paperLessCounterAdsList;
    }

    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(StatusInfo statusInfo) {
        this.statusInfo = statusInfo;
    }
}