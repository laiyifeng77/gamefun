package com.pizidea.coolplay.Modules;

import com.pizidea.framework.providers.UtilProvider;
import com.pizidea.coolplay.PizApplication;

import dagger.Module;

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