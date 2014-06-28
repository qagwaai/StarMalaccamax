package com.qagwaai.starmalaccamax.server;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qagwaai.starmalaccamax.server.business.ChatRoomManager;
import com.qagwaai.starmalaccamax.shared.model.ChatRoomDTO;
import com.qagwaai.starmalaccamax.shared.model.channel.ChatRoomMessage;

/**
 * Use this to test the Channel API manually by going to the servlet URL in a
 * web browser where the GWT client is *already logged in*.
 * 
 */
@SuppressWarnings("serial")
public final class TestPushServlet extends HttpServlet {

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        sendMessage(req, resp);
    }

    /**
     * 
     * @param req
     *            the request
     * @param resp
     *            the response
     */
    public void sendMessage(final HttpServletRequest req, final HttpServletResponse resp) {
        ChatRoomDTO gameRoom = ChatRoomManager.getInstance().getChatRoom("Game");
        ChatRoomManager.getInstance().sendMessageToAllInRoom(gameRoom, "testPush",
            new ChatRoomMessage("Hello from push", "testPush", gameRoom));

    }

} // end class
