
package com.rentcentric.paperlesscounter.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayMethod {

    @SerializedName("PayMethodId")
    @Expose
    private Integer payMethodId;
    @SerializedName("PayMethod")
    @Expose
    private String payMethod;

    public PayMethod(Integer payMethodId, String payMethod) {
        this.payMethodId = payMethodId;
        this.payMethod = payMethod;
    }

    public PayMethod() {
    }

    public Integer getPayMethodId() {
        return payMethodId;
    }

    public void setPayMethodId(Integer payMethodId) {
        this.payMethodId = payMethodId;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    @Override
    public String toString() {
        return "{" +
                "\"payMethodId\" :" + payMethodId +
                ", \"payMethod\" : \"" + payMethod + "\"" +
                '}';
    }
}
