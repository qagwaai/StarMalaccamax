package com.qagwaai.starmalaccamax.client.core;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.smartgwt.client.util.SC;

public final class TimeoutManager extends Timer {
    private int _callbackTimeout = 30000;
    public static final int TIMEOUT_MANAGER_CHECK = 1000;
    private HashMap<Integer, AsyncCallbackWatcher> activeCalls = new HashMap<Integer, AsyncCallbackWatcher>();
    private static TimeoutManager _instance;
    private int unique = 0;

    private TimeoutManager() {
    }

    public static synchronized TimeoutManager getInstance() {
        if (_instance == null) {
            _instance = new TimeoutManager();
        }
        return _instance;
    }

    public void addActiveCall(AsyncCallbackWatcher watcher) {
        if (watcher != null) {
            watcher.setStartMillis(System.currentTimeMillis());
            watcher.setUnique(unique++);
            activeCalls.put(Integer.valueOf(watcher.getUnique()), watcher);
            if (unique > Integer.MAX_VALUE - 5) {
                // reset
                unique = 0;
            }
        }
    }

    public void completed(AsyncCallbackWatcher watcher) {
        if (watcher != null) {
            activeCalls.remove(watcher.getUnique());
        }
    }

    @Override
    public void run() {
        // GWT.log("Timeout Manager checking " + activeCalls.size() +
        // " active calls");
        Iterator<AsyncCallbackWatcher> iterator = activeCalls.values().iterator();
        while (iterator.hasNext()) {
            AsyncCallbackWatcher watcher = null;
            try {
                watcher = iterator.next();
            } catch (ConcurrentModificationException e) {
                // the watcher is in use
                // ignore
            }
            if (watcher != null) {
                if (System.currentTimeMillis() - watcher.getStartMillis() > _callbackTimeout) {
                    // we have been waiting too long
                    GWT.log("Timeout of call using " + ((BaseAsyncCallback) watcher.getCallback()).getAsyncName());
                    SC.say(watcher.getCallback().getClass().getName() + " is taking longer than "
                        + (_callbackTimeout / 1000) + " seconds.  Please wait...");
                    // watcher.timeout();
                    // activeCalls.remove(watcher.getUnique());
                    watcher = null;
                }
            }
        }
    }

    public void initializeManager(int callbackTimeout, int monitorCheck) {
        if (callbackTimeout > 0) {
            _callbackTimeout = callbackTimeout;
        }
        if (monitorCheck > 0) {
            this.scheduleRepeating(monitorCheck);
        } else {
            this.scheduleRepeating(TIMEOUT_MANAGER_CHECK);
        }
        GWT.log("TimeoutManager initialized with timeout of " + _callbackTimeout + "ms, checking every " + monitorCheck
            + "ms");

    }
}