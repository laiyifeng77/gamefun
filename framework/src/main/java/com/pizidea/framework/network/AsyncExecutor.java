package com.pizidea.framework.network;

/**
 * desc your class
 * Created by yflai on 2015/5/31.
 */
public interface AsyncExecutor {

    public <E> void execute(NetworkCallable<E> runnable);

    public <E> void execute(BackgroundCallable<E> runnable);

}
