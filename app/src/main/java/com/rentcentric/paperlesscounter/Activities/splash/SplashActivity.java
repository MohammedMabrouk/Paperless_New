package com.rentcentric.paperlesscounter.Activities.splash;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.rentcentric.paperlesscounter.Activities.LoginActivity;
import com.rentcentric.paperlesscounter.Activities.MainActivity;
import com.rentcentric.paperlesscounter.BuildConfig;
import com.rentcentric.paperlesscounter.R;


public class SplashActivity extends AppCompatActivity implements SplashActivityPresenter.View {
    private static final String TAG = SplashActivity.class.getSimpleName() + "TAGG";

    ProgressBar progressBar;
    TextView retry, version;
   SplashActivityPresenter splashActivityPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // init ui
        version = findViewById(R.id.tv_version);
        version.setText("version: " + BuildConfig.VERSION_NAME);

        progressBar = findViewById(R.id.LoginProgressBar);
        retry = findViewById(R.id.LoginRetry);

        splashActivityPresenter = new SplashActivityPresenter(this, this);

        splashActivityPresenter.checkAppVersion();

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (splashActivityPresenter.isOnline()) {
//                    splashActivityPresenter.checkLogin();
//                }
                splashActivityPresenter.checkAppVersion();

            }
        });

        // AppCenter
        AppCenter.start(getApplication(), "6820e617-ff1d-4916-909f-06afb0b2260e",
                Analytics.class, Crashes.class);
    }


    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showRetryButton() {
        retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetryButton() {
        retry.setVisibility(View.INVISIBLE);
    }

    @Override
    public void goToLoginActivity() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToHomeActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showAppUpdateDialog(String latestVersion) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(getString(R.string.you_are_not_updated_title));
        alertDialogBuilder.setMessage(getResources().getString(R.string.app_update_message, latestVersion));
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton(R.string.update, (dialog, id) -> {
            this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + this.getPackageName())));
            //dialog.cancel();
        });

        alertDialogBuilder.show();
    }

    @Override
    public void showError(String message) {
        android.app.AlertDialog.Builder
                alert = new android.app.AlertDialog.Builder(this);
        alert.setPositiveButton(getResources().getString(R.string.ok), null);
        alert.setCancelable(false);

        alert.setMessage(message);
        alert.show();
    }

    @Override
    public void checkLogin() {
        if (splashActivityPresenter.isOnline()) {
            splashActivityPresenter.checkLogin();
        }
    }
}