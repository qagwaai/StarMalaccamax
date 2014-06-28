/**
 * ModelTest.java
 * Created by pgirard at 1:57:30 PM on Aug 11, 2010
 * in the com.qagwaai.starmalaccamax.server package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao.twig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.qagwaai.starmalaccamax.client.Application;
import com.qagwaai.starmalaccamax.client.admin.util.DelimitedToRecord;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.JumpGateDAO;
import com.qagwaai.starmalaccamax.shared.TransformationException;
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

	private static final long TESTID = -10L;
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
	public void testBulkLoad() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		JumpGateDAO dao = factory.getJumpGateDAO();
		Assert.assertNotNull(dao);

		URL batch = this.getClass().getResource("/Top_100_JumpGate_Query.txt");
		File batchFile = new File(batch.toURI());
		FileReader fr = new FileReader(batchFile);
		BufferedReader br = new BufferedReader(fr);
		String strLine;
		ArrayList<JumpGateDTO> cache = new ArrayList<JumpGateDTO>();
		while ((strLine = br.readLine()) != null) {
			try {
				cache.add(DelimitedToRecord.toJumpGate(strLine));
			} catch (TransformationException e) {
				System.out.println(e);
			}
		}
		br.close();
		fr.close();

		dao.bulkAddJumpGate(cache);

		ArrayList<JumpGateDTO> found = dao.getAllJumpGates(0, 2000, "");

		Assert.assertEquals(cache.size(), found.size());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testCreateAndLoad() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		JumpGateDAO dao = factory.getJumpGateDAO();
		Assert.assertNotNull(dao);

		JumpGateDTO ss = new JumpGateDTO();
		Assert.assertNotNull(ss);
		ss.setSolarSystem1Id(-1L);

		JumpGate addedSS = dao.addJumpGate(ss);
		Assert.assertNotNull(addedSS);

		JumpGate foundSS = dao.getJumpGate(addedSS.getId());
		Assert.assertNotNull(foundSS);
		Assert.assertEquals(foundSS.getId(), addedSS.getId());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetJumpGates() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		JumpGateDAO dao = factory.getJumpGateDAO();
		Assert.assertNotNull(dao);

		JumpGateDTO ss = new JumpGateDTO();
		Assert.assertNotNull(ss);
		ss.setSolarSystem1Id(-1L);
		JumpGateDTO ss2 = new JumpGateDTO();
		Assert.assertNotNull(ss2);
		ss2.setSolarSystem1Id(-1L);

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
	public void testIdFilter() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		JumpGateDAO dao = factory.getJumpGateDAO();
		Assert.assertNotNull(dao);

		URL batch = this.getClass().getResource("/Top_100_JumpGate_Query.txt");
		File batchFile = new File(batch.toURI());
		FileReader fr = new FileReader(batchFile);
		BufferedReader br = new BufferedReader(fr);
		String strLine;
		ArrayList<JumpGateDTO> cache = new ArrayList<JumpGateDTO>();
		while ((strLine = br.readLine()) != null) {
			try {
				cache.add(DelimitedToRecord.toJumpGate(strLine));
			} catch (TransformationException e) {
				System.out.println(e);
			}
		}
		br.close();
		fr.close();

		dao.bulkAddJumpGate(cache);
		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("systemId1");
		filter.setValue("32460");
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);

		ArrayList<JumpGateDTO> found = dao.getAllJumpGates(0, 2000, filterAll, "systemId1");
		Assert.assertNotSame(cache.get(0).getId(), found.get(0).getId());
		Assert.assertEquals(found.get(0).getId(), Long.valueOf(17));
		Assert.assertEquals(found.size(), 4);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testRemove() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		JumpGateDAO dao = factory.getJumpGateDAO();
		Assert.assertNotNull(dao);

		JumpGateDTO ss = new JumpGateDTO();
		Assert.assertNotNull(ss);
		ss.setSolarSystem1Id(-1L);

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
	public void testSolarSystem1Sort() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		JumpGateDAO dao = factory.getJumpGateDAO();
		Assert.assertNotNull(dao);

		URL batch = this.getClass().getResource("/Top_100_JumpGate_Query.txt");
		File batchFile = new File(batch.toURI());
		FileReader fr = new FileReader(batchFile);
		BufferedReader br = new BufferedReader(fr);
		String strLine;
		ArrayList<JumpGateDTO> cache = new ArrayList<JumpGateDTO>();
		while ((strLine = br.readLine()) != null) {
			try {
				cache.add(DelimitedToRecord.toJumpGate(strLine));
			} catch (TransformationException e) {
				System.out.println(e);
			}
		}
		br.close();
		fr.close();

		dao.bulkAddJumpGate(cache);

		ArrayList<JumpGateDTO> found = dao.getAllJumpGates(0, 2000, "systemId1");
		Assert.assertNotSame(cache.get(0).getId(), found.get(0).getId());

	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testTotal() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		JumpGateDAO dao = factory.getJumpGateDAO();
		Assert.assertNotNull(dao);

		JumpGateDTO ss = new JumpGateDTO();
		Assert.assertNotNull(ss);
		ss.setSolarSystem1Id(-1L);
		JumpGateDTO ss2 = new JumpGateDTO();
		Assert.assertNotNull(ss2);
		ss2.setSolarSystem1Id(-1L);

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
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		JumpGateDAO dao = factory.getJumpGateDAO();
		Assert.assertNotNull(dao);

		JumpGateDTO ss = new JumpGateDTO();
		Assert.assertNotNull(ss);
		ss.setSolarSystem1Id(-1L);

		JumpGate addedSS = dao.addJumpGate(ss);
		Assert.assertNotNull(addedSS);

		ss.setSolarSystem1Id(TESTID);
		JumpGate updatedSS = dao.updateJumpGate(ss);
		Assert.assertEquals(addedSS.getSolarSystem1Id(), updatedSS.getSolarSystem1Id());
	}

}
