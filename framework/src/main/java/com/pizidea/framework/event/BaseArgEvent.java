package com.pizidea.framework.event;

import com.google.common.base.Preconditions;

/**
 * <b>带有参数的通知事件</b><br/>
 * Created by yflai on 2015/6/28.
 */
public class BaseArgEvent<T> extends BaseEvent{
    public final T item;
    public BaseArgEvent(int callingId,T item){
        super(callingId);
        this.item = Preconditions.checkNotNull(item,"item cannot be null");
    }

}
