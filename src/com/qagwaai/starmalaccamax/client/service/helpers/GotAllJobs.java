/**
 * GotUser.java
 * Created by pgirard at 2:04:51 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.service package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.service.action.GetAllJobsResponse;
import com.qagwaai.starmalaccamax.shared.model.JobDTO;

/**
 * @author pgirard
 * 
 */
public abstract class GotAllJobs extends BaseAsyncCallback<GetAllJobsResponse> {
    /**
     * notify that we got a new user
     * 
     * @param jobs
     *            the jobs found by the command or null
     */
    public abstract void got(final ArrayList<JobDTO> jobs);

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onFailure(final Throwable caught) {
        super.onFailure(caught);
        ErrorPresenter.present(caught);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onSuccess(final GetAllJobsResponse result) {
        super.onSuccess(result);
        got(result.getJobs());

    }

}
