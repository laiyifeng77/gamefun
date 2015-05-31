package com.pizidea.framework.beans;

import java.io.Serializable;

/**
 * desc your class
 * Created by yflai on 2015/5/31.
 */
public class WeatherBean implements Serializable{
    private String city;
    private String cityid;
    private String temp;
    private String WD;
    private String WS;
    private String time;
    private String isRadar;
    private String qy;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWD() {
        return WD;
    }

    public void setWD(String WD) {
        this.WD = WD;
    }

    public String getWS() {
        return WS;
    }

    public void setWS(String WS) {
        this.WS = WS;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIsRadar() {
        return isRadar;
    }

    public void setIsRadar(String isRadar) {
        this.isRadar = isRadar;
    }

    public String getQy() {
        return qy;
    }

    public void setQy(String qy) {
        this.qy = qy;
    }
}
