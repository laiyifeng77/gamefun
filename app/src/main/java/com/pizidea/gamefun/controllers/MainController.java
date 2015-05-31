package com.pizidea.gamefun.controllers;

import android.util.Log;

import com.google.common.base.Preconditions;
import com.pizidea.framework.beans.WeatherBean;
import com.pizidea.framework.network.BackgroundExecutor;
import com.pizidea.framework.network.NetworkCallRunnable;
import com.pizidea.framework.qualifiers.GeneralPurpose;
import com.pizidea.framework.services.ApiClient;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit.RetrofitError;

/**
 * desc your class
 * Created by yflai on 2015/5/31.
 */
@Singleton
public class MainController {
    private static final String TAG = MainController.class.getSimpleName();

    private final BackgroundExecutor mExecutor;


    @Inject
    public MainController(@GeneralPurpose BackgroundExecutor executor){
        mExecutor = Preconditions.checkNotNull(executor, "executor cannot be null");
    }

    private <E> void execTask(NetworkCallRunnable<E> task){
        //注入这个task
        mExecutor.execute(task);

    }

    public void doTask(){
        execTask(new NetworkCallRunnable<WeatherBean>() {
            @Override
            public void onPreCall() {
                Log.i(TAG,"-----onPreCall");
            }

            @Override
            public WeatherBean doInBackground() {
                Log.i(TAG,"-----doInBackground");
                ApiClient client = new ApiClient();
                WeatherBean result = client.weatherService().fetchWeather();
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
