
package com.rentcentric.paperlesscounter.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaperLessCounterAdsList {

    @SerializedName("PaperLessCounterAdsID")
    @Expose
    private Integer paperLessCounterAdsID;
    @SerializedName("PaperImage")
    @Expose
    private String paperImage;
    @SerializedName("Duration")
    @Expose
    private Integer duration;
    @SerializedName("PaperOrder")
    @Expose
    private Integer paperOrder;

    public Integer getPaperLessCounterAdsID() {
        return paperLessCounterAdsID;
    }

    public void setPaperLessCounterAdsID(Integer paperLessCounterAdsID) {
        this.paperLessCounterAdsID = paperLessCounterAdsID;
    }

    public String getPaperImage() {
        return paperImage;
    }

    public void setPaperImage(String paperImage) {
        this.paperImage = paperImage;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getPaperOrder() {
        return paperOrder;
    }

    public void setPaperOrder(Integer paperOrder) {
        this.paperOrder = paperOrder;
    }

}
