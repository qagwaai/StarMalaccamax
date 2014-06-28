/**
 * LandingPresenter.java
 * Created by pgirard at 9:54:58 AM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.client.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.AbstractPresenter;
import com.qagwaai.starmalaccamax.client.event.CurrentUserChangedEvent;
import com.qagwaai.starmalaccamax.client.event.CurrentUserChangedHandler;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.NewCaptainWizardClickHandler;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerSummaryClickHandlerImplementation;
import com.qagwaai.starmalaccamax.shared.model.User;

/**
 * @author pgirard
 * 
 */
public final class LandingPresenterImpl extends AbstractPresenter<LandingView, User> implements CurrentUserChangedHandler, LandingPresenter {



    /**
     * 
     * @param eventBus
     *            the bus to publish on
     * @param view
     *            an associated view
     * @param model
     *            the user model
     */
    public LandingPresenterImpl(final EventBus eventBus, final LandingViewImpl view, final User model) {
        super(eventBus, view, model);
        // make sure to do the association before calling layout
        view.setPresenter(this);
        view.layout();

        getEventBus().addHandler(CurrentUserChangedEvent.getType(), this);
        if (view.getStartWizard() != null) {
            view.getStartWizard().addClickHandler(new NewCaptainWizardClickHandler(eventBus));
        }

        view.getGotoPlayerSummaryPage().addClickHandler(new PlayerSummaryClickHandlerImplementation(eventBus));
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

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.LandingPresenter#onCurrentUserChanged(com.qagwaai.starmalaccamax.client.event.CurrentUserChangedEvent)
	 */
	@Override
    public void onCurrentUserChanged(final CurrentUserChangedEvent event) {
        getView().gotUser(event.getCurrentUser());

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
