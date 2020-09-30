
package com.rentcentric.paperlesscounter.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateCustomerResult {

    @SerializedName("CustomerId")
    @Expose
    private Integer customerId;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("MiddleName")
    @Expose
    private String middleName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("SSN")
    @Expose
    private String sSN;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Address2")
    @Expose
    private String address2;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("StateCode")
    @Expose
    private String stateCode;
    @SerializedName("Zip")
    @Expose
    private String zip;
    @SerializedName("CountryCode")
    @Expose
    private String countryCode;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Fax")
    @Expose
    private String fax;
    @SerializedName("Memo")
    @Expose
    private String memo;
    @SerializedName("LicenseNumber")
    @Expose
    private String licenseNumber;
    @SerializedName("LicenseExpiry")
    @Expose
    private String licenseExpiry;
    @SerializedName("LicenseState")
    @Expose
    private String licenseState;
    @SerializedName("LicenseCountry")
    @Expose
    private String licenseCountry;
    @SerializedName("CompanyName")
    @Expose
    private String companyName;
    @SerializedName("CompanyContactName")
    @Expose
    private String companyContactName;
    @SerializedName("CompanyAddress")
    @Expose
    private String companyAddress;
    @SerializedName("CompanyCity")
    @Expose
    private String companyCity;
    @SerializedName("CompanyStateCode")
    @Expose
    private String companyStateCode;
    @SerializedName("CompanyCountryCode")
    @Expose
    private String companyCountryCode;
    @SerializedName("CompanyZip")
    @Expose
    private String companyZip;
    @SerializedName("CompanyPhone")
    @Expose
    private String companyPhone;
    @SerializedName("CompanyFax")
    @Expose
    private String companyFax;
    @SerializedName("CompanyEmail")
    @Expose
    private String companyEmail;
    @SerializedName("LocationId")
    @Expose
    private Integer locationId;
    @SerializedName("SourceOfReferral")
    @Expose
    private String sourceOfReferral;
    @SerializedName("Region")
    @Expose
    private String region;
    @SerializedName("Birthday")
    @Expose
    private String birthday;
    @SerializedName("LastModifiedDate")
    @Expose
    private String lastModifiedDate;
    @SerializedName("FreqTravelerId")
    @Expose
    private String freqTravelerId;
    @SerializedName("InsuranceCompany")
    @Expose
    private String insuranceCompany;
    @SerializedName("PolicyNumber")
    @Expose
    private String policyNumber;
    @SerializedName("InsuranceExpiry")
    @Expose
    private String insuranceExpiry;
    @SerializedName("DriversLicenseIssueDate")
    @Expose
    private String driversLicenseIssueDate;
    @SerializedName("Agent")
    @Expose
    private String agent;
    @SerializedName("InsurancePhone")
    @Expose
    private String insurancePhone;
    @SerializedName("InsuranceFax")
    @Expose
    private String insuranceFax;
    @SerializedName("ApprovedBy")
    @Expose
    private String approvedBy;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("LicenseValidated")
    @Expose
    private Boolean licenseValidated;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("PassportNumber")
    @Expose
    private String passportNumber;
    @SerializedName("PassportExpiry")
    @Expose
    private String passportExpiry;
    @SerializedName("PassportCountry")
    @Expose
    private String passportCountry;
    @SerializedName("PassportType")
    @Expose
    private String passportType;
    @SerializedName("PassportIssuingDate")
    @Expose
    private String passportIssuingDate;
    @SerializedName("JobPosition")
    @Expose
    private String jobPosition;
    @SerializedName("DLScan")
    @Expose
    private String dLScan;
    @SerializedName("Cvv")
    @Expose
    private String cvv;
    @SerializedName("CcTrack")
    @Expose
    private String ccTrack;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;
    @SerializedName("MembershipTypeId")
    @Expose
    private Integer membershipTypeId;
    @SerializedName("SendEmail")
    @Expose
    private Boolean sendEmail;
    @SerializedName("IsRegistrationCompleted")
    @Expose
    private Boolean isRegistrationCompleted;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSSN() {
        return sSN;
    }

    public void setSSN(String sSN) {
        this.sSN = sSN;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseExpiry() {
        return licenseExpiry;
    }

    public void setLicenseExpiry(String licenseExpiry) {
        this.licenseExpiry = licenseExpiry;
    }

    public String getLicenseState() {
        return licenseState;
    }

    public void setLicenseState(String licenseState) {
        this.licenseState = licenseState;
    }

    public String getLicenseCountry() {
        return licenseCountry;
    }

    public void setLicenseCountry(String licenseCountry) {
        this.licenseCountry = licenseCountry;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyContactName() {
        return companyContactName;
    }

    public void setCompanyContactName(String companyContactName) {
        this.companyContactName = companyContactName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyStateCode() {
        return companyStateCode;
    }

    public void setCompanyStateCode(String companyStateCode) {
        this.companyStateCode = companyStateCode;
    }

    public String getCompanyCountryCode() {
        return companyCountryCode;
    }

    public void setCompanyCountryCode(String companyCountryCode) {
        this.companyCountryCode = companyCountryCode;
    }

    public String getCompanyZip() {
        return companyZip;
    }

    public void setCompanyZip(String companyZip) {
        this.companyZip = companyZip;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getSourceOfReferral() {
        return sourceOfReferral;
    }

    public void setSourceOfReferral(String sourceOfReferral) {
        this.sourceOfReferral = sourceOfReferral;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getFreqTravelerId() {
        return freqTravelerId;
    }

    public void setFreqTravelerId(String freqTravelerId) {
        this.freqTravelerId = freqTravelerId;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getInsuranceExpiry() {
        return insuranceExpiry;
    }

    public void setInsuranceExpiry(String insuranceExpiry) {
        this.insuranceExpiry = insuranceExpiry;
    }

    public String getDriversLicenseIssueDate() {
        return driversLicenseIssueDate;
    }

    public void setDriversLicenseIssueDate(String driversLicenseIssueDate) {
        this.driversLicenseIssueDate = driversLicenseIssueDate;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getInsurancePhone() {
        return insurancePhone;
    }

    public void setInsurancePhone(String insurancePhone) {
        this.insurancePhone = insurancePhone;
    }

    public String getInsuranceFax() {
        return insuranceFax;
    }

    public void setInsuranceFax(String insuranceFax) {
        this.insuranceFax = insuranceFax;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getLicenseValidated() {
        return licenseValidated;
    }

    public void setLicenseValidated(Boolean licenseValidated) {
        this.licenseValidated = licenseValidated;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportExpiry() {
        return passportExpiry;
    }

    public void setPassportExpiry(String passportExpiry) {
        this.passportExpiry = passportExpiry;
    }

    public String getPassportCountry() {
        return passportCountry;
    }

    public void setPassportCountry(String passportCountry) {
        this.passportCountry = passportCountry;
    }

    public String getPassportType() {
        return passportType;
    }

    public void setPassportType(String passportType) {
        this.passportType = passportType;
    }

    public String getPassportIssuingDate() {
        return passportIssuingDate;
    }

    public void setPassportIssuingDate(String passportIssuingDate) {
        this.passportIssuingDate = passportIssuingDate;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getDLScan() {
        return dLScan;
    }

    public void setDLScan(String dLScan) {
        this.dLScan = dLScan;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCcTrack() {
        return ccTrack;
    }

    public void setCcTrack(String ccTrack) {
        this.ccTrack = ccTrack;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getMembershipTypeId() {
        return membershipTypeId;
    }

    public void setMembershipTypeId(Integer membershipTypeId) {
        this.membershipTypeId = membershipTypeId;
    }

    public Boolean getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(Boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public Boolean getIsRegistrationCompleted() {
        return isRegistrationCompleted;
    }

    public void setIsRegistrationCompleted(Boolean isRegistrationCompleted) {
        this.isRegistrationCompleted = isRegistrationCompleted;
    }

}
