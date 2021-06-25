
package com.rentcentric.paperlesscounter.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddNewCustomerRequest {

    @SerializedName("CustomerCreditCardDto")
    @Expose
    private CustomerCreditCardDto customerCreditCardDto;
    @SerializedName("LocationId")
    @Expose
    private Integer locationId;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
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
    @SerializedName("CountryCode")
    @Expose
    private String countryCode;
    @SerializedName("Zip")
    @Expose
    private String zip;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Birthday")
    @Expose
    private String birthday;
    @SerializedName("Nationality")
    @Expose
    private String nationality;
    @SerializedName("CityOfBirth")
    @Expose
    private String cityOfBirth;
    @SerializedName("NationalInsuranceNumber")
    @Expose
    private String nationalInsuranceNumber;
    @SerializedName("PassportType")
    @Expose
    private String passportType;
    @SerializedName("PassportNumber")
    @Expose
    private String passportNumber;
    @SerializedName("PassportCountryCode")
    @Expose
    private String passportCountryCode;
    @SerializedName("PassportExpiry")
    @Expose
    private String passportExpiry;
    @SerializedName("PassportIssuingDate")
    @Expose
    private String passportIssuingDate;
    @SerializedName("CompanyPhone")
    @Expose
    private String companyPhone;
    @SerializedName("DriverLicenseFullName")
    @Expose
    private String driverLicenseFullName;
    @SerializedName("LicenseNumber")
    @Expose
    private String licenseNumber;
    @SerializedName("LicenseStateCode")
    @Expose
    private String licenseStateCode;
    @SerializedName("LicenseCountryCode")
    @Expose
    private String licenseCountryCode;
    @SerializedName("LicenseExpiryDate")
    @Expose
    private String licenseExpiryDate;
    @SerializedName("EmailMarketingUnSubscribtion")
    @Expose
    private Boolean emailMarketingUnSubscribtion;
    @SerializedName("InsuranceCompany")
    @Expose
    private String insuranceCompany;
    @SerializedName("PolicyNumber")
    @Expose
    private String policyNumber;
    @SerializedName("MembershipTypeId")
    @Expose
    private Integer membershipTypeId;
    @SerializedName("TlcLicenceNumber")
    @Expose
    private String tlcLicenceNumber;
    @SerializedName("Ssn")
    @Expose
    private String ssn;
    @SerializedName("CustomerNotes")
    @Expose
    private String customerNotes;
    @SerializedName("Cvv")
    @Expose
    private String cvv;
    @SerializedName("CompanyId")
    @Expose
    private Integer companyId;
    @SerializedName("MobileRequestId")
    @Expose
    private Integer mobileRequestId;
    @SerializedName("Airport")
    @Expose
    private String airport;
    @SerializedName("Airline")
    @Expose
    private String airline;
    @SerializedName("FlightNumber")
    @Expose
    private String flightNumber;
    @SerializedName("localInformation")
    @Expose
    private String localInformation;
    @SerializedName("CompanyName")
    @Expose
    private String companyName;


    public AddNewCustomerRequest(CustomerCreditCardDto customerCreditCardDto,
                                 Integer locationId,
                                 String email,
                                 String firstName,
                                 String lastName,
                                 String phone,
                                 String licenseNumber,
                                 String licenseStateCode,
                                 String licenseCountryCode,
                                 String licenseExpiryDate,
                                 String dateOfBirth,
                                 int companyId) {
        this.customerCreditCardDto = customerCreditCardDto;
        this.locationId = locationId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.licenseNumber = licenseNumber;
        this.licenseStateCode = licenseStateCode;
        this.licenseCountryCode = licenseCountryCode;
        this.licenseExpiryDate = licenseExpiryDate;
        this.birthday = dateOfBirth;
        this.companyId = companyId;
    }

    public AddNewCustomerRequest(
            String firstName,
                                 String lastName,
                                 String email,
                                 String phone,
                                 String birthday,
                                 String address,
                                 String city,
                                 String stateCode,
                                 String countryCode,
                                 String zip,
                                 String licenseNumber,
                                 String licenseExpiryDate,
                                 String licenseStateCode,
                                 CustomerCreditCardDto customerCreditCardDto,
                                 String companyName,
                                 String companyPhone,
                                 String insuranceCompany,
                                 String policyNumber,
                                 String ssn,
                                 int mobileRequestId,
                                 String airport,
                                 String airline,
                                 String flightNumber,
                                 String localInformation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.address = address;
        this.city = city;
        this.stateCode = stateCode;
        this.countryCode = countryCode;
        this.zip = zip;
        this.licenseNumber = licenseNumber;
        this.licenseExpiryDate = licenseExpiryDate;
        this.licenseStateCode = licenseStateCode;
        this.customerCreditCardDto = customerCreditCardDto;
        this.companyName = companyName;
        this.companyPhone = companyPhone;
        this.insuranceCompany = insuranceCompany;
        this.policyNumber = policyNumber;
        this.ssn = ssn;
        this.mobileRequestId = mobileRequestId;
        this.airport = airport;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.localInformation = localInformation;
    }

    public CustomerCreditCardDto getCustomerCreditCardDto() {
        return customerCreditCardDto;
    }

    public void setCustomerCreditCardDto(CustomerCreditCardDto customerCreditCardDto) {
        this.customerCreditCardDto = customerCreditCardDto;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCityOfBirth() {
        return cityOfBirth;
    }

    public void setCityOfBirth(String cityOfBirth) {
        this.cityOfBirth = cityOfBirth;
    }

    public String getNationalInsuranceNumber() {
        return nationalInsuranceNumber;
    }

    public void setNationalInsuranceNumber(String nationalInsuranceNumber) {
        this.nationalInsuranceNumber = nationalInsuranceNumber;
    }

    public String getPassportType() {
        return passportType;
    }

    public void setPassportType(String passportType) {
        this.passportType = passportType;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportCountryCode() {
        return passportCountryCode;
    }

    public void setPassportCountryCode(String passportCountryCode) {
        this.passportCountryCode = passportCountryCode;
    }

    public String getPassportExpiry() {
        return passportExpiry;
    }

    public void setPassportExpiry(String passportExpiry) {
        this.passportExpiry = passportExpiry;
    }

    public String getPassportIssuingDate() {
        return passportIssuingDate;
    }

    public void setPassportIssuingDate(String passportIssuingDate) {
        this.passportIssuingDate = passportIssuingDate;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getDriverLicenseFullName() {
        return driverLicenseFullName;
    }

    public void setDriverLicenseFullName(String driverLicenseFullName) {
        this.driverLicenseFullName = driverLicenseFullName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseStateCode() {
        return licenseStateCode;
    }

    public void setLicenseStateCode(String licenseStateCode) {
        this.licenseStateCode = licenseStateCode;
    }

    public String getLicenseCountryCode() {
        return licenseCountryCode;
    }

    public void setLicenseCountryCode(String licenseCountryCode) {
        this.licenseCountryCode = licenseCountryCode;
    }

    public String getLicenseExpiryDate() {
        return licenseExpiryDate;
    }

    public void setLicenseExpiryDate(String licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }

    public Boolean getEmailMarketingUnSubscribtion() {
        return emailMarketingUnSubscribtion;
    }

    public void setEmailMarketingUnSubscribtion(Boolean emailMarketingUnSubscribtion) {
        this.emailMarketingUnSubscribtion = emailMarketingUnSubscribtion;
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

    public Integer getMembershipTypeId() {
        return membershipTypeId;
    }

    public void setMembershipTypeId(Integer membershipTypeId) {
        this.membershipTypeId = membershipTypeId;
    }

    public String getTlcLicenceNumber() {
        return tlcLicenceNumber;
    }

    public void setTlcLicenceNumber(String tlcLicenceNumber) {
        this.tlcLicenceNumber = tlcLicenceNumber;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getCustomerNotes() {
        return customerNotes;
    }

    public void setCustomerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getLocalInformation() {
        return localInformation;
    }

    public void setLocalInformation(String localInformation) {
        this.localInformation = localInformation;
    }
}
