package com.rentcentric.paperlesscounter.CallBacks;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.rentcentric.paperlesscounter.Activities.MainActivity;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.Utility.Extensions;
import com.rentcentric.paperlesscounter.Models.Requests.SaveCustomerSignatureRequest;
import com.rentcentric.paperlesscounter.Models.Responses.SaveCustomerSignatureResponse;
import com.rentcentric.paperlesscounter.Network.RetrofitFactory;
import com.rentcentric.paperlesscounter.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveCustomerSignatureCallBack implements Callback<SaveCustomerSignatureResponse> {

    private AppCompatActivity context;
    private ProgressDialog progressDialog;
    private LoginPreference loginPreference;

    public SaveCustomerSignatureCallBack(AppCompatActivity context, SaveCustomerSignatureRequest request) {
        this.context = context;
        progressDialog = ProgressDialog.show(context, "", context.getString(R.string.loading));
        loginPreference = new LoginPreference(context);
        RetrofitFactory.getOldClientService(loginPreference.getServerName(), loginPreference.getClientId()).saveCustomerSignature(request).enqueue(this);
    }

    @Override
    public void onResponse(Call<SaveCustomerSignatureResponse> call, Response<SaveCustomerSignatureResponse> response) {
        progressDialog.dismiss();
        if (response.isSuccessful()) {
            if (response.body().getStatusInfo().getStatusCode().equals("0")) {
                ((MainActivity) context).onSaveCustomerSignatureCallBack();
            } else {
                Extensions.Dialog(context, response.body().getStatusInfo().getDescription());
            }
        } else {
            Extensions.Dialog(context, context.getString(R.string.invalid_response) + " (SaveCustomerSignature API)");
        }
    }

    @Override
    public void onFailure(Call<SaveCustomerSignatureResponse> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(context, context.getString(R.string.server_connection_error), Toast.LENGTH_LONG).show();
    }
}