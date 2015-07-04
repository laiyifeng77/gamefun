package com.pizidea.framework;

import android.support.v7.internal.view.menu.BaseMenuPresenter;

import com.google.common.base.Preconditions;
import com.pizidea.framework.consts.AppConfig;
import com.pizidea.framework.state.BaseState;
import com.pizidea.framework.utils.Logger;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.inject.Inject;

/**
 * <b>desc your class</b><br/>
 * Created by yflai on 2015/7/4.
 */
abstract class BasePresenter<U extends BasePresenter.Ui<UC>,UC>{

    public interface Ui<UC>{

        void setCallbacks(UC callbacks);
        boolean isModal();

    }

    private Display mDisplay;
    private boolean mInited;
    private final Set<U> mUis;
    private final Set<U> mUnmodifiableUis;

    @Inject Logger mLogger;

    public BasePresenter(){
        mUis = new CopyOnWriteArraySet<>();
        mUnmodifiableUis = Collections.unmodifiableSet(mUis);
    }

    public final void init() {
        Preconditions.checkState(mInited == false, "Already inited");
        mInited = true;
        onInited();
    }

    public final void suspend() {
        Preconditions.checkState(mInited == true, "Not inited");
        onSuspended();
        mInited = false;
    }

    protected void onInited() {
        if (!mUis.isEmpty()) {
            for (U ui : mUis) {
                onUiAttached(ui);
                populateUi(ui);
            }
        }
    }

    public final boolean isInited() {
        return mInited;
    }

    protected synchronized final void populateUis() {
        if (AppConfig.DEBUG) {
            //mLogger.d(getClass().getSimpleName(), "populateUis");
        }
        for (U ui : mUis) {
            populateUi(ui);
        }
    }


    protected final Set<U> getUis() {
        return mUnmodifiableUis;
    }

    protected final Display getDisplay() {
        return mDisplay;
    }

    protected int getId(U ui) {
        return ui.hashCode();
    }

    protected synchronized U findUi(final int id) {
        for (U ui : mUis) {
            if (getId(ui) == id) {
                return ui;
            }
        }
        return null;
    }


    protected final void populateUiFromEvent(BaseState.UiCausedEvent event) {
        Preconditions.checkNotNull(event, "event cannot be null");

        final U ui = findUi(event.callingId);
        if (ui != null) {
            populateUi(ui);
        }
    }

    public synchronized final void attachUi(U ui) {
        Preconditions.checkArgument(ui != null, "ui cannot be null");
        Preconditions.checkState(!mUis.contains(ui), "UI is already attached");

        mUis.add(ui);

        ui.setCallbacks(createUiCallbacks(ui));

        if (isInited()) {
            if (!ui.isModal() /*&& !(ui instanceof SubUi)*/ ) {
                updateDisplayTitle(getUiTitle(ui));
            }

            onUiAttached(ui);
            populateUi(ui);
        }
    }

    protected final void updateDisplayTitle(String title) {
        Display display = getDisplay();
        if (display != null) {
            display.setActionBarTitle(title);
        }
    }

    protected final void updateDisplayTitle(U ui) {
        updateDisplayTitle(getUiTitle(ui));
    }

    protected String getUiTitle(U ui) {
        return null;
    }

    protected void onSuspended() {}

    protected void onUiAttached(U ui) { }

    protected void onUiDetached(U ui) { }

    protected void populateUi(U ui) { }

    protected abstract UC createUiCallbacks(U ui);


}
