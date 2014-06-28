/**
 * GetAllUsersResponse.java
 * Created by pgirard at 2:07:29 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the JobMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.JobDTO;

/**
 * @author pgirard
 * 
 */
public final class GetAllJobsResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ArrayList<JobDTO> jobs;
    /**
	 * 
	 */
    private int totalJobs;

    /**
     * @return the users
     */
    public ArrayList<JobDTO> getJobs() {
        return jobs;
    }

    /**
     * @return the totalJobs
     */
    public int getTotalJobs() {
        return totalJobs;
    }

    /**
     * @param jobs
     *            the users to set
     */
    public void setJobs(final ArrayList<JobDTO> jobs) {
        this.jobs = jobs;
    }

    /**
     * @param totalJobs
     *            the totalJobs to set
     */
    public void setTotalJobs(final int totalJobs) {
        this.totalJobs = totalJobs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetAllJobsResponse [jobs=" + jobs + ", totalJobs=" + totalJobs + "]";
    }

}
