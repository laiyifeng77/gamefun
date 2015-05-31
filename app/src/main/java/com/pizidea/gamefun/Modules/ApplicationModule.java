package com.pizidea.gamefun.Modules;

import android.accounts.AccountManager;
import android.app.AlarmManager;
import android.content.Context;
import android.content.res.AssetManager;

import com.google.common.base.Preconditions;
import com.pizidea.framework.providers.UtilProvider;
import com.pizidea.gamefun.PizApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * desc your class
 * Created by yflai on 2015/5/31.
 */
@Module(
        injects = PizApplication.class,
        includes = {
                UtilProvider.class,
//                AccountsProvider.class,
//                NetworkProvider.class,
//                StateProvider.class,
//                PersistenceProvider.class,
//                InjectorModule.class
        }
)
public class ApplicationModule {
    //
}