package com.rentcentric.paperlesscounter.CallBacks;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.rentcentric.paperlesscounter.Models.Requests.MobileUserLoginRequest;
import com.rentcentric.paperlesscounter.Models.Requests.PaperLessAdminLoginRequest;
import com.rentcentric.paperlesscounter.Models.Responses.MobileUserLoginResponse;
import com.rentcentric.paperlesscounter.Network.RetrofitFactory;
import com.rentcentric.paperlesscounter.R;
import com.rentcentric.paperlesscounter.Utility.Extensions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileUserLoginCallBack implements Callback<MobileUserLoginResponse> {

    private AppCompatActivity context;
    private ProgressDialog progressDialog;
    private MobileUserLoginRequest request;

    public MobileUserLoginCallBack(AppCompatActivity context, MobileUserLoginRequest request) {
        this.context = context;
        this.request = request;
        progressDialog = ProgressDialog.show(context, "", context.getString(R.string.loading));

        RetrofitFactory.buildPortal().MobileUserLogin(request).enqueue(this);
    }

    @Override
    public void onResponse(Call<MobileUserLoginResponse> call, Response<MobileUserLoginResponse> response) {
        if (response.isSuccessful()) {
            if (response.body().getStatus().getStatusCode().equals("0")) {
                RetrofitFactory.WWW = response.body().getServerName();
                RetrofitFactory.ClientID = response.body().getClientID().toString();

                new PaperLessAdminLoginCallBack(context, progressDialog, new PaperLessAdminLoginRequest(
                        request.getEmail(),
                        RetrofitFactory.ClientID,
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