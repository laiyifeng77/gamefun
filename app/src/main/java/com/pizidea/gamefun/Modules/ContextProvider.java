package com.pizidea.gamefun.Modules;

import android.accounts.AccountManager;
import android.app.AlarmManager;
import android.content.Context;
import android.content.res.AssetManager;

import com.google.common.base.Preconditions;
import com.pizidea.gamefun.qualifiers.ApplicationContext;
import com.pizidea.gamefun.qualifiers.FileDirectory;

import java.io.File;

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
public class ContextProvider {
    private final Context mApplicationContext;

    public ContextProvider(Context context) {
        mApplicationContext = Preconditions.checkNotNull(context, "context cannot be null");
    }

    @Provides
    @ApplicationContext
    public Context provideApplicationContext() {
        return mApplicationContext;
    }

    @Provides @Singleton
    public AccountManager provideAccountManager() {
        return AccountManager.get(mApplicationContext);
    }

    @Provides @FileDirectory
    public File providePrivateFileDirectory() {  return mApplicationContext.getFilesDir(); }

    @Provides @Singleton
    public AssetManager provideAssetManager() {
        return mApplicationContext.getAssets();
    }

    @Provides @Singleton
    public AlarmManager provideAlarmManager() {
        return (AlarmManager) mApplicationContext.getSystemService(Context.ALARM_SERVICE);
    }

}
