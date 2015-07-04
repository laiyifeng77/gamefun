package com.pizidea.framework.consts;

/**
 * Created by yflai on 2015/5/30.
 */
public class AppConfig {

    public static final boolean DEBUG = true;

    public static final boolean DEBUG_NETWORK = true;

    public static final String URL_HOST = "http://www.weather.com.cn";

    public static final int CONNECT_TIMEOUT_MILLIS = 15 * 1000;//15s

    public static final int READ_TIMEOUT_MILLIS = 20 * 1000;//20s

    private static final long DAY_IN_MILLIS = 24 * 60 * 60 * 1000;

    public static final long STALE_USER_PROFILE_THRESHOLD = 3 * DAY_IN_MILLIS;//用户状态保存三天过期
    public static final long STALE_GAME_DETAIL_THRESHOLD = 2 * DAY_IN_MILLIS;//活动状态保存两天


}
