/**
 * ModelTest.java
 * Created by pgirard at 1:57:30 PM on Aug 11, 2010
 * in the com.qagwaai.starmalaccamax.server package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.google.code.twig.ObjectDatastore;
import com.google.code.twig.annotation.AnnotationObjectDatastore;
import com.google.code.twig.annotation.Id;
import com.qagwaai.starmalaccamax.shared.model.GameEventTest;
import com.qagwaai.starmalaccamax.shared.model.GameEventTestDTO;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;
import com.qagwaai.starmalaccamax.shared.model.MarketCommodity;
import com.qagwaai.starmalaccamax.shared.model.gameevent.ShipLaunchActivity;

/**
 * @author pgirard
 * 
 */
public final class SandboxPolyDataTest {
	/**
	 * 
	 * @author pgirard
	 * 
	 */
	@SuppressWarnings("unused")
	private static class TestMarket {
		/**
		 * 
		 */
		@Id
		private Long id;
		/**
		 * 
		 */
		private Map<String, MarketCommodity> commodities = new HashMap<String, MarketCommodity>();

		/**
		 * 
		 * @return a
		 */
		public Map<String, MarketCommodity> getCommodities() {
			return commodities;
		}

		/**
		 * 
		 * @return a
		 */
		public Long getId() {
			return id;
		}

		/**
		 * 
		 * @param commodities a
		 */
		public void setCommodities(final Map<String, MarketCommodity> commodities) {
			this.commodities = commodities;
		}

		/**
		 * 
		 * @param id a
		 */
		public void setId(final Long id) {
			this.id = id;
		}
	}

	/**
	 * 
	 */
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalUserServiceTestConfig(), new LocalDatastoreServiceTestConfig()).setEnvIsAdmin(true)
			.setEnvIsLoggedIn(true);

	/**
	 * @throws Exception
	 *             if the environment cannot be setup
	 */
	@Before
	public void setUp() throws Exception {
		helper.setUp();

	}

	/**
	 * @throws Exception
	 *             if the environment cannot be torn down
	 */
	@After
	public void tearDown() throws Exception {
		helper.tearDown();

	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testCreateAndLoad() throws Exception {
		ObjectDatastore datastore = new AnnotationObjectDatastore();
		Assert.assertNotNull(datastore);

		GameEventTest get = new GameEventTestDTO();
		get.setEndDateTime(new Date(System.currentTimeMillis()));
		get.setStartDateTime(new Date(System.currentTimeMillis()));

		ShipLaunchActivity shipLaunch = new ShipLaunchActivity();
		shipLaunch.setDestination(new LocationDTO(1L, 0L, 5L, 5L, 5L));
		shipLaunch.setShipId(1L);
		get.setActivity(shipLaunch);

		Key key = datastore.store(get);
		Long id = key.getId();
		Assert.assertNotNull(id);

		GameEventTest foundGe = datastore.load(GameEventTestDTO.class, id);
		Assert.assertNotNull(foundGe);
	}
}
