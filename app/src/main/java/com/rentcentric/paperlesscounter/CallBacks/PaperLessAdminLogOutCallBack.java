package com.rentcentric.paperlesscounter.CallBacks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rentcentric.paperlesscounter.Activities.LoginActivity;
import com.rentcentric.paperlesscounter.Models.Requests.PaperLessAdminLogOutRequest;
import com.rentcentric.paperlesscounter.Models.Responses.PaperLessAdminLogOutResponse;
import com.rentcentric.paperlesscounter.Network.RetrofitFactory;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.R;
import com.rentcentric.paperlesscounter.Utility.Extensions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaperLessAdminLogOutCallBack implements Callback<PaperLessAdminLogOutResponse> {

    private AppCompatActivity context;
    private ProgressDialog progressDialog;
    private LoginPreference loginPreference;

    public PaperLessAdminLogOutCallBack(AppCompatActivity context, PaperLessAdminLogOutRequest request) {
        this.context = context;
        progressDialog = ProgressDialog.show(context, "", context.getString(R.string.loading));
        loginPreference = new LoginPreference(context);
        RetrofitFactory.getClientService(loginPreference.getServerName(),
                loginPreference.getClientId(),
                loginPreference.getToken()
        ).paperLessAdminLogOut(request).enqueue(this);
    }

    @Override
    public void onResponse(Call<PaperLessAdminLogOutResponse> call, Response<PaperLessAdminLogOutResponse> response) {
        progressDialog.dismiss();
        if (response.isSuccessful()) {
            if (response.body() != null && response.body().getState() != null && response.body().getState().equals(true)) {
                loginPreference.clearLoginData();
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                context.finish();
            } else {
                Extensions.Dialog(context, response.body() != null ? response.body().getDescription() : "Error");
            }
        } else {
            Extensions.Dialog(context, context.getString(R.string.invalid_response) + " (PaperLessAdminLogOut API)");
        }
    }

    @Override
    public void onFailure(Call<PaperLessAdminLogOutResponse> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(context, context.getString(R.string.server_connection_error), Toast.LENGTH_LONG).show();
    }
}
