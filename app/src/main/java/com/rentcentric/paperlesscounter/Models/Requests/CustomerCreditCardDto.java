
package com.rentcentric.paperlesscounter.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rentcentric.paperlesscounter.Models.Responses.PayMethod;

public class CustomerCreditCardDto {

    @SerializedName("PayMethod")
    @Expose
    private PayMethod payMethod;
    @SerializedName("CustomerCreditCardId")
    @Expose
    private Integer customerCreditCardId;
    @SerializedName("CustomerId")
    @Expose
    private Integer customerId;
    @SerializedName("CustomerCreditCardNumber")
    @Expose
    private String customerCreditCardNumber;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("CardHolder")
    @Expose
    private String cardHolder;
    @SerializedName("ExpiryMonth")
    @Expose
    private Integer expiryMonth;
    @SerializedName("ExpiryYear")
    @Expose
    private Integer expiryYear;
    @SerializedName("IsDefault")
    @Expose
    private Boolean isDefault;
    @SerializedName("IsAuthorized")
    @Expose
    private Boolean isAuthorized;

    public CustomerCreditCardDto(PayMethod payMethod, Integer customerId, String customerCreditCardNumber, Boolean isActive, String cardHolder, Integer expiryMonth, Integer expiryYear) {
        this.payMethod = payMethod;
        this.customerId = customerId;
        this.customerCreditCardNumber = customerCreditCardNumber;
        this.isActive = isActive;
        this.cardHolder = cardHolder;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public Integer getCustomerCreditCardId() {
        return customerCreditCardId;
    }

    public void setCustomerCreditCardId(Integer customerCreditCardId) {
        this.customerCreditCardId = customerCreditCardId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerCreditCardNumber() {
        return customerCreditCardNumber;
    }

    public void setCustomerCreditCardNumber(String customerCreditCardNumber) {
        this.customerCreditCardNumber = customerCreditCardNumber;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Integer getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(Integer expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public Integer getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(Integer expiryYear) {
        this.expiryYear = expiryYear;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Boolean getIsAuthorized() {
        return isAuthorized;
    }

    public void setIsAuthorized(Boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

}
