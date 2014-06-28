/**
 * ChannelServlet.java
 * Created by pgirard at 3:21:10 PM on Jan 21, 2011
 * in the com.qagwaai.starmalaccamax.server package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * @author pgirard
 * @deprecated
 */
@SuppressWarnings("serial")
public final class ChannelServlet extends HttpServlet {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
        IOException {
        UserService userService = UserServiceFactory.getUserService();

        // Game creation, user sign-in, etc. omitted for brevity.
        // String userId = userService.getCurrentUser().getUserId();

        ChannelService channelService = ChannelServiceFactory.getChannelService();

        // The 'Game' object exposes a method which creates a unique string
        // based on the game's key
        // and the user's id.
        String token = channelService.createChannel("Game");

        // Index is the contents of our index.html resource, details omitted for
        // brevity.
        // index = index.replaceAll("\\{\\{ token \\}\\}", token);

        resp.setContentType("text/html");
        resp.getWriter().write(token);
    }

}
