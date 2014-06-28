package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.View;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;
import com.smartgwt.client.widgets.Button;

public interface LandingView extends View {

	/**
	 * @return the gotoPlayerSummaryPage
	 */
	Button getGotoPlayerSummaryPage();

	/**
	 * @return the startWizard
	 */
	Button getStartWizard();

	/**
	 * 
	 * @param user
	 *            the user found from the login check
	 */
	void gotUser(UserDTO user);

}