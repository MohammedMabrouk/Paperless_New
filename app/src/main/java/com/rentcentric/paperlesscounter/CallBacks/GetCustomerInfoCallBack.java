package com.rentcentric.paperlesscounter.CallBacks;

import android.widget.Toast;

import com.rentcentric.paperlesscounter.Fragments.CustomerInformationFragment;
import com.rentcentric.paperlesscounter.Models.Responses.GetCustomerInfoResponse;
import com.rentcentric.paperlesscounter.Network.RetrofitFactory;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.R;
import com.rentcentric.paperlesscounter.Utility.Extensions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCustomerInfoCallBack implements Callback<GetCustomerInfoResponse> {
    private CustomerInformationFragment customerInformationFragment;
    private LoginPreference loginPreference;

    public GetCustomerInfoCallBack(CustomerInformationFragment customerInformationFragment, String customerId) {
        this.customerInformationFragment = customerInformationFragment;
        loginPreference = new LoginPreference(customerInformationFragment.getActivity());
        RetrofitFactory.getClientService(
                loginPreference.getServerName(),
                loginPreference.getClientId(),
                loginPreference.getToken()
        ).getCustomerInformation(customerId).enqueue(this);
    }

    @Override
    public void onResponse(Call<GetCustomerInfoResponse> call, Response<GetCustomerInfoResponse> response) {
        if (response.isSuccessful()) {
            if (response.body() != null && response.body().getState()) {
                customerInformationFragment.onGetCustomerInfoSuccess(response.body().getCustomerInfoResult());
            } else {
                Toast.makeText(customerInformationFragment.getActivity(), response.body().getDescription(), Toast.LENGTH_LONG).show();
            }
        } else {
            customerInformationFragment.progressDialog.dismiss();
            Extensions.Dialog(customerInformationFragment.getActivity(), customerInformationFragment.getActivity().getString(R.string.invalid_response) + " (GetCustomerInfo API)");
        }
    }

    @Override
    public void onFailure(Call<GetCustomerInfoResponse> call, Throwable t) {
        Toast.makeText(customerInformationFragment.getActivity(), customerInformationFragment.getActivity().getString(R.string.server_connection_error), Toast.LENGTH_LONG).show();
    }
}
