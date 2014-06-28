package com.qagwaai.starmalaccamax.client.core;

/**
 * 
 * @author ehldxae
 *
 */
public class AsyncCallbackStat {
    private int numSuccessful;
    private int numFailed;
    private int totalDuration;
    private int minDuration;
    private int maxDuration;

    /**
     * 
     * @return the number of successful calls
     */
    public int getNumSuccessful() {
        return numSuccessful;
    }

    /**
     * 
     * @param numSuccessful the number of successful calls
     */
    public void setNumSuccessful(final int numSuccessful) {
        this.numSuccessful = numSuccessful;
    }

    /**
     * 
     * @return the number of failed calls
     */
    public int getNumFailed() {
        return numFailed;
    }

    /**
     * 
     * @param numFailed the number of failed calls
     */
    public void setNumFailed(final int numFailed) {
        this.numFailed = numFailed;
    }

    /**
     * 
     * @return the total duration of the calls
     */
    public int getTotalDuration() {
        return totalDuration;
    }

    /**
     * 
     * @param totalDuration the total duration of the calls
     */
    public void setTotalDuration(final int totalDuration) {
        this.totalDuration = totalDuration;
    }

    /**
     * 
     * @return the shortest duration of a call
     */
    public int getMinDuration() {
        return minDuration;
    }

    /**
     * 
     * @param minDuration the shortest duration of a call
     */
    public void setMinDuration(final int minDuration) {
        this.minDuration = minDuration;
    }

    /**
     * 
     * @return the longest duration of a call
     */
    public int getMaxDuration() {
        return maxDuration;
    }

    /**
     * 
     * @param maxDuration the longest duration of a call
     */
    public void setMaxDuration(final int maxDuration) {
        this.maxDuration = maxDuration;
    }

    @Override
    public String toString() {
        return "AsyncCallbackStat [numSuccessful=" + numSuccessful + ", numFailed=" + numFailed + ", totalDuration="
            + totalDuration + ", minDuration=" + minDuration + ", maxDuration=" + maxDuration + "]";
    }

    /**
     * Constructor
     * @param numSuccessful the number of successful calls
     * @param numFailed the number of failed calls
     * @param totalDuration the total duration of all calls
     * @param minDuration the shortest duration of all calls
     * @param maxDuration the longest duration of all calls
     */
    public AsyncCallbackStat(final int numSuccessful, final int numFailed, final int totalDuration, final int minDuration, final int maxDuration) {
        super();
        this.numSuccessful = numSuccessful;
        this.numFailed = numFailed;
        this.totalDuration = totalDuration;
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;
    }

    /**
     * 
     */
    public AsyncCallbackStat() {
        super();
    }

}
