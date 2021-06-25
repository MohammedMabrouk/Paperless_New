package com.rentcentric.paperlesscounter.Models.Responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignatureSetup implements Parcelable {

    @SerializedName("ConditionID")
    @Expose
    private Integer conditionID;
    @SerializedName("Disclaimer")
    @Expose
    private String disclaimer;
    @SerializedName("InsuranceID")
    @Expose
    private Integer insuranceID;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("MiscChargeID")
    @Expose
    private Integer miscChargeID;
    @SerializedName("SignatureCode")
    @Expose
    private String signatureCode;
    @SerializedName("SignatureSetupId")
    @Expose
    private Integer signatureSetupID;
    @SerializedName("SignatureTypeID")
    @Expose
    private Integer signatureTypeID;

    protected SignatureSetup(Parcel in) {
        if (in.readByte() == 0) {
            conditionID = null;
        } else {
            conditionID = in.readInt();
        }
        disclaimer = in.readString();
        if (in.readByte() == 0) {
            insuranceID = null;
        } else {
            insuranceID = in.readInt();
        }
        byte tmpIsActive = in.readByte();
        isActive = tmpIsActive == 0 ? null : tmpIsActive == 1;
        if (in.readByte() == 0) {
            miscChargeID = null;
        } else {
            miscChargeID = in.readInt();
        }
        signatureCode = in.readString();
        if (in.readByte() == 0) {
            signatureSetupID = null;
        } else {
            signatureSetupID = in.readInt();
        }
        if (in.readByte() == 0) {
            signatureTypeID = null;
        } else {
            signatureTypeID = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (conditionID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(conditionID);
        }
        dest.writeString(disclaimer);
        if (insuranceID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(insuranceID);
        }
        dest.writeByte((byte) (isActive == null ? 0 : isActive ? 1 : 2));
        if (miscChargeID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(miscChargeID);
        }
        dest.writeString(signatureCode);
        if (signatureSetupID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(signatureSetupID);
        }
        if (signatureTypeID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(signatureTypeID);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SignatureSetup> CREATOR = new Creator<SignatureSetup>() {
        @Override
        public SignatureSetup createFromParcel(Parcel in) {
            return new SignatureSetup(in);
        }

        @Override
        public SignatureSetup[] newArray(int size) {
            return new SignatureSetup[size];
        }
    };

    public Integer getConditionID() {
        return conditionID;
    }

    public void setConditionID(Integer conditionID) {
        this.conditionID = conditionID;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public Integer getInsuranceID() {
        return insuranceID;
    }

    public void setInsuranceID(Integer insuranceID) {
        this.insuranceID = insuranceID;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getMiscChargeID() {
        return miscChargeID;
    }

    public void setMiscChargeID(Integer miscChargeID) {
        this.miscChargeID = miscChargeID;
    }

    public String getSignatureCode() {
        return signatureCode;
    }

    public void setSignatureCode(String signatureCode) {
        this.signatureCode = signatureCode;
    }

    public Integer getSignatureSetupID() {
        return signatureSetupID;
    }

    public void setSignatureSetupID(Integer signatureSetupID) {
        this.signatureSetupID = signatureSetupID;
    }

    public Integer getSignatureTypeID() {
        return signatureTypeID;
    }

    public void setSignatureTypeID(Integer signatureTypeID) {
        this.signatureTypeID = signatureTypeID;
    }

    @Override
    public String toString() {
        return "SignatureSetup{" +
                "\"conditionID\" : " + conditionID +
                ", \"disclaimer\" : " + disclaimer + "\"" +
                ", \"insuranceID\" : " + insuranceID +
                ", \"isActive\" : " + isActive +
                ", \"miscChargeID\" : " + miscChargeID +
                ", \"signatureCode\" : \"" + signatureCode + "\"" +
                ", \"signatureSetupID\" : " + signatureSetupID +
                ", \"signatureTypeID\" : " + signatureTypeID +
                '}';
    }
}
