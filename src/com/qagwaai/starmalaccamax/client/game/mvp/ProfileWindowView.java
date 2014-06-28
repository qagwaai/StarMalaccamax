package com.qagwaai.starmalaccamax.client.game.mvp;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.qagwaai.starmalaccamax.client.core.mvp.View;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.DateTimeItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.ResetItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public interface ProfileWindowView extends View {

	void closeWindow();
	/**
	 * @return the emailItem
	 */
	TextItem getEmailItem();

	/**
	 * @return the isNewItem
	 */
	CheckboxItem getIsNewItem();

	/**
	 * @return the usernameItem
	 */
	TextItem getUsernameItem();

	/**
	 * @return the saveItem
	 */
	SubmitItem getSaveItem();

	/**
	 * @return the regionItem
	 */
	SelectItem getRegionItem();

	/**
	 * @return the lastLoggedIn
	 */
	DateTimeItem getLastLoggedIn();

	/**
	 * @return the resetItem
	 */
	ResetItem getResetItem();

	/**
	 * @return the isAdminItem
	 */
	CheckboxItem getIsAdminItem();

	/**
	 * @return the timezoneItem
	 */
	SelectItem getTimezoneItem();

	/**
	 * @return the isNPCItem
	 */
	CheckboxItem getIsNPCItem();

	/**
	 * @return the ratingItem
	 */
	IntegerItem getRatingItem();

	/**
	 * @return the uploadForm
	 */
	FormPanel getUploadForm();

	/**
	 * @return the uploadButton
	 */
	Button getUploadButton();

	/**
	 * @return the uploadPanel
	 */
	HorizontalPanel getUploadPanel();

	/**
	 * @return the profileImage
	 */
	Img getProfileImage();

	/**
	 * @return the isUserActive
	 */
	CheckboxItem getIsUserActive();

}