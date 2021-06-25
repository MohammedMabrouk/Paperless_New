package com.rentcentric.paperlesscounter.CallBacks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rentcentric.paperlesscounter.Activities.MainActivity;
import com.rentcentric.paperlesscounter.Models.Requests.PaperLessAdminLoginRequest;
import com.rentcentric.paperlesscounter.Models.Responses.PaperLessAdminLoginResponse;
import com.rentcentric.paperlesscounter.Network.RetrofitFactory;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.R;
import com.rentcentric.paperlesscounter.Utility.Extensions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaperLessAdminLoginCallBack implements Callback<PaperLessAdminLoginResponse> {

    private AppCompatActivity context;
    private ProgressDialog progressDialog;
    private LoginPreference loginPreference;

    public PaperLessAdminLoginCallBack(AppCompatActivity context, ProgressDialog progressDialog, PaperLessAdminLoginRequest request) {
        this.context = context;
        this.progressDialog = progressDialog;
        loginPreference = new LoginPreference(context);
        RetrofitFactory.getClientService(loginPreference.getServerName(),
                loginPreference.getClientId(),
                loginPreference.getToken()
        ).paperLessAdminLogin(request).enqueue(this);
    }

    @Override
    public void onResponse(Call<PaperLessAdminLoginResponse> call, Response<PaperLessAdminLoginResponse> response) {
        progressDialog.dismiss();
        if (response.isSuccessful()) {
            if (response.body() != null && response.body().getState() != null && response.body().getState().equals(true)) {

//                intent.putExtra("FullName", response.body().getResult().getFullName());
//                intent.putExtra("AdminID", response.body().getResult().getAdminId());
//                intent.putExtra("LocationId", response.body().getResult().getLocationId());
//                intent.putExtra("PaperlessAdminLoginID", response.body().getResult().getPaperLessAdminLoginId());

                // save data
                loginPreference.setIsSignedIn(true);
                loginPreference.setFullName(response.body().getResult().getFullName());
                loginPreference.setAdminId(response.body().getResult().getAdminId());
                loginPreference.setLocationId(response.body().getResult().getLocationId().toString());
                loginPreference.setPaperlessAdminLoginId(response.body().getResult().getPaperLessAdminLoginId().toString());
                loginPreference.setToken(response.body().getResult().getToken());

                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                context.finish();
            } else {
                Extensions.Dialog(context, response.body() != null ? response.body().getDescription() : "Error");
            }
        } else {
            Extensions.Dialog(context, context.getString(R.string.invalid_response) + " (PaperLessAdminLogin API)");
        }
    }

    @Override
    public void onFailure(Call<PaperLessAdminLoginResponse> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(context, context.getString(R.string.server_connection_error), Toast.LENGTH_LONG).show();
    }
}