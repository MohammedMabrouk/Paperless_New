package com.rentcentric.paperlesscounter.Network;

import com.rentcentric.paperlesscounter.Models.Requests.AddNewCustomerRequest;
import com.rentcentric.paperlesscounter.Models.Requests.GetMobileRequestsRequest;
import com.rentcentric.paperlesscounter.Models.Requests.GetPaperLessAgreementRequest;
import com.rentcentric.paperlesscounter.Models.Requests.GetPaperLessCounterAdsRequest;
import com.rentcentric.paperlesscounter.Models.Requests.MobileUserLoginRequest;
import com.rentcentric.paperlesscounter.Models.Requests.PaperLessAdminLogOutRequest;
import com.rentcentric.paperlesscounter.Models.Requests.PaperLessAdminLoginRequest;
import com.rentcentric.paperlesscounter.Models.Requests.SaveCustomerSignatureRequest;
import com.rentcentric.paperlesscounter.Models.Requests.UpdateCustomerRequest;
import com.rentcentric.paperlesscounter.Models.Responses.AddNewCustomerResponse;
import com.rentcentric.paperlesscounter.Models.Responses.GetCountriesResponse;
import com.rentcentric.paperlesscounter.Models.Responses.GetCustomerInfoResponse;
import com.rentcentric.paperlesscounter.Models.Responses.GetMobileRequestsResponse;
import com.rentcentric.paperlesscounter.Models.Responses.GetPaperLessAgreementResponse;
import com.rentcentric.paperlesscounter.Models.Responses.GetPaperLessCounterAdsResponse;
import com.rentcentric.paperlesscounter.Models.Responses.GetPayMethodsResponse;
import com.rentcentric.paperlesscounter.Models.Responses.GetStatesResponse;
import com.rentcentric.paperlesscounter.Models.Responses.MobileUserLoginResponse;
import com.rentcentric.paperlesscounter.Models.Responses.PaperLessAdminLogOutResponse;
import com.rentcentric.paperlesscounter.Models.Responses.PaperLessAdminLoginResponse;
import com.rentcentric.paperlesscounter.Models.Responses.SaveCustomerSignatureResponse;
import com.rentcentric.paperlesscounter.Models.Responses.UpdateCustomerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIs {

    // login
    @POST("MobileUserLogin")
    Call<MobileUserLoginResponse> mobileUserLogin(@Body MobileUserLoginRequest mobileUserLoginRequest);

    @POST("PaperLess/PaperLessAdminLogin")
    Call<PaperLessAdminLoginResponse> paperLessAdminLogin(@Body PaperLessAdminLoginRequest paperLessAdminLoginRequest);

    // logout
    @POST("PaperLess/PaperLessAdminLogOut")
    Call<PaperLessAdminLogOutResponse> paperLessAdminLogOut(@Body PaperLessAdminLogOutRequest paperLessAdminLogOutRequest);


    // get requests
    @POST("PaperLess/GetMobileRequests")
    Call<GetMobileRequestsResponse> getMobileRequests(@Body GetMobileRequestsRequest getMobileRequestsRequest);


    @POST("PaperLess/PaperLessAgreement")
    Call<GetPaperLessAgreementResponse> getPaperLessAgreement(@Body GetPaperLessAgreementRequest getPaperLessAgreementRequest);

    @POST("SaveCustomerSignature")
    Call<SaveCustomerSignatureResponse> saveCustomerSignature(@Body SaveCustomerSignatureRequest saveCustomerSignatureRequest);

    @POST("PaperLess/GetPaperLessCounterAds")
    Call<GetPaperLessCounterAdsResponse> getPaperLessCounterAds(@Body GetPaperLessCounterAdsRequest getPaperLessCounterAdsRequest);

    @GET("Setting/GetStates")
    Call<GetStatesResponse> getStates();

    @GET("Setting/GetCountries")
    Call<GetCountriesResponse> getCountries();

    @GET("Setting/GetPayMethods")
    Call<GetPayMethodsResponse> getPayMethods();


    // customer
    @POST("Customer/CustomerRegistration")
    Call<AddNewCustomerResponse> addNewCustomer(@Body AddNewCustomerRequest request);

    @POST("Customer/UpdateCustomer")
    Call<UpdateCustomerResponse> updateCustomerInfo(@Body UpdateCustomerRequest updateCustomerRequest);

    @GET("Customer/GetCustomer")
    Call<GetCustomerInfoResponse> getCustomerInformation(@Query("customerId") String customerId);

}