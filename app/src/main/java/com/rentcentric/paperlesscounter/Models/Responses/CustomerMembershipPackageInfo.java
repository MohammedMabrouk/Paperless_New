
package com.rentcentric.paperlesscounter.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerMembershipPackageInfo {

    @SerializedName("CustomerMembershipPackageID")
    @Expose
    private Integer customerMembershipPackageID;
    @SerializedName("MembershipPackageID")
    @Expose
    private Integer membershipPackageID;
    @SerializedName("MembershipPackageName")
    @Expose
    private String membershipPackageName;
    @SerializedName("DrivingCredit")
    @Expose
    private Integer drivingCredit;
    @SerializedName("MembershipPeriod")
    @Expose
    private String membershipPeriod;
    @SerializedName("MembershipFee")
    @Expose
    private Integer membershipFee;
    @SerializedName("ApplicationFee")
    @Expose
    private Integer applicationFee;
    @SerializedName("AccountSetupFee")
    @Expose
    private Integer accountSetupFee;
    @SerializedName("BillingPeriod")
    @Expose
    private String billingPeriod;

    public Integer getCustomerMembershipPackageID() {
        return customerMembershipPackageID;
    }

    public void setCustomerMembershipPackageID(Integer customerMembershipPackageID) {
        this.customerMembershipPackageID = customerMembershipPackageID;
    }

    public Integer getMembershipPackageID() {
        return membershipPackageID;
    }

    public void setMembershipPackageID(Integer membershipPackageID) {
        this.membershipPackageID = membershipPackageID;
    }

    public String getMembershipPackageName() {
        return membershipPackageName;
    }

    public void setMembershipPackageName(String membershipPackageName) {
        this.membershipPackageName = membershipPackageName;
    }

    public Integer getDrivingCredit() {
        return drivingCredit;
    }

    public void setDrivingCredit(Integer drivingCredit) {
        this.drivingCredit = drivingCredit;
    }

    public String getMembershipPeriod() {
        return membershipPeriod;
    }

    public void setMembershipPeriod(String membershipPeriod) {
        this.membershipPeriod = membershipPeriod;
    }

    public Integer getMembershipFee() {
        return membershipFee;
    }

    public void setMembershipFee(Integer membershipFee) {
        this.membershipFee = membershipFee;
    }

    public Integer getApplicationFee() {
        return applicationFee;
    }

    public void setApplicationFee(Integer applicationFee) {
        this.applicationFee = applicationFee;
    }

    public Integer getAccountSetupFee() {
        return accountSetupFee;
    }

    public void setAccountSetupFee(Integer accountSetupFee) {
        this.accountSetupFee = accountSetupFee;
    }

    public String getBillingPeriod() {
        return billingPeriod;
    }

    public void setBillingPeriod(String billingPeriod) {
        this.billingPeriod = billingPeriod;
    }

}
