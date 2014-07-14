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
import com.qagwaai.starmalaccamax.server.dao.JumpGateDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.JumpGate;
import com.qagwaai.starmalaccamax.shared.model.JumpGateDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;

/**
 * @author pgirard
 * 
 */
public final class JumpGateDataTest {
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
	private void addLotsOfRecords(final JumpGateDAO dao) throws Exception {
		int ownerId = 0;
		for (int i = 0; i < 100; i++) {
			JumpGateDTO ss = new JumpGateDTO();
			ss.setSolarSystem1Id(Long.valueOf(ownerId));
			ss.setSolarSystem2Id(Long.valueOf(ownerId++));
			assertNotNull(ss);
			dao.addJumpGate(ss);
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
		JumpGateDAO dao = factory.getJumpGateDAO();
		assertNotNull(dao);

		JumpGateDTO ss = new JumpGateDTO();
		assertNotNull(ss);
		ss.setSolarSystem1Id(Long.valueOf(1));

		JumpGate addedSS = dao.addJumpGate(ss);
		assertNotNull(addedSS);

		JumpGate foundSS = dao.getJumpGate(addedSS.getId());
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
	public void testGetJumpGates() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		JumpGateDAO dao = factory.getJumpGateDAO();
		assertNotNull(dao);

		JumpGateDTO ss = new JumpGateDTO();
		assertNotNull(ss);
		ss.setSolarSystem1Id(Long.valueOf(1));
		JumpGateDTO ss2 = new JumpGateDTO();
		assertNotNull(ss2);
		ss2.setSolarSystem1Id(Long.valueOf(2));

		JumpGate addedSS = dao.addJumpGate(ss);
		assertNotNull(addedSS);
		JumpGate added2SS = dao.addJumpGate(ss2);
		assertNotNull(added2SS);

		ArrayList<JumpGateDTO> found = dao.getAllJumpGates(0, 15, "");

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
		JumpGateDAO dao = factory.getJumpGateDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("solarSystemId1");
		filter.setValue("10");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<JumpGateDTO> found = dao.getAllJumpGates(0, 2000, filterAll, "solarSystemId1");
		assertEquals(found.get(0).getSolarSystem1Id(), Long.valueOf(10));
		assertTrue(found.size() > 1);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testSort() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		JumpGateDAO dao = factory.getJumpGateDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		ArrayList<JumpGateDTO> found = dao.getAllJumpGates(0, 2000, "-solarSystemId1");
		//ArrayList<JumpGateDTO> found = dao.getAllJumpGates();
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
		JumpGateDAO dao = factory.getJumpGateDAO();
		assertNotNull(dao);

		JumpGateDTO ss = new JumpGateDTO();
		assertNotNull(ss);
		ss.setSolarSystem1Id(Long.valueOf(50));

		JumpGate addedSS = dao.addJumpGate(ss);
		assertNotNull(addedSS);

		boolean removed = dao.removeJumpGate(ss);

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
		JumpGateDAO dao = factory.getJumpGateDAO();
		assertNotNull(dao);

		JumpGateDTO ss = new JumpGateDTO();
		assertNotNull(ss);
		ss.setSolarSystem1Id(Long.valueOf(50));
		JumpGateDTO ss2 = new JumpGateDTO();
		assertNotNull(ss2);
		ss2.setSolarSystem1Id(Long.valueOf(50));

		JumpGate addedSS = dao.addJumpGate(ss);
		assertNotNull(addedSS);
		JumpGate added2SS = dao.addJumpGate(ss2);
		assertNotNull(added2SS);

		int total = dao.getTotalJumpGates();
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
		JumpGateDAO dao = factory.getJumpGateDAO();
		assertNotNull(dao);

		JumpGateDTO ss = new JumpGateDTO();
		assertNotNull(ss);
		ss.setSolarSystem1Id(Long.valueOf(50));

		JumpGate addedSS = dao.addJumpGate(ss);
		assertNotNull(addedSS);

		ss.setSolarSystem1Id(Long.valueOf(55));
		JumpGate updatedSS = dao.updateJumpGate(ss);
		assertEquals(addedSS.getSolarSystem1Id(), updatedSS.getSolarSystem1Id());
		assertNotNull(updatedSS.getId());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetAllJumpGates() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		JumpGateDAO dao = factory.getJumpGateDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		int total = dao.getTotalJumpGates();

		ArrayList<JumpGateDTO> found = dao.getAllJumpGates();

		assertEquals(total, found.size());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testDeleteAllJumpGates() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		JumpGateDAO dao = factory.getJumpGateDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		int total = dao.getTotalJumpGates();
		dao.deleteAllJumpGates();

		ArrayList<JumpGateDTO> found = dao.getAllJumpGates();

		assertEquals(0, found.size());
	}
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetTotalJumpGatesWithFilter() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		JumpGateDAO dao = factory.getJumpGateDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("solarSystemId1");
		filter.setValue("5");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<JumpGateDTO> found = dao.getAllJumpGates(0, 2000, filterAll, "solarSystemId1");
		assertEquals(found.get(0).getSolarSystem1Id(), Long.valueOf(5));
		assertTrue((found.size() > 1));
		
		int foundCount = dao.getTotalJumpGatesWithFilter(filterAll);
		
		assertEquals(found.size(), foundCount);
	}

	
	
	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetTotalJumpGatesWithAllFilters() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.OBJECTIFY);
		JumpGateDAO dao = factory.getJumpGateDAO();
		assertNotNull(dao);
		addLotsOfRecords(dao);

		Reflections reflections = 
				new Reflections(new ConfigurationBuilder()
					.setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
					.setUrls(ClasspathHelper.forPackage("com.qagwaai.starmalaccamax.shared.model"))
					.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("com.qagwaai.starmalaccamax.shared.model.JumpGateDTO"))));
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

				ArrayList<JumpGateDTO> found = null;
				if (field.getName().equals("id")) {
					found = dao.getAllJumpGates(0, 2000, filterAll, "__key__");
				} else {
					found = dao.getAllJumpGates(0, 2000, filterAll, field.getName());
				}
				
				Method getValueMethod = clazz.getMethod(JumpGateDTO.getFieldGetter(field.getName()), null);
				Object fieldObject = getValueMethod.invoke(found.get(0), null);
				//Object fieldObject = field.get(found.get(0));
				assertEquals(field.getName() + " search does not exist", Long.valueOf(5), fieldObject);
				//assertTrue((found.size() == 1));
				
				int foundCount = dao.getTotalJumpGatesWithFilter(filterAll);
				
				assertEquals(found.size(), foundCount);				
			}
		}

	}
}
