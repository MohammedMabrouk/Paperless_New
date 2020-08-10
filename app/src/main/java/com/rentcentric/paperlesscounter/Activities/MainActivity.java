package com.rentcentric.paperlesscounter.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.rentcentric.paperlesscounter.BuildConfig;
import com.rentcentric.paperlesscounter.CallBacks.GetMobileRequestsCallBack;
import com.rentcentric.paperlesscounter.CallBacks.GetPaperLessAgreementCallBack;
import com.rentcentric.paperlesscounter.CallBacks.PaperLessAdminLogOutCallBack;
import com.rentcentric.paperlesscounter.CallBacks.SaveCustomerSignatureCallBack;
import com.rentcentric.paperlesscounter.Models.Requests.GetMobileRequestsRequest;
import com.rentcentric.paperlesscounter.Models.Requests.GetPaperLessAgreementRequest;
import com.rentcentric.paperlesscounter.Models.Requests.PaperLessAdminLogOutRequest;
import com.rentcentric.paperlesscounter.Models.Requests.SaveCustomerSignatureRequest;
import com.rentcentric.paperlesscounter.Models.Responses.GetMobileRequestsResponse;
import com.rentcentric.paperlesscounter.Models.Responses.GetPaperLessAgreementResponse;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.R;
import com.rentcentric.paperlesscounter.Utility.Extensions;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    DrawerLayout drawer;
    RelativeLayout rlTimeoutContainer, rlNotFoundContainer, rlMainContainer;
    LinearLayout llContractContainer;
    TextView tvError, tvRetry, tvNavFullName, tvNavVersion, tvTimer;
    ProgressBar pbMain;
    WebView wvMain;
    SignaturePad spMain;
    Button btnCancel, btnSubmit;

    int i;

    private GetPaperLessAgreementResponse response;

    private LoginPreference loginPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this);

        loginPreference = new LoginPreference(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Contract");

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navHeader = navigationView.getHeaderView(0);


        tvNavFullName = navHeader.findViewById(R.id.NavFullName);
        tvNavVersion = navHeader.findViewById(R.id.NavVersion);

        rlTimeoutContainer = findViewById(R.id.TimeoutContainer);
        rlNotFoundContainer = findViewById(R.id.NotFoundContainer);
        rlMainContainer = findViewById(R.id.MainContainer);

        llContractContainer = findViewById(R.id.ContractContainer);

        tvError = findViewById(R.id.TimeoutError);

        tvRetry = findViewById(R.id.TimeoutRetry);
        tvRetry.setOnClickListener(this);

        tvTimer = findViewById(R.id.Main_TV_Timer);

        pbMain = findViewById(R.id.Main_PB);

        wvMain = findViewById(R.id.Main_WV);
        wvMain.getSettings().setSupportZoom(true);
        wvMain.getSettings().setBuiltInZoomControls(true);
        wvMain.getSettings().setLoadWithOverviewMode(true);
        wvMain.getSettings().setUseWideViewPort(true);
        wvMain.getSettings().setAllowFileAccess(true);
        wvMain.getSettings().setJavaScriptEnabled(true);

        spMain = findViewById(R.id.Main_SP);

        btnCancel = findViewById(R.id.Main_BTN_Cancel);
        btnCancel.setOnClickListener(this);

        btnSubmit = findViewById(R.id.Main_BTN_Submit);
        btnSubmit.setOnClickListener(this);

        tvNavFullName.setText(loginPreference.getFullName());
        tvNavVersion.setText("Version " + BuildConfig.VERSION_NAME);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pbMain.setVisibility(View.VISIBLE);
        wvMain.loadUrl("about:blank");

        // check for requests
        new GetMobileRequestsCallBack(
                this,
                new GetMobileRequestsRequest(
                        loginPreference.getAdminId(),
                        Integer.parseInt(loginPreference.getLocationId()),
                        "Paperless"
                )
        );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.TimeoutRetry:
                Extensions.ReplaceContainers(4, rlTimeoutContainer, rlNotFoundContainer, rlMainContainer, pbMain);
                onResume();
                break;

            case R.id.Main_BTN_Cancel:
                spMain.clear();
                startActivity(new Intent(MainActivity.this, AdsActivity.class));
                break;

            case R.id.Main_BTN_Submit:
                if (spMain.isEmpty()) {
                    Toast.makeText(this, "Please sign your signature", Toast.LENGTH_LONG).show();
                } else {
                    ByteArrayOutputStream bao = new ByteArrayOutputStream();
                    spMain.getTransparentSignatureBitmap(true).compress(Bitmap.CompressFormat.PNG, 100, bao);
                    byte[] signatureByteArray = bao.toByteArray();

                    new SaveCustomerSignatureCallBack(this, new SaveCustomerSignatureRequest(
                            response.getResult().getAdminId(),
                            String.valueOf(response.getResult().getAgreementId()),
                            String.valueOf(response.getResult().getAgreementSignId()),
                            loginPreference.getLocationId(),
                            loginPreference.getPaperlessAdminLoginId(),
                            Base64.encodeToString(signatureByteArray, Base64.DEFAULT)));
                }
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.nav_logout) {
            AlertDialog.Builder logoutDialog = new AlertDialog.Builder(MainActivity.this);
            logoutDialog.setMessage("Are you sure you want to logout?").setCancelable(false)
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new PaperLessAdminLogOutCallBack(MainActivity.this,
                                    new PaperLessAdminLogOutRequest(
                                            Integer.parseInt(loginPreference.getPaperlessAdminLoginId()),
                                            Integer.parseInt(loginPreference.getLocationId())
                                    ));
                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = logoutDialog.create();
            alertDialog.show();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    // todo -- handle this to be shown on agreemeents only
    public void onGetPaperLessAgreementCallBack(GetPaperLessAgreementResponse response) {
        if (!Extensions.isNullOrEmpty(response.getResult().getAgreementUrl()) && !response.getResult().getAgreementUrl().contains("AgreementFormID=0")) {
            this.response = response;
            Extensions.ReplaceContainers(3, rlTimeoutContainer, rlNotFoundContainer, rlMainContainer, pbMain);

            wvMain.loadUrl(response.getResult().getAgreementUrl());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1.0f);
            llContractContainer.setLayoutParams(params);
        } else {
            Extensions.ReplaceContainers(2, rlTimeoutContainer, rlNotFoundContainer, rlMainContainer, pbMain);
            Timer();

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0.0f);
            llContractContainer.setLayoutParams(params);
        }
    }

    public void onGetMobileRequestsCallBack(GetMobileRequestsResponse response) {
        if (response.getState() != null
                && response.getState().equals(true)
                && !Extensions.isNullOrEmpty(response.getResult().getRequestType())) {

            Intent customerInformationIntent = new Intent(this, CustomerInformationActivity.class);
            customerInformationIntent.putExtra("customerId", response.getResult().getCustomerId());
            customerInformationIntent.putExtra("mobileRequestId", response.getResult().getMobileRequestId());

            // handle request type
            switch (response.getResult().getRequestType()) {
                case "Contract":
                    new GetPaperLessAgreementCallBack(this, new GetPaperLessAgreementRequest(
                            Integer.parseInt(loginPreference.getPaperlessAdminLoginId()),
                            loginPreference.getAdminId(),
                            Integer.parseInt(loginPreference.getLocationId()),
                            response.getResult().getMobileRequestId()));
                    break;

                case "Create":
                    customerInformationIntent.putExtra("isCreateNewCustomer", true);
                    startActivity(customerInformationIntent);
                    break;

                case "Update":
                    customerInformationIntent.putExtra("isCreateNewCustomer", false);
                    startActivity(customerInformationIntent);
                    break;
            }
        } else {
            Extensions.ReplaceContainers(2, rlTimeoutContainer, rlNotFoundContainer, rlMainContainer, pbMain);
            Timer();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0.0f);
            llContractContainer.setLayoutParams(params);
        }
    }

    public void onGetCallBackError(String error) {
        Extensions.ReplaceContainers(1, rlTimeoutContainer, rlNotFoundContainer, rlMainContainer, pbMain);
        tvError.setText(error);
    }

    private void Timer() {
        tvTimer.setText("Going back to Ads page after 5 sec...");
        final Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    i = 5;
                    for (; i > 0; i--) {
                        Log.v("okhttp", "i = " + i);
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (i != 1) {
                                    tvTimer.setText("Going back to Ads page after " + i + " sec...");
                                } else {
                                    startActivity(new Intent(MainActivity.this, AdsActivity.class));
                                    i = 1;
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    public void onSaveCustomerSignatureCallBack() {
        spMain.clear();
        startActivity(new Intent(this, AdsActivity.class));
    }
}