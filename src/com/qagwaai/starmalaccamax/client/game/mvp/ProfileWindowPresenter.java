package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.Presenter;
import com.qagwaai.starmalaccamax.client.event.CurrentUserChangedEvent;
import com.qagwaai.starmalaccamax.shared.model.User;

public interface ProfileWindowPresenter extends Presenter<ProfileWindowView, User> {

	/**
	 * 
	 * {@inheritDoc}
	 */
	void onCurrentUserChanged(CurrentUserChangedEvent event);

	/**
	 * 
	 * @return if the user is flagged admin
	 */
	boolean isCurrentUserAdmin();

}