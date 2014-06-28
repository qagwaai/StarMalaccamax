package com.qagwaai.starmalaccamax.client.core;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class SimpleAsyncCallbackWatcherImpl implements AsyncCallbackWatcher {

    @SuppressWarnings("rawtypes")
    private AsyncCallback callback;
    private int unique;
    private long startMillis;

    @Override
    public void timeout() {
        if (callback != null) {
            callback.onFailure(new RemoteProcedureCallTimeout());
        }
    }

    @Override
    public void setCallback(@SuppressWarnings("rawtypes") final AsyncCallback callback) {
        this.callback = callback;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public AsyncCallback getCallback() {
        return this.callback;
    }

    @Override
    public int getUnique() {
        return unique;
    }

    @Override
    public void setUnique(final int unique) {
        this.unique = unique;
    }

    @Override
    public long getStartMillis() {
        return startMillis;
    }

    @Override
    public void setStartMillis(final long millis) {
        this.startMillis = millis;
    }

}
