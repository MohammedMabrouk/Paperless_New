package com.rentcentric.paperlesscounter.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.rentcentric.paperlesscounter.CallBacks.GetPaperLessCounterAdsCallBack;
import com.rentcentric.paperlesscounter.Models.Requests.GetPaperLessCounterAdsRequest;
import com.rentcentric.paperlesscounter.Models.Responses.GetPaperLessCounterAdsResponse;
import com.rentcentric.paperlesscounter.Network.RetrofitFactory;
import com.rentcentric.paperlesscounter.Preferences.LoginPreference;
import com.rentcentric.paperlesscounter.R;
import com.rentcentric.paperlesscounter.Utility.SwipeGesture;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdsActivity extends AppCompatActivity {
    TextView tvSwipeMessage;
    ImageView ivAdsImage;

    List<String> adsImages = new ArrayList<>();
    List<Integer> adsImagesDuration = new ArrayList<>();
    int i;
    private LoginPreference loginPreference;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_ads);

        tvSwipeMessage = findViewById(R.id.Ads_TV_SwipeMessage);

        ivAdsImage = findViewById(R.id.Ads_IV_AdsImage);
        ivAdsImage.setOnTouchListener(new SwipeGesture(AdsActivity.this) {
            public void onSwipeTop() {
                finish();
            }

            public void onSwipeRight() {
                finish();
            }

            public void onSwipeLeft() {
                finish();
            }

            public void onSwipeBottom() {
                finish();
            }

        });

        swipeMessageAnimation();

        loginPreference = new LoginPreference(this);
        new GetPaperLessCounterAdsCallBack(this, new GetPaperLessCounterAdsRequest(
                loginPreference.getClientId(),
                loginPreference.getServerName()));
    }

    private void swipeMessageAnimation() {
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(700);
        anim.setStartOffset(500);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        tvSwipeMessage.startAnimation(anim);
    }

    public void onGetPaperLessCounterAdsCallBack(GetPaperLessCounterAdsResponse response) {
        if (response.getGetPaperLessCounterAdsResult().getPaperLessCounterAdsList() != null &&
                response.getGetPaperLessCounterAdsResult().getPaperLessCounterAdsList().size() > 0) {
            for (int i = 0; i < response.getGetPaperLessCounterAdsResult().getPaperLessCounterAdsList().size(); i++) {
                adsImages.add(response.getGetPaperLessCounterAdsResult().getPaperLessCounterAdsList().get(i).getPaperImage());
                adsImagesDuration.add(response.getGetPaperLessCounterAdsResult().getPaperLessCounterAdsList().get(i).getDuration());
            }
            Timer();
        }
    }

    private void Timer() {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    for (i = 1; i <= adsImages.size(); i++) {
                        Thread.sleep(adsImagesDuration.get(i) * 1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Picasso.get().load(adsImages.get(i-1)).into(ivAdsImage);
                                if (i == adsImages.size()) {
                                    Timer();
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
}