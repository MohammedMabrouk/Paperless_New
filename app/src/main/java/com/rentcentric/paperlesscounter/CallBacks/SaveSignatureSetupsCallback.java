package com.rentcentric.paperlesscounter.CallBacks;

import android.app.ProgressDialog;
import android.widget.Toast;

import com.rentcentric.paperlesscounter.Activities.MainActivity;
import com.rentcentric.paperlesscounter.Models.Requests.SaveSignatureRequest;
import com.rentcentric.paperlesscounter.Models.Responses.SaveSignatureResponse;
import com.rentcentric.paperlesscounter.Network.RetrofitFactory;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.R;
import com.rentcentric.paperlesscounter.Utility.Extensions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveSignatureSetupsCallback implements Callback<SaveSignatureResponse> {
    private MainActivity mainActivity;
    private LoginPreference loginPreference;
    private ProgressDialog progressDialog;

    public SaveSignatureSetupsCallback(MainActivity mainActivity, SaveSignatureRequest saveSignatureRequest) {
        this.mainActivity = mainActivity;
        loginPreference = new LoginPreference(mainActivity);
        progressDialog = ProgressDialog.show(mainActivity, "", mainActivity.getString(R.string.loading));
        RetrofitFactory.getClientService(
                loginPreference.getServerName(),
                loginPreference.getClientId(),
                loginPreference.getToken()
        ).saveSignatureSetups(
                saveSignatureRequest
        ).enqueue(this);
    }

    @Override
    public void onResponse(Call<SaveSignatureResponse> call, Response<SaveSignatureResponse> response) {
        progressDialog.dismiss();
        if (response.isSuccessful()) {
            if (response.body() != null && response.body().getState()) {
                // return signature list
                mainActivity.onSaveSignaturesCallBack();
            } else {
                Toast.makeText(mainActivity, "SaveSignature API: " + response.body().getDescription(), Toast.LENGTH_LONG).show();
            }
        } else {
            Extensions.Dialog(mainActivity, mainActivity.getString(R.string.invalid_response) + " (SaveSignature API)");
        }
    }

    @Override
    public void onFailure(Call<SaveSignatureResponse> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(mainActivity, mainActivity.getString(R.string.server_connection_error), Toast.LENGTH_LONG).show();
    }
}
