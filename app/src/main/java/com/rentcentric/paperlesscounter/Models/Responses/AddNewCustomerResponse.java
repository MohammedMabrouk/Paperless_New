
package com.rentcentric.paperlesscounter.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddNewCustomerResponse {

    @SerializedName("Result")
    @Expose
    private addNewCustomerResult addNewCustomerResult;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ExceptionMessage")
    @Expose
    private String exceptionMessage;
    @SerializedName("State")
    @Expose
    private Boolean state;

    public addNewCustomerResult getAddNewCustomerResult() {
        return addNewCustomerResult;
    }

    public void setAddNewCustomerResult(addNewCustomerResult addNewCustomerResult) {
        this.addNewCustomerResult = addNewCustomerResult;
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
