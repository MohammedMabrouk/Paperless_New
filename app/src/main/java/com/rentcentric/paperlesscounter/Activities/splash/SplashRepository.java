package com.rentcentric.paperlesscounter.Activities.splash;

import android.content.Context;
import android.os.AsyncTask;


import com.rentcentric.paperlesscounter.BuildConfig;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.R;

import org.json.JSONObject;
import org.jsoup.Jsoup;

public class SplashRepository {
    Context context;
    LoginPreference loginPreference;
    SplashActivityPresenter presenter;

    SplashRepository(Context context, SplashActivityPresenter presenter) {
        this.context = context;
        this.loginPreference = new LoginPreference(context);
        this.presenter = presenter;
    }

    boolean getLoginStatus() {
        return loginPreference.isSignedIn();
    }

    void checkAppVersion() {
        new ForceUpdateAsync().execute();
    }

    public class ForceUpdateAsync extends AsyncTask<String, String, JSONObject> {
        private String latestVersion = null;
//        private String currentVersion;
//        private Context context;

//        public ForceUpdateAsync(String currentVersion, Context context) {
//            this.currentVersion = currentVersion;
//            this.context = context;
//        }

        String networkErrorMessage = null;

        @Override
        protected JSONObject doInBackground(String... params) {
            try {
                latestVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + context.getPackageName() + "&hl=en")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select("div.hAyfc:nth-child(4) > span:nth-child(2) > div:nth-child(1) > span:nth-child(1)")
                        .first()
                        .ownText();
            } catch (Exception e) {
                e.printStackTrace();
//                networkErrorMessage = e.getLocalizedMessage();
                networkErrorMessage = context.getString(R.string.network_error);
            }
            return new JSONObject();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            if (networkErrorMessage != null) {
                presenter.onError(networkErrorMessage);
            } else if (latestVersion != null) {
                if (isOldVersion(BuildConfig.VERSION_NAME , latestVersion)) {
//                    if ((context instanceof SplashActivity)) {
//                        if (!((Activity) context).isFinishing()) {
//                            showForceUpdateDialog();
//                        }
//                    }
                    presenter.onOldAppVersionDetected(latestVersion);
                } else {
//                    ((SplashActivity) context).loginCheck();
                    presenter.onNewAppVersionDetected();
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
