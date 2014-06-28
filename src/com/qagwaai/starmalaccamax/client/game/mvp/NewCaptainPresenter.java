package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.Presenter;
import com.qagwaai.starmalaccamax.shared.model.Captain;

public interface NewCaptainPresenter extends Presenter<NewCaptainView, Captain> {

	int getCaptainImage();

	void setCaptainImage(int captainImage);

	/**
	 * 
	 */
	void saveNewCaptain();

	/**
	 * 
	 * @return sum of all points spent
	 */
	int sumAttributesSpent();

	/**
	 * 
	 * @return sum of all points spent
	 */
	int sumSkillsSpent();

}