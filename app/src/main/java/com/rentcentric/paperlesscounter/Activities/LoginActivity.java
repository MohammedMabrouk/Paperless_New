package com.rentcentric.paperlesscounter.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

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

        if (loginPreference.getIsChecked()) {
            etMail.setText(loginPreference.getMail());
            etPassword.setText(loginPreference.getPassword());
            cbRememberMe.setChecked(true);
        }

        if (BuildConfig.DEBUG) {
            etMail.setText("hsroahu@gmail.com");
            etPassword.setText("oWq6wg5S");
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
                        loginPreference.setIsChecked(true);
                    }

                    new MobileUserLoginCallBack(this, new MobileUserLoginRequest(
                            etMail.getText().toString(),
                            etPassword.getText().toString()));
                }
                break;
        }
    }
}