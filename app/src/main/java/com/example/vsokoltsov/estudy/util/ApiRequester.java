package com.example.vsokoltsov.estudy.util;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by vsokoltsov on 11.03.16.
 */
public class ApiRequester {
    private static final String APP_HOST = "http://a0e4b993.ngrok.io";
    private static final String API_VERSION = "v0/";
    public static final String API_ADDRESS = APP_HOST + "/api/" + API_VERSION;

    public static class SingletonHolder {
        public static final ApiRequester HOLDER_INSTANCE = new ApiRequester();
    }

    public static ApiRequester getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    public String fullResourceURL(String url) {
        return APP_HOST + url;
    }


    private OkHttpClient okHttpClient() {
        OkHttpClient httpClientWLogging = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                String locale = Locale.getDefault().getLanguage();
                Request request = chain.request().newBuilder()
                        .addHeader("locale", locale).build();
                return chain.proceed(request);
            }
        }).build();
        return httpClientWLogging;
    }

    public Retrofit getRestAdapter() {
        OkHttpClient client = this.okHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiRequester.API_ADDRESS)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;

    }
}
