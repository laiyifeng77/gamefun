package com.pizidea.coolplay.controllers;

import android.util.Log;

import com.google.common.base.Preconditions;
import com.pizidea.framework.beans.WeatherBean;
import com.pizidea.framework.beans.WeatherInfo;
import com.pizidea.framework.network.AsyncExecutor;
import com.pizidea.framework.network.NetworkCallable;
import com.pizidea.framework.qualifiers.GeneralPurpose;
import com.pizidea.framework.services.ApiClient;
import com.pizidea.framework.services.WeatherService;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * desc your class
 * Created by yflai on 2015/5/31.
 */
@Singleton
public class MainController {
    private static final String TAG = MainController.class.getSimpleName();

    private final AsyncExecutor mExecutor;


    @Inject
    public MainController(@GeneralPurpose AsyncExecutor executor){
        mExecutor = Preconditions.checkNotNull(executor, "executor cannot be null");
    }

    private <E> void execTask(NetworkCallable<E> task){
        //注入这个task
        mExecutor.execute(task);

    }


    public void doTaskAsync(){
        ApiClient client = ApiClient.getInstance();
        WeatherService service = client.weatherService();

        service.getWeather(new Callback<WeatherInfo>() {
            @Override
            public void success(WeatherInfo info, Response response) {
                Log.i(TAG,"-----weather.city=" + info.getWeatherinfo().getCity());
                Log.i(TAG,"-----result.body=" + response.getBody().toString()+"---"+response.getStatus());

            }

            @Override
            public void failure(RetrofitError retrofitError) {

                Log.i(TAG,"-----onError=" + retrofitError.getBody());
            }

        });

    }


    public void doTask(){
        execTask(new NetworkCallable<WeatherBean>() {
            @Override
            public void onPreCall() {
                Log.i(TAG,"-----onPreCall");
            }

            @Override
            public WeatherBean doInBackground() {
                Log.i(TAG,"-----doInBackground");
                ApiClient client = ApiClient.getInstance();
                WeatherBean result = client.weatherService().fetchWeather().getWeatherinfo();
                return result;
            }

            @Override
            public void onSuccess(WeatherBean result) {
                Log.i(TAG,"-----onSuccess");

                Log.i(TAG,"-----weather.city=" + result.getCity());

            }

            @Override
            public void onError(RetrofitError re) {
                Log.i(TAG,"-----onError=" + re.getBody());

            }

            @Override
            public void onPostCall() {
                Log.i(TAG,"-----onPostCall");

            }
        });
    }

}
