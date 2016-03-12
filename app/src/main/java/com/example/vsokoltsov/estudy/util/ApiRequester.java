package com.example.vsokoltsov.estudy.util;

/**
 * Created by vsokoltsov on 11.03.16.
 */
public class ApiRequester {
    private static final String APP_HOST = "http://02a6545a.ngrok.io";
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

//    public RestAdapter getRestAdapter() {
//        return new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(ApiRequester.API_ADDRESS)
//                .setClient(new OkClient(new OkHttpClient())).build();
//    }
}
