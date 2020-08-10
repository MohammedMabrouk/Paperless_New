package com.rentcentric.paperlesscounter.CallBacks;

import android.widget.Toast;

import com.rentcentric.paperlesscounter.Fragments.CustomerInformationFragment;
import com.rentcentric.paperlesscounter.Models.Requests.AddNewCustomerRequest;
import com.rentcentric.paperlesscounter.Models.Responses.AddNewCustomerResponse;
import com.rentcentric.paperlesscounter.Network.RetrofitFactory;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.R;
import com.rentcentric.paperlesscounter.Utility.Extensions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewCustomerCallBack implements Callback<AddNewCustomerResponse> {

    private CustomerInformationFragment customerInformationFragment;
    private LoginPreference loginPreference;

    public AddNewCustomerCallBack(CustomerInformationFragment customerInformationFragment, AddNewCustomerRequest request) {
        if (customerInformationFragment != null) {
            this.customerInformationFragment = customerInformationFragment;
            loginPreference = new LoginPreference(customerInformationFragment.getActivity());
            RetrofitFactory.getClientService(loginPreference.getServerName(),
                    loginPreference.getClientId(),
                    loginPreference.getToken()
            ).addNewCustomer(request).enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<AddNewCustomerResponse> call, Response<AddNewCustomerResponse> response) {
        if (response.isSuccessful()) {
            if (response.body() != null && response.body().getState() != null && response.body().getState().equals(true)) {
                customerInformationFragment.onAddCustomerSuccess();
            } else {
                customerInformationFragment.progressDialog.dismiss();
                Extensions.Dialog(customerInformationFragment.getActivity(), response.body().getDescription());
            }
        } else {
            customerInformationFragment.progressDialog.dismiss();
            Extensions.Dialog(customerInformationFragment.getActivity(), customerInformationFragment.getActivity().getString(R.string.invalid_response) + " (AddNewCustomer API)");
        }
    }

    @Override
    public void onFailure(Call<AddNewCustomerResponse> call, Throwable t) {
        customerInformationFragment.progressDialog.dismiss();
        Toast.makeText(customerInformationFragment.getActivity(), customerInformationFragment.getActivity().getString(R.string.server_connection_error), Toast.LENGTH_LONG).show();
    }
}