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
public class AboutPresenter extends BasePresenter<AboutPresenter.MovieUi,AboutPresenter.MovieUiCallbacks>{

    public interface MovieUi extends BasePresenter.Ui<MovieUiCallbacks> {

        //void showError(NetworkError error);
        void showLoadingProgress(boolean visible);
        void showSecondaryLoadingProgress(boolean visible);
        String getRequestParameter();

    }

    public interface MovieUiCallbacks {
        String getUiTitle();
        void setHeaderScrollValue(float alpha);
    }


    @Override
    protected MovieUiCallbacks createUiCallbacks(MovieUi ui) {
        return new MovieUiCallbacks() {
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
    public AboutPresenter() {
        //mStringFetcher = Preconditions.checkNotNull(stringFetcher, "stringFetcher cannot be null");
    }




}
