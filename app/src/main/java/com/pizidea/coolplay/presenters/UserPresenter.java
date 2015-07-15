package com.pizidea.coolplay.presenters;

import com.google.common.base.Preconditions;
import com.pizidea.framework.BasePresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * <b>活动的presenter</b><br/>
 * Created by yflai on 2015/6/28.
 */
@Singleton
public class UserPresenter extends BasePresenter<UserPresenter.UserUi,UserPresenter.UserUiCallbacks>{

    public interface UserUi extends BasePresenter.Ui<UserUiCallbacks> {

        //void showError(NetworkError error);
        void showLoadingProgress(boolean visible);
        void showSecondaryLoadingProgress(boolean visible);
        String getRequestParameter();

    }

    public interface UserUiCallbacks {
        String getUiTitle();
        void setHeaderScrollValue(float alpha);
    }


    @Override
    protected UserUiCallbacks createUiCallbacks(UserUi ui) {
        return new UserUiCallbacks() {
            @Override
            public String getUiTitle() {
                return null;
            }

            @Override
            public void setHeaderScrollValue(float alpha) {
                //
            }

        };

    }


    @Inject
    public UserPresenter(/*StringFetcher stringFetcher*/) {
        //mStringFetcher = Preconditions.checkNotNull(stringFetcher, "stringFetcher cannot be null");
    }




}
