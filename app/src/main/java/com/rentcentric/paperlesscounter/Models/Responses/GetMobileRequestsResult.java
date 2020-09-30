
package com.rentcentric.paperlesscounter.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMobileRequestsResult {

    @SerializedName("MobileRequestId")
    @Expose
    private Integer mobileRequestId;
    @SerializedName("RequestType")
    @Expose
    private String requestType;
    @SerializedName("CustomerId")
    @Expose
    private Integer customerId;

    public Integer getMobileRequestId() {
        return mobileRequestId;
    }

    public void setMobileRequestId(Integer mobileRequestId) {
        this.mobileRequestId = mobileRequestId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

}
