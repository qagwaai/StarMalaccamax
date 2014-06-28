package com.qagwaai.starmalaccamax.client.service.helpers;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.qagwaai.starmalaccamax.client.core.AsyncCallbackWatcher;
import com.qagwaai.starmalaccamax.client.core.SimpleAsyncCallbackWatcherImpl;
import com.qagwaai.starmalaccamax.client.core.StatsManager;
import com.qagwaai.starmalaccamax.client.core.TimeoutManager;

/**
 * 
 * @author ehldxae
 *
 * @param <T> the type of the result
 */
public abstract class BaseAsyncCallback<T> implements AsyncCallback<T> {
    private AsyncCallbackWatcher watcher;
    private long callStart;

    /**
     * 
     * @param watcher the watcher associated with this call
     */
    public BaseAsyncCallback(final AsyncCallbackWatcher watcher) {
        super();
        watcher.setCallback(this);
        this.watcher = watcher;
        if (GWT.isClient()) {
            TimeoutManager.getInstance().addActiveCall(watcher);
        }
        startCall();
    }
    
    public String getAsyncName() { 
    	return getClass().getName();
    }

    /**
     * 
     */
    public BaseAsyncCallback() {
        this(new SimpleAsyncCallbackWatcherImpl());
    }

    /**
     * 
     */
    private void startCall() {
        callStart = System.currentTimeMillis();
        GWT.log("Starting call for " + getAsyncName());

    }

    @Override
    public void onFailure(final Throwable caught) {
        StatsManager.getInstance().addAsyncCallbackStat(getClass().getName(), false,
            (int) (System.currentTimeMillis() - callStart));
        GWT.log("failure of callback", caught);
        if (watcher != null) {
            if (GWT.isClient()) {
                TimeoutManager.getInstance().completed(watcher);
            }
        }
    }

    @Override
    public void onSuccess(final T result) {
        StatsManager.getInstance().addAsyncCallbackStat(getClass().getName(), true,
            (int) (System.currentTimeMillis() - callStart));
        GWT.log("Successful callback for (" + result.getClass().getName() + "): duration["
            + (System.currentTimeMillis() - callStart) + "ms]");
        if (watcher != null) {
            if (GWT.isClient()) {
                TimeoutManager.getInstance().completed(watcher);
            }
        }
    }

}
