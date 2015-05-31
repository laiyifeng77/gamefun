package com.pizidea.gamefun;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.pizidea.framework.injector.Injector;
import com.pizidea.gamefun.Modules.ApplicationModule;
import com.pizidea.gamefun.Modules.ContextProvider;
import com.pizidea.gamefun.constants.AppConfig;
import com.pizidea.gamefun.controllers.MainController;

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
    MainController mMainController;

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
                //new ContextProvider(this),
                new ApplicationModule()
                //new ViewUtilProvider(),
                //new TaskProvider(),
                //new InjectorModule(this),
                //new ReceiverProvider()
        );

        mObjectGraph.inject(this);
    }

    public MainController getMainController() {
        return mMainController;
    }

    public ObjectGraph getObjectGraph() {
        return mObjectGraph;
    }

    @Override
    public void inject(Object object) {
        mObjectGraph.inject(object);
    }
}
