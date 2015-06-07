package com.pizidea.framework.providers;

import com.pizidea.framework.network.AsyncExecutor;
import com.pizidea.framework.network.impl.AsyncExecutorImpl;
import com.pizidea.framework.qualifiers.ForDatabase;
import com.pizidea.framework.qualifiers.GeneralPurpose;
import com.pizidea.framework.utils.Logger;
import com.pizidea.framework.utils.impl.AndroidLogger;
import com.squareup.otto.Bus;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.android.AndroidLog;

/**
 * desc your class
 * Created by yflai on 2015/5/31.
 */
@Module(
    library = true
)
public class UtilProvider {

    @Provides
    @Singleton
    public Bus provideEventBus(){return new Bus(); }

    @Provides
    @Singleton
    public Logger provideLogger(){return new AndroidLogger(); }


    @Provides
    @Singleton
    @GeneralPurpose
    public AsyncExecutor provideMultiThreadExecutor(){
        final int numberCores = Runtime.getRuntime().availableProcessors();
        return new AsyncExecutorImpl(Executors.newFixedThreadPool(numberCores * 2 + 1));
    }


    @Provides
    @Singleton
    @ForDatabase
    public AsyncExecutor provideDatabaseThreadExecutor() {
        return new AsyncExecutorImpl(Executors.newSingleThreadExecutor());
    }

}
