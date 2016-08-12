package io.intrepid.skeleton.rest;

import android.support.annotation.VisibleForTesting;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import timber.log.Timber;

public class RetrofitClient {

    private static final String BASE_URL = "http://google.com";
    private static final int CONNECTION_TIMEOUT = 30;

    private static RestApi instance;

    public static RestApi getApi() {
        if (instance == null) {
            instance = createRestApi(BASE_URL);
        }
        return instance;
    }

    @VisibleForTesting
    static RestApi getTestApi(String baseUrl) {
        return createRestApi(baseUrl);
    }

    private RetrofitClient() {
    }

    private static RestApi createRestApi(String baseUrl) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient httpClient = builder
                .addInterceptor(new HttpLoggingInterceptor(message -> Timber.v(message)).setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                // TODO: add Gson/Jackson converter factory
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(RestApi.class);
    }
}
