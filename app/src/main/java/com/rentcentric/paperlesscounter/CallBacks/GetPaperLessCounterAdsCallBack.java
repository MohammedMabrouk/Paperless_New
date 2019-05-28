package com.rentcentric.paperlesscounter.CallBacks;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.rentcentric.paperlesscounter.Activities.AdsActivity;
import com.rentcentric.paperlesscounter.Utility.Extensions;
import com.rentcentric.paperlesscounter.Models.Requests.GetPaperLessCounterAdsRequest;
import com.rentcentric.paperlesscounter.Models.Responses.GetPaperLessCounterAdsResponse;
import com.rentcentric.paperlesscounter.Network.RetrofitFactory;
import com.rentcentric.paperlesscounter.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPaperLessCounterAdsCallBack implements Callback<GetPaperLessCounterAdsResponse> {

    private AppCompatActivity context;

    public GetPaperLessCounterAdsCallBack(AppCompatActivity context, GetPaperLessCounterAdsRequest request) {
        this.context = context;

        RetrofitFactory.build().GetPaperLessCounterAds(request).enqueue(this);
    }

    @Override
    public void onResponse(Call<GetPaperLessCounterAdsResponse> call, Response<GetPaperLessCounterAdsResponse> response) {
        if (response.isSuccessful()) {
            if (response.body().getStatusInfo().getStatusCode().equals("0")) {
                ((AdsActivity) context).onGetPaperLessCounterAdsCallBack(response.body());
            } else {
                Extensions.Dialog(context, response.body().getStatusInfo().getDescription());
            }
        } else {
            Extensions.Dialog(context, context.getString(R.string.invalid_response) + " (GetPaperLessCounterAds API)");
        }
    }

    @Override
    public void onFailure(Call<GetPaperLessCounterAdsResponse> call, Throwable t) {
        Toast.makeText(context, context.getString(R.string.server_connection_error), Toast.LENGTH_LONG).show();
    }
}