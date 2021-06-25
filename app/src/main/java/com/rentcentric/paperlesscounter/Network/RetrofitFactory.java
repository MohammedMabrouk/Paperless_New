package com.rentcentric.paperlesscounter.Network;

import com.rentcentric.paperlesscounter.BuildConfig;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    public static APIs getUserLoginService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if(BuildConfig.DEBUG) interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        else interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(6, TimeUnit.MINUTES)
                .connectTimeout(6, TimeUnit.MINUTES)
                .writeTimeout(6, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl("https://www5.rentcentric.com/Portal_Service/PortalService.svc/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIs.class);
    }

//    public static APIs getOldClientService(String serverName, String clientId) {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        if(BuildConfig.DEBUG) interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        else interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
//
//        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .readTimeout(600000, TimeUnit.SECONDS)
//                .connectTimeout(600000, TimeUnit.SECONDS)
//                .addInterceptor(interceptor)
//                .build();
//
//        return new Retrofit.Builder()
//                .baseUrl("https://" + serverName + ".rentcentric.com/Client" + clientId + "/mobileservice.svc/")
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(APIs.class);
//    }

    public static APIs getClientService(String serverName, String clientId, String token) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if(BuildConfig.DEBUG) interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        else interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(6, TimeUnit.MINUTES)
                .connectTimeout(6, TimeUnit.MINUTES)
                .writeTimeout(6, TimeUnit.MINUTES)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("Authorization", token)
                                .addHeader("AppName", "Paperless")
                                .addHeader("Platform", "Android")
                                .addHeader("AppVersion", BuildConfig.VERSION_NAME)
                                .build();
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(interceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl("https://" + serverName + ".rentcentric.com/Client" + clientId + "/CarShareSelfServiceApi/api/")
                //http://www5.rentcentric.com:80/Client6913/CarShareSelfServiceApi/api/PaperLess/PaperLessAdminLogin
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIs.class);
    }
}