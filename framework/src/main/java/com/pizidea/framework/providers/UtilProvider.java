package com.pizidea.framework.providers;

import com.pizidea.framework.network.BackgroundExecutor;
import com.pizidea.framework.network.impl.BackgroundExecutorImpl;
import com.pizidea.framework.qualifiers.ForDatabase;
import com.pizidea.framework.qualifiers.GeneralPurpose;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
    @GeneralPurpose
    public BackgroundExecutor provideMultiThreadExecutor(){
        final int numberCores = Runtime.getRuntime().availableProcessors();
        return new BackgroundExecutorImpl(Executors.newFixedThreadPool(numberCores * 2 + 1));
    }


    @Provides
    @Singleton
    @ForDatabase
    public BackgroundExecutor provideDatabaseThreadExecutor() {
        return new BackgroundExecutorImpl(Executors.newSingleThreadExecutor());
    }

}
