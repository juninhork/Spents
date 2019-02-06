package com.example.aplication.service;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by juniorbraga on 28/03/17.
 */

public abstract class ConnectPortadorService {

    private static PortadorService service;

    public static PortadorService getService(){
        if(service==null){

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            // init cookie manager
            CookieHandler cookieHandler = new CookieManager();

            OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor)
                    .cookieJar(new JavaNetCookieJar(cookieHandler))
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://fipeapi.appspot.com/api/1/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(PortadorService.class);
        }
        return service;
    }
}
