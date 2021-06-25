package com.rentcentric.paperlesscounter.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.facebook.stetho.Stetho;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.android.material.navigation.NavigationView;
import com.rentcentric.paperlesscounter.BuildConfig;
import com.rentcentric.paperlesscounter.CallBacks.GetMobileRequestsCallBack;
import com.rentcentric.paperlesscounter.CallBacks.GetPaperLessAgreementCallBack;
import com.rentcentric.paperlesscounter.CallBacks.GetSignatureSetupCallBack;
import com.rentcentric.paperlesscounter.CallBacks.PaperLessSaveCustomerCheckInSignatureCallBack;
import com.rentcentric.paperlesscounter.CallBacks.SaveCustomerSignatureCallBack;
import com.rentcentric.paperlesscounter.CallBacks.SaveSignatureSetupsCallback;
import com.rentcentric.paperlesscounter.Models.Requests.GetMobileRequestsRequest;
import com.rentcentric.paperlesscounter.Models.Requests.GetPaperLessAgreementRequest;
import com.rentcentric.paperlesscounter.Models.Requests.PaperLessSaveCustomerCheckInSignatureRequest;
import com.rentcentric.paperlesscounter.Models.Requests.SaveCustomerSignatureRequest;
import com.rentcentric.paperlesscounter.Models.Requests.SaveSignatureRequest;
import com.rentcentric.paperlesscounter.Models.Requests.Signature;
import com.rentcentric.paperlesscounter.Models.Responses.GetMobileRequestsResponse;
import com.rentcentric.paperlesscounter.Models.Responses.GetPaperLessAgreementResponse;
import com.rentcentric.paperlesscounter.Models.Responses.SignatureSetup;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.R;
import com.rentcentric.paperlesscounter.Utility.Const;
import com.rentcentric.paperlesscounter.Utility.Extensions;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import im.delight.android.webview.AdvancedWebView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener, AdvancedWebView.Listener {

    public static MainActivity mainActivity;

    DrawerLayout drawer;
    RelativeLayout rlTimeoutContainer, rlNotFoundContainer, rlMainContainer;
    LinearLayout llContractContainer;
    TextView tvError, tvRetry, tvNavFullName, tvNavVersion, tvTimer;
    ProgressBar mainProgressBar;
    AdvancedWebView mainWebView;
    com.github.barteksc.pdfviewer.PDFView pdfView;
    SignaturePad spMain;
    Button btnCancel, btnSubmit;

    private Thread timerThread;

    int i;

    private GetPaperLessAgreementResponse getPaperLessAgreementResponse;

    private LoginPreference loginPreference;
    private GetMobileRequestsResponse getMobileRequestsResponse;

    // Signatures List (if Found)
    public List<Signature> signaturesList = null;
    public final static String INTENT_SIGNATURES_MAP = "signatures_map";
    public final static int SIGNATURES_REQUEST_CODE = 0;
    private boolean isSignaturesSigned = false;

    public boolean showAds = false;
    public boolean isWaitForContract = false;

    private boolean isDrawerOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainActivity = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this);

        loginPreference = new LoginPreference(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // drawer.open

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                isDrawerOpen = true;
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                isDrawerOpen = false;
                Extensions.ReplaceContainers(2, rlTimeoutContainer, rlNotFoundContainer, rlMainContainer, mainProgressBar);
                startTimer();
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        0.0f);
                llContractContainer.setLayoutParams(params);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

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

        mainProgressBar = findViewById(R.id.Main_PB);

        mainWebView = findViewById(R.id.Main_WV);

        mainWebView.setListener(this, this);
        mainWebView.setMixedContentAllowed(false);

        pdfView = findViewById(R.id.pdfView);
        spMain = findViewById(R.id.Main_SP);

        btnCancel = findViewById(R.id.Main_BTN_Cancel);
        btnCancel.setOnClickListener(this);

        btnSubmit = findViewById(R.id.Main_BTN_Submit);
        btnSubmit.setOnClickListener(this);

        tvNavFullName.setText(loginPreference.getFullName());
        tvNavVersion.setText("version:" + BuildConfig.VERSION_NAME);
    }



    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (showAds) {
            Extensions.ReplaceContainers(2, rlTimeoutContainer, rlNotFoundContainer, rlMainContainer, mainProgressBar);
            startTimer();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0.0f);
            llContractContainer.setLayoutParams(params);
            showAds = false;
        } else if (signaturesList == null && !isSignaturesSigned) {
            // reset
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle(getString(R.string.app_name));
            pdfView.setVisibility(View.INVISIBLE);
            mainWebView.setVisibility(View.INVISIBLE);

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

        if (isWaitForContract) {
            Toast.makeText(this, "Please wait...\n" +
                    "We are almost there", Toast.LENGTH_LONG).show();
            isWaitForContract = false;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.TimeoutRetry:
                Extensions.ReplaceContainers(4, rlTimeoutContainer, rlNotFoundContainer, rlMainContainer, mainProgressBar);
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
                    if (signaturesList != null) {
                        new SaveSignatureSetupsCallback(
                                this,
                                new SaveSignatureRequest(
                                        getPaperLessAgreementResponse.getResult().getAgreementId(),
                                        signaturesList));
                    } else {
                        ByteArrayOutputStream bao = new ByteArrayOutputStream();
                        spMain.getTransparentSignatureBitmap(true).compress(Bitmap.CompressFormat.PNG, 100, bao);
                        byte[] signatureByteArray = bao.toByteArray();


                        if (getMobileRequestsResponse.getResult().getRequestType().equals("Contract")) {
                            new SaveCustomerSignatureCallBack(
                                    this,
                                    new SaveCustomerSignatureRequest(
                                            getPaperLessAgreementResponse.getResult().getAdminId(),
                                            getPaperLessAgreementResponse.getResult().getAgreementId(),
                                            getPaperLessAgreementResponse.getResult().getAgreementSignId(),
                                            Integer.valueOf(loginPreference.getLocationId()),
                                            Integer.valueOf(loginPreference.getPaperlessAdminLoginId()),
                                            Base64.encodeToString(signatureByteArray, Base64.DEFAULT),
                                            getMobileRequestsResponse.getResult().getMobileRequestId(),
                                            getPaperLessAgreementResponse.getResult().getAgreementUrl()
                                    )
                            );
                        } else if (getMobileRequestsResponse.getResult().getRequestType().equals("Contract CheckIn")) {
                            new PaperLessSaveCustomerCheckInSignatureCallBack(
                                    this,
                                    new PaperLessSaveCustomerCheckInSignatureRequest(
                                            getPaperLessAgreementResponse.getResult().getAdminId(),
                                            getPaperLessAgreementResponse.getResult().getAgreementId(),
                                            getPaperLessAgreementResponse.getResult().getAgreementSignId(),
                                            Integer.valueOf(loginPreference.getLocationId()),
                                            Integer.valueOf(loginPreference.getPaperlessAdminLoginId()),
                                            Base64.encodeToString(signatureByteArray, Base64.DEFAULT),
                                            getMobileRequestsResponse.getResult().getMobileRequestId(),
                                            getPaperLessAgreementResponse.getResult().getAgreementUrl()
                                    )
                            );
                        }

                    }

                }
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.nav_logout) {
//           stopTimer();
            AlertDialog.Builder logoutDialog = new AlertDialog.Builder(MainActivity.this);
            logoutDialog.setMessage("Are you sure you want to logout?").setCancelable(false)
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            loginPreference.clearLoginData();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
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
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logout() {
        loginPreference.clearLoginData();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void sessionExpiredLogout() {
        AlertDialog.Builder logoutDialog = new AlertDialog.Builder(MainActivity.this);
        logoutDialog.setMessage("Session Expired").setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                    }
                });
        AlertDialog alertDialog = logoutDialog.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public void onGetPaperLessAgreementCallBack(GetPaperLessAgreementResponse getPaperLessAgreementResponse) {
        if (getPaperLessAgreementResponse.getState()) {
            if (!Extensions.isNullOrEmpty(getPaperLessAgreementResponse.getResult().getAgreementUrl())
//                && !getPaperLessAgreementResponse.getResult().getAgreementUrl().contains("AgreementFormID=0")
            ) {
                this.getPaperLessAgreementResponse = getPaperLessAgreementResponse;
                Extensions.ReplaceContainers(3, rlTimeoutContainer, rlNotFoundContainer, rlMainContainer, mainProgressBar);

                mainProgressBar.setVisibility(View.VISIBLE);

                if (getPaperLessAgreementResponse.getResult().getContractType().toLowerCase().equals("pdf")) {
                    new DownloadFileFromURL().execute(getPaperLessAgreementResponse.getResult().getAgreementUrl());
                } else {
                    String contractURL = getPaperLessAgreementResponse.getResult().getAgreementUrl();
                    if (contractURL.contains("http") && !contractURL.contains("https"))
                        contractURL = contractURL.replace("http", "https");

                    mainWebView.getSettings().setSupportZoom(true);
                    mainWebView.getSettings().setBuiltInZoomControls(true);
                    mainWebView.getSettings().setLoadWithOverviewMode(true);
                    mainWebView.getSettings().setUseWideViewPort(true);
                    mainWebView.getSettings().setAllowFileAccess(true);
                    mainWebView.getSettings().setJavaScriptEnabled(true);
                    mainWebView.getSettings().setAppCacheEnabled(true);
                    mainWebView.loadUrl(contractURL);
                }


                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        1.0f);
                llContractContainer.setLayoutParams(params);


                if (getMobileRequestsResponse.getResult().getRequestType().equals("Contract"))
                    new GetSignatureSetupCallBack(
                            this,
                            Integer.parseInt(loginPreference.getLocationId()),
                            -1,
                            getPaperLessAgreementResponse.getResult().getAgreementId());

            } else {
                Extensions.ReplaceContainers(2, rlTimeoutContainer, rlNotFoundContainer, rlMainContainer, mainProgressBar);
                startTimer();

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        0.0f);
                llContractContainer.setLayoutParams(params);
            }
        } else if (getPaperLessAgreementResponse.getDescription().equals("Please Login First")) {
            // logout
            AlertDialog.Builder logoutDialog = new AlertDialog.Builder(MainActivity.this);
            logoutDialog.setMessage(getPaperLessAgreementResponse.getDescription()).setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            loginPreference.clearLoginData();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
            AlertDialog alertDialog = logoutDialog.create();
            alertDialog.show();
        }

    }

    public void onGetMobileRequestsCallBack(GetMobileRequestsResponse response) {
        this.getMobileRequestsResponse = response;

        if (response.getState() != null
                && response.getState().equals(true)
                && !Extensions.isNullOrEmpty(response.getResult().getRequestType())) {

            Intent customerInformationIntent = new Intent(this, CustomerInformationActivity.class);
            customerInformationIntent.putExtra("customerId", response.getResult().getCustomerId());
            customerInformationIntent.putExtra("mobileRequestId", response.getResult().getMobileRequestId());

            // handle request type
            switch (response.getResult().getRequestType()) {
                case "Contract":
                    getSupportActionBar().setTitle("Contract");
                    mainProgressBar.setVisibility(View.VISIBLE);
                    new GetPaperLessAgreementCallBack(
                            this,
                            new GetPaperLessAgreementRequest(
                                    Integer.parseInt(loginPreference.getPaperlessAdminLoginId()),
                                    loginPreference.getAdminId(),
                                    Integer.parseInt(loginPreference.getLocationId())));

                    break;

                case "Create":
                    customerInformationIntent.putExtra("isCreateNewCustomer", true);
                    startActivity(customerInformationIntent);
                    break;

                case "Update":
                    customerInformationIntent.putExtra("isCreateNewCustomer", false);
                    startActivity(customerInformationIntent);
                    break;

                case "TempChargeSummery":
                    startActivity(new Intent(this, ChargesSummaryActivity.class));
                    break;

                case "Contract CheckIn":
                    // don't check on signature setups
                    getSupportActionBar().setTitle("Contract");
                    mainProgressBar.setVisibility(View.VISIBLE);
                    new GetPaperLessAgreementCallBack(
                            this,
                            new GetPaperLessAgreementRequest(
                                    Integer.parseInt(loginPreference.getPaperlessAdminLoginId()),
                                    loginPreference.getAdminId(),
                                    Integer.parseInt(loginPreference.getLocationId())));

                    break;

            }
        } else if (response.getDescription().equals("Session Expired, Please Log in")) {
//            Extensions.ReplaceContainers(2, rlTimeoutContainer, rlNotFoundContainer, rlMainContainer, mainProgressBar);
//            startTimer();
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    0.0f);
//            llContractContainer.setLayoutParams(params);

            // logout
            AlertDialog.Builder logoutDialog = new AlertDialog.Builder(MainActivity.this);
            logoutDialog.setMessage(response.getDescription()).setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            logout();
                        }
                    });
            AlertDialog alertDialog = logoutDialog.create();
            alertDialog.show();
        } else if (response.getDescription().equals("Your Rent Centric account is not subscribed to this service. Please contact Rent Centric Sales (sales@rentcentric.com) for additional information and to enable.")) {

            // logout
            AlertDialog.Builder logoutDialog = new AlertDialog.Builder(MainActivity.this);
            logoutDialog.setMessage(response.getDescription()).setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            logout();
                        }
                    });
            AlertDialog alertDialog = logoutDialog.create();
            alertDialog.show();
        } else {
            Extensions.ReplaceContainers(2, rlTimeoutContainer, rlNotFoundContainer, rlMainContainer, mainProgressBar);
            startTimer();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0.0f);
            llContractContainer.setLayoutParams(params);
        }
    }

    public void onGetCallBackError(String error) {
        Extensions.ReplaceContainers(1, rlTimeoutContainer, rlNotFoundContainer, rlMainContainer, mainProgressBar);
        tvError.setText(error);
    }

    private void startTimer() {
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
                                if (i == 0) {
                                    if (!isDrawerOpen) {
                                        startActivity(new Intent(MainActivity.this, AdsActivity.class));
                                    }
                                } else {
                                    tvTimer.setText("Going back to Ads page after " + i + " sec...");
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

    private void stopTimer() {
        if (timerThread.isAlive())
            timerThread.stop();
    }

    public void onSaveCustomerSignatureCallBack() {
        spMain.clear();
        startActivity(new Intent(this, AdsActivity.class));
        showToast("Signature uploaded successfully.");
    }

    public void onSaveSignaturesCallBack() {
        // reset
        signaturesList = null;
        isSignaturesSigned = false;

//        spMain.clear();

//        startActivity(new Intent(this, AdsActivity.class));

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        spMain.getTransparentSignatureBitmap(true).compress(Bitmap.CompressFormat.PNG, 100, bao);
        byte[] signatureByteArray = bao.toByteArray();

        new SaveCustomerSignatureCallBack(
                this,
                new SaveCustomerSignatureRequest(
                        getPaperLessAgreementResponse.getResult().getAdminId(),
                        getPaperLessAgreementResponse.getResult().getAgreementId(),
                        getPaperLessAgreementResponse.getResult().getAgreementSignId(),
                        Integer.valueOf(loginPreference.getLocationId()),
                        Integer.valueOf(loginPreference.getPaperlessAdminLoginId()),
                        Base64.encodeToString(signatureByteArray, Base64.DEFAULT),
                        getMobileRequestsResponse.getResult().getMobileRequestId(),
                        getPaperLessAgreementResponse.getResult().getAgreementUrl()
                )
        );
    }

    public void onGetSignatureSetupSuccess(ArrayList<SignatureSetup> signaturesList) {
        if (signaturesList != null) {
            // go to signatures activity
            Intent intent = new Intent(this, SignaturesActivity.class);
            intent.putExtra(Const.INTENT_SIGNATURES_KEY, signaturesList);
//            intent.putExtra(Const.INTENT_RESERVATION_ID, reservation.getReservationId());
            startActivityForResult(intent, SIGNATURES_REQUEST_CODE);
        }

//        new GetPaperLessAgreementCallBack(
//                this,
//                new GetPaperLessAgreementRequest(
//                        Integer.parseInt(loginPreference.getPaperlessAdminLoginId()),
//                        loginPreference.getAdminId(),
//                        Integer.parseInt(loginPreference.getLocationId())
////                        getMobileRequestsResponse.getResult().getMobileRequestId()
//                )
//        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGNATURES_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null && data.getIntegerArrayListExtra(INTENT_SIGNATURES_MAP) != null) {
                    signaturesList = data.getParcelableArrayListExtra(INTENT_SIGNATURES_MAP);
                    isSignaturesSigned = true;
                }
            }
        }
    }


    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        Log.v("", "");
    }

    @Override
    public void onPageFinished(String url) {
        Log.v("", "");
        mainProgressBar.setVisibility(View.INVISIBLE);
        mainWebView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        Log.v("", "");
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
//        Log.v("", "");
//        new DownloadFileFromURL().execute(url);
    }

    @Override
    public void onExternalPageRequest(String url) {
        Log.v("", "");
    }

    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("Starting download");
            mainProgressBar.setVisibility(View.VISIBLE);
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            String filePath = "";
            try {
                String timeStamp = String.valueOf(System.currentTimeMillis());
                String imageFileName = timeStamp + "_contract";
                File storageDir = MainActivity.this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                File pdfFile = File.createTempFile(
                        imageFileName,  /* prefix */
                        ".pdf",         /* suffix */
                        storageDir      /* directory */
                );

                filePath = pdfFile.getPath();

//                String root = Environment.getExternalStorageDirectory().toString();

                System.out.println("Downloading");
                URL url = new URL(f_url[0]);

                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file

                OutputStream output = new FileOutputStream(pdfFile);
                byte data[] = new byte[1024];

                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;

                    // writing data to file
                    output.write(data, 0, count);

                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return filePath;
        }


        /**
         * After completing background task
         **/
        @Override
        protected void onPostExecute(String file_path) {
            System.out.println("Downloaded");
            mainProgressBar.setVisibility(View.INVISIBLE);
            mainWebView.setVisibility(View.INVISIBLE);
            pdfView.setVisibility(View.VISIBLE);
            pdfView.fromFile(new File(file_path))
                    .enableSwipe(true)
                    .enableAnnotationRendering(true)
                    .swipeHorizontal(false)
                    .load();
        }
    }

}