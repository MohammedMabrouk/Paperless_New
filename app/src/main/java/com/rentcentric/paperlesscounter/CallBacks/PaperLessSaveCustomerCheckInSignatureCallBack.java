package com.rentcentric.paperlesscounter.CallBacks;

import android.app.ProgressDialog;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rentcentric.paperlesscounter.Activities.MainActivity;
import com.rentcentric.paperlesscounter.Models.Requests.PaperLessSaveCustomerCheckInSignatureRequest;
import com.rentcentric.paperlesscounter.Models.Requests.SaveCustomerSignatureRequest;
import com.rentcentric.paperlesscounter.Models.Responses.PaperLessSaveCustomerCheckInSignatureResponse;
import com.rentcentric.paperlesscounter.Models.Responses.SaveCustomerSignatureResponse;
import com.rentcentric.paperlesscounter.Network.RetrofitFactory;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.R;
import com.rentcentric.paperlesscounter.Utility.Extensions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaperLessSaveCustomerCheckInSignatureCallBack implements Callback<PaperLessSaveCustomerCheckInSignatureResponse> {

    private AppCompatActivity context;
    private ProgressDialog progressDialog;
    private LoginPreference loginPreference;

    public PaperLessSaveCustomerCheckInSignatureCallBack(AppCompatActivity context, PaperLessSaveCustomerCheckInSignatureRequest request) {
        this.context = context;
        progressDialog = ProgressDialog.show(context, "", context.getString(R.string.loading));
        loginPreference = new LoginPreference(context);
        RetrofitFactory.getClientService(
                loginPreference.getServerName(),
                loginPreference.getClientId(),
                loginPreference.getToken()
        ).saveCustomerSignatureForCheckIn(request).enqueue(this);
    }

    @Override
    public void onResponse(Call<PaperLessSaveCustomerCheckInSignatureResponse> call, Response<PaperLessSaveCustomerCheckInSignatureResponse> response) {
        progressDialog.dismiss();
        if (response.isSuccessful()) {
            if (response.body() != null && response.body().getState()) {
                ((MainActivity) context).onSaveCustomerSignatureCallBack();
            } else {
                Extensions.Dialog(context, response.body().getDescription());
            }
        } else {
            Extensions.Dialog(context, context.getString(R.string.invalid_response) + " (PaperLessSaveCustomerCheckInSignature API)");
        }
    }

    @Override
    public void onFailure(Call<PaperLessSaveCustomerCheckInSignatureResponse> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(context, context.getString(R.string.server_connection_error), Toast.LENGTH_LONG).show();
    }
}
