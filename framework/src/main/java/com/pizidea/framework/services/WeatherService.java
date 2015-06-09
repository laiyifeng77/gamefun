package com.pizidea.framework.services;

import com.pizidea.framework.beans.UserBean;
import com.pizidea.framework.beans.WeatherBean;
import com.pizidea.framework.beans.WeatherInfo;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * <b>用户信息接口</b><br/>
 * Created by yflai on 2015/5/31.
 */
public interface WeatherService {

    //http://www.weather.com.cn/adat/sk/101010100.html

    @GET("/adat/sk/101010100.html")
    WeatherInfo fetchWeather();

    @POST("/user/{uid}")
    boolean modifyUserInfo(@Path("uid") int userId);


    @GET("/adat/sk/101010100.html")
    void getWeather(Callback<WeatherInfo> callback);


}
