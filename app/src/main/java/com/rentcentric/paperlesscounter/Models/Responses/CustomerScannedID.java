
package com.rentcentric.paperlesscounter.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerScannedID {

    @SerializedName("ScannedIdImageUrl")
    @Expose
    private String scannedIdImageUrl;
    @SerializedName("FileType")
    @Expose
    private String fileType;
    @SerializedName("CustomerId")
    @Expose
    private Integer customerId;

    public String getScannedIdImageUrl() {
        return scannedIdImageUrl;
    }

    public void setScannedIdImageUrl(String scannedIdImageUrl) {
        this.scannedIdImageUrl = scannedIdImageUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

}
