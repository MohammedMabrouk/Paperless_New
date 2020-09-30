
package com.rentcentric.paperlesscounter.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaperLessAdminLoginResult {

    @SerializedName("AdminId")
    @Expose
    private String adminId;
    @SerializedName("LocationId")
    @Expose
    private Integer locationId;
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("PaperLessAdminLoginId")
    @Expose
    private Integer paperLessAdminLoginId;
    @SerializedName("Token")
    @Expose
    private String token;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getPaperLessAdminLoginId() {
        return paperLessAdminLoginId;
    }

    public void setPaperLessAdminLoginId(Integer paperLessAdminLoginId) {
        this.paperLessAdminLoginId = paperLessAdminLoginId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
