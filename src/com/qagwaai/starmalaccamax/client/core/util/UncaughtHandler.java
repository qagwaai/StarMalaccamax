/**
 * UncaughtHandler.java
 * Created by pgirard at 11:34:21 AM on Oct 1, 2010
 * in the com.qagwaai.starmalaccamax.client.util package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.core.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.event.EventBus;

/**
 * @author pgirard
 * 
 */
public final class UncaughtHandler implements UncaughtExceptionHandler {

    /**
	 * 
	 */
    private EventBus eventBus;

    /**
     * @param eventBus
     *            the bus to publish on
     */
    public UncaughtHandler(final EventBus eventBus) {
        this.eventBus = eventBus;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onUncaughtException(final Throwable e) {
        GWT.log("Uncaught Exception", e);
        /*
         * AppError error = new AppErrorDTO(); error.setDetail(e);
         * error.setShortMessage(e.getMessage()); error.setPriority(1);
         * ErrorPresenter presenter = new ErrorPresenter(eventBus, new
         * ErrorView(), error); presenter.showView();
         */

        ErrorPresenter.present(e);

    }

}
