package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsViewImpl;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.LeaveChatRoom;
import com.qagwaai.starmalaccamax.client.service.action.LeaveChatRoomResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO;



/**
 * 
 * @author pgirard
 * 
 */
public final class PlayerCommCloseChannelClickHandlerImplementation implements
    com.smartgwt.client.widgets.tab.events.CloseClickHandler {
    /**
	 * 
	 */
    private final PlayerCommunicationsViewImpl view;

    /**
     * 
     * @param view
     *            the view associated with this handler
     */
    public PlayerCommCloseChannelClickHandlerImplementation(final PlayerCommunicationsViewImpl view) {
        this.view = view;
    }

    @Override
    public void onCloseClick(final com.smartgwt.client.widgets.tab.events.TabCloseClickEvent event) {
        view.say("Close", "Closing channel " + event.getTab().getTitle());
        ChatRoomDTO chatRoom = (ChatRoomDTO) event.getTab().getAttributeAsObject(PlayerCommunicationsViewImpl.CHAT_ROOM_OBJ);
        String name = event.getTab().getAttributeAsString(PlayerCommunicationsViewImpl.CHAT_ROOM_CHAT_AS);

        if (chatRoom != null) {
            ServiceFactory.getChannelService().execute(new LeaveChatRoom(name, chatRoom),
                new BaseAsyncCallback<LeaveChatRoomResponse>() {

                    @Override
                    public void onFailure(final Throwable caught) {
                        super.onFailure(caught);
                        view.say("Error", "Could not close channel: " + caught.getMessage());

                    }

                    @Override
                    public void onSuccess(final LeaveChatRoomResponse result) {
                        super.onSuccess(result);
                        if (!result.isSuccess()) {
                            GWT.log("Leave Chat Room returned failure");
                        }
                    }
                });
        }
    }
}