package com.qagwaai.starmalaccamax.server;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.jetty.HttpStatus;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.qagwaai.starmalaccamax.server.business.TickManager;
import com.qagwaai.starmalaccamax.server.business.tick.DevTickJob;
import com.qagwaai.starmalaccamax.shared.ServiceException;

/**
 * 
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class TickServiceImpl extends RemoteServiceServlet {
    /**
     * Logger for this service
     */
    private static Logger log = Logger.getLogger(TickServiceImpl.class.getName());

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
        IOException {
        // log.info("tick service get woke up");
        String devCommand = req.getParameter("dev");
        if (devCommand != null) {
            if (req.getParameter("dev").equals("insert")) {
                TickManager.getInstance().insertSampleGameEvents();
                resp.setStatus(HttpStatus.ORDINAL_200_OK);
                resp.getWriter().print(new Date(System.currentTimeMillis()).toString() + ": dev, insert, OK!");
	    } else if (req.getParameter("dev").equals("quartz")) {
		try {
		    // Grab the Scheduler instance from the Factory
		    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		    // System.out.println(scheduler.isStarted());
		    // define the job and tie it to our HelloJob class
		    JobDetail job = newJob(DevTickJob.class)
		        .withIdentity("job1", "group1")
		        .build();

		    // Trigger the job to run now, and then repeat every 40 seconds
		    Trigger trigger = newTrigger()
		        .withIdentity("trigger1", "group1")
		        .startNow()
		        .withSchedule(simpleSchedule()
		        	.withIntervalInSeconds(10)
		                .repeatForever())            
		        .build();

		    // Tell quartz to schedule the job using our trigger
		    scheduler.scheduleJob(job, trigger);
		    resp.setStatus(HttpStatus.ORDINAL_200_OK);
		    resp.getWriter().print(new Date(System.currentTimeMillis()).toString() + ": dev, quartz started, OK!");
		} catch (SchedulerException se) {
		    se.printStackTrace();
		}
            }
        } else {
            try {
                TickManager.getInstance().tick();
                resp.setStatus(HttpStatus.ORDINAL_200_OK);
                resp.getWriter().print(new Date(System.currentTimeMillis()).toString() + ": tick OK!");
            } catch (ServiceException e) {
                resp.setStatus(HttpStatus.ORDINAL_500_Internal_Server_Error);
                resp.getWriter().print("Error: " + e.getMessage());
            }
        }

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void doPut(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
        IOException {
        log.info("tick service put woke up");

        resp.setStatus(HttpStatus.ORDINAL_200_OK);

    }

}
