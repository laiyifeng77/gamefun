package com.pizidea.coolplay;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.pizidea.coolplay.presenters.MainPresenter;
import com.pizidea.framework.utils.Injector;
import com.pizidea.coolplay.Modules.ApplicationModule;
import com.pizidea.coolplay.Modules.ContextProvider;
import com.pizidea.coolplay.constants.AppConfig;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * desc your class
 * Created by yflai on 2015/5/31.
 */
public class PizApplication extends Application implements Injector{

    public static PizApplication from(Context context) {
        return (PizApplication) context.getApplicationContext();
    }

    @Inject
    MainPresenter mMainPresenter;

    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        if (AppConfig.STRICT_MODE) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDialog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyDeath()
                    .penaltyLog()
                    .build());
        }

        mObjectGraph = ObjectGraph.create(
                new ContextProvider(this),
                new ApplicationModule()
                //new ViewUtilProvider(),
                //new TaskProvider(),
                //new InjectorModule(this),
                //new ReceiverProvider()
        );

        mObjectGraph.inject(this);
    }

    public MainPresenter getMainController() {
        return mMainPresenter;
    }

    public ObjectGraph getObjectGraph() {
        return mObjectGraph;
    }

    @Override
    public void inject(Object object) {
        mObjectGraph.inject(object);
    }
}
