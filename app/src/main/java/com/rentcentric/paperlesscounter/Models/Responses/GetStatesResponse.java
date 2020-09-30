
package com.rentcentric.paperlesscounter.Models.Responses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetStatesResponse {

    @SerializedName("Result")
    @Expose
    private List<State> statesList = null;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ExceptionMessage")
    @Expose
    private String exceptionMessage;
    @SerializedName("State")
    @Expose
    private boolean state;

    public List<State> getStatesList() {
        return statesList;
    }

    public void setStatesList(List<State> statesList) {
        this.statesList = statesList;
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

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

}
