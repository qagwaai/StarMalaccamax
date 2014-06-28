/**
 * DurationDTO.java
 * com.qagwaai.starmalaccamax.shared.model
 * StarMalaccamax
 * Created Mar 14, 2011 at 2:57:20 PM
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * 
 */
public final class DurationDTO implements Serializable, IsSerializable, Duration {
    /**
	 * 
	 */
    private int milliseconds;
    /**
	 * 
	 */
    private int seconds;
    /**
	 * 
	 */
    private int minutes;
    /**
	 * 
	 */
    private int hours;
    /**
	 * 
	 */
    private int days;
    /**
	 * 
	 */
    private int months;
    /**
	 * 
	 */
    private int years;

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMilliseconds() {
        return milliseconds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMilliseconds(final int milliseconds) {
        this.milliseconds = milliseconds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSeconds() {
        return seconds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSeconds(final int seconds) {
        this.seconds = seconds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMinutes() {
        return minutes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMinutes(final int minutes) {
        this.minutes = minutes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHours() {
        return hours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHours(final int hours) {
        this.hours = hours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDays() {
        return days;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDays(final int days) {
        this.days = days;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMonths() {
        return months;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMonths(final int months) {
        this.months = months;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getYears() {
        return years;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setYears(final int years) {
        this.years = years;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (years > 0) {
            if (years > 1) {
                builder.append(years + " years");
            } else {
                builder.append("1 year");
            }
        }
        if (months > 0) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            if (months > 1) {
                builder.append(months + " months");
            } else {
                builder.append("1 month");
            }
        }
        if (days > 0) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            if (days > 1) {
                builder.append(days + " days");
            } else {
                builder.append("1 day");
            }
        }
        if (hours > 0) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            if (hours > 1) {
                builder.append(hours + " hours");
            } else {
                builder.append("1 hour");
            }
        }
        if (minutes > 0) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            if (minutes > 1) {
                builder.append(minutes + " minutes");
            } else {
                builder.append("1 minute");
            }
        }
        if (seconds > 0) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            if (seconds > 1) {
                builder.append(seconds + " seconds");
            } else {
                builder.append("1 second");
            }
        }
        if (milliseconds > 0) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            if (milliseconds > 1) {
                builder.append(milliseconds + " milliseconds");
            } else {
                builder.append("1 millisecond");
            }
        }
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     */
    public String toDebugString() {
        return "DurationDTO [years=" + years + ", months=" + months + ", days=" + days + ", hours=" + hours
            + ", minutes=" + minutes + ", seconds=" + seconds + ", milliseconds=" + milliseconds + "]";
    }

    /**
     * create a duration from the parameters
     * 
     * @param milliseconds
     *            m
     * @param seconds
     *            s
     * @param minutes
     *            m
     * @param hours
     *            h
     * @param days
     *            d
     * @param months
     *            m
     * @param years
     *            y
     */
    public DurationDTO(final int milliseconds, final int seconds, final int minutes, final int hours, final int days,
        final int months, final int years) {
        super();
        this.milliseconds = milliseconds;
        this.seconds = seconds;
        this.minutes = minutes;
        this.hours = hours;
        this.days = days;
        this.months = months;
        this.years = years;
    }

    /**
	 * 
	 */
    public DurationDTO() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public int toMinutes() {
        float totalMinutes = 0;

        totalMinutes += years * 525948.766;
        totalMinutes += months * 43829.0639;
        totalMinutes += days * 1440;
        totalMinutes += hours * 60;
        totalMinutes += minutes;
        return Math.round(totalMinutes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fromMinutes(final long fromMinutes) {
        double lyears = Double.valueOf(fromMinutes) / 525948.766;
        double remainder = lyears % 1;
        lyears = lyears - remainder;
        remainder = remainder * 525948.766;
        double lmonths = remainder / 43829.0639;
        remainder = lmonths % 1;
        lmonths = lmonths - remainder;
        remainder = remainder * 43829.0639;
        double ldays = remainder / 1440;
        remainder = ldays % 1;
        ldays = ldays - remainder;
        remainder = remainder * 1440;
        double lhours = remainder / 60;
        remainder = lhours % 1;
        lhours = lhours - remainder;
        remainder = remainder * 60;
        double lminutes = remainder;
        remainder = lminutes % 1;
        lminutes = lminutes - remainder;

        setYears((int) Math.round(lyears));
        setMonths((int) Math.round(lmonths));
        setDays((int) Math.round(ldays));
        setHours((int) Math.round(lhours));
        setMinutes((int) Math.round(lminutes));
    }
}
