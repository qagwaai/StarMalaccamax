package com.qagwaai.starmalaccamax.client.core.mvp.handlers;

import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowViewImpl;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * 
 * @author pgirard
 * 
 */
public final class ProfileClickHandlerImpl implements ClickHandler {

    /**
	 * 
	 */
    private EventBus eventBus;
    /**
	 * 
	 */
    private UserDTO user;

    /**
     * 
     * @param eventBus
     *            the bus to publish events to
     * @param user
     *            the user to show
     */
    public ProfileClickHandlerImpl(final EventBus eventBus, final UserDTO user) {
        this.eventBus = eventBus;
    }

    /**
     * @return the user
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final ClickEvent event) {

        ProfileWindowPresenterImpl profilePresenter =
            new ProfileWindowPresenterImpl(eventBus, new ProfileWindowViewImpl(), getUser());
        profilePresenter.renderView();

    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(final UserDTO user) {
        this.user = user;
    }

}