package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.View;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.form.DynamicForm;

public interface GameEventWindowView extends View {

	/**
	 * @return the closeButton
	 */
	IButton getCloseButton();

	/**
	 * @return the form
	 */
	DynamicForm getForm();

	/**
	 * @return the saveButton
	 */
	IButton getSaveButton();

	/**
	 * 
	 * @param windowTitle
	 *            the title of the window
	 */
	void setTitle(String windowTitle);

}