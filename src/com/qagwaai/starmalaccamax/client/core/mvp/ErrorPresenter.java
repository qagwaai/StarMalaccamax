/**
 * ErrorPresenter.java
 * Created by pgirard at 10:27:30 AM on Oct 1, 2010
 * in the com.qagwaai.starmalaccamax.client.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.core.mvp;

import com.google.gwt.event.shared.UmbrellaException;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.shared.model.AppError;
import com.qagwaai.starmalaccamax.shared.model.AppErrorDTO;

/**
 * @author pgirard
 * 
 */
public final class ErrorPresenter extends AbstractPresenter<ErrorView, AppError> {

    /**
	 * 
	 */
    private static ErrorPresenter instance;

    /**
     * 
     * @return singleton
     */
    public static ErrorPresenter getInstance() {
        if (instance == null) {
            instance = new ErrorPresenter(null, new ErrorView(), new AppErrorDTO());
        }
        return instance;
    }

    /**
     * 
     * @param t
     *            present the error
     */
    public static void present(final Throwable t) {
        AppError model = new AppErrorDTO();
        if (t instanceof UmbrellaException) {
            model.setDetails(((UmbrellaException) t).getCauses());
        } else {
            model.setDetail(t);
            model.setShortMessage(t.getMessage());
        }
        if (instance == null) {
            instance = new ErrorPresenter(null, new ErrorView(), model);
        } else {
            instance.setModel(model);
            instance.updateView();
        }
        instance.showView();
    }

    /**
     * 
     * @param eventBus
     *            the bus to publish on
     * @param view
     *            an associated view
     * @param model
     *            the error model
     */
    public ErrorPresenter(final EventBus eventBus, final ErrorView view, final AppError model) {
        super(eventBus, view, model);
        // make sure to do the association before calling layout
        view.setPresenter(this);
        view.layout();
        updateView();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void destroyView() {
        getView().destroy();

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void hideView() {
        getView().hide();

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void renderView() {
        getView().render();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void showView() {
        getView().show();

    }

    /**
	 * 
	 */
    private void updateView() {
        getView().getShortMessageItem().setDefaultValue(getModel().getShortMessage());
        getView().getPriorityItem().setDefaultValue(getModel().getPriority());
        getView().getDetailItem().setDefaultValue(getModel().getDetail());
    }

}
