package com.pizidea.framework.state;

import com.google.common.base.Preconditions;
import com.pizidea.framework.beans.Account;

/**
 * Created by Administrator on 2015/5/30.
 */
public interface BaseState {

    public Account getCurrentAccount();

    public void registerForEvents(Object receiver);

    public void unregisterForEvents(Object receiver);


    public static class UiCausedEvent {
        public final int callingId;

        public UiCausedEvent(int callingId) {
            this.callingId = callingId;
        }
    }

    public static class BaseArgumentEvent<T> extends UiCausedEvent {
        public final T item;

        public BaseArgumentEvent(int callingId, T item) {
            super(callingId);
            this.item = Preconditions.checkNotNull(item, "item cannot be null");
        }
    }


    public static class ShowLoadingEvent {
        public final int callingId;
        public final boolean show;

        public ShowLoadingEvent(int callingId, boolean show) {
            this.callingId = callingId;
            this.show = show;
        }
    }



}
