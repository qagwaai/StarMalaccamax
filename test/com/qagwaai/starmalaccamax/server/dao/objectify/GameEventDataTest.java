/**
 * ModelTest.java
 * Created by pgirard at 1:57:30 PM on Aug 11, 2010
 * in the com.qagwaai.gameEventmalaccamax.server package
 * for the GameEventMalaccamax project
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
import com.qagwaai.starmalaccamax.server.dao.GameEventDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.GameEvent;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;

/**
 * @author pgirard
 * 
 */
public final class GameEventDataTest {
	
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
	private void addLotsOfRecords(final GameEventDAO dao) throws Exception {
		int ownerId = 0;
		for (int i = 0; i < 100; i++) {
			GameEventDTO gameEvent = new GameEventDTO();
			gameEvent.setPlayerId(Long.valueOf(ownerId++));
			gameEvent.setEventEnabled(((ownerId & 1) == 0));
			gameEvent.setEndDateTime(new Date(System.currentTimeMillis()));
			assertNotNull(gameEvent);
			dao.addGameEvent(gameEvent);
			if (ownerId > 15) {
				ownerId = 0;
			}
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
		GameEventDAO dao = factory.getGameEventDAO();
		assertNotNull(dao);

		GameEventDTO gameEvent = new GameEventDTO();
		assertNotNull(gameEvent);
		gameEvent.setPlayerId(Long.valueOf(1));

		GameEvent addedGameEvent = dao.addGameEvent(gameEvent);
		assertNotNull(addedGameEvent);

		GameEvent foundGameEvent = dao.getGameEvent(addedGameEvent.getId());
		assertNotNull(foundGameEvent);
		assertNotNull(addedGameEvent.getId());
		assertEquals(foundGameEvent.getId(), addedGameEvent.getId());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetGameEvents() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		GameEventDAO dao = factory.getGameEventDAO();
		assertNotNull(dao);

		GameEventDTO gameEvent = new GameEventDTO();
		assertNotNull(gameEvent);
		gameEvent.setPlayerId(Long.valueOf(1));
		GameEventDTO gameEvent2 = new GameEventDTO();
		assertNotNull(gameEvent2);
		gameEvent2.setPlayerId(Long.valueOf(2));

		GameEvent addedSS = dao.addGameEvent(gameEvent);
		assertNotNull(addedSS);
		GameEvent added2SS = dao.addGameEvent(gameEvent2);
		assertNotNull(added2SS);

		ArrayList<GameEventDTO> found = dao.getAllGameEvents(0, 15, "");

		assertEquals(2, found.size());
	}

	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testFilter() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		GameEventDAO dao = factory.getGameEventDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("playerId");
		filter.setValue("10");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<GameEventDTO> found = dao.getAllGameEvents(0, 2000, filterAll, "playerId");
		assertTrue("Could not find any records on sort", found.size() > 0);
		assertEquals(found.get(0).getPlayerId(), Long.valueOf(10));
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
		GameEventDAO dao = factory.getGameEventDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		ArrayList<GameEventDTO> found = dao.getAllGameEvents(0, 2000, "-playerId");
		//ArrayList<GameEventDTO> found = dao.getAllGameEvents();
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
		GameEventDAO dao = factory.getGameEventDAO();
		assertNotNull(dao);

		GameEventDTO gameEvent = new GameEventDTO();
		assertNotNull(gameEvent);
		gameEvent.setPlayerId(Long.valueOf(50));

		GameEvent addedSS = dao.addGameEvent(gameEvent);
		assertNotNull(addedSS);

		boolean removed = dao.removeGameEvent(gameEvent);

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
		GameEventDAO dao = factory.getGameEventDAO();
		assertNotNull(dao);

		GameEventDTO gameEvent = new GameEventDTO();
		assertNotNull(gameEvent);
		gameEvent.setPlayerId(Long.valueOf(50));
		GameEventDTO gameEvent2 = new GameEventDTO();
		assertNotNull(gameEvent2);
		gameEvent2.setPlayerId(Long.valueOf(50));

		GameEvent addedSS = dao.addGameEvent(gameEvent);
		assertNotNull(addedSS);
		GameEvent added2SS = dao.addGameEvent(gameEvent2);
		assertNotNull(added2SS);

		int total = dao.getTotalGameEvents();
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
		GameEventDAO dao = factory.getGameEventDAO();
		assertNotNull(dao);

		GameEventDTO gameEvent = new GameEventDTO();
		assertNotNull(gameEvent);
		gameEvent.setPlayerId(Long.valueOf(50));

		GameEvent addedSS = dao.addGameEvent(gameEvent);
		assertNotNull(addedSS);

		gameEvent.setPlayerId(Long.valueOf(55));
		GameEvent updatedGameEvent = dao.updateGameEvent(gameEvent);
		assertEquals(addedSS.getPlayerId(), updatedGameEvent.getPlayerId());
		assertNotNull(updatedGameEvent.getId());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetAllGameEvents() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		GameEventDAO dao = factory.getGameEventDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		int total = dao.getTotalGameEvents();

		ArrayList<GameEventDTO> found = dao.getAllGameEvents();

		assertEquals(total, found.size());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testDeleteAllGameEvents() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		GameEventDAO dao = factory.getGameEventDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		int total = dao.getTotalGameEvents();
		assertTrue(total > 0);
		dao.deleteAllGameEvents();

		ArrayList<GameEventDTO> found = dao.getAllGameEvents();

		assertEquals(0, found.size());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetTotalGameEventsWithFilter() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		GameEventDAO dao = factory.getGameEventDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("playerId");
		filter.setValue("5");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<GameEventDTO> found = dao.getAllGameEvents(0, 2000, filterAll, "playerId");
		assertTrue("Could not find any records on sort", found.size() > 0);
		assertEquals(found.get(0).getPlayerId(), Long.valueOf(5));
		assertTrue((found.size() > 0));
		
		int foundCount = dao.getTotalGameEventsWithFilter(filterAll);
		
		assertEquals(found.size(), foundCount);
	}

	
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetTotalGameEventsWithAllFilters() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		GameEventDAO dao = factory.getGameEventDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		Reflections reflections = 
				new Reflections(new ConfigurationBuilder()
					.setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
					.setUrls(ClasspathHelper.forPackage("com.qagwaai.starmalaccamax.shared.model"))
					.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("com.qagwaai.starmalaccamax.shared.model.GameEventDTO"))));
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
				if (field.getName().equals("eventEnabled")) {
					filter.setValue("true");
				} else if (field.getName().equals("endDateTime")) {
					filter.setValue(new Date(System.currentTimeMillis()).toString());
				} else {
					filter.setValue("5");
				}
				ArrayList<Filter> filterAll = new ArrayList<Filter>();
				filterAll.add(filter);

				ArrayList<GameEventDTO> found = null;
				if (field.getName().equals("id")) {
					found = dao.getAllGameEvents(0, 2000, filterAll, "__key__");
				} else {
					found = dao.getAllGameEvents(0, 2000, filterAll, field.getName());
				}
				
				Method getValueMethod = clazz.getMethod(GameEventDTO.getFieldGetter(field.getName()), null);
				Object fieldObject = getValueMethod.invoke(found.get(0), null);
				//Object fieldObject = field.get(found.get(0));
				if (field.getName().equals("eventEnabled")) {
					assertEquals(field.getName() + " search does not exist", true, fieldObject);
				} else if (field.getName().equals("endDateTime")) {
					assertEquals(field.getName() + " search does not exist", new Date(System.currentTimeMillis()), fieldObject);
				} else {
					assertEquals(field.getName() + " search does not exist", Long.valueOf(5), fieldObject);
				}
				
				//assertTrue((found.size() == 1));
				
				int foundCount = dao.getTotalGameEventsWithFilter(filterAll);
				
				assertEquals(found.size(), foundCount);				
			}
		}

	}
}
