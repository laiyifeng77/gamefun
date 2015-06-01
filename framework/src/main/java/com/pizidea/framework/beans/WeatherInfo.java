package com.pizidea.framework.beans;

import java.io.Serializable;

/**
 * desc your class
 * Created by yflai on 2015/5/31.
 */
public class WeatherInfo implements Serializable{
    private WeatherBean weatherinfo;

    public WeatherBean getWeatherinfo() {
        return weatherinfo;
    }

    public void setWeatherinfo(WeatherBean weatherinfo) {
        this.weatherinfo = weatherinfo;
    }
}
