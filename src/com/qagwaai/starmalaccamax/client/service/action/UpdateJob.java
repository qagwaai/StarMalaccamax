/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the JobMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.JobDTO;

/**
 * @author pgirard
 * 
 */
public final class UpdateJob implements IsSerializable, Action<UpdateJobResponse> {

    /**
	 * 
	 */
    private JobDTO job;

    /**
	 * 
	 */
    public UpdateJob() {

    }

    /**
     * @param job
     *            the Job to update
     */
    public UpdateJob(final JobDTO job) {
        this.job = job;
    }

    /**
     * @return the job
     */
    public JobDTO getJob() {
        return job;
    }

    /**
     * @param job
     *            the job to set
     */
    public void setJob(final JobDTO job) {
        this.job = job;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "UpdateJob [job=" + job + "]";
    }

}
