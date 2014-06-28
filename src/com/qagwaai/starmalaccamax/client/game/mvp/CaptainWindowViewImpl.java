/**
 * CaptainWindowView.java
 * Created by pgirard at 2:17:48 PM on Nov 3, 2010
 * in the com.qagwaai.starmalaccamax.client.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import com.google.gwt.user.client.ui.Widget;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractView;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ColorPickerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author pgirard
 * 
 */
public class CaptainWindowViewImpl extends AbstractView<CaptainWindowPresenter> implements CaptainWindowView {
    private static final int BUTTON_BAR_MARGIN = 10;
    /**
	 * 
	 */
    private Window rootWindow = new Window();
    /**
	 * 
	 */
    private VLayout rootPanel = new VLayout();

    /**
	 * 
	 */
    private TextItem nameItem = new TextItem();

    /**
	 * 
	 */
    private ColorPickerItem colorPickerItem = new ColorPickerItem();

    /**
	 * 
	 */
    private ListGrid attributesListGrid = new ListGrid();

    /**
	 * 
	 */
    private ListGrid skillsListGrid = new ListGrid();
    /**
	 * 
	 */
    private IButton saveButton = new IButton("Save");
    /**
	 * 
	 */
    private IButton cancelButton = new IButton("Cancel");

    /**
     * {@inheritDoc}
     */
    @Override
    public final Widget asWidget() {
        return rootWindow;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.CaptainWindowView#closeWindow()
	 */
    @Override
	public final void closeWindow() {
        rootWindow.destroy();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void destroy() {
        closeWindow();

    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.CaptainWindowView#getAttributesListGrid()
	 */
    @Override
	public final ListGrid getAttributesListGrid() {
        return attributesListGrid;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.CaptainWindowView#getCancelButton()
	 */
    @Override
	public final IButton getCancelButton() {
        return cancelButton;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.CaptainWindowView#getColorPickerItem()
	 */
    @Override
	public final ColorPickerItem getColorPickerItem() {
        return colorPickerItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.CaptainWindowView#getNameItem()
	 */
    @Override
	public final TextItem getNameItem() {
        return nameItem;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Canvas getRootCanvas() {
        return rootWindow;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.CaptainWindowView#getSaveButton()
	 */
    @Override
	public final IButton getSaveButton() {
        return saveButton;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.CaptainWindowView#getSkillsListGrid()
	 */
    @Override
	public final ListGrid getSkillsListGrid() {
        return skillsListGrid;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void layout() {
        rootWindow.setIsModal(true);
        rootWindow.setShowModalMask(true);
        rootWindow.setCanDragResize(true);
        rootWindow.setCanDragReposition(true);
        rootWindow.setShowMinimizeButton(false);

        SectionStack sectionStack = new SectionStack();
        sectionStack.setOverflow(Overflow.AUTO);
        sectionStack.setWidth100();
        sectionStack.setHeight100();
        sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);

        /****************************** properties ********************************/
        String propertiesTitle = Canvas.imgHTML("silk/world.png") + " Properties";
        SectionStackSection propertiesSection = new SectionStackSection(propertiesTitle);
        propertiesSection.setCanCollapse(true);
        propertiesSection.setExpanded(true);

        final DynamicForm propertiesForm = new DynamicForm();
        propertiesForm.setIsGroup(true);
        propertiesForm.setGroupTitle("Update");

        nameItem.setTitle("Name");
        colorPickerItem.setTitle("Color");
        propertiesForm.setFields(nameItem, colorPickerItem);
        propertiesSection.setItems(propertiesForm);

        /************************** attributes ************************************/
        String attributesTitle = Canvas.imgHTML("silk/world.png") + " Attributes";
        SectionStackSection attributesSection = new SectionStackSection(attributesTitle);
        attributesSection.setCanCollapse(true);
        attributesSection.setExpanded(true);

        ListGridField attributeName = new ListGridField("attributeName", "Name");
        attributeName.setCanEdit(false);
        ListGridField attributeValue = new ListGridField("attributeValue", "Value");
        attributeValue.setType(ListGridFieldType.INTEGER);

        attributesListGrid.setFields(attributeName, attributeValue);
        attributesListGrid.setHeight(100);
        attributesListGrid.setCanEdit(true);
        attributesSection.setItems(attributesListGrid);

        /************************** skills ************************************/
        String skillsTitle = Canvas.imgHTML("silk/world.png") + " Skills";
        SectionStackSection skillsSection = new SectionStackSection(skillsTitle);
        skillsSection.setCanCollapse(true);
        skillsSection.setExpanded(true);

        ListGridField skillName = new ListGridField("skillName", "Name");
        skillName.setCanEdit(false);
        ListGridField skillValue = new ListGridField("skillValue", "Value");
        skillValue.setType(ListGridFieldType.INTEGER);

        skillsListGrid.setFields(skillName, skillValue);
        skillsListGrid.setHeight(250);
        skillsListGrid.setCanEdit(true);
        skillsSection.setItems(skillsListGrid);

        sectionStack.setSections(propertiesSection, attributesSection, skillsSection);

        HLayout buttonBar = new HLayout(BUTTON_BAR_MARGIN);
        buttonBar.addMember(saveButton);
        buttonBar.addMember(cancelButton);

        rootPanel.addMember(sectionStack);
        rootPanel.addMember(buttonBar);

        rootWindow.addItem(rootPanel);

        rootWindow.setWidth(400);
        rootWindow.setHeight(550);
        rootWindow.setTitle("Captain");
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void render() {
        rootWindow.setAutoCenter(true);
        rootWindow.show();
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.CaptainWindowView#setTitle(java.lang.String)
	 */
    @Override
	public final void setTitle(final String title) {
        rootWindow.setTitle(title);
    }

}
