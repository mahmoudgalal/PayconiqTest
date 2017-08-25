package com.mgalal.payconiq.payconiqtest.networking;

import com.mgalal.payconiq.payconiqtest.Utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fujitsu-lap on 25/08/2017.
 */

/**
 * Singleton class that  is responsible for generating the services.
 */

public class Network {

    private static Network mNetwork;
    private static Retrofit mRetrofit;

    //private methods.
    private Network() {
        mRetrofit = createRetrofit();
    }



    private OkHttpClient createOkHttpClient(){
        return new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .build();
    }

    //public methods.
    public static synchronized Network getInstance() {
        if (mNetwork == null) {
            mNetwork = new Network();
        }
        return mNetwork;
    }

    public <T> T createService(Class<T> service) {
        return mRetrofit.create(service);
    }
}
