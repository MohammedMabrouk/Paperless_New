package com.rentcentric.paperlesscounter.Preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LoginPreference {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public LoginPreference(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public String getMail() {
        return sharedPreferences.getString("mail", "");
    }

    public void setMail(String mail) {
        editor.putString("mail", mail);
        editor.apply();
    }

    public String getPassword() {
        return sharedPreferences.getString("password", "");
    }

    public void setPassword(String password) {
        editor.putString("password", password);
        editor.apply();
    }

    public Boolean getIsChecked() {
        return sharedPreferences.getBoolean("isChecked", false);
    }

    public void setIsChecked(Boolean isChecked) {
        editor.putBoolean("isChecked", isChecked);
        editor.apply();
    }
}