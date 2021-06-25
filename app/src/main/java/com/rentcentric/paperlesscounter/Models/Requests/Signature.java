package com.rentcentric.paperlesscounter.Models.Requests;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Signature implements Parcelable {

    @SerializedName("SignatureImage")
    @Expose
    private String signatureImage;
    @SerializedName("SignatureSetupId")
    @Expose
    private Integer signatureSetupID;

    public Signature() {

    }

    protected Signature(Parcel in) {
        signatureImage = in.readString();
        if (in.readByte() == 0) {
            signatureSetupID = null;
        } else {
            signatureSetupID = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(signatureImage);
        if (signatureSetupID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(signatureSetupID);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Signature> CREATOR = new Creator<Signature>() {
        @Override
        public Signature createFromParcel(Parcel in) {
            return new Signature(in);
        }

        @Override
        public Signature[] newArray(int size) {
            return new Signature[size];
        }
    };

    @Override
    public String toString() {
        return "{" +
                "\"signatureImage\": \"" + signatureImage + '\"' +
                ", \"signatureSetupID\": " + signatureSetupID +
                '}';
    }

    public String getSignatureImage() {
        return signatureImage;
    }

    public void setSignatureImage(String signatureImage) {
        this.signatureImage = signatureImage;
    }

    public Integer getSignatureSetupID() {
        return signatureSetupID;
    }

    public void setSignatureSetupID(Integer signatureSetupID) {
        this.signatureSetupID = signatureSetupID;
    }

}
