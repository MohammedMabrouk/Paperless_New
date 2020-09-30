package com.rentcentric.paperlesscounter.CallBacks;

import android.app.Fragment;
import android.widget.Toast;

import com.rentcentric.paperlesscounter.Fragments.CustomerInformationFragment;
import com.rentcentric.paperlesscounter.Models.Responses.GetPayMethodsResponse;
import com.rentcentric.paperlesscounter.Models.Responses.PayMethod;
import com.rentcentric.paperlesscounter.Network.RetrofitFactory;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.R;
import com.rentcentric.paperlesscounter.Utility.Extensions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPayMethodsCallBack implements Callback<GetPayMethodsResponse> {
    private Fragment fragment;
    private LoginPreference loginPreference;
    private PayMethod payMethod;

    public GetPayMethodsCallBack(Fragment fragment, PayMethod payMethod) {
        this.fragment = fragment;
        this.payMethod = payMethod;
        loginPreference = new LoginPreference(fragment.getActivity());
        RetrofitFactory.getClientService(
                loginPreference.getServerName(),
                loginPreference.getClientId(),
                loginPreference.getToken()
        ).getPayMethods().enqueue(this);
    }

    @Override
    public void onResponse(Call<GetPayMethodsResponse> call, Response<GetPayMethodsResponse> response) {
        if (response.isSuccessful()) {
            if (response.body() != null && response.body().getState()) {
                if (fragment instanceof CustomerInformationFragment) {
                    ((CustomerInformationFragment) fragment).bindPayMethods(response.body().getPayMethodsList(), payMethod);
                }
            } else {
                Toast.makeText(fragment.getActivity(), response.body().getDescription(), Toast.LENGTH_LONG).show();
            }
        } else {
            Extensions.Dialog(fragment.getActivity(), fragment.getActivity().getString(R.string.invalid_response) + " (GetPayMethods API)");
        }
    }

    @Override
    public void onFailure(Call<GetPayMethodsResponse> call, Throwable t) {
        Toast.makeText(fragment.getActivity(), fragment.getActivity().getString(R.string.server_connection_error), Toast.LENGTH_LONG).show();
    }
}