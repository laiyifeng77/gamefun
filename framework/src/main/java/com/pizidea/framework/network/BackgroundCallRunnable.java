package com.pizidea.framework.network;

/**
 * Created by Administrator on 2015/5/30.
 */
public interface BackgroundCallRunnable<E> {

    public void onPreCall();

    public E runAsync();

    public void onPostCall(E result);

}
