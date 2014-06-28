package com.qagwaai.starmalaccamax.client.game.mvp;

import java.util.HashMap;

import com.qagwaai.starmalaccamax.client.core.mvp.View;
import com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.RichTextEditor;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.tab.TabSet;

public interface PlayerCommunicationsView extends View {

	/**
	 * 
	 * @param chatRoom
	 *            the chat room associated with the tab
	 * @param roomName
	 *            the room name
	 * @param userName
	 *            the user name used in the chat room
	 * @return the roomName
	 */
	String addRoomTab(ChatRoomDTO chatRoom, String roomName, String userName);

	/**
	 * @return the captainsListGrid
	 */
	ListGrid getCaptainsListGrid();

	/**
	 * @return the gameChannelsListGrid
	 */
	ListGrid getGameRoomsListGrid();

	/**
	 * @return the richTextEditor
	 */
	RichTextEditor getRichTextEditor();

	/**
	 * @return the channelTabSet
	 */
	TabSet getRoomTabSet();

	/**
	 * @return the sendButton
	 */
	IButton getSendButton();

	/**
	 * 
	 * @param userName
	 *            the name of the user to post "from"
	 * @param chatRoom
	 *            the room to post to
	 * @param message
	 *            the message to post
	 */
	void postMessageToRoom(String userName, ChatRoomDTO chatRoom, String message);

	/**
	 * 
	 * @param userName
	 *            the user name to add
	 * @param chatRoom
	 *            the room to add to
	 */
	void addUserToRoomList(String userName, ChatRoomDTO chatRoom);

	/**
	 * 
	 * @param userName
	 *            the user name to remove
	 * @param chatRoom
	 *            the room to remove from
	 */
	void removeUserFromRoomList(String userName, ChatRoomDTO chatRoom);

	/**
	 * @return the chatAttendees
	 */
	HashMap<String, ListGrid> getChatAttendees();

}