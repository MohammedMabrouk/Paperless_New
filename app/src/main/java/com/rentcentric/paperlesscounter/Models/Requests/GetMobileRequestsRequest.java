
package com.rentcentric.paperlesscounter.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMobileRequestsRequest {

    public GetMobileRequestsRequest(String adminId, Integer locationId, String appName) {
        this.adminId = adminId;
        this.locationId = locationId;
        this.appName = appName;
    }

    @SerializedName("AdminId")
    @Expose
    private String adminId;
    @SerializedName("LocationId")
    @Expose
    private Integer locationId;
    @SerializedName("AppName")
    @Expose
    private String appName;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

}
