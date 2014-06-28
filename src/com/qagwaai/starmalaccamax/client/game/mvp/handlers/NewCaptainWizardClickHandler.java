package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainViewImpl;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;


/**
 * 
 * @author pgirard
 * 
 */
public final class NewCaptainWizardClickHandler implements ClickHandler {

    /**
	 * 
	 */
    private EventBus eventBus;

    /**
     * @param eventBus
     *            the bus to publish events on
     */
    public NewCaptainWizardClickHandler(final EventBus eventBus) {
        this.eventBus = eventBus;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final ClickEvent event) {
        NewCaptainPresenterImpl captainPresenter =
            new NewCaptainPresenterImpl(eventBus, new NewCaptainViewImpl(), new CaptainDTO());
        captainPresenter.renderView();
    }

}