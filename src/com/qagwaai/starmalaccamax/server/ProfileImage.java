/**
 * ProfileImage.java
 * com.qagwaai.starmalaccamax.server
 * StarMalaccamax
 * Created Mar 7, 2011 at 1:50:00 PM
 */
package com.qagwaai.starmalaccamax.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class ProfileImage extends HttpServlet {
    /**
	 * 
	 */
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
        IOException {

        BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
        blobstoreService.serve(blobKey, resp);
    }

}
