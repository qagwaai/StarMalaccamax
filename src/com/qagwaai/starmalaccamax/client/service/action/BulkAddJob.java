/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.JobDTO;

/**
 * @author pgirard
 * 
 */
public final class BulkAddJob implements IsSerializable, Action<BulkAddJobResponse> {

    /**
	 * 
	 */
    private ArrayList<JobDTO> jobs;

    /**
	 * 
	 */
    public BulkAddJob() {

    }

    /**
     * @param jobs
     *            the Stars to add
     */
    public BulkAddJob(final ArrayList<JobDTO> jobs) {
        this.jobs = jobs;
    }

    /**
     * @return the jobs
     */
    public ArrayList<JobDTO> getJobs() {
        return jobs;
    }

    /**
     * @param jobs
     *            the Stars to set
     */
    public void setJobs(final ArrayList<JobDTO> jobs) {
        this.jobs = jobs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final int maxLen = 10;
        return "BulkAddJob [jobs=" + (jobs != null ? jobs.subList(0, Math.min(jobs.size(), maxLen)) : null) + "]";
    }

}
