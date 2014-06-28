/**
 * Job.java
 * Created by pgirard at 8:19:09 AM on Oct 20, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.util.Date;

/**
 * @author pgirard
 * 
 */
public interface Job extends Model {
    /**
     * 
     * @return the command to execute
     */
    String getCommandClass();

    /**
     * 
     * @return the description of the job
     */
    String getDescription();

    /**
     * @return the id
     */
    Long getId();

    /**
     * 
     * @return the date of the last run
     */
    Date getLastRun();

    /**
     * 
     * @return how often to rerun
     */
    Long getRecurrence();

    /**
     * 
     * @return the last status of the job
     */
    String getStatus();

    /**
     * 
     * @return true if the job is enabled
     */
    boolean isEnabled();

    /**
     * 
     * @param commandClass
     *            the command to execute
     */
    void setCommandClass(String commandClass);

    /**
     * 
     * @param description
     *            the description of the job
     */
    void setDescription(String description);

    /**
     * 
     * @param enabled
     *            true if the job is enabled
     */
    void setEnabled(boolean enabled);

    /**
     * @param id
     *            the id to set
     */
    void setId(final Long id);

    /**
     * 
     * @param lastRun
     *            date of last run
     */
    void setLastRun(Date lastRun);

    /**
     * 
     * @param milliseconds
     *            number of milliseconds between each run
     */
    void setRecurrence(Long milliseconds);

    /**
     * 
     * @param status
     *            the last status of the job
     */
    void setStatus(String status);

}
