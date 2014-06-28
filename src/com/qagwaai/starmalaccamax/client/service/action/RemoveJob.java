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
public final class RemoveJob implements IsSerializable, Action<RemoveJobResponse> {

    /**
	 * 
	 */
    private JobDTO job;

    /**
	 * 
	 */
    public RemoveJob() {

    }

    /**
     * @param job
     *            the Job to remove
     */
    public RemoveJob(final JobDTO job) {
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
        return "RemoveJob [job=" + job + "]";
    }

}
