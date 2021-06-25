package com.rentcentric.paperlesscounter.Activities;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rentcentric.paperlesscounter.Fragments.CustomerInformationFragment;
import com.rentcentric.paperlesscounter.R;

public class CustomerInformationActivity extends AppCompatActivity
        implements View.OnClickListener {
    // UI
    private TextView toolbarTitleTextView;
    private ImageView backImageView;
    private Button submitButton;

    // vars
    public Boolean isNewCustomer;
    public int customerId;
    public int mobileRequestId;

    private SubmitClickListener onSubmitClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_information);

        initUI();
        // get sent data
        if (getIntent() != null) {
            isNewCustomer = getIntent().getBooleanExtra("isCreateNewCustomer", false);
            customerId = getIntent().getIntExtra("customerId", 0);
            mobileRequestId = getIntent().getIntExtra("mobileRequestId", 0);

            if (isNewCustomer) {
                toolbarTitleTextView.setText("Add New Customer");
                submitButton.setText("Add New Customer");
            }else{
                toolbarTitleTextView.setText("Customer Information");
                submitButton.setText("Update Customer Info");
            }
        }

        // add fragment
        CustomerInformationFragment customerInformationFragment = new CustomerInformationFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fragmentContainer, customerInformationFragment).commit();
    }

    private void initUI() {
        toolbarTitleTextView = findViewById(R.id.toolbar_title_tv);
        backImageView = findViewById(R.id.back_btn_iv);
        backImageView.setOnClickListener(this);
        submitButton = findViewById(R.id.submit_btn);
        submitButton.setOnClickListener(this);
    }

    public void setOnSubmitClickListener(SubmitClickListener onSubmitClickListener) {
        this.onSubmitClickListener = onSubmitClickListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn_iv:
                this.finish();
                break;

            case R.id.submit_btn:
                onSubmitClickListener.onSubmitClicked();
                break;
        }
    }

    public interface SubmitClickListener {
        void onSubmitClicked();
    }
}