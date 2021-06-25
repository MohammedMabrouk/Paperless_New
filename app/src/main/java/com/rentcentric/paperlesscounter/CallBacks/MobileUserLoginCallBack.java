package com.rentcentric.paperlesscounter.CallBacks;

import android.app.ProgressDialog;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rentcentric.paperlesscounter.Models.Requests.MobileUserLoginRequest;
import com.rentcentric.paperlesscounter.Models.Requests.PaperLessAdminLoginRequest;
import com.rentcentric.paperlesscounter.Models.Responses.MobileUserLoginResponse;
import com.rentcentric.paperlesscounter.Network.RetrofitFactory;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.R;
import com.rentcentric.paperlesscounter.Utility.Extensions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileUserLoginCallBack implements Callback<MobileUserLoginResponse> {

    private AppCompatActivity context;
    private ProgressDialog progressDialog;
    private MobileUserLoginRequest request;
    private LoginPreference loginPreference;

    public MobileUserLoginCallBack(AppCompatActivity context, MobileUserLoginRequest request) {
        this.context = context;
        this.request = request;
        loginPreference = new LoginPreference(context);
        progressDialog = ProgressDialog.show(context, "", context.getString(R.string.loading));

        RetrofitFactory.getUserLoginService().mobileUserLogin(request).enqueue(this);
    }

    @Override
    public void onResponse(Call<MobileUserLoginResponse> call, Response<MobileUserLoginResponse> response) {
        if (response.isSuccessful()) {
            if (response.body().getStatus().getStatusCode().equals("0")) {
                loginPreference.setServerName(response.body().getServerName());
                loginPreference.setClientId(response.body().getClientID().toString());

                new PaperLessAdminLoginCallBack(context, progressDialog, new PaperLessAdminLoginRequest(
                        request.getEmail(),
                        loginPreference.getClientId(),
                        request.getPassword()));
            } else {
                progressDialog.dismiss();
                Extensions.Dialog(context, response.body().getStatus().getDescription());
            }
        } else {
            progressDialog.dismiss();
            Extensions.Dialog(context, context.getString(R.string.invalid_response) + " (MobileUserLogin API)");
        }
    }

    @Override
    public void onFailure(Call<MobileUserLoginResponse> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(context, context.getString(R.string.server_connection_error), Toast.LENGTH_LONG).show();
    }
}