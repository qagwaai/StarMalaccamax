/**
 * LoginService.java
 * Created by pgirard at 10:25:33 AM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.qagwaai.starmalaccamax.client.Application;
import com.qagwaai.starmalaccamax.client.service.LoginService;
import com.qagwaai.starmalaccamax.client.service.action.Action;
import com.qagwaai.starmalaccamax.client.service.action.GetCurrentUser;
import com.qagwaai.starmalaccamax.client.service.action.GetCurrentUserResponse;
import com.qagwaai.starmalaccamax.client.service.action.Response;
import com.qagwaai.starmalaccamax.server.business.ChannelManager;
import com.qagwaai.starmalaccamax.server.dao.CaptainDAO;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.UserDAO;
import com.qagwaai.starmalaccamax.shared.ServiceException;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
    /**
     * Logger for this service
     */
    private static Logger log = Logger.getLogger(LoginServiceImpl.class.getName());

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Response> T execute(final Action<T> action) throws ServiceException {
        Object response = null;
        long startTime = Instrumentation.callStart("LoginServiceImpl: " + action);
        if (action instanceof GetCurrentUser) {
            response = (T) executeGetCurrentUser((GetCurrentUser) action);
        }
        if (response != null) {
            Instrumentation.callEnd("LoginServiceImpl: " + response, startTime, action.getClass().getSimpleName(),
                response);
            return (T) response;
        }

        log.severe("Action not Implemented: " + action.getClass().getName());
        throw new ServiceException("Action not Implemented");
    }

    /**
     * Get the current user logged into the application through google appworks
     * 
     * @param action
     *            the get current user action
     * @return the filled out response - the user object if logged in, or null
     * @throws ServiceException
     *             if the action cannot be completed
     */
    private GetCurrentUserResponse executeGetCurrentUser(final GetCurrentUser action) throws ServiceException {

        GetCurrentUserResponse response = new GetCurrentUserResponse();

        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if (user != null) {
            com.qagwaai.starmalaccamax.shared.model.UserDTO bean = new com.qagwaai.starmalaccamax.shared.model.UserDTO();
            bean.setEmail(user.getEmail());
            bean.setNickname(user.getNickname());
            bean.setAppengineUniqueId(user.getUserId());
            bean.setAdmin(userService.isUserAdmin());

            DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
            UserDAO userDAO = factory.getUserDAO();
            CaptainDAO captainDAO = factory.getCaptainDAO();
            com.qagwaai.starmalaccamax.shared.model.UserDTO foundUser = null;
            try {
                foundUser = userDAO.findUserByAppworksId(user.getUserId());
                if (foundUser == null) {
                    bean.setNoob(true);
                    bean.setHasCaptains(false);
                    foundUser = userDAO.addUser(bean);
                    if (foundUser != null) {
                    	foundUser.setCaptains(new ArrayList<CaptainDTO>());
                    } else {
                    	log.severe("Could not get a valid User from DAO");
                    }
                } else {
                    foundUser.setNoob(false);
                    ArrayList<CaptainDTO> captains = captainDAO.getCaptainsForUser(foundUser);
                    if (captains.size() > 0) {
                        foundUser.setHasCaptains(true);
                        ArrayList<CaptainDTO> capD = new ArrayList<CaptainDTO>();
                        for (CaptainDTO captain : captains) {
                            capD.add((CaptainDTO) captain);
                        }
                        foundUser.setCaptains(capD);
                    } else {
                        foundUser.setHasCaptains(false);
                        foundUser.setCaptains(new ArrayList<CaptainDTO>());
                    }
                }
                if (foundUser != null) {
                	DateFormat format = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
                	foundUser.setLastLoggedin(format.format(new Date(System.currentTimeMillis())));
                	foundUser.setChannelId(ChannelManager.createChannel(foundUser));
                	userDAO.updateUser(foundUser);
                } else {
                	log.severe("Login Service could not login user");
                }
            } catch (DAOException e) {
                log.severe(e.toString());
                throw new ServiceException("Could not complete command GetCurrentUser.");
            }

            response.setUser(foundUser);
        }
        response.setLoginUrl(userService.createLoginURL(action.getCurrentLocation()));
        response.setLogoutUrl(userService.createLogoutURL(action.getCurrentLocation()));

        return response;
    }

}
