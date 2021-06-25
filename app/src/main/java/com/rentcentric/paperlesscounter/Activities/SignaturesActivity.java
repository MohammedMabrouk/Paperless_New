package com.rentcentric.paperlesscounter.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rentcentric.paperlesscounter.Models.Responses.SignatureSetup;
import com.rentcentric.paperlesscounter.R;
import com.rentcentric.paperlesscounter.Utility.Const;

import java.util.ArrayList;

public class SignaturesActivity
 extends AppCompatActivity implements
        View.OnClickListener {


    ImageView ivBack;
    RecyclerView rvSignatures;
    Button btnNext;
    ProgressDialog progressDialog;

    ArrayList<SignatureSetup> allSignatures;
    SignaturesAdapter signaturesAdapter;

    int totalNumberOfSignatures = 0;
    boolean areSignaturesSaved = false;

    int reservationId = -1;

    boolean isActivationDialogVisible = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signatures);

        initUI();

        Intent intent = getIntent();
        if (intent.getParcelableArrayListExtra(Const.INTENT_SIGNATURES_KEY) != null) {
            allSignatures = intent.getParcelableArrayListExtra(Const.INTENT_SIGNATURES_KEY);
            totalNumberOfSignatures = allSignatures.size();
            bindSignaturesList(allSignatures);
        }

        if (intent.getIntExtra(Const.INTENT_RESERVATION_ID, -1) != -1) {
            reservationId = intent.getIntExtra(Const.INTENT_RESERVATION_ID, -1);
        }
    }

    void initUI() {
        ivBack = findViewById(R.id.SignaturesBack);
        ivBack.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(getResources().getString(R.string.loading_txt));
        progressDialog.setCancelable(false);

        rvSignatures = findViewById(R.id.Signatures_RV);
        rvSignatures.setLayoutManager(new LinearLayoutManager(SignaturesActivity.this));

        btnNext = findViewById(R.id.SignaturesNext);
        btnNext.setOnClickListener(this);
    }

    void bindSignaturesList(ArrayList<SignatureSetup> signaturesList) {
        signaturesAdapter = new SignaturesAdapter(signaturesList, this);
        rvSignatures.setAdapter(signaturesAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.SignaturesBack:
                onBackPressed();
                break;

            case R.id.SignaturesNext:
                // check if all signatures were signed
                if (!areAllSignaturesSigned()) {
                    showToast(getString(R.string.you_should_sign_all_items));
                } else {

//                    presenter.saveSignaturesSetup(
//                            reservationId,
//                            new ArrayList<>(signaturesAdapter.signaturesMap.values()));

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra(MainActivity.INTENT_SIGNATURES_MAP, new ArrayList<>(signaturesAdapter.signaturesMap.values()));
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }

                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (!areAllSignaturesSigned()) {
            showToast(getString(R.string.you_should_sign_all_items));
        } else if (!areSignaturesSaved) {
            showToast(getString(R.string.you_should_submit_your_signature));
        } else {
            super.onBackPressed();
        }
    }

    boolean areAllSignaturesSigned() {
        if (signaturesAdapter != null) {
            if (SignaturesAdapter.setOfSignedSignatures.size() == totalNumberOfSignatures) {
                return true;
            }
        }
        return false;
    }

    void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}