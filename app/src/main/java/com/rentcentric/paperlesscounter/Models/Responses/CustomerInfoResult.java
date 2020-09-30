
package com.rentcentric.paperlesscounter.Models.Responses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerInfoResult {

    @SerializedName("CustomerInfo")
    @Expose
    private CustomerInfo customerInfo;
    @SerializedName("CustomerCreditCards")
    @Expose
    private List<CustomerCreditCard> customerCreditCards = null;
    @SerializedName("CustomerScannedIDs")
    @Expose
    private List<CustomerScannedID> customerScannedIDs = null;
    @SerializedName("CustomerMembershipPackageInfo")
    @Expose
    private CustomerMembershipPackageInfo customerMembershipPackageInfo;

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public List<CustomerCreditCard> getCustomerCreditCards() {
        return customerCreditCards;
    }

    public void setCustomerCreditCards(List<CustomerCreditCard> customerCreditCards) {
        this.customerCreditCards = customerCreditCards;
    }

    public List<CustomerScannedID> getCustomerScannedIDs() {
        return customerScannedIDs;
    }

    public void setCustomerScannedIDs(List<CustomerScannedID> customerScannedIDs) {
        this.customerScannedIDs = customerScannedIDs;
    }

    public CustomerMembershipPackageInfo getCustomerMembershipPackageInfo() {
        return customerMembershipPackageInfo;
    }

    public void setCustomerMembershipPackageInfo(CustomerMembershipPackageInfo customerMembershipPackageInfo) {
        this.customerMembershipPackageInfo = customerMembershipPackageInfo;
    }

}
