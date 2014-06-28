/**
 * LandingPresenter.java
 * Created by pgirard at 9:54:58 AM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.client.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.AbstractPresenter;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.shared.model.User;

/**
 * @author pgirard
 * 
 */
public final class PrimerPresenterImpl extends AbstractPresenter<PrimerView, User> implements PrimerPresenter {

    /**
     * 
     * @param eventBus
     *            the bus to publish on
     * @param view
     *            an associated view
     * @param model
     *            the user model
     */
    public PrimerPresenterImpl(final EventBus eventBus, final PrimerViewImpl view, final User model) {
        super(eventBus, view, model);
        // make sure to do the association before calling layout
        view.setPresenter(this);
        view.layout();

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
}
