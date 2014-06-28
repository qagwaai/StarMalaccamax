package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.View;
import com.qagwaai.starmalaccamax.client.service.action.GetLocationForNewCaptainResponse;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Slider;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ColorPickerItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public interface NewCaptainView extends View {

	/**
	 * 
	 */
	void closeWindow();

	/**
	 * move the values from the edit fields to the summary page
	 */
	void copyValues();

	/**
	 * 
	 * @param page
	 *            the page to display
	 */
	void displayPage(int page);

	/**
	 * @return the colorPicker
	 */
	ColorPickerItem getColorPicker();

	/**
	 * @return the currentPage
	 */
	int getCurrentPage();

	/**
	 * @return the defenseItem
	 */
	Slider getDefenseItem();

	/**
	 * @return the enginesItem
	 */
	Slider getEnginesItem();

	/**
	 * @return the intelligenceItem
	 */
	Slider getIntelligenceItem();

	/**
	 * @return the knowledgeItem
	 */
	Slider getKnowledgeItem();

	/**
	 * @return the lasersItem
	 */
	Slider getLasersItem();

	/**
	 * @return the missilesItem
	 */
	Slider getMissilesItem();

	/**
	 * @return the nameTextItem
	 */
	TextItem getNameTextItem();

	/**
	 * @return the navigationItem
	 */
	Slider getNavigationItem();

	/**
	 * @return the negotiationItem
	 */
	Slider getNegotiationItem();

	/**
	 * @return the nextButton
	 */
	Button getNextButton();

	/**
	 * @return the previousButton
	 */
	Button getPreviousButton();

	/**
	 * @return the repairItem
	 */
	Slider getRepairItem();

	/**
	 * @return the shieldsItem
	 */
	Slider getShieldsItem();

	/**
	 * @return the storageItem
	 */
	Slider getStorageItem();

	/**
	 * 
	 * @return the TotalPages
	 */
	int getTotalPages();

	/**
	 * @return the captainImage
	 */
	Img getCaptainImage();

	/**
	 * @return the captainImageButtonNext
	 */
	IButton getCaptainImageButtonNext();

	/**
	 * @return the captainImageButtonPrevious
	 */
	IButton getCaptainImageButtonPrevious();

	void insertLocation(GetLocationForNewCaptainResponse response);

	/**
	 * @return the nameForm
	 */
	DynamicForm getNameForm();

	/**
	 * @param nameForm
	 *            the nameForm to set
	 */
	void setNameForm(DynamicForm nameForm);

	/**
	 * @return the totalSkillAvailable
	 */
	Label getTotalSkillAvailable();

	/**
	 * @return the totalAttributeAvailable
	 */
	Label getTotalAttributeAvailable();

	/**
	 * @return the genderGroupItem
	 */
	RadioGroupItem getGenderGroupItem();

	/**
	 * @return the raceGroupItem
	 */
	RadioGroupItem getRaceGroupItem();

	/**
	 * @return the shipNameItem
	 */
	TextItem getShipNameItem();

	/**
	 * @return the shipLocation
	 */
	LocationDTO getShipLocation();

}