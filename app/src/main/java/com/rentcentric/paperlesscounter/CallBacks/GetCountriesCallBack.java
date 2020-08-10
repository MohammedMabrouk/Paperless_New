package com.rentcentric.paperlesscounter.CallBacks;

import android.app.Fragment;
import android.widget.Toast;

import com.rentcentric.paperlesscounter.Fragments.CustomerInformationFragment;
import com.rentcentric.paperlesscounter.Models.Responses.GetCountriesResponse;
import com.rentcentric.paperlesscounter.Network.RetrofitFactory;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.R;
import com.rentcentric.paperlesscounter.Utility.Extensions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCountriesCallBack implements Callback<GetCountriesResponse> {
    private Fragment fragment;
    private LoginPreference loginPreference;
    String countryCode;

    public GetCountriesCallBack(Fragment fragment, String countryCode) {
        this.fragment = fragment;
        this.countryCode = countryCode;
        loginPreference = new LoginPreference(fragment.getActivity());
        RetrofitFactory.getClientService(
                loginPreference.getServerName(),
                loginPreference.getClientId(),
                loginPreference.getToken()
        ).getCountries().enqueue(this);
    }

    @Override
    public void onResponse(Call<GetCountriesResponse> call, Response<GetCountriesResponse> response) {
        if (response.isSuccessful()) {
            if (response.body() != null && response.body().getState()) {
                if (fragment instanceof CustomerInformationFragment) {
                    ((CustomerInformationFragment) fragment).bindCountries(
                            response.body().getCountriesList(),
                            countryCode);
                }
            } else {
                Toast.makeText(fragment.getActivity(), response.body().getDescription(), Toast.LENGTH_LONG).show();
            }
        } else {
            Extensions.Dialog(fragment.getActivity(), fragment.getActivity().getString(R.string.invalid_response) + " (GetCountries API)");
        }
    }

    @Override
    public void onFailure(Call<GetCountriesResponse> call, Throwable t) {
        Toast.makeText(fragment.getActivity(), fragment.getActivity().getString(R.string.server_connection_error), Toast.LENGTH_LONG).show();
    }
}