/**
 * ModelTest.java
 * Created by pgirard at 1:57:30 PM on Aug 11, 2010
 * in the com.qagwaai.starmalaccamax.server package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao.twig;

import java.util.ArrayList;
import java.util.Date;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.qagwaai.starmalaccamax.client.Application;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.JobDAO;
import com.qagwaai.starmalaccamax.shared.model.Job;
import com.qagwaai.starmalaccamax.shared.model.JobDTO;

/**
 * @author pgirard
 * 
 */
public final class JobDataTest {
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
	private void addLotsOfRecords(final JobDAO dao) throws Exception {
		for (int i = 0; i < 100; i++) {
			JobDTO ss = new JobDTO();
			Assert.assertNotNull(ss);
			ss.setLastRun(new Date(System.currentTimeMillis()));
			dao.addJob(ss);
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
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		JobDAO dao = factory.getJobDAO();
		Assert.assertNotNull(dao);

		JobDTO ss = new JobDTO();
		Assert.assertNotNull(ss);
		ss.setLastRun(new Date(System.currentTimeMillis()));

		Job addedSS = dao.addJob(ss);
		Assert.assertNotNull(addedSS);

		Job foundSS = dao.getJob(addedSS.getId());
		Assert.assertNotNull(foundSS);
		Assert.assertEquals(foundSS.getId(), addedSS.getId());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testGetJobs() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		JobDAO dao = factory.getJobDAO();
		Assert.assertNotNull(dao);

		JobDTO ss = new JobDTO();
		Assert.assertNotNull(ss);
		ss.setLastRun(new Date(System.currentTimeMillis()));
		JobDTO ss2 = new JobDTO();
		Assert.assertNotNull(ss2);
		ss2.setLastRun(new Date(System.currentTimeMillis()));

		Job addedSS = dao.addJob(ss);
		Assert.assertNotNull(addedSS);
		Job added2SS = dao.addJob(ss2);
		Assert.assertNotNull(added2SS);

		ArrayList<JobDTO> found = dao.getAllJobs(0, 15, "");

		Assert.assertEquals(2, found.size());
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testLastVisitedSort() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		JobDAO dao = factory.getJobDAO();
		Assert.assertNotNull(dao);
		addLotsOfRecords(dao);

		ArrayList<JobDTO> found = dao.getAllJobs(0, 2000, "-lastRun");
		Assert.assertNotSame(1L, found.get(0).getId());

	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testRemove() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		JobDAO dao = factory.getJobDAO();
		Assert.assertNotNull(dao);

		JobDTO ss = new JobDTO();
		Assert.assertNotNull(ss);
		ss.setLastRun(new Date(System.currentTimeMillis()));

		Job addedSS = dao.addJob(ss);
		Assert.assertNotNull(addedSS);

		boolean removed = dao.removeJob(ss);

		Assert.assertTrue(removed);
	}

	/**
	 * 
	 * @throws Exception
	 *             if the test fails
	 */
	@Test
	public void testTotal() throws Exception {
		DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
		JobDAO dao = factory.getJobDAO();
		Assert.assertNotNull(dao);

		JobDTO ss = new JobDTO();
		Assert.assertNotNull(ss);
		ss.setLastRun(new Date(System.currentTimeMillis()));
		JobDTO ss2 = new JobDTO();
		Assert.assertNotNull(ss2);
		ss2.setLastRun(new Date(System.currentTimeMillis()));

		Job addedSS = dao.addJob(ss);
		Assert.assertNotNull(addedSS);
		Job added2SS = dao.addJob(ss2);
		Assert.assertNotNull(added2SS);

		int total = dao.getTotalJobs();
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
		JobDAO dao = factory.getJobDAO();
		Assert.assertNotNull(dao);

		JobDTO ss = new JobDTO();
		Assert.assertNotNull(ss);
		ss.setLastRun(new Date(System.currentTimeMillis()));

		Job addedSS = dao.addJob(ss);
		Assert.assertNotNull(addedSS);

		ss.setLastRun(new Date(System.currentTimeMillis()));
		Job updatedSS = dao.updateJob(ss);
		Assert.assertEquals(addedSS.getLastRun(), updatedSS.getLastRun());
	}
}
