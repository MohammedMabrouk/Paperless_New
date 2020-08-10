package com.rentcentric.paperlesscounter.CallBacks;

import android.app.Fragment;
import android.widget.Toast;

import com.rentcentric.paperlesscounter.Fragments.CustomerInformationFragment;
import com.rentcentric.paperlesscounter.Models.Responses.GetStatesResponse;
import com.rentcentric.paperlesscounter.Network.RetrofitFactory;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.R;
import com.rentcentric.paperlesscounter.Utility.Extensions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetStatesCallBack implements Callback<GetStatesResponse> {
    private Fragment fragment;
    private LoginPreference loginPreference;
    private String stateCode, licenseStateCode;

    public GetStatesCallBack(Fragment fragment, String stateCode, String licenseStateCode) {
        this.fragment = fragment;
        this.stateCode = stateCode;
        this.licenseStateCode = licenseStateCode;
        loginPreference = new LoginPreference(fragment.getActivity());
        RetrofitFactory.getClientService(
                loginPreference.getServerName(),
                loginPreference.getClientId(),
                loginPreference.getToken()
        ).getStates().enqueue(this);
    }

    @Override
    public void onResponse(Call<GetStatesResponse> call, Response<GetStatesResponse> response) {
        if (response.isSuccessful()) {
            if (response.body() != null && response.body().getState()) {
                if (fragment instanceof CustomerInformationFragment) {
                    ((CustomerInformationFragment) fragment).bindStates(
                            response.body().getStatesList(),
                            stateCode,
                            licenseStateCode);
                }
            } else {
                Toast.makeText(fragment.getActivity(), response.body().getDescription(), Toast.LENGTH_LONG).show();
            }
        } else {
            Extensions.Dialog(fragment.getActivity(), fragment.getActivity().getString(R.string.invalid_response) + " (GetStates API)");
        }
    }

    @Override
    public void onFailure(Call<GetStatesResponse> call, Throwable t) {
        Toast.makeText(fragment.getActivity(), fragment.getActivity().getString(R.string.server_connection_error), Toast.LENGTH_LONG).show();
    }
}
