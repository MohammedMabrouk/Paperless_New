package com.rentcentric.paperlesscounter.CallBacks;

import android.support.v7.app.AppCompatActivity;

import com.rentcentric.paperlesscounter.Activities.MainActivity;
import com.rentcentric.paperlesscounter.Models.Requests.GetPaperLessAgreementRequest;
import com.rentcentric.paperlesscounter.Models.Responses.GetPaperLessAgreementResponse;
import com.rentcentric.paperlesscounter.Network.RetrofitFactory;
import com.rentcentric.paperlesscounter.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPaperLessAgreementCallBack implements Callback<GetPaperLessAgreementResponse> {

    private AppCompatActivity context;

    public GetPaperLessAgreementCallBack(AppCompatActivity context, GetPaperLessAgreementRequest request) {
        this.context = context;

        RetrofitFactory.build().GetPaperLessAgreement(request).enqueue(this);
    }

    @Override
    public void onResponse(Call<GetPaperLessAgreementResponse> call, Response<GetPaperLessAgreementResponse> response) {
        if (response.isSuccessful()) {
            if (response.body().getStatusInfo().getStatusCode().equals("0")) {
                ((MainActivity) context).onGetPaperLessAgreementCallBack(response.body());
            } else {
                ((MainActivity) context).onGetPaperLessAgreementCallBack(response.body().getStatusInfo().getDescription());
            }
        } else {
            ((MainActivity) context).onGetPaperLessAgreementCallBack(context.getString(R.string.invalid_response) + " (GetPaperLessAgreement API)");
        }
    }

    @Override
    public void onFailure(Call<GetPaperLessAgreementResponse> call, Throwable t) {
        ((MainActivity) context).onGetPaperLessAgreementCallBack(context.getString(R.string.server_connection_error));
    }
}