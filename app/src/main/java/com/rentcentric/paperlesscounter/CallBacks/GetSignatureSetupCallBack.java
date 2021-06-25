package com.rentcentric.paperlesscounter.CallBacks;

import android.widget.Toast;

import com.rentcentric.paperlesscounter.Activities.MainActivity;
import com.rentcentric.paperlesscounter.Models.Requests.GetSignatureSetupsRequest;
import com.rentcentric.paperlesscounter.Models.Responses.GetSignatureSetupsResponse;
import com.rentcentric.paperlesscounter.Network.RetrofitFactory;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.R;
import com.rentcentric.paperlesscounter.Utility.Extensions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetSignatureSetupCallBack implements Callback<GetSignatureSetupsResponse> {
    private MainActivity mainActivity;
    private LoginPreference loginPreference;

    public GetSignatureSetupCallBack(
            MainActivity mainActivity,
            Integer locationId,
            Integer reservationId,
            Integer agreementId) {
        this.mainActivity = mainActivity;
        loginPreference = new LoginPreference(mainActivity);
        RetrofitFactory.getClientService(
                loginPreference.getServerName(),
                loginPreference.getClientId(),
                loginPreference.getToken()
        ).getSignatureSetups(new GetSignatureSetupsRequest(
                locationId,
                reservationId,
                 agreementId

        )).enqueue(this);
    }

    @Override
    public void onResponse(Call<GetSignatureSetupsResponse> call, Response<GetSignatureSetupsResponse> response) {
        if (response.isSuccessful()) {
            if (response.body() != null && response.body().getState()) {
                // return signature list
                if (response.body().getSignatureSetup().size() > 0) {
                    mainActivity.onGetSignatureSetupSuccess(response.body().getSignatureSetup());
                } else {
                    mainActivity.onGetSignatureSetupSuccess(null);
                }

            } else {
                Toast.makeText(mainActivity, "GetSignatureSetups API: " + response.body().getDescription(), Toast.LENGTH_LONG).show();
            }
        } else {
            Extensions.Dialog(mainActivity, mainActivity.getString(R.string.invalid_response) + " (GetSignatureSetups API)");
        }
    }

    @Override
    public void onFailure(Call<GetSignatureSetupsResponse> call, Throwable t) {
        Toast.makeText(mainActivity, mainActivity.getString(R.string.server_connection_error), Toast.LENGTH_LONG).show();
    }
}
