package com.qagwaai.starmalaccamax.client.core.mvp;

import com.qagwaai.starmalaccamax.client.event.CurrentUserChangedEvent;
import com.qagwaai.starmalaccamax.shared.model.User;

public interface LoginBarPresenter extends Presenter<LoginBarView, User> {

    /**
     * 
     * {@inheritDoc}
     */
    void destroyView();

    /**
     * 
     * {@inheritDoc}
     */
    void hideView();

    /**
     * 
     * {@inheritDoc}
     */
    void onCurrentUserChanged(final CurrentUserChangedEvent event);

    /**
     * 
     * {@inheritDoc}
     */
    void renderView();

    /**
     * 
     * {@inheritDoc}
     */
    void showView();

}