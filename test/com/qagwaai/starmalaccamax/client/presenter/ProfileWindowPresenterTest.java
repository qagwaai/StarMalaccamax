/**
 * ProfileWindowPresenterTest.java
 * Created by pgirard at 12:31:31 PM on Aug 27, 2010
 * in the com.qagwaai.starmalaccamax.client.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.presenter;

import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.qagwaai.starmalaccamax.client.event.CurrentUserChangedEvent;
import com.qagwaai.starmalaccamax.client.event.DefaultEventBus;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowViewImpl;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public final class ProfileWindowPresenterTest {
	/**
	 * 
	 */
	private ProfileWindowPresenterImpl presenter;

	/**
	 * @throws Exception
	 *             if the environment cannot be setup
	 */
	@Before
	public void setUp() throws Exception {
		ProfileWindowViewImpl mockView = mock(ProfileWindowViewImpl.class);

		EventBus eventBus = new DefaultEventBus();
		presenter = new ProfileWindowPresenterImpl(eventBus, mockView, new UserDTO());
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
	 * {@link com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowPresenterImpl#onCurrentUserChanged (com.qagwaai.starmalaccamax.client.event.CurrentUserChangedEvent)}.
	 */
	@Test
	public void testOnCurrentUserChanged() {
		UserDTO user = new UserDTO();
		user.setEmail("email@email.com");

		CurrentUserChangedEvent event = new CurrentUserChangedEvent(user, "loginurl", "logouturl");

		presenter.onCurrentUserChanged(event);
	}

	/**
	 * Test method for {@link com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowPresenterImpl#renderView()}.
	 */
	@Test
	public void testRenderView() {
		presenter.renderView();
	}

}
