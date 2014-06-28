/**
 * UserAdminPresenterTest.java
 * Created by pgirard at 8:44:30 AM on Aug 30, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.admin.presenter;

import static org.mockito.Mockito.mock;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.qagwaai.starmalaccamax.client.admin.mvp.UserAdminPresenter;
import com.qagwaai.starmalaccamax.client.admin.mvp.UserAdminPresenterImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.UserAdminViewImpl;
import com.qagwaai.starmalaccamax.client.event.DefaultEventBus;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;
import com.smartgwt.client.data.DataSource;

/**
 * @author pgirard
 * 
 */
public final class UserAdminPresenterTest {
	/**
	 * 
	 */
	private UserAdminPresenter presenter;

	/**
	 * @throws Exception
	 *             if the environment cannot be setup
	 */
	@Before
	public void setUp() throws Exception {
		UserAdminViewImpl mockView = mock(UserAdminViewImpl.class);

		EventBus eventBus = new DefaultEventBus();
		presenter = new UserAdminPresenterImpl(eventBus, mockView, new UserDTO());
	}

	/**
	 * @throws Exception
	 *             if the environment cannot be torn down
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.qagwaai.starmalaccamax.client.admin.mvp.UserAdminPresenterImpl#getDataSource()}.
	 */
	@Test
	public void testGetDataSource() {
		DataSource ds = presenter.getDataSource();

		Assert.assertNotNull(ds);
	}

	/**
	 * Test method for {@link com.qagwaai.starmalaccamax.client.admin.mvp.UserAdminPresenterImpl#renderView()}.
	 */
	@Test
	public void testRenderView() {
		presenter.renderView();
	}

}
