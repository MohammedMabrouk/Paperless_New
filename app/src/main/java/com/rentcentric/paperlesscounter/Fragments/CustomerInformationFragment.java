package com.rentcentric.paperlesscounter.Fragments;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rentcentric.paperlesscounter.Activities.CustomerInformationActivity;
import com.rentcentric.paperlesscounter.CallBacks.AddNewCustomerCallBack;
import com.rentcentric.paperlesscounter.CallBacks.GetCountriesCallBack;
import com.rentcentric.paperlesscounter.CallBacks.GetCustomerInfoCallBack;
import com.rentcentric.paperlesscounter.CallBacks.GetPayMethodsCallBack;
import com.rentcentric.paperlesscounter.CallBacks.GetStatesCallBack;
import com.rentcentric.paperlesscounter.CallBacks.UpdateCustomerCallBack;
import com.rentcentric.paperlesscounter.Models.Requests.AddNewCustomerRequest;
import com.rentcentric.paperlesscounter.Models.Requests.CustomerCreditCardDto;
import com.rentcentric.paperlesscounter.Models.Requests.UpdateCustomerRequest;
import com.rentcentric.paperlesscounter.Models.Responses.Country;
import com.rentcentric.paperlesscounter.Models.Responses.CustomerCreditCard;
import com.rentcentric.paperlesscounter.Models.Responses.CustomerInfoResult;
import com.rentcentric.paperlesscounter.Models.Responses.PayMethod;
import com.rentcentric.paperlesscounter.Models.Responses.State;
import com.rentcentric.paperlesscounter.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerInformationFragment extends Fragment
        implements View.OnClickListener, CustomerInformationActivity.SubmitClickListener {

    // UI
    private View rootView;

    private EditText firstNameEt, lastNameEt, emailEt, phoneEt, addressEt, cityEt, zipCodeEt,
            licenseNumberEt, creditCardNumberEt, creditCardHolderEt, cvvEt, cardExpiryMonthEt, cardExpiryYearEt, companyNameEt, companyPhoneEt, insuranceCompanyEt, policyNumberEt, ssnEt, airportEt,
            airlineEt, flightNumberEt, specialRequestEt;

    private TextView birthdayTv, driverLicenseExpiryTv;

    private Spinner stateSpinner, countrySpinner, driverLicenseStateSpinner, creditCardTypeSpinner;
    private ImageView stateArrowIv, countryArrowIv, driverLicenseStateArrowIv, creditCardTypeArrowIv;
    private ProgressBar stateProgressBar, countryProgressBar, licenseStateProgressBar, cardTypeProgressBar;

    public ProgressDialog progressDialog;

    // VARS
    private List<State> stateList = null;
    private List<Country> countryList = null;
    private List<PayMethod> payMethodList = null;

    private int licenseExpiryDay, licenseExpiryMonth, licenseExpiryYear;
    private int cardExpiryDay, cardExpiryMonth, cardExpiryYear;

    private boolean isEmailChanged = false,
            isLicenseExpiryChanged = false,
            isLicenseNumberChanged = false,
            isPhoneChanged = false,
            isSSNChanged = false,
            isCCNumberChanged = false,
            isCvvChanged = false;

    private CustomerInfoResult customerInfoResult = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // get saved instance
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_customer_information, container, false);
        initUi();
        ((CustomerInformationActivity) getActivity()).setOnSubmitClickListener(this);
        if (((CustomerInformationActivity) getActivity()).isNewCustomer) {
            new GetStatesCallBack(this, null, null);
            new GetCountriesCallBack(this, null);
            new GetPayMethodsCallBack(this, null);
        } else {
            progressDialog.show();
            new GetCustomerInfoCallBack(this,
                    String.valueOf(((CustomerInformationActivity) getActivity()).customerId));
        }
        return rootView;
    }

    private void initUi() {
        firstNameEt = rootView.findViewById(R.id.first_name_et);
        lastNameEt = rootView.findViewById(R.id.last_name_et);
        emailEt = rootView.findViewById(R.id.email_et);
        phoneEt = rootView.findViewById(R.id.phone_et);
        addressEt = rootView.findViewById(R.id.address_et);
        cityEt = rootView.findViewById(R.id.city_et);
        zipCodeEt = rootView.findViewById(R.id.zipcode_et);
        licenseNumberEt = rootView.findViewById(R.id.driver_license_number_et);
        creditCardNumberEt = rootView.findViewById(R.id.credit_Card_number_et);
        creditCardHolderEt = rootView.findViewById(R.id.card_holder_et);
        cvvEt = rootView.findViewById(R.id.cvv_et);
        companyNameEt = rootView.findViewById(R.id.company_name_et);
        companyPhoneEt = rootView.findViewById(R.id.company_phone_et);
        insuranceCompanyEt = rootView.findViewById(R.id.insurance_company_et);
        policyNumberEt = rootView.findViewById(R.id.policy_number_et);
        ssnEt = rootView.findViewById(R.id.ssn_et);
        airportEt = rootView.findViewById(R.id.airport_et);
        airlineEt = rootView.findViewById(R.id.airline_et);
        flightNumberEt = rootView.findViewById(R.id.flight_number_et);
        specialRequestEt = rootView.findViewById(R.id.special_request_et);

        birthdayTv = rootView.findViewById(R.id.birthday_tv);
        birthdayTv.setOnClickListener(this);
        driverLicenseExpiryTv = rootView.findViewById(R.id.driver_license_expiry_tv);
        driverLicenseExpiryTv.setOnClickListener(this);
        cardExpiryMonthEt = rootView.findViewById(R.id.card_expiry_month_et);
        cardExpiryYearEt = rootView.findViewById(R.id.card_expiry_year_et);

        stateSpinner = rootView.findViewById(R.id.state_spinner);
        countrySpinner = rootView.findViewById(R.id.country_spinner);
        driverLicenseStateSpinner = rootView.findViewById(R.id.driver_license_state_spinner);
        creditCardTypeSpinner = rootView.findViewById(R.id.credit_card_type_spinner);

        stateArrowIv = rootView.findViewById(R.id.spinner_arrow_state);
        countryArrowIv = rootView.findViewById(R.id.spinner_arrow_county);
        driverLicenseStateArrowIv = rootView.findViewById(R.id.spinner_arrow_license_state);
        creditCardTypeArrowIv = rootView.findViewById(R.id.spinner_arrow_credit_card_type);

        stateProgressBar = rootView.findViewById(R.id.state_pb);
        countryProgressBar = rootView.findViewById(R.id.country_pb);
        licenseStateProgressBar = rootView.findViewById(R.id.license_state_pb);
        cardTypeProgressBar = rootView.findViewById(R.id.card_type_pb);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(getResources().getString(R.string.loading_txt));
        progressDialog.setCancelable(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.birthday_tv:
                selectDateOfBirth();
                break;

            case R.id.birthday_iv:
                selectDateOfBirth();
                break;

            case R.id.driver_license_expiry_tv:
                selectLicenseExpiryDate();
                break;

            case R.id.license_expiry_iv:
                selectLicenseExpiryDate();
                break;

//            case R.id.card_expiry_et:
//                selectCardExpiryDate();
//                break;
//
//            case R.id.card_expiry_iv:
//                selectCardExpiryDate();
//                break;
        }
    }


    // for states and license states
    public void bindStates(List<State> stateList, String stateCode, String licenseStateCode) {
        this.stateList = stateList;

        stateProgressBar.setVisibility(View.INVISIBLE);
        stateArrowIv.setVisibility(View.VISIBLE);

        licenseStateProgressBar.setVisibility(View.INVISIBLE);
        driverLicenseStateArrowIv.setVisibility(View.VISIBLE);

        List<String> states = new ArrayList<>();
        for (State s : stateList) {
            states.add(s.getStateName());
        }
        ArrayAdapter<String> statesAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()), R.layout.country_list_item, states);
        stateSpinner.setAdapter(statesAdapter);
        driverLicenseStateSpinner.setAdapter(statesAdapter);

        if (stateCode != null) {
            int index = 0;
            for (State s : stateList) {
                if (s.getStateCode().equals(stateCode)) {
                    stateSpinner.setSelection(index);
                    break;
                }
                index++;
            }
        }

        if (licenseStateCode != null) {
            int index = 0;
            for (State s : stateList) {
                if (s.getStateCode().equals(licenseStateCode)) {
                    driverLicenseStateSpinner.setSelection(index);
                    break;
                }
                index++;
            }
        }
    }

    public void bindCountries(List<Country> countryList, String countryCode) {
        this.countryList = countryList;

        countryProgressBar.setVisibility(View.INVISIBLE);
        countryArrowIv.setVisibility(View.VISIBLE);

        List<String> countries = new ArrayList<>();
        for (Country c : countryList) {
            countries.add(c.getCountryName());
        }
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()), R.layout.country_list_item, countries);
        countrySpinner.setAdapter(countryAdapter);

        if (countryCode != null) {
            int index = 0;
            for (Country c : countryList) {
                if (c.getCountryCode().equals(countryCode)) {
                    countrySpinner.setSelection(index);
                    break;
                }
                index++;
            }
        }
    }

    public void bindPayMethods(List<PayMethod> payMethodList, PayMethod payMethod) {
        this.payMethodList = payMethodList;

        cardTypeProgressBar.setVisibility(View.INVISIBLE);
        creditCardTypeArrowIv.setVisibility(View.VISIBLE);

        List<String> payMethods = new ArrayList<>();
        for (PayMethod p : payMethodList) {
            payMethods.add(p.getPayMethod());
        }
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()), R.layout.country_list_item, payMethods);
        creditCardTypeSpinner.setAdapter(countryAdapter);

        if (payMethod != null) {
            int index = 0;
            for (PayMethod pm : payMethodList) {
                if (pm.getPayMethodId().equals(payMethod.getPayMethodId())) {
                    creditCardTypeSpinner.setSelection(index);
                    break;
                }
                index++;
            }
        }
    }


    private boolean validateInput() {
        // todo: handle update case
        if (customerInfoResult != null) { // update case
            if (!emailEt.getText().toString().equals(customerInfoResult.getCustomerInfo().getEmail())) {
                isEmailChanged = true;
            }
            if (!driverLicenseExpiryTv.getText().toString().equals(customerInfoResult.getCustomerInfo().getLicenseExpiry())) {
                isLicenseExpiryChanged = true;
            }
            if (!cvvEt.getText().toString().equals(customerInfoResult.getCustomerInfo().getCvv())) {
                isCvvChanged = true;
            }
            if (customerInfoResult.getCustomerCreditCards() != null &&
                    customerInfoResult.getCustomerCreditCards().get(0) != null &&
                    !creditCardNumberEt.getText().toString().equals(customerInfoResult.getCustomerCreditCards().get(0).getCustomerCreditCardNumber())) {
                isCCNumberChanged = true;
            }

            if(!ssnEt.getText().toString().equals(customerInfoResult.getCustomerInfo().getSSN())){
                isSSNChanged = true;
            }
            if(!phoneEt.getText().toString().equals(customerInfoResult.getCustomerInfo().getPhone())){
                isPhoneChanged = true;
            }
            if(!licenseNumberEt.getText().toString().equals(customerInfoResult.getCustomerInfo().getLicenseNumber())){
                isLicenseNumberChanged = true;
            }

        }

        // validate
        boolean proceed = true;
        if (firstNameEt.getText().toString().isEmpty()) {
            firstNameEt.setError(getActivity().getString(R.string.required));
            firstNameEt.requestFocus();
            proceed = false;
        } else if (lastNameEt.getText().toString().isEmpty()) {
            lastNameEt.setError(getActivity().getString(R.string.required));
            lastNameEt.requestFocus();
            proceed = false;
        } else if (emailEt.getText().toString().isEmpty()) {
            emailEt.setError(getActivity().getString(R.string.required));
            emailEt.requestFocus();
            proceed = false;
        } else if ((customerInfoResult == null || isEmailChanged) &&
                !android.util.Patterns.EMAIL_ADDRESS.matcher(emailEt.getText().toString()).matches()) {
            emailEt.setError(getActivity().getString(R.string.invalid_email_address_error));
            emailEt.requestFocus();
            proceed = false;
        } else if (phoneEt.getText().toString().isEmpty()) {
            phoneEt.setError(getActivity().getString(R.string.required));
            phoneEt.requestFocus();
            proceed = false;
        } else if (birthdayTv.getText().toString().isEmpty()) {
            birthdayTv.setError(getActivity().getString(R.string.required));
            birthdayTv.requestFocus();
            proceed = false;
        } else if (addressEt.getText().toString().isEmpty()) {
            addressEt.setError(getActivity().getString(R.string.required));
            addressEt.requestFocus();
            proceed = false;
        } else if (cityEt.getText().toString().isEmpty()) {
            cityEt.setError(getActivity().getString(R.string.required));
            cityEt.requestFocus();
            proceed = false;
        } else if (stateList == null) {
            proceed = false;
            Toast.makeText(getActivity(), "Select State", Toast.LENGTH_SHORT).show();
        } else if (countryList == null) {
            proceed = false;
            Toast.makeText(getActivity(), "Select Country", Toast.LENGTH_SHORT).show();
        } else if (zipCodeEt.getText().toString().isEmpty()) {
            zipCodeEt.setError(getActivity().getString(R.string.required));
            zipCodeEt.requestFocus();
            proceed = false;
        } else if (licenseNumberEt.getText().toString().isEmpty()) {
            licenseNumberEt.setError(getActivity().getString(R.string.required));
            licenseNumberEt.requestFocus();
            proceed = false;
        } else if (driverLicenseExpiryTv.getText().toString().isEmpty()) {
            driverLicenseExpiryTv.setError(getActivity().getString(R.string.required));
            driverLicenseExpiryTv.requestFocus();
            proceed = false;
        } else if (payMethodList == null) {
            proceed = false;
            Toast.makeText(getActivity(), "Select Card Type", Toast.LENGTH_SHORT).show();
        } else if (creditCardNumberEt.getText().toString().isEmpty()) {
            creditCardNumberEt.setError(getActivity().getString(R.string.required));
            creditCardNumberEt.requestFocus();
            proceed = false;
        } else if (creditCardHolderEt.getText().toString().isEmpty()) {
            creditCardHolderEt.setError(getActivity().getString(R.string.required));
            creditCardHolderEt.requestFocus();
            proceed = false;
        } else if (cvvEt.getText().toString().isEmpty()) {
            cvvEt.setError(getActivity().getString(R.string.required));
            cvvEt.requestFocus();
            proceed = false;
        } else if (cardExpiryMonthEt.getText().toString().isEmpty()) {
            cardExpiryMonthEt.setError(getActivity().getString(R.string.required));
            cardExpiryMonthEt.requestFocus();
            proceed = false;
        }

        return proceed;
    }

    @Override
    public void onSubmitClicked() {
        if (validateInput()) {
            progressDialog.show();
            if (((CustomerInformationActivity) getActivity()).isNewCustomer) {
                // add new customer
                new AddNewCustomerCallBack(this,
                        new AddNewCustomerRequest(
                                firstNameEt.getText().toString(),
                                lastNameEt.getText().toString(),
                                emailEt.getText().toString(),
                                phoneEt.getText().toString(),
                                birthdayTv.getText().toString(),
                                addressEt.getText().toString(),
                                cityEt.getText().toString(),
                                stateList.get(stateSpinner.getSelectedItemPosition()).getStateCode(),
                                countryList.get(countrySpinner.getSelectedItemPosition()).getCountryCode(),
                                zipCodeEt.getText().toString(),
                                licenseNumberEt.getText().toString(),
                                driverLicenseExpiryTv.getText().toString(),
                                stateList.get(driverLicenseStateSpinner.getSelectedItemPosition()).getStateCode(),
                                new CustomerCreditCardDto(
                                        payMethodList.get(creditCardTypeSpinner.getSelectedItemPosition()),
                                        0,
                                        creditCardNumberEt.getText().toString(),
                                        true,
                                        creditCardHolderEt.getText().toString(),
                                        Integer.parseInt(cardExpiryMonthEt.getText().toString()),
                                        Integer.parseInt(cardExpiryYearEt.getText().toString())
                                ),
                                companyNameEt.getText().toString(),
                                companyPhoneEt.getText().toString(),
                                insuranceCompanyEt.getText().toString(),
                                policyNumberEt.getText().toString(),
                                ssnEt.getText().toString(),
                                ((CustomerInformationActivity) getActivity()).mobileRequestId,
                                airportEt.getText().toString(),
                                airlineEt.getText().toString(),
                                flightNumberEt.getText().toString(),
                                specialRequestEt.getText().toString()
                        ));
            } else {
                // update customer
                new UpdateCustomerCallBack(this,
                        new UpdateCustomerRequest(
                                ((CustomerInformationActivity) getActivity()).mobileRequestId,
                                customerInfoResult.getCustomerInfo().getCustomerId(),
                                firstNameEt.getText().toString(),
                                lastNameEt.getText().toString(),
                                isEmailChanged ? emailEt.getText().toString() : null,
                                isPhoneChanged ? phoneEt.getText().toString() : null,
                                birthdayTv.getText().toString(),
                                addressEt.getText().toString(),
                                cityEt.getText().toString(),
                                stateList.get(stateSpinner.getSelectedItemPosition()).getStateCode(),
                                countryList.get(countrySpinner.getSelectedItemPosition()).getCountryCode(),
                                zipCodeEt.getText().toString(),
                                isLicenseNumberChanged? licenseNumberEt.getText().toString() : null,
                                isLicenseExpiryChanged ? driverLicenseExpiryTv.getText().toString() : null,
                                stateList.get(driverLicenseStateSpinner.getSelectedItemPosition()).getStateCode(),
                                companyNameEt.getText().toString(),
                                companyPhoneEt.getText().toString(),
                                insuranceCompanyEt.getText().toString(),
                                policyNumberEt.getText().toString(),
                                isSSNChanged? ssnEt.getText().toString() : null,
                                airportEt.getText().toString(),
                                airlineEt.getText().toString(),
                                flightNumberEt.getText().toString(),
                                specialRequestEt.getText().toString()
                        ));
            }
        }
    }

    private void selectDateOfBirth() {
        DatePickerDialog pickupDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String result = "";

                if (dayOfMonth <= 9) {
                    result = ((month + 1 <= 9 ? "0" : "") + (month + 1) + "/" + "0" + dayOfMonth + "/" + year);
                } else {
                    result = ((month + 1 <= 9 ? "0" : "") + (month + 1) + "/" + dayOfMonth + "/" + year);
                }

                birthdayTv.setText(result);
                birthdayTv.setError(null);
            }
        }, 2000,
                0,
                1);
        pickupDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        pickupDatePickerDialog.show();
    }

    private void selectLicenseExpiryDate() {
        DatePickerDialog pickupDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                licenseExpiryMonth = month;
                licenseExpiryYear = year;
                licenseExpiryDay = dayOfMonth;

                String result = "";

                if (dayOfMonth <= 9) {
                    result = ((month + 1 <= 9 ? "0" : "") + (month + 1) + "/" + "0" + dayOfMonth + "/" + year);
                } else {
                    result = ((month + 1 <= 9 ? "0" : "") + (month + 1) + "/" + dayOfMonth + "/" + year);
                }

                driverLicenseExpiryTv.setText(result);
                driverLicenseExpiryTv.setError(null);
            }
        }, licenseExpiryYear,
                licenseExpiryMonth,
                licenseExpiryDay);
        pickupDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        pickupDatePickerDialog.show();
    }

    private void selectCardExpiryDate() {
        DatePickerDialog pickupDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cardExpiryMonth = month;
                cardExpiryYear = year;
                cardExpiryDay = dayOfMonth;

                String result = "";

                if (dayOfMonth <= 9) {
                    result = ((month + 1 <= 9 ? "0" : "") + (month + 1) + "/" + "0" + dayOfMonth + "/" + year);
                } else {
                    result = ((month + 1 <= 9 ? "0" : "") + (month + 1) + "/" + dayOfMonth + "/" + year);
                }

                cardExpiryMonthEt.setText(result);
                cardExpiryMonthEt.setError(null);
            }
        }, cardExpiryYear,
                cardExpiryMonth,
                cardExpiryDay);
        pickupDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        pickupDatePickerDialog.show();
    }

    public void onAddCustomerSuccess() {
        progressDialog.dismiss();
        getActivity().finish();
        Toast.makeText(getActivity(), "Customer Added Successfully!", Toast.LENGTH_SHORT).show();
    }

    public void onUpdateCustomerSuccess() {
        progressDialog.dismiss();
        getActivity().finish();
        Toast.makeText(getActivity(), "Customer Updated Successfully!", Toast.LENGTH_SHORT).show();
    }

    public void onGetCustomerInfoSuccess(CustomerInfoResult customerInfoResult) {
        this.customerInfoResult = customerInfoResult;
        progressDialog.dismiss();
        // bind data
        firstNameEt.setText(customerInfoResult.getCustomerInfo().getFirstName());
        lastNameEt.setText(customerInfoResult.getCustomerInfo().getLastName());
        emailEt.setText(customerInfoResult.getCustomerInfo().getEmail());
//        emailEt.setFocusable(false);
        phoneEt.setText(customerInfoResult.getCustomerInfo().getPhone());
        birthdayTv.setText(customerInfoResult.getCustomerInfo().getBirthday());
        addressEt.setText(customerInfoResult.getCustomerInfo().getAddress());
        cityEt.setText(customerInfoResult.getCustomerInfo().getCity());

        new GetStatesCallBack(
                this,
                customerInfoResult.getCustomerInfo().getStateCode(),
                customerInfoResult.getCustomerInfo().getLicenseState());

        new GetCountriesCallBack(
                this,
                customerInfoResult.getCustomerInfo().getCountryCode());

        zipCodeEt.setText(customerInfoResult.getCustomerInfo().getZip());
        cityEt.setText(customerInfoResult.getCustomerInfo().getCity());
        licenseNumberEt.setText(customerInfoResult.getCustomerInfo().getLicenseNumber());
        driverLicenseExpiryTv.setText(customerInfoResult.getCustomerInfo().getLicenseExpiry());

        // select the default cc
        if (customerInfoResult.getCustomerCreditCards() != null) {
            for (CustomerCreditCard cc : customerInfoResult.getCustomerCreditCards()) {
                if (cc.getIsDefault()) {
                    new GetPayMethodsCallBack(this, cc.getPayMethod());
                    creditCardHolderEt.setText(cc.getCardHolder());
                    creditCardNumberEt.setText(cc.getCustomerCreditCardNumber());
                    cvvEt.setText(customerInfoResult.getCustomerInfo().getCvv());
                    cardExpiryMonthEt.setText(cc.getExpiryMonth().toString());
                    cardExpiryYearEt.setText(cc.getExpiryYear().toString());
                    break;
                }
            }
        } else {
            new GetPayMethodsCallBack(this, null);
        }


//        creditCardTypeSpinner.setEnabled(false);
//        cardTypeProgressBar.setVisibility(View.GONE);
//        creditCardTypeArrowIv.setVisibility(View.VISIBLE);
//
//        creditCardNumberEt.setEnabled(false);
//        creditCardHolderEt.setEnabled(false);
//        cvvEt.setEnabled(false);
//        cardExpiryMonthEt.setClickable(false);

        companyNameEt.setText(customerInfoResult.getCustomerInfo().getCompanyName());
        companyPhoneEt.setText(customerInfoResult.getCustomerInfo().getCompanyPhone());
        insuranceCompanyEt.setText(customerInfoResult.getCustomerInfo().getInsuranceCompany());
        policyNumberEt.setText(customerInfoResult.getCustomerInfo().getPolicyNumber());
        ssnEt.setText(customerInfoResult.getCustomerInfo().getSSN());
        airportEt.setText(customerInfoResult.getCustomerInfo().getAirport());
        airlineEt.setText(customerInfoResult.getCustomerInfo().getAirline());
        flightNumberEt.setText(customerInfoResult.getCustomerInfo().getFlightNumber());
        specialRequestEt.setText(customerInfoResult.getCustomerInfo().getLocalInformation());
    }
}