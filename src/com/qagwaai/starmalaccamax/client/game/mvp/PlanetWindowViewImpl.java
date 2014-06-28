/**
 * PlanetWindowView.java
 * Created by pgirard at 11:50:58 AM on Oct 26, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import com.google.gwt.user.client.ui.Widget;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractView;
import com.qagwaai.starmalaccamax.client.data.PlanetRecord;
import com.qagwaai.starmalaccamax.client.game.mvp.widget.LocationItem;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
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
public class PlanetWindowViewImpl extends AbstractView<PlanetWindowPresenter> implements PlanetWindowView {
    /**
	 * 
	 */
    private Window rootWindow;

    private Img planetImage = new Img("Earth-02-june.gif");

    /**
	 * 
	 */
    private ListGrid commodityGrid = new ListGrid();
    /**
	 * 
	 */
    private TextItem namePropertyField = new TextItem(PlanetRecord.NAME, "Name");

    /**
	 * 
	 */
    private TextItem atmosphereField = new TextItem(PlanetRecord.ATMOSPHERE, "Atmosphere");
    /**
	 * 
	 */
    private TextItem dockQualityField = new TextItem(PlanetRecord.DOCK_QUALITY, "Dock Quality");
    /**
	 * 
	 */
    private TextItem governmentField = new TextItem(PlanetRecord.GOVERNMENT, "Government");
    /**
	 * 
	 */
    private TextItem hydrographicsField = new TextItem(PlanetRecord.HYDROGRAPHICS, "Hydrographics");
    /**
	 * 
	 */
    private TextItem lawLevelField = new TextItem(PlanetRecord.LAW_LEVEL, "Law Level");
    /**
	 * 
	 */
    private TextItem orbitField = new TextItem(PlanetRecord.ORBIT, "Orbit");
    /**
	 * 
	 */
    private TextItem populationField = new TextItem(PlanetRecord.POPULATION, "Population");
    /**
	 * 
	 */
    private TextItem sizeField = new TextItem(PlanetRecord.SIZE, "Size");
    /**
	 * 
	 */
    private TextItem techLevelField = new TextItem(PlanetRecord.TECH_LEVEL, "Tech Level");
    /**
	 * 
	 */
    private CheckboxItem gasGiantField = new CheckboxItem(PlanetRecord.IS_GAS_GIANT, "Is Gas Giant");
    /**
	 * 
	 */
    private CheckboxItem mainWorldField = new CheckboxItem(PlanetRecord.IS_MAIN_WORLD, "Is Main World");
    /**
	 * 
	 */
    private CheckboxItem satelliteField = new CheckboxItem(PlanetRecord.IS_SATELLITE, "Is Satellite");
    /**
	 * 
	 */
    private LocationItem location = new LocationItem();
    /**
	 * 
	 */
    private ListGrid marketGrid = new ListGrid();
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
    private SectionStack sectionStack = new SectionStack();

    /**
	 * 
	 */
    public PlanetWindowViewImpl() {
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
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#getAtmosphereField()
	 */
    @Override
	public final TextItem getAtmosphereField() {
        return atmosphereField;
    }

    /**
     * @return the commoditiesGrid
     */
    /*
     * public final ListGrid getCargoGrid() { return marketGrid; }
     */

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#getCloseButton()
	 */
    @Override
	public final IButton getCloseButton() {
        return closeButton;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#getCommodityGrid()
	 */
    @Override
	public final ListGrid getCommodityGrid() {
        return commodityGrid;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#getDockQualityField()
	 */
    @Override
	public final TextItem getDockQualityField() {
        return dockQualityField;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#getForm()
	 */
    @Override
	public final DynamicForm getForm() {
        return form;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#getGasGiantField()
	 */
    @Override
	public final CheckboxItem getGasGiantField() {
        return gasGiantField;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#getGovernmentField()
	 */
    @Override
	public final TextItem getGovernmentField() {
        return governmentField;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#getHydrographicsField()
	 */
    @Override
	public final TextItem getHydrographicsField() {
        return hydrographicsField;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#getLawLevelField()
	 */
    @Override
	public final TextItem getLawLevelField() {
        return lawLevelField;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#getLocationItem()
	 */
    @Override
	public final LocationItem getLocationItem() {
        return location;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#getMainWorldField()
	 */
    @Override
	public final CheckboxItem getMainWorldField() {
        return mainWorldField;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#getNamePropertyField()
	 */
    @Override
	public final TextItem getNamePropertyField() {
        return namePropertyField;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#getOrbitField()
	 */
    @Override
	public final TextItem getOrbitField() {
        return orbitField;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#getPopulationField()
	 */
    @Override
	public final TextItem getPopulationField() {
        return populationField;
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
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#getSatelliteField()
	 */
    @Override
	public final CheckboxItem getSatelliteField() {
        return satelliteField;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#getSaveButton()
	 */
    @Override
	public final IButton getSaveButton() {
        return saveButton;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#getSizeField()
	 */
    @Override
	public final TextItem getSizeField() {
        return sizeField;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#getTechLevelField()
	 */
    @Override
	public final TextItem getTechLevelField() {
        return techLevelField;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void layout() {
        final VLayout baseLayout = new VLayout(10);
        rootWindow.setWidth(600);
        rootWindow.setHeight(450);
        rootWindow.setTitle("Planet");
        rootWindow.setIsModal(true);
        rootWindow.setShowModalMask(true);
        rootWindow.setCanDragResize(true);
        rootWindow.setCanDragReposition(true);
        rootWindow.setShowMinimizeButton(false);

        baseLayout.setWidth100();
        baseLayout.setHeight100();

        sectionStack.setOverflow(Overflow.AUTO);
        sectionStack.setWidth100();
        sectionStack.setHeight(375);
        sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);

        /************************* image ****************************/
        String title3 = Canvas.imgHTML("silk/world.png") + " View";
        SectionStackSection imageSection = new SectionStackSection(title3);
        imageSection.setCanCollapse(true);
        imageSection.setExpanded(true);

        // Img planetImage = new Img();
        planetImage.setHeight(85);
        planetImage.setWidth(180);
        planetImage.setMargin(15);
        imageSection.setItems(planetImage);

        /**************************** properties *****************************/
        String title2 = Canvas.imgHTML("silk/world.png") + " Properties";
        SectionStackSection propertiesSection = new SectionStackSection(title2);
        propertiesSection.setCanCollapse(true);
        propertiesSection.setExpanded(true);

        form.setNumCols(4);
        form.setFields(namePropertyField, atmosphereField, dockQualityField, governmentField, hydrographicsField,
            lawLevelField, orbitField, populationField, sizeField, techLevelField, gasGiantField, mainWorldField,
            satelliteField);
        propertiesSection.setItems(form);

        /************************* market ******************************/
        String marketTitle = Canvas.imgHTML("silk/world.png") + " Market";
        SectionStackSection marketSection = new SectionStackSection(marketTitle);

        marketSection.setCanCollapse(true);
        marketSection.setExpanded(true);

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

        marketSection.setItems(commodityGrid);

        // marketSection.setItems(marketGrid);

        sectionStack.setSections(imageSection, propertiesSection, marketSection);
        sectionStack.setHeight("*");

        HLayout buttonBar = new HLayout();
        buttonBar.setWidth100();
        buttonBar.setHeight(25);
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
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#setTitle(java.lang.String)
	 */
    @Override
	public final void setTitle(final String windowTitle) {
        rootWindow.setTitle(windowTitle);
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#getPlanetImage()
	 */
    @Override
	public Img getPlanetImage() {
        return planetImage;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlanetWindowView#getSectionStack()
	 */
    @Override
	public SectionStack getSectionStack() {
        return sectionStack;
    }

}
