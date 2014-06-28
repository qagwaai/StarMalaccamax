/**
 * 
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.service.action.JoinSolarSystemChatRoomResponse;
import com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO;

/**
 * @author pgirard
 * 
 */
public abstract class JoinedSolarSystemChatRoom extends BaseAsyncCallback<JoinSolarSystemChatRoomResponse> {

    /**
     * 
     * @param chatRoom
     *            the chatroom joined
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
    public void onSuccess(final JoinSolarSystemChatRoomResponse result) {
        super.onSuccess(result);
        joined(result.getChatRoom());

    }

}
