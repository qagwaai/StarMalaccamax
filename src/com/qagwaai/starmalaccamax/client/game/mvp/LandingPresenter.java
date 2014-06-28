package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.Presenter;
import com.qagwaai.starmalaccamax.client.event.CurrentUserChangedEvent;
import com.qagwaai.starmalaccamax.shared.model.User;

public interface LandingPresenter extends Presenter<LandingView, User> {

	/**
	 * 
	 * {@inheritDoc}
	 */
	void onCurrentUserChanged(CurrentUserChangedEvent event);

}