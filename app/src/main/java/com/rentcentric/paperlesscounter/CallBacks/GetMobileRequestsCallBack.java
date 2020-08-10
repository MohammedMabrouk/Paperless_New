package com.rentcentric.paperlesscounter.CallBacks;

import android.support.v7.app.AppCompatActivity;

import com.rentcentric.paperlesscounter.Activities.MainActivity;
import com.rentcentric.paperlesscounter.Models.Requests.GetMobileRequestsRequest;
import com.rentcentric.paperlesscounter.Models.Responses.GetMobileRequestsResponse;
import com.rentcentric.paperlesscounter.Network.RetrofitFactory;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMobileRequestsCallBack implements Callback<GetMobileRequestsResponse> {

    private AppCompatActivity context;
    private LoginPreference loginPreference;

    public GetMobileRequestsCallBack(AppCompatActivity context, GetMobileRequestsRequest request) {
        this.context = context;
        loginPreference = new LoginPreference(context);
        RetrofitFactory.getClientService(loginPreference.getServerName(),
                loginPreference.getClientId(),
                loginPreference.getToken()
        ).getMobileRequests(request).enqueue(this);
    }

    @Override
    public void onResponse(Call<GetMobileRequestsResponse> call, Response<GetMobileRequestsResponse> response) {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                ((MainActivity) context).onGetMobileRequestsCallBack(response.body());
            } else {
                ((MainActivity) context).onGetCallBackError(response.body().getDescription());
            }
        } else {
            ((MainActivity) context).onGetCallBackError((R.string.invalid_response) + " (GetMobileRequests API)");
        }
    }

    @Override
    public void onFailure(Call<GetMobileRequestsResponse> call, Throwable t) {
        ((MainActivity) context).onGetCallBackError(context.getString(R.string.server_connection_error));
    }
}