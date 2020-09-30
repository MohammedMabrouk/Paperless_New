
package com.rentcentric.paperlesscounter.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCountersResult {

    @SerializedName("ReservationCount")
    @Expose
    private Integer reservationCount;
    @SerializedName("LeavingToday")
    @Expose
    private Integer leavingToday;
    @SerializedName("AgreementCount")
    @Expose
    private Integer agreementCount;
    @SerializedName("ReturningToday")
    @Expose
    private Integer returningToday;

    public Integer getReservationCount() {
        return reservationCount;
    }

    public void setReservationCount(Integer reservationCount) {
        this.reservationCount = reservationCount;
    }

    public Integer getLeavingToday() {
        return leavingToday;
    }

    public void setLeavingToday(Integer leavingToday) {
        this.leavingToday = leavingToday;
    }

    public Integer getAgreementCount() {
        return agreementCount;
    }

    public void setAgreementCount(Integer agreementCount) {
        this.agreementCount = agreementCount;
    }

    public Integer getReturningToday() {
        return returningToday;
    }

    public void setReturningToday(Integer returningToday) {
        this.returningToday = returningToday;
    }

}
