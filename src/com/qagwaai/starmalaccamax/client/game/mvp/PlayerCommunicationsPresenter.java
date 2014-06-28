package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.Presenter;
import com.qagwaai.starmalaccamax.client.event.PushMessageReceivedEvent;
import com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;
import com.qagwaai.starmalaccamax.shared.model.User;

public interface PlayerCommunicationsPresenter extends Presenter<PlayerCommunicationsView, User> {

	/**
	 * 
	 * @param chatRoom
	 *            the room to add the message to
	 * @param name
	 *            the user name who wrote the message
	 * @param message
	 *            the message
	 */
	void addMessage(ChatRoomDTO chatRoom, String name, String message);

	/**
	 * 
	 * @param solarSystem
	 *            the solarsystem to join
	 * @param name
	 *            the username
	 */
	void joinChatRoom(SolarSystemDTO solarSystem, String name);

	/**
	 * 
	 * @param roomName
	 *            the game room to join
	 */
	void joinGameChatRoom(String roomName);

	/**
	 * 
	 * {@inheritDoc}
	 */
	void onPushMessageReceived(PushMessageReceivedEvent event);

}