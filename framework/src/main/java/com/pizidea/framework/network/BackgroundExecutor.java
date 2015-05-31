package com.pizidea.framework.network;

/**
 * desc your class
 * Created by yflai on 2015/5/31.
 */
public interface BackgroundExecutor {

    public <E> void execute(NetworkCallRunnable<E> runnable);

    public <E> void execute(BackgroundCallRunnable<E> runnable);

}
