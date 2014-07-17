package com.qagwaai.starmalaccamax.server;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.qagwaai.starmalaccamax.server.config.Configuration;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.ShipDAO;

public class ServerStartupTest {
    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
	    new LocalUserServiceTestConfig(), 
	    new LocalDatastoreServiceTestConfig()
	    ).setEnvIsAdmin(true).setEnvIsLoggedIn(true);

    DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
    /**
     * @throws Exception if the environment cannot be setup
     */
    @Before
    public void setUp() throws Exception {
	helper.setUp();

    }

    /**
     * @throws Exception if the environment cannot be torn down
     */
    @After
    public void tearDown() throws Exception {
	helper.tearDown();

    }
    @Test
    public void test() {
	DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
	ShipDAO dao = factory.getShipDAO();
	Assert.assertNotNull(dao);
    }

}
