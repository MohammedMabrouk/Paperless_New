package com.rentcentric.paperlesscounter.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSignatureSetupsRequest {
    @SerializedName("LocationID")
    @Expose
    private Integer locationID;
    @SerializedName("ReservationID")
    @Expose
    private Integer reservationID;
    @SerializedName("AgreementId")
    @Expose
    private Integer agreementId;
    @SerializedName("AgreementFormId")
    @Expose
    private Integer agreementFormId;


    public GetSignatureSetupsRequest(
            Integer locationID,
            Integer reservationID,
            Integer agreementId) {
        this.locationID = locationID;
        this.reservationID = reservationID;
        this.agreementId = agreementId;
    }

    public Integer getLocationID() {
        return locationID;
    }

    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }

    public Integer getReservationID() {
        return reservationID;
    }

    public void setReservationID(Integer reservationID) {
        this.reservationID = reservationID;
    }

    public Integer getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Integer agreementId) {
        this.agreementId = agreementId;
    }

    public Integer getAgreementFormId() {
        return agreementFormId;
    }

    public void setAgreementFormId(Integer agreementFormId) {
        this.agreementFormId = agreementFormId;
    }
}
