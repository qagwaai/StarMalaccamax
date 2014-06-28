/**
 * 
 */
package com.qagwaai.starmalaccamax.server.business.tick;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.qagwaai.starmalaccamax.server.business.TickManager;
import com.qagwaai.starmalaccamax.shared.ServiceException;

/**
 * @author ehldxae
 *
 */
public class DevTickJob implements Job {

    /* (non-Javadoc)
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
	try {
	    TickManager.getInstance().tick();
	} catch (ServiceException e) {
	    throw new JobExecutionException(e);
	}
    }

}
