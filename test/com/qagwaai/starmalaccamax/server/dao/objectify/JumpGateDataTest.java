/**
 * ModelTest.java
 * Created by pgirard at 1:57:30 PM on Aug 11, 2010
 * in the com.qagwaai.starmalaccamax.server package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao.objectify;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.qagwaai.starmalaccamax.server.dao.JumpGateDAO;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.shared.model.JumpGate;
import com.qagwaai.starmalaccamax.shared.model.JumpGateDTO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

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
			ss.setSolarSystem1Id(Long.valueOf(ownerId++));
			Assert.assertNotNull(ss);
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
		Assert.assertNotNull(dao);

		JumpGateDTO ss = new JumpGateDTO();
		Assert.assertNotNull(ss);
		ss.setSolarSystem1Id(Long.valueOf(1));

		JumpGate addedSS = dao.addJumpGate(ss);
		Assert.assertNotNull(addedSS);

		JumpGate foundSS = dao.getJumpGate(addedSS.getId());
		Assert.assertNotNull(foundSS);
		Assert.assertNotNull(addedSS.getId());
		Assert.assertEquals(foundSS.getId(), addedSS.getId());
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
		Assert.assertNotNull(dao);

		JumpGateDTO ss = new JumpGateDTO();
		Assert.assertNotNull(ss);
		ss.setSolarSystem1Id(Long.valueOf(1));
		JumpGateDTO ss2 = new JumpGateDTO();
		Assert.assertNotNull(ss2);
		ss2.setSolarSystem1Id(Long.valueOf(2));

		JumpGate addedSS = dao.addJumpGate(ss);
		Assert.assertNotNull(addedSS);
		JumpGate added2SS = dao.addJumpGate(ss2);
		Assert.assertNotNull(added2SS);

		ArrayList<JumpGateDTO> found = dao.getAllJumpGates(0, 15, "");

		Assert.assertEquals(2, found.size());
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
		Assert.assertNotNull(dao);
		addLotsOfRecords(dao);

		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("solarSystem1Id");
		filter.setValue("10");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<JumpGateDTO> found = dao.getAllJumpGates(0, 2000, filterAll, "solarSystem1Id");
		Assert.assertEquals(found.get(0).getSolarSystem1Id(), Long.valueOf(50));
		Assert.assertTrue(found.size() > 1);
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
		Assert.assertNotNull(dao);
		addLotsOfRecords(dao);

		ArrayList<JumpGateDTO> found = dao.getAllJumpGates(0, 2000, "-solarSystem1Id");
		Assert.assertNotSame(1L, found.get(0).getId());

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
		Assert.assertNotNull(dao);

		JumpGateDTO ss = new JumpGateDTO();
		Assert.assertNotNull(ss);
		ss.setSolarSystem1Id(Long.valueOf(50));

		JumpGate addedSS = dao.addJumpGate(ss);
		Assert.assertNotNull(addedSS);

		boolean removed = dao.removeJumpGate(ss);

		Assert.assertTrue(removed);
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
		Assert.assertNotNull(dao);

		JumpGateDTO ss = new JumpGateDTO();
		Assert.assertNotNull(ss);
		ss.setSolarSystem1Id(Long.valueOf(50));
		JumpGateDTO ss2 = new JumpGateDTO();
		Assert.assertNotNull(ss2);
		ss2.setSolarSystem1Id(Long.valueOf(50));

		JumpGate addedSS = dao.addJumpGate(ss);
		Assert.assertNotNull(addedSS);
		JumpGate added2SS = dao.addJumpGate(ss2);
		Assert.assertNotNull(added2SS);

		int total = dao.getTotalJumpGates();
		Assert.assertEquals(2, total);
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
		Assert.assertNotNull(dao);

		JumpGateDTO ss = new JumpGateDTO();
		Assert.assertNotNull(ss);
		ss.setSolarSystem1Id(Long.valueOf(50));

		JumpGate addedSS = dao.addJumpGate(ss);
		Assert.assertNotNull(addedSS);

		ss.setSolarSystem1Id(Long.valueOf(55));
		JumpGate updatedSS = dao.updateJumpGate(ss);
		Assert.assertEquals(addedSS.getSolarSystem1Id(), updatedSS.getSolarSystem1Id());
		Assert.assertNotNull(updatedSS.getId());
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
		Assert.assertNotNull(dao);
		addLotsOfRecords(dao);

		int total = dao.getTotalJumpGates();

		ArrayList<JumpGateDTO> found = dao.getAllJumpGates();

		Assert.assertEquals(total, found.size());
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
		Assert.assertNotNull(dao);
		addLotsOfRecords(dao);

		int total = dao.getTotalJumpGates();
		dao.deleteAllJumpGates();

		ArrayList<JumpGateDTO> found = dao.getAllJumpGates();

		Assert.assertEquals(0, found.size());
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
		Assert.assertNotNull(dao);
		addLotsOfRecords(dao);

		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("solarSystem1Id");
		filter.setValue("50");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<JumpGateDTO> found = dao.getAllJumpGates(0, 2000, filterAll, "solarSystem1Id");
		Assert.assertEquals(found.get(0).getSolarSystem1Id(), Long.valueOf(50));
		Assert.assertEquals(found.size(), 1);
		
		int foundCount = dao.getTotalJumpGatesWithFilter(filterAll);
		
		Assert.assertEquals(found.size(), foundCount);
	}

}
