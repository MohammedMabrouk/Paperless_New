
package com.rentcentric.paperlesscounter.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPaperLessCounterAdsResponse {

    @SerializedName("Result")
    @Expose
    private GetPaperLessCounterAdsResult getPaperLessCounterAdsResult;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ExceptionMessage")
    @Expose
    private Object exceptionMessage;
    @SerializedName("State")
    @Expose
    private Boolean state;

    public GetPaperLessCounterAdsResult getGetPaperLessCounterAdsResult() {
        return getPaperLessCounterAdsResult;
    }

    public void setGetPaperLessCounterAdsResult(GetPaperLessCounterAdsResult getPaperLessCounterAdsResult) {
        this.getPaperLessCounterAdsResult = getPaperLessCounterAdsResult;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(Object exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

}
