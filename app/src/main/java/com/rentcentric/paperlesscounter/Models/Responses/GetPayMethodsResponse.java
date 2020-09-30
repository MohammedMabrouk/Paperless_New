
package com.rentcentric.paperlesscounter.Models.Responses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPayMethodsResponse {

    @SerializedName("Result")
    @Expose
    private List<PayMethod> payMethodsList = null;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ExceptionMessage")
    @Expose
    private Object exceptionMessage;
    @SerializedName("State")
    @Expose
    private Boolean state;

    public List<PayMethod> getPayMethodsList() {
        return payMethodsList;
    }

    public void setPayMethodsList(List<PayMethod> payMethodsList) {
        this.payMethodsList = payMethodsList;
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
