
package com.rentcentric.paperlesscounter.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaperLessAdminLogOutResponse {

    @SerializedName("Result")
    @Expose
    private PaperLessAdminLogOutResult paperLessAdminLogOutResult;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ExceptionMessage")
    @Expose
    private String exceptionMessage;
    @SerializedName("State")
    @Expose
    private Boolean state;

    public PaperLessAdminLogOutResult getPaperLessAdminLogOutResult() {
        return paperLessAdminLogOutResult;
    }

    public void setPaperLessAdminLogOutResult(PaperLessAdminLogOutResult paperLessAdminLogOutResult) {
        this.paperLessAdminLogOutResult = paperLessAdminLogOutResult;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

}
