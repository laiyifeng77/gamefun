package com.pizidea.framework.event;

/**
 * <b>事件通知的基类</b><br/>
 * Created by yflai on 2015/6/28.
 */
public class BaseEvent {
    public final int callingId;//这叫做final空白,必须在初始化对象的时候赋初值
    public BaseEvent(int callingId){
        this.callingId = callingId;
    }

}
