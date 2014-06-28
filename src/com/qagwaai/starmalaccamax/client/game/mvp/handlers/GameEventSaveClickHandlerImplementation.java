package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.GameEventWindowViewImpl;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.UpdateGameEvent;
import com.qagwaai.starmalaccamax.client.service.action.UpdateGameEventResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.model.GameEvent;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;



/**
 * 
 * @author pgirard
 * 
 */
public final class GameEventSaveClickHandlerImplementation implements ClickHandler {
    /**
	 * 
	 */
    private final GameEvent model;
    /**
	 * 
	 */
    private final GameEventWindowViewImpl view;

    /**
     * 
     * @param model
     *            the model to update
     * @param view
     *            the view
     */
    public GameEventSaveClickHandlerImplementation(final GameEvent model, final GameEventWindowViewImpl view) {
        this.model = model;
        this.view = view;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final ClickEvent event) {

        ServiceFactory.getGameEventService().execute(new UpdateGameEvent((GameEventDTO) model),
            new BaseAsyncCallback<UpdateGameEventResponse>() {

                @Override
                public void onFailure(final Throwable caught) {
                    super.onFailure(caught);
                    view.alert("Could not save market: " + caught.getMessage());

                }

                @Override
                public void onSuccess(final UpdateGameEventResponse result) {
                    super.onSuccess(result);
                    view.destroy();
                }
            });
    }
}
