/**
 * PlanetWindowView.java
 * Created by pgirard at 11:50:58 AM on Oct 26, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import com.google.gwt.user.client.ui.Widget;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractView;
import com.qagwaai.starmalaccamax.client.data.PlanetDistanceRecord;
import com.qagwaai.starmalaccamax.client.data.PlanetRecord;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author pgirard
 * 
 */
public class ClosestPlanetWindowViewImpl extends AbstractView<ClosestPlanetWindowPresenter> implements ClosestPlanetWindowView {
    private static final int GRID_HEIGHT = 350;
    private static final int GRID_WIDTH = 300;
    private static final int FORM_HEIGHT = 350;
    private static final int FORM_WIDTH = 200;
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
    private final ListGrid planetGrid = new ListGrid();
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
    private TextItem locationField = new TextItem(PlanetRecord.LOCATION_OBJ, "Location");
    /**
	 * 
	 */
    private Label currentLocationLabel = new Label("Current Location:");

    /**
     * 
     */
    private IButton closeButton = new IButton("Close");

    /**
	 * 
	 */
    public ClosestPlanetWindowViewImpl() {
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
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ClosestPlanetWindowView#getCloseButton()
	 */
    @Override
	public final IButton getCloseButton() {
        return closeButton;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ClosestPlanetWindowView#getCurrentLocationLabel()
	 */
    @Override
	public final Label getCurrentLocationLabel() {
        return currentLocationLabel;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ClosestPlanetWindowView#getPlanetGrid()
	 */
    @Override
	public final ListGrid getPlanetGrid() {
        return planetGrid;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Canvas getRootCanvas() {
        return rootWindow;
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
        rootWindow.setTitle("Planet");
        rootWindow.setIsModal(true);
        rootWindow.setShowModalMask(true);
        rootWindow.setCanDragResize(true);
        rootWindow.setCanDragReposition(true);
        rootWindow.setShowMinimizeButton(false);

        baseLayout.setWidth100();
        baseLayout.setHeight100();

        HLayout current = new HLayout();
        current.setWidth100();
        current.addMember(currentLocationLabel);

        HLayout masterDetailLayout = new HLayout();
        masterDetailLayout.setWidth100();

        final DynamicForm planetForm = new DynamicForm();
        planetForm.setWidth(FORM_WIDTH);
        planetForm.setHeight(FORM_HEIGHT);
        planetForm.setFields(locationField, namePropertyField, atmosphereField, dockQualityField, governmentField,
            hydrographicsField, lawLevelField, orbitField, populationField, sizeField, techLevelField, gasGiantField,
            mainWorldField, satelliteField);

        planetGrid.setWidth(GRID_WIDTH);
        planetGrid.setHeight(GRID_HEIGHT);
        planetGrid.setShowResizeBar(true);

        planetGrid.addRecordClickHandler(new RecordClickHandler() {
            public void onRecordClick(final RecordClickEvent event) {
                planetForm.reset();
                planetForm.editSelectedData(planetGrid);
            }
        });

        ListGridField nameField = new ListGridField(PlanetDistanceRecord.NAME, "Name");
        ListGridField orbitLocationField = new ListGridField(PlanetDistanceRecord.ORBIT, "Orbit");
        ListGridField numberOfJumpsField = new ListGridField(PlanetDistanceRecord.NUMBER_OF_JUMPS, "Jumps");
        ListGridField distanceField = new ListGridField(PlanetDistanceRecord.DISTANCE_IN_AU, "Distance (AU)");
        planetGrid.setFields(nameField, orbitLocationField, numberOfJumpsField, distanceField);

        masterDetailLayout.setMembers(planetGrid, planetForm);

        HStack buttonBar = new HStack();
        buttonBar.addMember(closeButton);

        baseLayout.addMember(current);
        baseLayout.addMember(masterDetailLayout);
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
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ClosestPlanetWindowView#setTitle(java.lang.String)
	 */
    @Override
	public final void setTitle(final String windowTitle) {
        rootWindow.setTitle(windowTitle);
    }

}
