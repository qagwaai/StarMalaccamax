/**
 * ModelTest.java
 * Created by pgirard at 1:57:30 PM on Aug 11, 2010
 * in the com.qagwaai.starmalaccamax.server package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao;

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
import com.qagwaai.starmalaccamax.shared.model.MarketCommodity;
import com.qagwaai.starmalaccamax.shared.model.MarketCommodityDTO;

/**
 * @author pgirard
 * 
 */
public final class SandboxDataTest {
	/**
	 * 
	 * @author pgirard
	 * 
	 */
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
		@SuppressWarnings("unused")
		public Long getId() {
			return id;
		}

		/**
		 * 
		 * @param commodities a
		 */
		@SuppressWarnings("unused")
		public void setCommodities(final Map<String, MarketCommodity> commodities) {
			this.commodities = commodities;
		}

		/**
		 * 
		 * @param id a
		 */
		@SuppressWarnings("unused")
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

		TestMarket tm = new TestMarket();
		tm.getCommodities().put("Agriculture", new MarketCommodityDTO("Agriculture"));
		tm.getCommodities().put("Base Metals", new MarketCommodityDTO("Base Metals"));
		Assert.assertNotNull(tm);

		Key key = datastore.store(tm);
		Long id = key.getId();
		Assert.assertNotNull(id);

		TestMarket foundTm = datastore.load(TestMarket.class, id);
		Assert.assertNotNull(foundTm);
		Assert.assertEquals(2, foundTm.getCommodities().size());
	}
}
