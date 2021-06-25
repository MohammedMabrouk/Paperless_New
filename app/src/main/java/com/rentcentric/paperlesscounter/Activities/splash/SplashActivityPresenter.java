package com.rentcentric.paperlesscounter.Activities.splash;

import android.content.Context;
import android.os.Handler;

import com.rentcentric.paperlesscounter.Utility.NetworkUtil;


public class SplashActivityPresenter {
    private View view;
    private SplashRepository splashRepository;
    private Context context;

    SplashActivityPresenter(Context context, View view) {
        this.context = context;
        this.view = view;
        splashRepository = new SplashRepository(context, this);
    }

    void checkLogin() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (splashRepository.getLoginStatus()) {
                    view.goToHomeActivity();
                } else {
                    view.goToLoginActivity();
                }
            }
        }, 1000);
    }

    boolean isOnline() {
        if (NetworkUtil.isOnline(context)) {
            view.hideRetryButton();
            view.showProgressBar();
            return true;
        } else {
            view.hideProgressBar();
            view.showRetryButton();
            return false;
        }
    }

    void checkAppVersion() {
        view.hideRetryButton();
        view.showProgressBar();
        splashRepository.checkAppVersion();

    }

    void onOldAppVersionDetected(String latestVersion) {
        view.hideProgressBar();
        view.showAppUpdateDialog(latestVersion);
        view.showRetryButton();
    }

    void onNewAppVersionDetected() {
        view.hideRetryButton();
        view.hideProgressBar();
        view.checkLogin();
    }

    void onError(String message) {
        view.hideProgressBar();
        view.showError(message);
        view.showRetryButton();
    }

    public interface View {
        void showProgressBar();

        void hideProgressBar();

        void showRetryButton();

        void hideRetryButton();

        void goToLoginActivity();

        void goToHomeActivity();

        void showAppUpdateDialog(String latestVersion);

        void showError(String message);

        void checkLogin();
    }
}
