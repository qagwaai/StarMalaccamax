/**
 * PlayerCalendarView.java
 * Created by pgirard at 1:11:14 PM on Dec 2, 2010
 * in the com.qagwaai.starmalaccamax.client.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractView;
import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarPresenterImpl;
import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarViewImpl;
import com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.RichTextEditor;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * @author pgirard
 * 
 */
public class PlayerCommunicationsViewImpl extends AbstractView<PlayerCommunicationsPresenter> implements PlayerCommunicationsView {
    /**
	 * 
	 */
    public static final String CHAT_ROOM_SYSTEM_NAME = "system";
    /**
	 * 
	 */
    public static final String CHAT_ROOM_CAPTAIN_NAME = "captain";
    /**
	 * 
	 */
    public static final String CHAT_ROOM_ACTION = "action";
    /**
	 * 
	 */
    public static final String SOLAR_SYSTEM_OBJ = "solarSystemObj";
    /**
	 * 
	 */
    public static final String CAPTAIN_OBJ = "captainObj";
    /**
	 * 
	 */
    public static final String CHAT_ROOM_OBJ = "chatRoomObj";
    /**
	 * 
	 */
    public static final String CHAT_ROOM_CHAT_AS = "chatName";
    /**
	 * 
	 */
    public static final String CHAT_ROOM_NAME = "roomName";

    /**
	 * 
	 */
    private HashMap<String, Canvas> chatCanvas = new HashMap<String, Canvas>();
    /**
	 * 
	 */
    private HashMap<String, ListGrid> chatAttendees = new HashMap<String, ListGrid>();
    /**
	 * 
	 */
    private VLayout baseLayout = new VLayout(15);
    /**
     * 
     */
    private LoginBarPresenterImpl loginBarPresenter;
    // private ListGrid captainsListGrid = new ListGrid();
    /**
	 * 
	 */
    private final ListGrid captainsListGrid = new ListGrid() {
        @Override
        protected Canvas createRecordComponent(final ListGridRecord record, final Integer colNum) {
            String fieldName = this.getFieldName(colNum);

            if (fieldName.equals(PlayerCommunicationsViewImpl.CHAT_ROOM_ACTION)) {
                HLayout recordCanvas = new HLayout(3);
                recordCanvas.setHeight(22);
                recordCanvas.setAlign(Alignment.CENTER);
                ImgButton connectImg = new ImgButton();
                connectImg.setShowDown(false);
                connectImg.setShowRollOver(false);
                connectImg.setLayoutAlign(Alignment.CENTER);
                connectImg.setSrc("silk/connect.png");
                connectImg.setPrompt("Connect to channel");
                connectImg.setHeight(16);
                connectImg.setWidth(16);
                connectImg.addClickHandler(new ClickHandler() {
                    public void onClick(final ClickEvent event) {

                        String captainName = record.getAttribute(PlayerCommunicationsViewImpl.CHAT_ROOM_CAPTAIN_NAME);
                        SolarSystemDTO solarSystem =
                            (SolarSystemDTO) record.getAttributeAsObject(PlayerCommunicationsViewImpl.SOLAR_SYSTEM_OBJ);
                        // Captain captain = (Captain)
                        // record.getAttributeAsObject(PlayerCommunicationsView.CAPTAIN_OBJ);
                        getPresenter().joinChatRoom(solarSystem, captainName);
                    }
                });

                ImgButton disconnectImg = new ImgButton();
                disconnectImg.setShowDown(false);
                disconnectImg.setShowRollOver(false);
                disconnectImg.setAlign(Alignment.CENTER);
                disconnectImg.setSrc("silk/delete.png");
                disconnectImg.setPrompt("Disconnect");
                disconnectImg.setHeight(16);
                disconnectImg.setWidth(16);
                disconnectImg.addClickHandler(new ClickHandler() {
                    public void onClick(final ClickEvent event) {
                        SC.say("Disconnect from channel : "
                            + record.getAttribute(PlayerCommunicationsViewImpl.CHAT_ROOM_SYSTEM_NAME));
                    }
                });

                recordCanvas.addMember(connectImg);
                recordCanvas.addMember(disconnectImg);
                return recordCanvas;
            } else {
                return null;
            }

        }
    };
    /**
	 * 
	 */
    private final ListGrid gameRoomsListGrid = new ListGrid() {
        @Override
        protected Canvas createRecordComponent(final ListGridRecord record, final Integer colNum) {
            String fieldName = this.getFieldName(colNum);
            if (fieldName.equals(PlayerCommunicationsViewImpl.CHAT_ROOM_ACTION)) {
                HLayout recordCanvas = new HLayout(3);
                recordCanvas.setHeight(22);
                recordCanvas.setAlign(Alignment.CENTER);
                ImgButton connectImg = new ImgButton();
                connectImg.setShowDown(false);
                connectImg.setShowRollOver(false);
                connectImg.setLayoutAlign(Alignment.CENTER);
                connectImg.setSrc("silk/connect.png");
                connectImg.setPrompt("Connect to channel");
                connectImg.setHeight(16);
                connectImg.setWidth(16);
                connectImg.addClickHandler(new ClickHandler() {
                    public void onClick(final ClickEvent event) {
                        String roomName = record.getAttribute(PlayerCommunicationsViewImpl.CHAT_ROOM_NAME);
                        getPresenter().joinGameChatRoom(roomName);
                    }
                });

                ImgButton disconnectImg = new ImgButton();
                disconnectImg.setShowDown(false);
                disconnectImg.setShowRollOver(false);
                disconnectImg.setAlign(Alignment.CENTER);
                disconnectImg.setSrc("silk/delete.png");
                disconnectImg.setPrompt("Disconnect");
                disconnectImg.setHeight(16);
                disconnectImg.setWidth(16);
                disconnectImg.addClickHandler(new ClickHandler() {
                    public void onClick(final ClickEvent event) {
                        SC.say("Disconnect from channel : "
                            + record.getAttribute(PlayerCommunicationsViewImpl.CHAT_ROOM_NAME));
                    }
                });

                recordCanvas.addMember(connectImg);
                recordCanvas.addMember(disconnectImg);
                return recordCanvas;
            } else {
                return null;
            }

        }
    };
    /**
	 * 
	 */
    private TabSet roomTabSet = new TabSet();
    /**
	 * 
	 */
    private RichTextEditor richTextEditor = new RichTextEditor();
    /**
	 * 
	 */
    private IButton sendButton = new IButton("Send");

    /**
	 * 
	 */
    public PlayerCommunicationsViewImpl() {
        super();
    }

    /**
     * 
     * @param location
     *            the location of the page - used for history
     */
    public PlayerCommunicationsViewImpl(final String location) {
        this();
        setLocation(location);
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsView#addRoomTab(com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO, java.lang.String, java.lang.String)
	 */
    @Override
	public final String addRoomTab(final ChatRoomDTO chatRoom, final String roomName, final String userName) {
        Tab roomTab = new Tab(roomName);
        roomTab.setCanClose(true);
        roomTab.setAttribute(PlayerCommunicationsViewImpl.CHAT_ROOM_OBJ, chatRoom);
        roomTab.setAttribute(PlayerCommunicationsViewImpl.CHAT_ROOM_CHAT_AS, userName);
        roomTab.setPane(buildChat(roomName, userName));
        roomTabSet.addTab(roomTab);
        roomTabSet.selectTab(roomTab);

        return roomName;
    }

    @Override
    public final Widget asWidget() {
        return baseLayout;
    }

    /**
     * 
     * @param id
     *            the id of the chat room
     * @param name
     *            the name of the chat room
     * @return a container to hold the chat
     */
    private VLayout buildChat(final String id, final String name) {
        VLayout layout = new VLayout();

        final Canvas nameCanvas = new Canvas();
        nameCanvas.setWidth100();
        nameCanvas.setHeight("5%");
        nameCanvas.setShowEdges(false);
        nameCanvas.setContents("Messaging as: <b>" + name + "</b>");

        HLayout mainLayout = new HLayout();

        final ListGrid attendees = new ListGrid();
        ListGridField nameField = new ListGridField("name", "Name");
        attendees.setFields(nameField);
        attendees.setWidth(100);

        final Canvas htmlCanvas = new Canvas();
        htmlCanvas.setHeight100();
        htmlCanvas.setPadding(2);
        htmlCanvas.setOverflow(Overflow.SCROLL);
        // htmlCanvas.setCanDragResize(true);
        htmlCanvas.setShowEdges(true);
        htmlCanvas.setWidth("*");
        mainLayout.addMember(htmlCanvas);
        mainLayout.addMember(attendees);

        layout.addMember(nameCanvas);
        layout.addMember(mainLayout);

        chatCanvas.put(id, htmlCanvas);
        GWT.log("adding chatCanvas with id = " + id);
        chatAttendees.put(id, attendees);

        return layout;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsView#getCaptainsListGrid()
	 */
    @Override
	public final ListGrid getCaptainsListGrid() {
        return captainsListGrid;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsView#getGameRoomsListGrid()
	 */
    @Override
	public final ListGrid getGameRoomsListGrid() {
        return gameRoomsListGrid;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsView#getRichTextEditor()
	 */
    @Override
	public final RichTextEditor getRichTextEditor() {
        return richTextEditor;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsView#getRoomTabSet()
	 */
    @Override
	public final TabSet getRoomTabSet() {
        return roomTabSet;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Canvas getRootCanvas() {
        return baseLayout;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsView#getSendButton()
	 */
    @Override
	public final IButton getSendButton() {
        return sendButton;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void layout() {
        baseLayout.setWidth100();
        baseLayout.setHeight100();
        loginBarPresenter =
            new LoginBarPresenterImpl(this.getPresenter().getEventBus(), new LoginBarViewImpl(), new UserDTO());

        baseLayout.addMember(loginBarPresenter.getView().asWidget());

        HLayout mainLayout = new HLayout();
        mainLayout.setWidth100();
        mainLayout.setWidth100();

        final SectionStack sectionStack = new SectionStack();
        sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
        sectionStack.setWidth("30%");
        sectionStack.setShowResizeBar(true);

        SectionStackSection captainsSection = new SectionStackSection("Captain's Channels");
        captainsSection.setExpanded(true);
        captainsListGrid.setFields(new ListGridField(CHAT_ROOM_SYSTEM_NAME, "System"), new ListGridField(
            CHAT_ROOM_CAPTAIN_NAME, "Captain"), new ListGridField(CHAT_ROOM_ACTION, "Action"));
        captainsListGrid.setShowRecordComponents(true);
        captainsListGrid.setShowRecordComponentsByCell(true);
        captainsSection.addItem(captainsListGrid);

        SectionStackSection gameSection = new SectionStackSection("Game Channels");
        gameSection.setExpanded(true);
        gameRoomsListGrid.setFields(new ListGridField(CHAT_ROOM_NAME, "Room"), new ListGridField(CHAT_ROOM_ACTION,
            "Action"));
        gameRoomsListGrid.setShowRecordComponents(true);
        gameRoomsListGrid.setShowRecordComponentsByCell(true);
        ListGridRecord gameRoom = new ListGridRecord();
        gameRoom.setAttribute(PlayerCommunicationsViewImpl.CHAT_ROOM_NAME, "Game");
        // gameRoom.setAttribute(PlayerCommunicationsView.CHAT_ROOM_ACTION,
        // "&nbsp;");
        ListGridRecord[] gameRoomsData = new ListGridRecord[1];
        gameRoomsData[0] = gameRoom;

        gameRoomsListGrid.setData(gameRoomsData);
        gameSection.addItem(gameRoomsListGrid);

        sectionStack.addSection(captainsSection);
        sectionStack.addSection(gameSection);

        VLayout vLayout = new VLayout();
        vLayout.setWidth("70%");

        roomTabSet.setTabBarPosition(Side.TOP);
        roomTabSet.setTabBarAlign(Side.LEFT);
        // topTabSet.setWidth(400);
        roomTabSet.setHeight("70%");
        roomTabSet.setShowResizeBar(true);

        richTextEditor.setHeight("30%");
        richTextEditor.setOverflow(Overflow.HIDDEN);
        richTextEditor.setCanDragResize(true);
        richTextEditor.setShowEdges(true);
        // sendButton.setLeft("10%");
        sendButton.setWidth(50);
        richTextEditor.addMember(sendButton);
        // richTextEditor.setShowResizeBar(true);

        // vLayout.addMember(listingLabel);
        vLayout.addMember(roomTabSet);
        vLayout.addMember(richTextEditor);

        mainLayout.addMember(sectionStack);
        mainLayout.addMember(vLayout);
        // mainLayout.draw();
        baseLayout.addMember(mainLayout);
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsView#postMessageToRoom(java.lang.String, com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO, java.lang.String)
	 */
    @Override
	public final void postMessageToRoom(final String userName, final ChatRoomDTO chatRoom, final String message) {

        Canvas htmlCanvas = chatCanvas.get(chatRoom.getId());
        if (htmlCanvas == null) {
            GWT.log("Could not post message to chatroom with id = " + chatRoom.getId());
        } else {
            String contents = htmlCanvas.getContents();
            contents = contents.concat("<BR>" + userName + "&nbsp;:&nbsp;" + message);
            htmlCanvas.setContents(contents);
            htmlCanvas.scrollToBottom();
        }
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsView#addUserToRoomList(java.lang.String, com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO)
	 */
    @Override
	public final void addUserToRoomList(final String userName, final ChatRoomDTO chatRoom) {
        ListGrid attendeesListGrid = chatAttendees.get(chatRoom.getId());
        if (attendeesListGrid == null) {
            GWT.log("Could not add user to chatroom with id = " + chatRoom.getId());
        } else {
            ListGridRecord newUser = new ListGridRecord();
            newUser.setAttribute("name", userName);
            attendeesListGrid.addData(newUser);
        }
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsView#removeUserFromRoomList(java.lang.String, com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO)
	 */
    @Override
	public final void removeUserFromRoomList(final String userName, final ChatRoomDTO chatRoom) {
        ListGrid attendeesListGrid = chatAttendees.get(chatRoom.getId());
        if (attendeesListGrid == null) {
            GWT.log("Could not add user to chatroom with id = " + chatRoom.getId());
        } else {
            ListGridRecord newUser = new ListGridRecord();
            newUser.setAttribute("name", userName);
            attendeesListGrid.removeData(newUser);
        }
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsView#getChatAttendees()
	 */
    @Override
	public HashMap<String, ListGrid> getChatAttendees() {
        return chatAttendees;
    }

}
