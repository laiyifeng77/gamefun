package com.pizidea.framework.network;

import retrofit.RetrofitError;

/**
 * Created by Administrator on 2015/5/30.
 */
public interface NetworkCallRunnable<E> {

    public void onPreCall();

    public E doInBackground();

    public void onSuccess(E result);

    public void onError(RetrofitError re);

    public void onPostCall();

}
