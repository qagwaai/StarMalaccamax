/**
 * MarketWindowView.java
 * Created by pgirard at 11:50:58 AM on Oct 26, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import com.google.gwt.user.client.ui.Widget;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractView;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author pgirard
 * 
 */
public class MarketWindowViewImpl extends AbstractView<MarketWindowPresenter> implements MarketWindowView {
    private static final int VIEW_HEIGHT = 450;
    private static final int VIEW_WIDTH = 600;
    private static final int VIEW_ROOT_MARGIN = 10;
    /**
	 * 
	 */
    private Window rootWindow;
    /**
	 * 
	 */
    private ListGrid commodityGrid = new ListGrid();

    /**
	 * 
	 */
    private DynamicForm form = new DynamicForm();

    /**
     * 
     */
    private DateItem lastVisitedItem = new DateItem("lastVisited");

    /**
     * 
     */
    private IButton saveButton = new IButton("Save");

    /**
     * 
     */
    private IButton closeButton = new IButton("Close");

    /**
	 * 
	 */
    public MarketWindowViewImpl() {
        rootWindow = new Window();

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Widget asWidget() {
        return rootWindow;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.MarketWindowView#getCloseButton()
	 */
    @Override
	public final IButton getCloseButton() {
        return closeButton;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.MarketWindowView#getCommodityGrid()
	 */
    @Override
	public final ListGrid getCommodityGrid() {
        return commodityGrid;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.MarketWindowView#getForm()
	 */
    @Override
	public final DynamicForm getForm() {
        return form;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.MarketWindowView#getLastVisitedItem()
	 */
    @Override
	public final DateItem getLastVisitedItem() {
        return lastVisitedItem;
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
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.MarketWindowView#getSaveButton()
	 */
    @Override
	public final IButton getSaveButton() {
        return saveButton;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void layout() {
        VLayout baseLayout = new VLayout(VIEW_ROOT_MARGIN);
        rootWindow.setWidth(VIEW_WIDTH);
        rootWindow.setHeight(VIEW_HEIGHT);
        rootWindow.setTitle("Market for Planet ");
        rootWindow.setIsModal(true);
        rootWindow.setShowModalMask(true);
        rootWindow.setCanDragResize(true);
        rootWindow.setCanDragReposition(true);
        rootWindow.setShowMinimizeButton(false);

        SectionStack sectionStack = new SectionStack();
        sectionStack.setOverflow(Overflow.AUTO);
        sectionStack.setWidth100();
        sectionStack.setHeight(350);
        sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);

        String title = Canvas.imgHTML("silk/world.png") + " Commodities";
        SectionStackSection section = new SectionStackSection(title);

        section.setCanCollapse(true);
        section.setExpanded(true);

        commodityGrid.setWidth100();
        commodityGrid.setHeight(224);
        commodityGrid.setTop(50);
        commodityGrid.setShowAllRecords(true);
        commodityGrid.setShowEmptyMessage(true);
        commodityGrid.setCanEdit(true);

        ListGridField nameField = new ListGridField("commodityName", "Name", 120);
        ListGridField sellPriceField = new ListGridField("sellPrice", "Sell Price");
        ListGridField purchasePriceField = new ListGridField("purchasePrice", "Purchase Price");
        ListGridField sellAvailableField = new ListGridField("sellAvailable", "Sell Available");
        ListGridField purchaseWantedField = new ListGridField("purchaseWanted", "Purchase Wanted");
        commodityGrid.setFields(nameField, sellPriceField, purchasePriceField, sellAvailableField, purchaseWantedField);

        section.setItems(commodityGrid);

        String title2 = Canvas.imgHTML("silk/world.png") + " Properties";
        SectionStackSection section2 = new SectionStackSection(title2);
        section2.setCanCollapse(true);
        section2.setExpanded(true);

        form.setFields(lastVisitedItem);
        section2.setItems(form);

        sectionStack.setSections(section, section2);

        HStack buttonBar = new HStack();
        buttonBar.addMember(saveButton);
        buttonBar.addMember(closeButton);

        baseLayout.addMember(sectionStack);
        baseLayout.addMember(buttonBar);

        rootWindow.addItem(baseLayout);
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
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.MarketWindowView#setTitle(java.lang.String)
	 */
    @Override
	public final void setTitle(final String windowTitle) {
        rootWindow.setTitle(windowTitle);
    }

}
