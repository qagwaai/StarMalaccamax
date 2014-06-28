/**
 * ShipWindowView.java
 * Created by pgirard at 11:50:58 AM on Oct 26, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Widget;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractView;
import com.qagwaai.starmalaccamax.client.game.mvp.widget.LocationItem;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author pgirard
 * 
 */
public class ShipWindowViewImpl extends AbstractView<ShipWindowPresenterImpl> implements ShipWindowView {

    /**
	 * 
	 */
    public static final String CARGO_AMOUNT = "amount";
    /**
	 * 
	 */
    public static final String CARGO_COMMODITY = "name";
    /**
	 * 
	 */
    public static final String CARGO_PURCHASE_DATE = "purchaseDate";
    /**
	 * 
	 */
    public static final String CARGO_PURCHASE_PRICE = "purchasePrice";
    /**
	 * 
	 */
    private Window rootWindow;
    /**
	 * 
	 */
    private ListGrid attributeGrid = new ListGrid();

    /**
	 * 
	 */
    private TextItem namePropertyField = new TextItem("name", "Name");

    /**
	 * 
	 */
    private LocationItem location = new LocationItem();
    /**
	 * 
	 */
    private ListGrid cargoGrid = new ListGrid();

    /*
     * { protected Canvas getExpansionComponent(final ListGridRecord record) {
     * final ListGrid shipCargoGrid = new ListGrid(); final ListGrid cargoGrid =
     * this; VLayout layout = new VLayout(5); layout.setPadding(5);
     * 
     * shipCargoGrid.setWidth(450); shipCargoGrid.setHeight(100);
     * shipCargoGrid.setTop(50); shipCargoGrid.setShowAllRecords(true);
     * shipCargoGrid.setShowEmptyMessage(true); shipCargoGrid.setCanEdit(true);
     * 
     * ListGridRecord[] data =
     * getPresenter().getCargoGridData(record.getAttribute
     * (ShipWindowView.CARGO_COMMODITY)); shipCargoGrid.setData(data);
     * 
     * ListGridField cargoNameField = new
     * ListGridField(ShipWindowView.CARGO_COMMODITY, "Name", 120);
     * cargoNameField.setCanEdit(false); ListGridField cargoValueField = new
     * ListGridField(ShipWindowView.CARGO_AMOUNT, "Amount in Hold");
     * cargoValueField.setEditorType(new IntegerItem()); ListGridField
     * cargoPurchaseDateField = new
     * ListGridField(ShipWindowView.CARGO_PURCHASE_DATE, "Purchase Date");
     * cargoPurchaseDateField.setEditorType(new DateItem()); ListGridField
     * cargoPurchasePriceField = new
     * ListGridField(ShipWindowView.CARGO_PURCHASE_PRICE, "Purchase Price");
     * cargoPurchasePriceField.setEditorType(new FloatItem());
     * cargoGrid.setFields(cargoNameField, cargoValueField,
     * cargoPurchaseDateField, cargoPurchasePriceField);
     * 
     * IButton saveButton = new IButton("Save"); saveButton.addClickHandler(new
     * ClickHandler() { public void onClick(final ClickEvent event) {
     * SC.say("Save!"); } });
     * 
     * IButton cancelButton = new IButton("Done");
     * cancelButton.addClickHandler(new ClickHandler() { public void
     * onClick(final ClickEvent event) { cargoGrid.collapseRecord(record); } });
     * 
     * HLayout hLayout = new HLayout(10); hLayout.setAlign(Alignment.CENTER);
     * hLayout.addMember(saveButton); hLayout.addMember(cancelButton);
     * 
     * layout.addMember(shipCargoGrid); layout.addMember(hLayout); return
     * layout; } };
     */
    /**
	 * 
	 */
    private DynamicForm form = new DynamicForm();

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
    public ShipWindowViewImpl() {
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
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ShipWindowView#getAttributeGrid()
	 */
    @Override
	public final ListGrid getAttributeGrid() {
        return attributeGrid;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ShipWindowView#getCargoGrid()
	 */
    @Override
	public final ListGrid getCargoGrid() {
        return cargoGrid;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ShipWindowView#getCloseButton()
	 */
    @Override
	public final IButton getCloseButton() {
        return closeButton;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ShipWindowView#getForm()
	 */
    @Override
	public final DynamicForm getForm() {
        return form;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ShipWindowView#getLocationItem()
	 */
    @Override
	public final LocationItem getLocationItem() {
        return location;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ShipWindowView#getNamePropertyField()
	 */
    @Override
	public final TextItem getNamePropertyField() {
        return namePropertyField;
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
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ShipWindowView#getSaveButton()
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
        VLayout baseLayout = new VLayout(10);
        rootWindow.setWidth(600);
        rootWindow.setHeight(450);
        rootWindow.setTitle("Ship");
        rootWindow.setIsModal(true);
        rootWindow.setShowModalMask(true);
        rootWindow.setCanDragResize(true);
        rootWindow.setCanDragReposition(true);
        rootWindow.setShowMinimizeButton(false);

        baseLayout.setWidth100();
        baseLayout.setHeight100();

        SectionStack sectionStack = new SectionStack();
        sectionStack.setOverflow(Overflow.AUTO);
        sectionStack.setWidth100();
        sectionStack.setHeight(375);
        sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);

        /************************* attributes ******************************/
        String title = Canvas.imgHTML("silk/world.png") + " Attributes";
        SectionStackSection attributesSection = new SectionStackSection(title);

        attributesSection.setCanCollapse(true);
        attributesSection.setExpanded(true);

        attributeGrid.setWidth100();
        attributeGrid.setHeight(224);
        attributeGrid.setTop(50);
        attributeGrid.setShowAllRecords(true);
        attributeGrid.setShowEmptyMessage(true);
        attributeGrid.setCanEdit(true);

        ListGridField nameField = new ListGridField("name", "Name", 120);
        ListGridField valueField = new ListGridField("value", "Value");
        valueField.setEditorType(new IntegerItem());
        attributeGrid.setFields(nameField, valueField);

        attributesSection.setItems(attributeGrid);

        /**************************** properties *****************************/
        String title2 = Canvas.imgHTML("silk/world.png") + " Properties";
        SectionStackSection propertiesSection = new SectionStackSection(title2);
        propertiesSection.setCanCollapse(true);
        propertiesSection.setExpanded(true);

        form.setFields(namePropertyField);
        propertiesSection.setItems(form);

        /**************************** location *****************************/
        String locationTitle = Canvas.imgHTML("silk/world.png") + " Location";
        SectionStackSection locationSection = new SectionStackSection(locationTitle);
        locationSection.setCanCollapse(true);
        locationSection.setExpanded(true);

        locationSection.setItems(location);

        /************************* cargo ******************************/
        String cargoTitle = Canvas.imgHTML("silk/world.png") + " Cargo";
        SectionStackSection cargoSection = new SectionStackSection(cargoTitle);

        cargoSection.setCanCollapse(true);
        cargoSection.setExpanded(true);

        // cargoGrid.setCanExpandRecords(true);
        cargoGrid.setWidth100();
        cargoGrid.setHeight(224);
        cargoGrid.setTop(50);
        cargoGrid.setShowAllRecords(true);
        cargoGrid.setShowEmptyMessage(true);
        cargoGrid.setCanEdit(true);
        cargoGrid.setGroupStartOpen(GroupStartOpen.ALL);
        cargoGrid.setGroupByField("category");

        ListGridField cargoCategoryField = new ListGridField("category", "Category", 120);
        cargoCategoryField.setCanEdit(false);
        cargoCategoryField.setHidden(true);
        ListGridField cargoNameField = new ListGridField(ShipWindowViewImpl.CARGO_COMMODITY, "Name", 120);
        cargoNameField.setCanEdit(false);
        ListGridField cargoValueField = new ListGridField(ShipWindowViewImpl.CARGO_AMOUNT, "Amount in Hold");
        cargoValueField.setEditorType(new IntegerItem());
        ListGridField cargoPurchaseDateField = new ListGridField(ShipWindowViewImpl.CARGO_PURCHASE_DATE, "Purchase Date");
        cargoPurchaseDateField.setEditorType(new DateItem());
        ListGridField cargoPurchasePriceField =
            new ListGridField(ShipWindowViewImpl.CARGO_PURCHASE_PRICE, "Purchase Price");
        cargoPurchasePriceField.setEditorType(new IntegerItem());
        cargoPurchasePriceField.setCellFormatter(new CellFormatter() {

            @Override
            public String format(final Object value, final ListGridRecord record, final int rowNum, final int colNum) {
                if (value == null) {
                    return null;
                }

                String val = null;
                try {
                    NumberFormat nf = NumberFormat.getFormat("#,###");
                    val = nf.format(((Number) value).longValue());
                } catch (Exception e) {
                    return value.toString();
                }
                return val + "&nbsp;&OElig;";
            }
        });
        cargoGrid.setFields(cargoNameField, cargoValueField, cargoCategoryField, cargoPurchaseDateField,
            cargoPurchasePriceField);

        cargoSection.setItems(cargoGrid);

        sectionStack.setSections(propertiesSection, locationSection, attributesSection, cargoSection);

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
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ShipWindowView#setTitle(java.lang.String)
	 */
    @Override
	public final void setTitle(final String windowTitle) {
        rootWindow.setTitle(windowTitle);
    }

}
