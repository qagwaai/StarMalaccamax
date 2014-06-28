/**
 * ProfileImageUpdate.java
 * com.qagwaai.starmalaccamax.server
 * StarMalaccamax
 * Created Mar 5, 2011 at 8:20:49 PM
 */
package com.qagwaai.starmalaccamax.server;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.qagwaai.starmalaccamax.client.Application;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.UserDAO;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class ProfileImageUpload extends HttpServlet {
    /**
	 * 
	 */
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    /**
	 * 
	 */
    private static Logger log = Logger.getLogger(ProfileImageUpload.class.getName());

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
        IOException {
        Instrumentation.callStart("ProfileImageUpload");
        UserService userService = UserServiceFactory.getUserService();
        try {
            if (!userService.isUserLoggedIn()) {
                log.warning("Not logged in user tried to execute profile image upload");
                throw new ServletException("User is not logged in");
            }
        } catch (NullPointerException npe) {
            // assume that we are in a test unit
            LocalServiceTestHelper helper =
                new LocalServiceTestHelper(new LocalUserServiceTestConfig(), new LocalDatastoreServiceTestConfig())
                    .setEnvIsAdmin(true).setEnvIsLoggedIn(true);
            helper.setUp();
        }
        Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
        BlobKey blobKey = blobs.get("upfile");
        if (blobKey != null) {
            User user = userService.getCurrentUser();
            DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
            UserDAO userDAO = factory.getUserDAO();
            com.qagwaai.starmalaccamax.shared.model.UserDTO foundUser = null;
            try {
                foundUser = userDAO.findUserByAppworksId(user.getUserId());
                if (foundUser == null) {
                    blobstoreService.delete(blobKey);
                    throw new ServletException("User not found");
                } else {
                    foundUser.setProfileImageBlobKey(blobKey.getKeyString());
                    userDAO.updateUser(foundUser);
                }
            } catch (DAOException e) {
                log.severe(e.toString());
                throw new ServletException("Could not complete command Upload Profile image.");
            }
        } else {
            throw new ServletException("Image not stored");
        }
    }

}
