package com.rentcentric.paperlesscounter.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.rentcentric.paperlesscounter.BuildConfig;
import com.rentcentric.paperlesscounter.CallBacks.MobileUserLoginCallBack;
import com.rentcentric.paperlesscounter.Models.Requests.MobileUserLoginRequest;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.R;

import at.markushi.ui.CircleButton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etMail, etPassword;
    CheckBox cbRememberMe;
    CircleButton btnLogin;

    LoginPreference loginPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etMail = findViewById(R.id.Login_ET_Mail);
        etPassword = findViewById(R.id.Login_ET_Password);
        cbRememberMe = findViewById(R.id.Login_CB_RememberMe);
        btnLogin = findViewById(R.id.Login_BTN_Login);
        btnLogin.setOnClickListener(this);

        loginPreference = new LoginPreference(this);

        if (!loginPreference.isSignedIn()) {
            if (loginPreference.getRememberMe()) {
                etMail.setText(loginPreference.getMail());
                etPassword.setText(loginPreference.getPassword());
                cbRememberMe.setChecked(true);
            }
            if (BuildConfig.DEBUG) {
                // client 7178 - test
//                etMail.setText("mobileagenttest@rentcentric.com");
//                etPassword.setText("Welcome123");

//                etMail.setText("ahmed.fattoh@rentcentric.com");
//                etPassword.setText("Rent45");
//
//                etMail.setText("mohamed.mabrook@rentcentric.com");
//                etPassword.setText("Rent45");

                // client 7081 - renty
//                etMail.setText("sean@renty.biz");
//                etPassword.setText("Tiq5pe");

                // client 7040
//                etMail.setText("AAASupportTHEVillages@rentcentric.com");
//                etPassword.setText("Rent45");

//                etMail.setText("ian@familyautos.ca");
//                etPassword.setText("Crazy888");
            }
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
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
}