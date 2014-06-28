/**
 * 
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.service.action.JoinGameChatRoomResponse;
import com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO;

/**
 * @author pgirard
 * 
 */
public abstract class JoinedGameChatRoom extends BaseAsyncCallback<JoinGameChatRoomResponse> {

    /**
     * 
     * @param chatRoom
     *            notify listeners that we have joined a chat room
     */
    public abstract void joined(final ChatRoomDTO chatRoom);

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFailure(final Throwable caught) {
        super.onFailure(caught);
        ErrorPresenter.present(caught);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onSuccess(final JoinGameChatRoomResponse result) {
        super.onSuccess(result);
        joined(result.getChatRoom());

    }

}
