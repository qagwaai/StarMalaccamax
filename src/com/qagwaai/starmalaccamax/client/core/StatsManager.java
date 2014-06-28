package com.qagwaai.starmalaccamax.client.core;

import java.util.HashMap;

public final class StatsManager {
    private HashMap<String, AsyncCallbackStat> callbackStats = new HashMap<String, AsyncCallbackStat>();

    private StatsManager() {
    }

    private static class SingletonHolder {
        private static final StatsManager INSTANCE = new StatsManager();
    }

    public static StatsManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void addAsyncCallbackStat(String callback, boolean wasSuccessful, int duration) {
        if (callbackStats.containsKey(callback)) {
            AsyncCallbackStat callbackStat = callbackStats.get(callback);
            callbackStat.setTotalDuration(callbackStat.getTotalDuration() + duration);
            if (wasSuccessful) {
                callbackStat.setNumSuccessful(callbackStat.getNumSuccessful() + 1);
            } else {
                callbackStat.setNumFailed(callbackStat.getNumFailed() + 1);
            }
            if ((callbackStat.getMinDuration() > 0) && (duration < callbackStat.getMinDuration())) {
                callbackStat.setMinDuration(duration);
            }
            if (duration > callbackStat.getMaxDuration()) {
                callbackStat.setMaxDuration(duration);
            }
        } else {
            if (wasSuccessful) {
                callbackStats.put(callback, new AsyncCallbackStat(1, 0, duration, duration, duration));
            } else {
                callbackStats.put(callback, new AsyncCallbackStat(0, 1, duration, duration, duration));
            }
        }
    }

    public String getCallbackStatsReport() {
        StringBuffer buf = new StringBuffer();
        buf.append("Callback stats:<br>");
        buf.append("<table style=\"font-family:arial;font-size:10px;\"><tr><th>Callback Name</th><th>Total calls</th>");
        buf.append("<th>Total successful</th><th>Total failed</th><th>Average Time</th><th>Max Time</th><th>Min Time</th></tr>");
        int totalNumFailed = 0;
        int totalNumSucceeded = 0;
        int totalDuration = 0;
        int minDuration = 10000;
        int maxDuration = 0;
        for (String key : callbackStats.keySet()) {
            AsyncCallbackStat stat = callbackStats.get(key);
            totalNumFailed += stat.getNumFailed();
            totalNumSucceeded += stat.getNumSuccessful();
            totalDuration += stat.getTotalDuration();
            if (stat.getMinDuration() < minDuration) {
                minDuration = stat.getMinDuration();
            }
            if (stat.getMaxDuration() > maxDuration) {
                maxDuration = stat.getMaxDuration();
            }
            buf.append("<tr><td>" + key + "</td>");
            buf.append("<td>" + (stat.getNumFailed() + stat.getNumSuccessful()) + "</td>");
            buf.append("<td>" + stat.getNumSuccessful() + "</td>");
            buf.append("<td>" + stat.getNumFailed() + "</td>");
            buf.append("<td>" + stat.getTotalDuration() / (stat.getNumFailed() + stat.getNumSuccessful()) + "</td>");
            buf.append("<td>" + stat.getMaxDuration() + "</td>");
            buf.append("<td>" + stat.getMinDuration() + "</td></tr>");
        }
        buf.append("<tr><td>Summary</td><td>" + (totalNumFailed + totalNumSucceeded) + "</td><td>" + totalNumSucceeded
            + "</td><td>" + totalNumFailed + "</td><td>" + (totalDuration / (totalNumFailed + totalNumSucceeded))
            + "</td><td>" + maxDuration + "</td><td>" + minDuration + "</td></tr>");
        buf.append("</table></div>");
        return buf.toString();
    }
}
