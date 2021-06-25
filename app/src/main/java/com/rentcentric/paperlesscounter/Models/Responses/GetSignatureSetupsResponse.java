package com.rentcentric.paperlesscounter.Models.Responses;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetSignatureSetupsResponse implements Parcelable {

    @SerializedName("Result")
    @Expose
    private ArrayList<SignatureSetup> signatureSetup = null;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ExceptionMessage")
    @Expose
    private String exceptionMessage;
    @SerializedName("State")
    @Expose
    private Boolean state;


    protected GetSignatureSetupsResponse(Parcel in) {
        signatureSetup = in.createTypedArrayList(SignatureSetup.CREATOR);
        description = in.readString();
        exceptionMessage = in.readString();
        byte tmpState = in.readByte();
        state = tmpState == 0 ? null : tmpState == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(signatureSetup);
        dest.writeString(description);
        dest.writeString(exceptionMessage);
        dest.writeByte((byte) (state == null ? 0 : state ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GetSignatureSetupsResponse> CREATOR = new Creator<GetSignatureSetupsResponse>() {
        @Override
        public GetSignatureSetupsResponse createFromParcel(Parcel in) {
            return new GetSignatureSetupsResponse(in);
        }

        @Override
        public GetSignatureSetupsResponse[] newArray(int size) {
            return new GetSignatureSetupsResponse[size];
        }
    };

    public ArrayList<SignatureSetup> getSignatureSetup() {
        return signatureSetup;
    }

    public void setSignatureSetup(ArrayList<SignatureSetup> signatureSetup) {
        this.signatureSetup = signatureSetup;
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
