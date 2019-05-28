package com.rentcentric.paperlesscounter.CallBacks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.rentcentric.paperlesscounter.Activities.MainActivity;
import com.rentcentric.paperlesscounter.Models.Requests.PaperLessAdminLoginRequest;
import com.rentcentric.paperlesscounter.Models.Responses.PaperLessAdminLoginResponse;
import com.rentcentric.paperlesscounter.Network.RetrofitFactory;
import com.rentcentric.paperlesscounter.R;
import com.rentcentric.paperlesscounter.Utility.Extensions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaperLessAdminLoginCallBack implements Callback<PaperLessAdminLoginResponse> {

    private AppCompatActivity context;
    private ProgressDialog progressDialog;

    public PaperLessAdminLoginCallBack(AppCompatActivity context, ProgressDialog progressDialog, PaperLessAdminLoginRequest request) {
        this.context = context;
        this.progressDialog = progressDialog;

        RetrofitFactory.build().PaperLessAdminLogin(request).enqueue(this);
    }

    @Override
    public void onResponse(Call<PaperLessAdminLoginResponse> call, Response<PaperLessAdminLoginResponse> response) {
        progressDialog.dismiss();
        if (response.isSuccessful()) {
            if (response.body().getStatusInfo().getStatusCode().equals("0")) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("FullName", response.body().getFullName());
                intent.putExtra("AdminID", response.body().getAdminId());
                intent.putExtra("LocationId", response.body().getLocationId());
                intent.putExtra("PaperlessAdminLoginID", response.body().getPaperLessAdminLoginId());
                context.startActivity(intent);
            } else {
                Extensions.Dialog(context, response.body().getStatusInfo().getDescription());
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