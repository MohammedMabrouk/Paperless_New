package com.rentcentric.paperlesscounter.CallBacks;

import androidx.appcompat.app.AppCompatActivity;

import com.rentcentric.paperlesscounter.Activities.MainActivity;
import com.rentcentric.paperlesscounter.Models.Requests.GetPaperLessAgreementRequest;
import com.rentcentric.paperlesscounter.Models.Responses.GetPaperLessAgreementResponse;
import com.rentcentric.paperlesscounter.Network.RetrofitFactory;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPaperLessAgreementCallBack implements Callback<GetPaperLessAgreementResponse> {
    private AppCompatActivity context;
    private LoginPreference loginPreference;

    public GetPaperLessAgreementCallBack(AppCompatActivity context, GetPaperLessAgreementRequest request) {
        this.context = context;
        loginPreference = new LoginPreference(context);
        RetrofitFactory.getClientService(
                loginPreference.getServerName(),
                loginPreference.getClientId(),
                loginPreference.getToken()
        ).getPaperLessAgreement(request).enqueue(this);
    }

    @Override
    public void onResponse(Call<GetPaperLessAgreementResponse> call, Response<GetPaperLessAgreementResponse> response) {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                ((MainActivity) context).onGetPaperLessAgreementCallBack(response.body());
            }
//            else {
//                ((MainActivity) context).onGetCallBackError(response.body().getDescription());
//            }
        } else {
            ((MainActivity) context).onGetCallBackError(context.getString(R.string.invalid_response) + " (PaperLessAgreement API)");
        }
    }

    @Override
    public void onFailure(Call<GetPaperLessAgreementResponse> call, Throwable t) {
        ((MainActivity) context).onGetCallBackError(context.getString(R.string.server_connection_error));
    }
}