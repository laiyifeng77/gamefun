/*
 * Copyright 2014 Chris Banes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pizidea.framework;

import android.os.Bundle;

//import app.philm.in.model.ColorScheme;

/**
 * 整个程序的UI功能操作列表
 */
public interface Display {

    public static final String FRAGMENT_TAG_COOL_PLAY = "cool_play";
    public static final String FRAGMENT_TAG_QUAN = "quan_quan";
    public static final String FRAGMENT_TAG_TOOLS = "tools";

    public void showSplash();//显示程序启动页

    public void showIndex();//显示主页面

    public void showCoolPlay();//酷玩
    public void showQuanQuan();//圈圈
    public void showTools();//工具

    public void go2Pan();//我的酷盘

    public void go2MyDirection();//我的风向标

    public void go2CoolShop();//打开酷玩商城

    public void showLogin();//打开登录页

    public void startGameShowActivity(String gameId, Bundle bundle);

    //public void showMovieDetailFragment(String gameId);

   // public void startMovieImagesActivity(String movieId);

    //public void showMovieImagesFragment(String movieId);

    //public void showSearchFragment();

    //public void showSearchMoviesFragment();

    //public void showSearchPeopleFragment();


    //public void showRateMovieFragment(String movieId);

    public void closeDrawerLayout();//关闭drawer

    //public boolean hasMainFragment();

    //public void startAddAccountActivity();

    public void startAboutActivity();

    public void startSettingsActivity();

    public void setActionBarTitle(CharSequence title);

    public void setActionBarSubtitle(CharSequence title);

    public boolean popEntireFragmentBackStack();

    public void showUpNavigation(boolean show);

    public void finishActivity();

    //public void showRelatedMovies(String movieId);

    //public void showCastList(String movieId);

    //public void showCrewList(String movieId);

    //public void showCheckin(String movieId);

    //public void showCancelCheckin();

    public void startPersonDetailActivity(String id, Bundle bundle);

    public void showPersonDetail(String id);

    //public void showPersonCastCredits(String id);

    //public void showPersonCrewCredits(String id);

    //public void showCredentialsChanged();

    //public void playYoutubeVideo(String id);

    //public void setColorScheme(ColorScheme colorScheme);

    public boolean toggleDrawer();

    public void setStatusBarColor(float scroll);

    public void setSupportActionBar(Object toolbar, boolean handleBackground);

}
