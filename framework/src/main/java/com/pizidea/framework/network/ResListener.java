package com.pizidea.framework.network;

/**
 * <b>Async Task Response Handler</b><br/>
 * Created by yflai on 2015/6/7.
 */
public interface ResListener<T> {
    public void onSuccess(T bean);
    public void onError(ResError error);
}
