package com.rentcentric.paperlesscounter.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rentcentric.paperlesscounter.CallBacks.GetPaperLessTempChargeSummeryCallBack;
import com.rentcentric.paperlesscounter.Models.Responses.GetPaperLessTempChargeSummeryResponse;
import com.rentcentric.paperlesscounter.R;

public class ChargesSummaryActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView backImageView;
    WebView webView;
    public ProgressBar loadingPb;
    FloatingActionButton refreshFAB;
    Button agreeBtn;
    TextView msgTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charges_summary);

        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getChargesSummary();
    }

    void getChargesSummary() {
        msgTextView.setVisibility(View.GONE);
        webView.setVisibility(View.INVISIBLE);
        new GetPaperLessTempChargeSummeryCallBack(this);
    }

    void initUI() {
        backImageView = findViewById(R.id.btnBack);
        backImageView.setOnClickListener(this);

        webView = findViewById(R.id.webview);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);

        loadingPb = findViewById(R.id.progressBar);
        refreshFAB = findViewById(R.id.fab_refresh);
        refreshFAB.setOnClickListener(this);

        agreeBtn = findViewById(R.id.btn_agree);
        agreeBtn.setOnClickListener(this);

        msgTextView = findViewById(R.id.tv_msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                MainActivity.mainActivity.showAds = true;
                onBackPressed();
                break;

            case R.id.fab_refresh:
                getChargesSummary();
                break;

            case R.id.btn_agree:
                MainActivity.mainActivity.isWaitForContract = true;
                onBackPressed();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            MainActivity.mainActivity.showAds = true;
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onGetPaperLessChargesSummaryCallBack(GetPaperLessTempChargeSummeryResponse response) {
        if (response.getResult() != null) {
            String htmlString = response.getResult();
            if (htmlString.contains(".")) {
                // edit position and width
                htmlString = htmlString.replace("style=\"\"", "style=\"width: 90%; margin-left: 20px; margin-right: 20px; margin-top:20px; font-size:35px\"");
                // remove links
                htmlString = htmlString.replace("href", "");

                webView.loadDataWithBaseURL("", htmlString, "text/html", "UTF-8", "");
                webView.setVisibility(View.VISIBLE);
                msgTextView.setVisibility(View.GONE);
            } else {
                msgTextView.setVisibility(View.VISIBLE);
            }
        } else {
            showToast("HTML not found (GetPaperLessTempChargeSummery API)");
        }
    }

    public void onGetCallBackError(String error) {
        showToast(error);
    }

    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}