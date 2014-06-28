/**
 * Duration.java
 * com.qagwaai.starmalaccamax.shared.model
 * StarMalaccamax
 * Created Mar 14, 2011 at 2:55:25 PM
 */
package com.qagwaai.starmalaccamax.shared.model;

/**
 * @author pgirard
 * 
 */
public interface Duration {
    /**
     * 
     * @return the milliseconds of the duration
     */
    int getMilliseconds();

    /**
     * 
     * @param milliseconds
     *            the milliseconds of the duration
     */
    void setMilliseconds(int milliseconds);

    /**
     * 
     * @return the seconds of the duration
     */
    int getSeconds();

    /**
     * 
     * @param seconds
     *            the seconds of the duration
     */
    void setSeconds(int seconds);

    /**
     * 
     * @return the minutes of the duration
     */
    int getMinutes();

    /**
     * 
     * @param minutes
     *            the minutes of the duration
     */
    void setMinutes(int minutes);

    /**
     * 
     * @return the hours of the duration
     */
    int getHours();

    /**
     * 
     * @param hours
     *            the hours of the duration
     */
    void setHours(int hours);

    /**
     * 
     * @return the days of the duration
     */
    int getDays();

    /**
     * 
     * @param days
     *            the days of the duration
     */
    void setDays(int days);

    /**
     * 
     * @return the months of the duration
     */
    int getMonths();

    /**
     * 
     * @param months
     *            the months of the duration
     */
    void setMonths(int months);

    /**
     * 
     * @return the years of the duration
     */
    int getYears();

    /**
     * 
     * @param years
     *            the years of the duration
     */
    void setYears(int years);

    /**
     * 
     * @return the duration in minutes
     */
    int toMinutes();

    /**
     * 
     * @param minutes
     *            create a duration from the number of minutes
     */
    void fromMinutes(long minutes);

    /**
     * 
     * @return the duration as a human readable string
     */
    String toDebugString();
}
