package com.rentcentric.paperlesscounter.Network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    public static String WWW;
    public static String ClientID;

    public static APIs buildPortal() {
        return new Retrofit.Builder()
                .baseUrl("https://www5.rentcentric.com/Portal_Service/PortalService.svc/")
                .client(new OkHttpClient.Builder()
                        .connectTimeout(5, TimeUnit.MINUTES)
                        .readTimeout(5, TimeUnit.MINUTES)
                        .writeTimeout(5, TimeUnit.MINUTES)
                        .build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIs.class);
    }

    public static APIs build() {
        return new Retrofit.Builder()
                .baseUrl("https://" + WWW + ".rentcentric.com/Client" + ClientID + "/mobileservice.svc/")
                .client(new OkHttpClient.Builder()
                        .connectTimeout(5, TimeUnit.MINUTES)
                        .readTimeout(5, TimeUnit.MINUTES)
                        .writeTimeout(5, TimeUnit.MINUTES)
                        .build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIs.class);
    }
}