package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainViewImpl;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

public final class NewCaptainWizardButtonClickHandlerImpl implements
    com.smartgwt.client.widgets.menu.events.ClickHandler {
    private final EventBus eventBus;

    public NewCaptainWizardButtonClickHandlerImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void onClick(MenuItemClickEvent event) {
        NewCaptainPresenterImpl captainPresenter =
            new NewCaptainPresenterImpl(eventBus, new NewCaptainViewImpl(), new CaptainDTO());
        captainPresenter.renderView();
    }
}