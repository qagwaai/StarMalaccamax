package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsPresenter;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsView;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsViewImpl;
import com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;



/**
 * 
 * @author pgirard
 * 
 */
public final class PlayerCommSendMessageClickHandlerImplementation implements ClickHandler {
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
    public PlayerCommSendMessageClickHandlerImplementation(final PlayerCommunicationsView view, final PlayerCommunicationsPresenter presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    @Override
    public void onClick(final ClickEvent event) {
        ChatRoomDTO chatRoom =
            (ChatRoomDTO) view.getRoomTabSet().getSelectedTab()
                .getAttributeAsObject(PlayerCommunicationsViewImpl.CHAT_ROOM_OBJ);
        String name =
            view.getRoomTabSet().getSelectedTab().getAttributeAsString(PlayerCommunicationsViewImpl.CHAT_ROOM_CHAT_AS);
        presenter.addMessage(chatRoom, name, view.getRichTextEditor().getValue());

    }
}
