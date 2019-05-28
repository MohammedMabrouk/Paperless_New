package com.rentcentric.paperlesscounter.Network;

import com.rentcentric.paperlesscounter.Models.Requests.GetPaperLessAgreementRequest;
import com.rentcentric.paperlesscounter.Models.Requests.GetPaperLessCounterAdsRequest;
import com.rentcentric.paperlesscounter.Models.Requests.MobileUserLoginRequest;
import com.rentcentric.paperlesscounter.Models.Requests.PaperLessAdminLoginRequest;
import com.rentcentric.paperlesscounter.Models.Requests.SaveCustomerSignatureRequest;
import com.rentcentric.paperlesscounter.Models.Responses.GetPaperLessAgreementResponse;
import com.rentcentric.paperlesscounter.Models.Responses.GetPaperLessCounterAdsResponse;
import com.rentcentric.paperlesscounter.Models.Responses.MobileUserLoginResponse;
import com.rentcentric.paperlesscounter.Models.Responses.PaperLessAdminLoginResponse;
import com.rentcentric.paperlesscounter.Models.Responses.SaveCustomerSignatureResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIs {

    @POST("MobileUserLogin")
    Call<MobileUserLoginResponse> MobileUserLogin(@Body MobileUserLoginRequest mobileUserLoginRequest);

    @POST("PaperLessAdminLogin")
    Call<PaperLessAdminLoginResponse> PaperLessAdminLogin(@Body PaperLessAdminLoginRequest paperLessAdminLoginRequest);

    @POST("GetPaperLessAgreement")
    Call<GetPaperLessAgreementResponse> GetPaperLessAgreement(@Body GetPaperLessAgreementRequest getPaperLessAgreementRequest);

    @POST("SaveCustomerSignature")
    Call<SaveCustomerSignatureResponse> SaveCustomerSignature(@Body SaveCustomerSignatureRequest saveCustomerSignatureRequest);

    @POST("GetPaperLessCounterAds")
    Call<GetPaperLessCounterAdsResponse> GetPaperLessCounterAds(@Body GetPaperLessCounterAdsRequest getPaperLessCounterAdsRequest);
}