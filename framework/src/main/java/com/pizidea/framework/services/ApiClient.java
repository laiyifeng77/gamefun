package com.pizidea.framework.services;

import com.pizidea.framework.consts.AppConfig;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * desc your class
 * Created by yflai on 2015/5/31.
 */
public class ApiClient {

    public static final String API_URL = "https://api.trakt.tv";
    public static final String API_KEY = "apikey";

    private String mApiKey;
    private boolean isDebug;
    private String mPasswordSha1;

    private String userAccount;
    private String userPassSha1;

    private RestAdapter restAdapter;

    public ApiClient(){
        //do something

    }

    protected RestAdapter.Builder newRestAdapterBuilder(){
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(AppConfig.URL_HOST);//设置远程地址
        //builder.setConverter(new GsonConverter(GsonUtils.newInstance()));
        //builder.setClient(new OkClient(OkHttpUtils.getInstance(context)));
        builder.setLogLevel(
                AppConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE);

        return builder;
    }

    protected RestAdapter getRestAdapter(){
        return restAdapter;
    }

    public UserService userService(){
        return newRestAdapterBuilder().build().create(UserService.class);
    }


    public WeatherService weatherService(){
        return newRestAdapterBuilder().build().create(WeatherService.class);

    }





    public void login(String userName,String password){
        //
    }



}
