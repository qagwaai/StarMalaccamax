/**
 * LoginBarPresenterTest.java
 * Created by pgirard at 11:33:23 AM on Aug 27, 2010
 * in the com.qagwaai.starmalaccamax.client.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.presenter;

import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarPresenterImpl;
import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarViewImpl;
import com.qagwaai.starmalaccamax.client.event.CurrentUserChangedEvent;
import com.qagwaai.starmalaccamax.client.event.DefaultEventBus;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public final class LoginBarPresenterTest {

	/**
	 * 
	 */
	private LoginBarPresenterImpl presenter;

	/**
	 * @throws Exception
	 *             if the environment cannot be setup
	 */
	@Before
	public void setUp() throws Exception {
		LoginBarViewImpl mockView = mock(LoginBarViewImpl.class);

		EventBus eventBus = new DefaultEventBus();
		presenter = new LoginBarPresenterImpl(eventBus, mockView, new UserDTO());
	}

	/**
	 * @throws Exception
	 *             if the environment cannot be torn down
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.qagwaai.starmalaccamax.client.core.mvp.LoginBarPresenterImpl#onCurrentUserChanged (com.qagwaai.starmalaccamax.client.event.CurrentUserChangedEvent)}.
	 */
	@Test
	public void testOnCurrentUserChanged() {
		UserDTO user = new UserDTO();
		user.setEmail("email@email.com");

		CurrentUserChangedEvent event = new CurrentUserChangedEvent(user, "loginurl", "logouturl");
		presenter.onCurrentUserChanged(event);
	}

	/**
	 * Test method for {@link com.qagwaai.starmalaccamax.client.core.mvp.LoginBarPresenterImpl#renderView()}.
	 */
	@Test
	public void testRenderView() {
		presenter.renderView();
	}

}
