package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsPresenter;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsView;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsViewImpl;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;


/**
 * 
 * @author pgirard
 * 
 */
public final class PlayerCommCaptainDoubleClickHandlerImplementation implements DoubleClickHandler {
    /**
	 * 
	 */
    private final PlayerCommunicationsView view;
    private final PlayerCommunicationsPresenter presenter;

    /**
     * 
     * @param view
     *            the view associated with this handler
     */
    public PlayerCommCaptainDoubleClickHandlerImplementation(final PlayerCommunicationsView view, final PlayerCommunicationsPresenter presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onDoubleClick(final DoubleClickEvent event) {
        ListGridRecord selected = view.getCaptainsListGrid().getSelectedRecord();
        String systemName = selected.getAttributeAsString(PlayerCommunicationsViewImpl.CHAT_ROOM_SYSTEM_NAME);
        String captainName = selected.getAttributeAsString(PlayerCommunicationsViewImpl.CHAT_ROOM_CAPTAIN_NAME);
        SolarSystemDTO solarSystem =
            (SolarSystemDTO) selected.getAttributeAsObject(PlayerCommunicationsViewImpl.SOLAR_SYSTEM_OBJ);
        // Captain captain = (Captain)
        // selected.getAttributeAsObject(PlayerCommunicationsView.CAPTAIN_OBJ);
        if (systemName != null) {
            presenter.joinChatRoom(solarSystem, captainName);
        }
    }
}