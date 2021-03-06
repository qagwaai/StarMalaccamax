/**
 * ModelTest.java
 * Created by pgirard at 1:57:30 PM on Aug 11, 2010
 * in the com.qagwaai.starmalaccamax.server package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao.objectify;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.reflections.ReflectionUtils.getAllFields;
import static org.reflections.ReflectionUtils.withAnnotation;
import static org.reflections.ReflectionUtils.withModifier;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.MarketDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.Market;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;

/**
 * @author pgirard
 * 
 */
public final class MarketDataTest {
	
	/**
	 * 
	 */
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalUserServiceTestConfig(), new LocalDatastoreServiceTestConfig()).setEnvIsAdmin(true)
			.setEnvIsLoggedIn(true);

	/**
	 * 
	 * @param dao
	 *            where to add the records
	 * @throws Exception
	 *             if the add doesn' t work
	 */
	private void addLotsOfRecords(final MarketDAO dao) throws Exception {
		int ownerId = 0;
		for (int i = 0; i < 100; i++) {
			MarketDTO market = new MarketDTO();
			market.setPlanetId(Long.valueOf(ownerId++));
			market.setLastVisited(new Date(System.currentTimeMillis()));
			assertNotNull(market);
			dao.addMarket(market);
			//if (ownerId > 15) {
			//	ownerId = 0;
			//}
		}
	}

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
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		MarketDAO dao = factory.getMarketDAO();
		assertNotNull(dao);

		MarketDTO ss = new MarketDTO();
		assertNotNull(ss);
		ss.setPlanetId(Long.valueOf(1));

		Market addedSS = dao.addMarket(ss);
		assertNotNull(addedSS);

		Market foundSS = dao.getMarket(addedSS.getId());
		assertNotNull(foundSS);
		assertNotNull(addedSS.getId());
		assertEquals(foundSS.getId(), addedSS.getId());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetMarkets() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		MarketDAO dao = factory.getMarketDAO();
		assertNotNull(dao);

		MarketDTO ss = new MarketDTO();
		assertNotNull(ss);
		ss.setPlanetId(Long.valueOf(1));
		MarketDTO ss2 = new MarketDTO();
		assertNotNull(ss2);
		ss2.setPlanetId(Long.valueOf(2));

		Market addedSS = dao.addMarket(ss);
		assertNotNull(addedSS);
		Market added2SS = dao.addMarket(ss2);
		assertNotNull(added2SS);

		ArrayList<MarketDTO> found = dao.getAllMarkets(0, 15, "");

		assertEquals(2, found.size());
	}

	@Test
	public void testGetMarketForPlanet() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		MarketDAO dao = factory.getMarketDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);
		
		PlanetDTO planet = new PlanetDTO();
		planet.setId(Long.valueOf(5));

		MarketDTO found =  dao.getMarketForPlanet(planet);
		
		assertNotNull(found);
	}

	@Test
	public void testGetMarketsForPlanets() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		MarketDAO dao = factory.getMarketDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);
		
		ArrayList<Long> planetIds = new ArrayList<Long>();
		planetIds.add(5L);
		planetIds.add(11L);

		ArrayList<MarketDTO> found =  dao.getMarketsForPlanets(planetIds);
		
		assertNotNull(found);
		assertTrue(found.size() == 2);
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testFilter() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		MarketDAO dao = factory.getMarketDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("planetId");
		filter.setValue("10");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<MarketDTO> found = dao.getAllMarkets(0, 2000, filterAll, "planetId");
		assertTrue("Could not find any records on sort", found.size() > 0);
		assertEquals(found.get(0).getPlanetId(), Long.valueOf(10));
		assertTrue(found.size() > 0);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testSort() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		MarketDAO dao = factory.getMarketDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		ArrayList<MarketDTO> found = dao.getAllMarkets(0, 2000, "-planetId");
		//ArrayList<MarketDTO> found = dao.getAllMarkets();
		assertTrue("Could not find any records on sort", found.size() > 0);
		assertNotSame(1L, found.get(0).getId());

	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testRemove() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		MarketDAO dao = factory.getMarketDAO();
		assertNotNull(dao);

		MarketDTO ss = new MarketDTO();
		assertNotNull(ss);
		ss.setPlanetId(Long.valueOf(50));

		Market addedSS = dao.addMarket(ss);
		assertNotNull(addedSS);

		boolean removed = dao.removeMarket(ss);

		assertTrue(removed);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testTotal() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		MarketDAO dao = factory.getMarketDAO();
		assertNotNull(dao);

		MarketDTO ss = new MarketDTO();
		assertNotNull(ss);
		ss.setPlanetId(Long.valueOf(50));
		MarketDTO ss2 = new MarketDTO();
		assertNotNull(ss2);
		ss2.setPlanetId(Long.valueOf(50));

		Market addedSS = dao.addMarket(ss);
		assertNotNull(addedSS);
		Market added2SS = dao.addMarket(ss2);
		assertNotNull(added2SS);

		int total = dao.getTotalMarkets();
		assertEquals(2, total);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testUpdate() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		MarketDAO dao = factory.getMarketDAO();
		assertNotNull(dao);

		MarketDTO ss = new MarketDTO();
		assertNotNull(ss);
		ss.setPlanetId(Long.valueOf(50));

		Market addedSS = dao.addMarket(ss);
		assertNotNull(addedSS);

		ss.setPlanetId(Long.valueOf(55));
		Market updatedSS = dao.updateMarket(ss);
		assertEquals(addedSS.getPlanetId(), updatedSS.getPlanetId());
		assertNotNull(updatedSS.getId());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetAllMarkets() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		MarketDAO dao = factory.getMarketDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		int total = dao.getTotalMarkets();

		ArrayList<MarketDTO> found = dao.getAllMarkets();

		assertEquals(total, found.size());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testDeleteAllMarkets() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		MarketDAO dao = factory.getMarketDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		int total = dao.getTotalMarkets();
		dao.deleteAllMarkets();

		ArrayList<MarketDTO> found = dao.getAllMarkets();

		assertEquals(0, found.size());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetTotalMarketsWithFilter() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		MarketDAO dao = factory.getMarketDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("planetId");
		filter.setValue("5");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<MarketDTO> found = dao.getAllMarkets(0, 2000, filterAll, "planetId");
		assertTrue("Could not find any records on sort", found.size() > 0);
		assertEquals(found.get(0).getPlanetId(), Long.valueOf(5));
		assertTrue((found.size() > 0));
		
		int foundCount = dao.getTotalMarketsWithFilter(filterAll);
		
		assertEquals(found.size(), foundCount);
	}

	
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetTotalMarketsWithAllFilters() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		MarketDAO dao = factory.getMarketDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		Reflections reflections = 
				new Reflections(new ConfigurationBuilder()
					.setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
					.setUrls(ClasspathHelper.forPackage("com.qagwaai.starmalaccamax.shared.model"))
					.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("com.qagwaai.starmalaccamax.shared.model.MarketDTO"))));
		Set<Class<? extends Object>> classes = reflections.getSubTypesOf(Object.class);
		ArrayList<Class<? extends Object>> sortedList = new ArrayList<Class<? extends Object>>(classes);
		Collections.sort(sortedList, new Comparator<Class<? extends Object>>(){

			@Override
			public int compare(Class<? extends Object> o1,
					Class<? extends Object> o2) {
				String str1 = o1.getName();
				String str2 = o2.getName();
				int x = String.CASE_INSENSITIVE_ORDER.compare(str1, str2);
				if (x == 0) {
					x = str1.compareTo(str2);
				}
				return x;
			}});
		
		for (Class clazz : sortedList) {
			Set<Field> fields = getAllFields(clazz, withModifier(Modifier.PRIVATE), withAnnotation(com.googlecode.objectify.annotation.Index.class));
			for (Field field : fields) {
				
				Class returnType = field.getType();
				if (returnType.isPrimitive() || returnType.getName().equalsIgnoreCase("java.lang.String") || returnType.getName().equalsIgnoreCase("java.lang.Long")) {
					// continue
				} else {
					// next
					continue;
				}
				SimpleFilterItem filter = new SimpleFilterItem();
				filter.setField(field.getName());
				filter.setValue("5");
				ArrayList<Filter> filterAll = new ArrayList<Filter>();
				filterAll.add(filter);

				ArrayList<MarketDTO> found = null;
				if (field.getName().equals("id")) {
					found = dao.getAllMarkets(0, 2000, filterAll, "__key__");
				} else {
					found = dao.getAllMarkets(0, 2000, filterAll, field.getName());
				}
				
				Method getValueMethod = clazz.getMethod(MarketDTO.getFieldGetter(field.getName()), null);
				Object fieldObject = getValueMethod.invoke(found.get(0), null);
				//Object fieldObject = field.get(found.get(0));
				assertEquals(field.getName() + " search does not exist", Long.valueOf(5), fieldObject);
				//assertTrue((found.size() == 1));
				
				int foundCount = dao.getTotalMarketsWithFilter(filterAll);
				
				assertEquals(found.size(), foundCount);				
			}
		}

	}
}
