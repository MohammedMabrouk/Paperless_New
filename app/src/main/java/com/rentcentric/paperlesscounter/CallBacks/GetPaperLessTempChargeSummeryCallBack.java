package com.rentcentric.paperlesscounter.CallBacks;

import android.view.View;

import com.rentcentric.paperlesscounter.Activities.ChargesSummaryActivity;
import com.rentcentric.paperlesscounter.Models.Requests.GetPaperLessTempChargeSummeryRequest;
import com.rentcentric.paperlesscounter.Models.Responses.GetPaperLessTempChargeSummeryResponse;
import com.rentcentric.paperlesscounter.Network.RetrofitFactory;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPaperLessTempChargeSummeryCallBack implements Callback<GetPaperLessTempChargeSummeryResponse> {
    private ChargesSummaryActivity chargesSummaryActivity;
    private LoginPreference loginPreference;

    public GetPaperLessTempChargeSummeryCallBack(
            ChargesSummaryActivity chargesSummaryActivity) {
        this.chargesSummaryActivity = chargesSummaryActivity;
        loginPreference = new LoginPreference(chargesSummaryActivity);

        chargesSummaryActivity.loadingPb.setVisibility(View.VISIBLE);

        RetrofitFactory.getClientService(
                loginPreference.getServerName(),
                loginPreference.getClientId(),
                loginPreference.getToken()
        ).getPaperLessTempChargeSummery(
                new GetPaperLessTempChargeSummeryRequest(loginPreference.getAdminId())
        ).enqueue(this);
    }

    @Override
    public void onResponse(Call<GetPaperLessTempChargeSummeryResponse> call, Response<GetPaperLessTempChargeSummeryResponse> response) {
        chargesSummaryActivity.loadingPb.setVisibility(View.GONE);

        if (response.isSuccessful()) {
            if (response.body() != null && response.body().getState()) {
                chargesSummaryActivity.onGetPaperLessChargesSummaryCallBack(response.body());
            } else {
                chargesSummaryActivity.onGetCallBackError(response.body().getDescription());
            }
        } else {
            chargesSummaryActivity.onGetCallBackError(chargesSummaryActivity.getString(R.string.invalid_response) + " (GetPaperLessTempChargeSummery API)");
        }
    }

    @Override
    public void onFailure(Call<GetPaperLessTempChargeSummeryResponse> call, Throwable t) {
        chargesSummaryActivity.loadingPb.setVisibility(View.GONE);
        chargesSummaryActivity.onGetCallBackError(chargesSummaryActivity.getString(R.string.server_connection_error));
    }
}
