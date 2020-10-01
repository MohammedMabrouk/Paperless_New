package com.rentcentric.paperlesscounter.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.rentcentric.paperlesscounter.BuildConfig;
import com.rentcentric.paperlesscounter.CallBacks.MobileUserLoginCallBack;
import com.rentcentric.paperlesscounter.Models.Requests.MobileUserLoginRequest;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.R;

import org.json.JSONObject;
import org.jsoup.Jsoup;

import at.markushi.ui.CircleButton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etMail, etPassword;
    CheckBox cbRememberMe;
    CircleButton btnLogin;
    TextView versionTextView;

    LoginPreference loginPreference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etMail = findViewById(R.id.Login_ET_Mail);
        etPassword = findViewById(R.id.Login_ET_Password);
        cbRememberMe = findViewById(R.id.Login_CB_RememberMe);
        btnLogin = findViewById(R.id.Login_BTN_Login);
        btnLogin.setOnClickListener(this);
        versionTextView = findViewById(R.id.tv_version);
        versionTextView.setText("version: " + BuildConfig.VERSION_NAME);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(getResources().getString(R.string.loading_txt));
        progressDialog.setCancelable(false);

        loginPreference = new LoginPreference(this);

//        if (!loginPreference.isSignedIn()) {
//            if (loginPreference.getRememberMe()) {
//                etMail.setText(loginPreference.getMail());
//                etPassword.setText(loginPreference.getPassword());
//                cbRememberMe.setChecked(true);
//            }
//            if (BuildConfig.DEBUG) {
//                etMail.setText("RCQA125@rentcentric.com");
//                etPassword.setText("3OmpYcx47q");
//
//                // client 7081 - renty
////                etMail.setText("sean@renty.biz");
////                etPassword.setText("Tiq5pe");
//            }
//        } else {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }

        new ForceUpdateAsync().execute();
        showProgressDialog();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Login_BTN_Login:
                if (TextUtils.isEmpty(etMail.getText())) {
                    etMail.setError("Set E-Mail Address");
                    etMail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(etMail.getText().toString()).matches()) {
                    etMail.setError("Invalid E-Mail Address");
                    etMail.requestFocus();
                } else if (TextUtils.isEmpty(etPassword.getText())) {
                    etPassword.setError("Set Password");
                    etPassword.requestFocus();
                } else {
                    if (cbRememberMe.isChecked()) {
                        loginPreference.setMail(etMail.getText().toString());
                        loginPreference.setPassword(etPassword.getText().toString());
                        loginPreference.setRememberMe(true);
                    } else if (!cbRememberMe.isChecked()) {
                        loginPreference.setRememberMe(false);
                        loginPreference.setMail("");
                        loginPreference.setPassword("");
                    }

                    new MobileUserLoginCallBack(this, new MobileUserLoginRequest(
                            etMail.getText().toString(),
                            etPassword.getText().toString()));
                }
                break;
        }
    }

    private void showProgressDialog(){
        progressDialog.show();
    }

    private void hideProgressDialog(){
        progressDialog.hide();
    }

    public void showDialog(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setPositiveButton(getResources().getString(R.string.ok), null);
        alert.setMessage(message);
        alert.setCancelable(false);
        alert.show();
    }

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

    public class ForceUpdateAsync extends AsyncTask<String, String, JSONObject> {
        private String latestVersion = null;
        String networkErrorMessage = null;

        @Override
        protected JSONObject doInBackground(String... params) {
            try {
                latestVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + getPackageName() + "&hl=en")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select("div.hAyfc:nth-child(4) > span:nth-child(2) > div:nth-child(1) > span:nth-child(1)")
                        .first()
                        .ownText();
            } catch (Exception e) {
                e.printStackTrace();
                networkErrorMessage = getString(R.string.network_error);
            }
            return new JSONObject();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            hideProgressDialog();
            if (networkErrorMessage != null) {
                showDialog(networkErrorMessage);
            } else if (latestVersion != null) {
                if (isOldVersion(BuildConfig.VERSION_NAME , latestVersion)) {
                    showAppUpdateDialog(latestVersion);
                } else {
                    if (!loginPreference.isSignedIn()) {
                        if (loginPreference.getRememberMe()) {
                            etMail.setText(loginPreference.getMail());
                            etPassword.setText(loginPreference.getPassword());
                            cbRememberMe.setChecked(true);
                        }
                        if (BuildConfig.DEBUG) {
                            etMail.setText("RCQA125@rentcentric.com");
                            etPassword.setText("3OmpYcx47q");

                            // client 7081 - renty
//                etMail.setText("sean@renty.biz");
//                etPassword.setText("Tiq5pe");
                        }
                    } else {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
            super.onPostExecute(jsonObject);
        }
    }

    boolean isOldVersion(String appVersion, String storeVersion) {
        boolean isOld = false;

        if (Integer.parseInt(appVersion.split("\\.")[0]) < Integer.parseInt(storeVersion.split("\\.")[0])) {
            isOld = true;
        } else if (Integer.parseInt(appVersion.split("\\.")[1]) < Integer.parseInt(storeVersion.split("\\.")[1]) && Integer.parseInt(appVersion.split("\\.")[0]) == Integer.parseInt(storeVersion.split("\\.")[0])) {
            isOld = true;
        } else if (appVersion.split("\\.").length > 2 && storeVersion.split("\\.").length > 2 &&
                Integer.parseInt(appVersion.split("\\.")[2]) < Integer.parseInt(storeVersion.split("\\.")[2]) && Integer.parseInt(appVersion.split("\\.")[1]) == Integer.parseInt(storeVersion.split("\\.")[1]) && Integer.parseInt(appVersion.split("\\.")[0]) == Integer.parseInt(storeVersion.split("\\.")[0])) {
            isOld = true;
        }

        return isOld;
    }
}