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

package com.pizidea.coolplay.presenters;

import com.google.common.base.Preconditions;
import com.pizidea.framework.BasePresenter;
import com.pizidea.framework.Display;
import com.pizidea.framework.utils.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class MainPresenter extends BasePresenter<MainPresenter.MainPresenterUi,MainPresenter.MainControllerUiCallbacks> {

    private static final String LOG_TAG = MainPresenter.class.getSimpleName();

    public enum SideMenuItem {
        DISCOVER, TRENDING, LIBRARY, WATCHLIST, SEARCH;
    }

    public interface MainPresenterUi extends BasePresenter.Ui<MainControllerUiCallbacks> {
    }


    public interface SideMenuUi extends MainPresenterUi {
        void setSideMenuItems(SideMenuItem[] items, SideMenuItem selected);

        //void showUserProfile(PhilmUserProfile profile);

        void showAddAccountButton();

        //void showMovieCheckin(WatchingMovie movie);

        void hideMovieCheckin();
    }

    public interface MainUi extends MainPresenterUi {
        void showLoginPrompt();

    }


    public interface MainControllerUiCallbacks {
        void onSideMenuItemSelected(SideMenuItem item);

        void addAccountRequested();

        void showMovieCheckin();

        void setShownLoginPrompt();

    }


    public interface HostCallbacks {
        void finish();

        void setAccountAuthenticatorResult(String username, String authToken, String accountType);
    }

    private final UserPresenter mUserPresenter;

    private final GamePresenter mGamePresenter;

    private final AboutPresenter mAboutPresenter;

    //private final AsyncDatabaseHelper mDbHelper;
    //private final ApplicationState mState;
    //private final PhilmPreferences mPreferences;
    private final Logger mLogger;

    private HostCallbacks mHostCallbacks;

    @Inject
    public MainPresenter(
            //ApplicationState state,
            UserPresenter userController,
            GamePresenter movieController,
            AboutPresenter aboutController,
            //AsyncDatabaseHelper dbHelper,
            //PhilmPreferences preferences,
            Logger logger) {
        super();

        //mState = Preconditions.checkNotNull(state, "state cannot be null");

        mUserPresenter = Preconditions.checkNotNull(userController,
                "userController cannot be null");
        mGamePresenter = Preconditions.checkNotNull(movieController,
                "movieController cannot be null");
        mAboutPresenter = Preconditions.checkNotNull(aboutController,
                "aboutController cannot be null");
        //mDbHelper = Preconditions.checkNotNull(dbHelper, "dbHelper cannot be null");
        //mPreferences = Preconditions.checkNotNull(preferences, "preferences cannot be null");
        mLogger = Preconditions.checkNotNull(logger, "logger cannot be null");

        /*mUserPresenter.setControllerCallbacks(new UserController.ControllerCallbacks() {
            @Override
            public void onAddAccountCompleted(String username, String authToken,
                                              String accountType) {
                if (mHostCallbacks != null) {
                    mHostCallbacks.setAccountAuthenticatorResult(username, authToken, accountType);
                    mHostCallbacks.finish();
                }
            }
        });*/



    }

   /* @Subscribe
    public void onUserProfileChanged(UserState.UserProfileChangedEvent event) {
        populateUis();
    }

    @Subscribe
    public void onAccountChanged(UserState.AccountChangedEvent event) {
        populateUis();
    }

    @Subscribe
    public void onMovieWatchingChanged(MoviesState.WatchingMovieUpdatedEvent event) {
        populateUis();
    }*/



   /* @Override
    public boolean handleIntent(Intent intent) {
        mLogger.d(LOG_TAG, "handleIntent: " + intent);

        return mUserPresenter.handleIntent(intent)
                || mGamePresenter.handleIntent(intent)
                || mAboutPresenter.handleIntent(intent);
    }*/

    @Override
    protected void onInited() {
        super.onInited();
        //mState.registerForEvents(this);

        mUserPresenter.init();
        mGamePresenter.init();
        mAboutPresenter.init();
    }

    @Override
    protected void populateUi(MainPresenterUi ui) {
        if (ui instanceof SideMenuUi) {
            populateUi((SideMenuUi) ui);
        } else if (ui instanceof MainUi) {
            populateUi((MainUi) ui);
        }
    }

    private void populateUi(SideMenuUi ui) {
        /*ui.setSideMenuItems(getEnabledSideMenuItems(), mState.getSelectedSideMenuItem());

        PhilmUserProfile profile = mState.getUserProfile();
        if (profile != null) {
            ui.showUserProfile(profile);
        } else {
            ui.showAddAccountButton();
        }

        WatchingMovie checkin = mState.getWatchingMovie();
        if (checkin != null) {
            ui.showMovieCheckin(checkin);
        } else {
            ui.hideMovieCheckin();
        }*/


    }

    private void populateUi(MainUi ui) {
        /*if (mState.getCurrentAccount() == null && !mPreferences.hasShownTraktLoginPrompt()) {
            ui.showLoginPrompt();
        }*/
    }

    @Override
    protected MainControllerUiCallbacks createUiCallbacks(final MainPresenterUi ui) {
        return new MainControllerUiCallbacks() {
            @Override
            public void onSideMenuItemSelected(SideMenuItem item) {
                Display display = getDisplay();
                if (display != null) {
                    showUiItem(display, item);
                    display.closeDrawerLayout();
                }
            }

            @Override
            public void addAccountRequested() {
                Display display = getDisplay();
                if (display != null) {
                    display.startAddAccountActivity();
                    display.closeDrawerLayout();
                }
            }

            @Override
            public void showMovieCheckin() {
                Display display = getDisplay();
                //WatchingMovie checkin = mState.getWatchingMovie();

               /* if (display != null && checkin != null) {
                    display.closeDrawerLayout();
                    display.startMovieDetailActivity(checkin.movie.getImdbId(), null);
                }*/


            }

            @Override
            public void setShownLoginPrompt() {
                //mPreferences.setShownTraktLoginPrompt();
            }


        };
    }

    public void setSelectedSideMenuItem(SideMenuItem item) {
        //mState.setSelectedSideMenuItem(item);
        populateUis();
    }


    private void showUiItem(Display display, SideMenuItem item) {
        Preconditions.checkNotNull(display, "display cannot be null");
        Preconditions.checkNotNull(item, "item cannot be null");

        mLogger.d(LOG_TAG, "showUiItem: " + item.name());

        switch (item) {
            case DISCOVER:
                display.showDiscover();
                break;
            case TRENDING:
                display.showTrending();
                break;
            case LIBRARY:
                display.showLibrary();
                break;
            case WATCHLIST:
                display.showWatchlist();
                break;
            case SEARCH:
                display.showSearchFragment();
                break;
        }

        setSelectedSideMenuItem(item);
    }

    @Override
    protected void onSuspended() {
        mAboutPresenter.suspend();
        mUserPresenter.suspend();
        mGamePresenter.suspend();

        //mDbHelper.close();
        //mState.unregisterForEvents(this);

        super.onSuspended();
    }

    public void attachDisplay(Display display) {
        Preconditions.checkNotNull(display, "display is null");
        Preconditions.checkState(getDisplay() == null, "we currently have a display");
        //setDisplay(display);
    }

    public void detachDisplay(Display display) {
        Preconditions.checkNotNull(display, "display is null");
        Preconditions.checkState(getDisplay() == display, "display is not attached");
        //setDisplay(null);
    }

   /* @Override
    protected void setDisplay(Display display) {
        super.setDisplay(display);
        *//*mGamePresenter.setDisplay(display);
        mUserPresenter.setDisplay(display);
        mAboutPresenter.setDisplay(display);*//*
    }*/

    public boolean onAboutButtonPressed() {
        Display display = getDisplay();
        if (display != null) {
            display.startAboutActivity();
        }
        return true;
    }

    public boolean onSettingsButtonPressed() {
        Display display = getDisplay();
        if (display != null) {
            display.showSettings();
        }
        return true;
    }

    public boolean onHomeButtonPressed() {
        Display display = getDisplay();
        if (display != null && (display.toggleDrawer() || display.popEntireFragmentBackStack())) {
            return true;
        }
        return false;
    }

    private SideMenuItem[] getEnabledSideMenuItems() {
        return new SideMenuItem[]{
                SideMenuItem.DISCOVER,
                SideMenuItem.LIBRARY,
                SideMenuItem.WATCHLIST,
                SideMenuItem.SEARCH};
    }

    public void setHostCallbacks(HostCallbacks hostCallbacks) {
        mHostCallbacks = hostCallbacks;
    }

    public final GamePresenter getMovieController() {
        return mGamePresenter;
    }

    public final UserPresenter getUserController() {
        return mUserPresenter;
    }

    public final AboutPresenter getAboutController() {
        return mAboutPresenter;
    }


}
