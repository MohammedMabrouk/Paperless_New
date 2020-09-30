
package com.rentcentric.paperlesscounter.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaperLessAdminLogOutRequest {

    @SerializedName("PaperLessAdminLoginId")
    @Expose
    private Integer paperLessAdminLoginId;
    @SerializedName("locationId")
    @Expose
    private Integer locationId;

    public PaperLessAdminLogOutRequest(Integer paperLessAdminLoginId, Integer locationId) {
        this.paperLessAdminLoginId = paperLessAdminLoginId;
        this.locationId = locationId;
    }

    public Integer getPaperLessAdminLoginId() {
        return paperLessAdminLoginId;
    }

    public void setPaperLessAdminLoginId(Integer paperLessAdminLoginId) {
        this.paperLessAdminLoginId = paperLessAdminLoginId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

}
