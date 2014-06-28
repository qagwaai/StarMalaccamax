package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsPresenter;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsView;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsViewImpl;
import com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;



/**
 * 
 * @author pgirard
 * 
 */
public final class PlayerCommEditorKeyPressHandlerImplementation implements KeyPressHandler {
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
    public PlayerCommEditorKeyPressHandlerImplementation(final PlayerCommunicationsView view, final PlayerCommunicationsPresenter presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    @Override
    public void onKeyPress(final KeyPressEvent event) {
        if (event.getKeyName().equals("Enter")) {
            ChatRoomDTO chatRoom =
                (ChatRoomDTO) view.getRoomTabSet().getSelectedTab()
                    .getAttributeAsObject(PlayerCommunicationsViewImpl.CHAT_ROOM_OBJ);
            String name =
                view.getRoomTabSet().getSelectedTab()
                    .getAttributeAsString(PlayerCommunicationsViewImpl.CHAT_ROOM_CHAT_AS);
            presenter.addMessage(chatRoom, name, view.getRichTextEditor().getValue());
        }
    }
}
