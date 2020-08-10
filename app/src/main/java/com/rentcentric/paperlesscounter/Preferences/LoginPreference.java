package com.rentcentric.paperlesscounter.Preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Map;

public class LoginPreference {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String PREF_IS_SIGNED_IN = "isSignedIn";
    private static final String PREF_EMAIL = "email";
    private static final String PREF_PASSWORD = "password";
    private static final String PREF_REMEMBER_ME = "rememberMe";
    private static final String PREF_FULL_NAME = "fullName";
    private static final String PREF_ADMIN_ID = "adminID";
    private static final String PREF_CLIENT_ID = "clientID";
    private static final String PREF_SERVER_NAME = "serverName";
    private static final String PREF_LOCATION_ID = "locationID";
    private static final String PREF_PAPERLESS_ADMIN_LOGIN_ID = "paperlessAdminLoginID";
    private static final String PREF_TOKEN = "token";

    public LoginPreference(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public Boolean isSignedIn() {
        return sharedPreferences.getBoolean(PREF_IS_SIGNED_IN, false);
    }

    public void setIsSignedIn(Boolean isSignedIn) {
        editor.putBoolean(PREF_IS_SIGNED_IN, isSignedIn).apply();
    }

    public String getMail() {
        return sharedPreferences.getString(PREF_EMAIL, "");
    }

    public void setMail(String mail) {
        editor.putString(PREF_EMAIL, mail);
        editor.apply();
    }

    public String getPassword() {
        return sharedPreferences.getString(PREF_PASSWORD, "");
    }

    public void setPassword(String password) {
        editor.putString(PREF_PASSWORD, password);
        editor.apply();
    }

    public String getFullName() {
        return sharedPreferences.getString(PREF_FULL_NAME, "");
    }

    public void setFullName(String fullName) {
        editor.putString(PREF_FULL_NAME, fullName);
        editor.apply();
    }

    public Boolean getRememberMe() {
        return sharedPreferences.getBoolean(PREF_REMEMBER_ME, false);
    }

    public void setRememberMe(Boolean rememberMe){
        editor.putBoolean(PREF_REMEMBER_ME, rememberMe);
        editor.apply();
    }

    public String getLocationId() {
        return sharedPreferences.getString(PREF_LOCATION_ID, "");
    }

    public void setLocationId(String locationId) {
        editor.putString(PREF_LOCATION_ID, locationId);
        editor.apply();
    }

    public String getAdminId() {
        return sharedPreferences.getString(PREF_ADMIN_ID, "");
    }

    public void setAdminId(String adminId) {
        editor.putString(PREF_ADMIN_ID, adminId);
        editor.apply();
    }

    public String getClientId() {
        return sharedPreferences.getString(PREF_CLIENT_ID, "");
    }

    public void setClientId(String clientId) {
        editor.putString(PREF_CLIENT_ID, clientId);
        editor.apply();
    }

    public String getServerName() {
        return sharedPreferences.getString(PREF_SERVER_NAME, "");
    }

    public void setServerName(String serverName) {
        editor.putString(PREF_SERVER_NAME, serverName);
        editor.apply();
    }

    public String getPaperlessAdminLoginId() {
        return sharedPreferences.getString(PREF_PAPERLESS_ADMIN_LOGIN_ID, "");
    }

    public void setPaperlessAdminLoginId(String paperlessAdminLoginId) {
        editor.putString(PREF_PAPERLESS_ADMIN_LOGIN_ID, paperlessAdminLoginId);
        editor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(PREF_TOKEN, "");
    }

    public void setToken(String token) {
        editor.putString(PREF_TOKEN, "bearer " + token);
        editor.apply();
    }

    public void clearLoginData() {
        if(getRememberMe()){
            Map<String, ?> prefs = sharedPreferences.getAll();
            for (Map.Entry<String, ?> prefToReset : prefs.entrySet()) {
                if (!prefToReset.getKey().equals(PREF_REMEMBER_ME) &&
                        !prefToReset.getKey().equals(PREF_EMAIL) &&
                        !prefToReset.getKey().equals(PREF_PASSWORD)
                )
                    editor.remove(prefToReset.getKey()).apply();
            }
        }else{ // clear all
            editor.clear().apply();
        }

    }
}