/**
 * PlayerOpportunitiesView.java
 * Created by pgirard at 1:11:14 PM on Dec 2, 2010
 * in the com.qagwaai.starmalaccamax.client.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Widget;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractView;
import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarPresenterImpl;
import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarViewImpl;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author pgirard
 * 
 */
public class PlayerOpportunitiesViewImpl extends AbstractView<PlayerOpportunitiesPresenter> implements PlayerOpportunitiesView {

    /**
	 * 
	 */
    public static final String SHIP_NAME = "shipName";
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
    public static final String CARGO_SALE_PRICE = "salePrice";
    /**
	 * 
	 */
    public static final String CARGO_PROFIT = "profit";
    /**
	 * 
	 */
    public static final String DISTANCE = "distance";
    /**
	 * 
	 */
    public static final String LOCATION = "location";
    /**
	 * 
	 */
    public static final String PLANET_NAME = "planetName";
    /**
	 * 
	 */
    public static final String SHIP_OBJ = "shipObj";
    /**
	 * 
	 */
    public static final String CARGO_OBJ = "cargoObj";
    /**
	 * 
	 */
    public static final String PLANET_OBJ = "planetObj";
    /**
	 * 
	 */
    private VLayout baseLayout = new VLayout(2);
    /**
     * 
     */
    private LoginBarPresenterImpl loginBarPresenter;
    /**
     * 
     */
    private ListGrid cargoGrid = new ListGrid();
    /**
	 * 
	 */
    private ListGrid opportunityGrid = new ListGrid();

    private ListGrid jobGrid = new ListGrid();
    /**
	 * 
	 */
    private SectionStackSection opportunitySection = new SectionStackSection();
    /**
	 * 
	 */
    private SectionStackSection cargoSection = new SectionStackSection();
    private SectionStackSection jobSection = new SectionStackSection();

    /**
	 * 
	 */
    private IButton shipPropertiesButton = new IButton("Ship Properties");
    /**
	 * 
	 */
    private IButton planetPropertiesButton = new IButton("Planet Properties");

    /**
	 * 
	 */
    private IButton launchShipButton = new IButton("Launch!");
    /**
	 * 
	 */
    private IButton refreshButton = new IButton("Refresh");

    /**
	 * 
	 */
    public PlayerOpportunitiesViewImpl() {
        super();
    }

    /**
     * 
     * @param location
     *            the location of the page - used for history
     */
    public PlayerOpportunitiesViewImpl(final String location) {
        this();
        setLocation(location);
    }

    @Override
    public final Widget asWidget() {
        return baseLayout;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerOpportunitiesView#getCargoGrid()
	 */
    @Override
	public final ListGrid getCargoGrid() {
        return cargoGrid;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerOpportunitiesView#getLaunchShipButton()
	 */
    @Override
	public final IButton getLaunchShipButton() {
        return launchShipButton;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerOpportunitiesView#getOpportunityGrid()
	 */
    @Override
	public final ListGrid getOpportunityGrid() {
        return opportunityGrid;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerOpportunitiesView#getPlanetPropertiesButton()
	 */
    @Override
	public final IButton getPlanetPropertiesButton() {
        return planetPropertiesButton;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerOpportunitiesView#getRefreshButton()
	 */
    @Override
	public final IButton getRefreshButton() {
        return refreshButton;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Canvas getRootCanvas() {
        return baseLayout;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerOpportunitiesView#getShipPropertiesButton()
	 */
    @Override
	public final IButton getShipPropertiesButton() {
        return shipPropertiesButton;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void layout() {
        baseLayout.setWidth100();
        baseLayout.setHeight100();
        loginBarPresenter =
            new LoginBarPresenterImpl(this.getPresenter().getEventBus(), new LoginBarViewImpl(), new UserDTO());

        /********************* View Label **********************/

        HTMLPane header = new HTMLPane();
        header.setWidth100();
        header.setHeight(15);
        header.setContents("<center><b>Opportunities</b></center>");

        /****************** opp grid ************************************/
        cargoGrid.setShowEmptyMessage(true);
        cargoGrid.setEmptyMessage("Loading...");

        cargoGrid.setWidth100();
        cargoGrid.setHeight100();
        cargoGrid.setShowAllRecords(true);
        cargoGrid.setShowEmptyMessage(true);
        cargoGrid.setCanEdit(true);

        SectionStack cargoSectionStack = new SectionStack();
        cargoSection.setCanCollapse(false);
        cargoSection.setExpanded(true);
        cargoSectionStack.setWidth100();
        cargoSectionStack.setHeight(180);

        String cargoTitle = Canvas.imgHTML("silk/brick.png") + " Cargo";
        cargoSection.setTitle(cargoTitle);

        ListGridField shipField = new ListGridField(PlayerOpportunitiesViewImpl.SHIP_NAME, "Ship");
        shipField.setCanEdit(false);
        shipField.setHidden(false);
        ListGridField cargoNameField = new ListGridField(PlayerOpportunitiesViewImpl.CARGO_COMMODITY, "Name", 120);
        cargoNameField.setCanEdit(false);
        ListGridField cargoValueField = new ListGridField(PlayerOpportunitiesViewImpl.CARGO_AMOUNT, "Amount in Hold");
        cargoValueField.setEditorType(new IntegerItem());
        ListGridField cargoPurchasePriceField =
            new ListGridField(PlayerOpportunitiesViewImpl.CARGO_PURCHASE_PRICE, "Purchase Price");
        cargoPurchasePriceField.setType(ListGridFieldType.INTEGER);
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
        ListGridField purchaseDateField =
            new ListGridField(PlayerOpportunitiesViewImpl.CARGO_PURCHASE_DATE, "Purchase Date");
        purchaseDateField.setEditorType(new DateItem());
        cargoGrid.setFields(shipField, cargoNameField, cargoValueField, cargoPurchasePriceField, purchaseDateField);

        cargoSection.setItems(cargoGrid);
        cargoSectionStack.setSections(cargoSection);
        /******************************** opportunity grid *********************************/

        SectionStack opportunitySectionStack = new SectionStack();
        opportunitySectionStack.setWidth100();
        opportunitySectionStack.setHeight(180);

        String opportunityTitle = Canvas.imgHTML("silk/world.png") + " Opportunities";
        opportunitySection.setTitle(opportunityTitle);

        opportunitySection.setCanCollapse(false);
        opportunitySection.setExpanded(true);
        opportunityGrid.setWidth100();
        opportunityGrid.setHeight100();
        opportunityGrid.setShowEmptyMessage(true);
        opportunityGrid.setEmptyMessage("Select cargo above to get a list of opportunities");

        opportunityGrid.setShowAllRecords(true);
        opportunityGrid.setShowEmptyMessage(true);
        opportunityGrid.setCanEdit(true);
        ListGridField planetNameField = new ListGridField(PlayerOpportunitiesViewImpl.PLANET_NAME, "Planet");
        planetNameField.setCanEdit(false);
        ListGridField distanceField = new ListGridField(PlayerOpportunitiesViewImpl.DISTANCE, "Distance");
        distanceField.setCanEdit(false);
        distanceField.setType(ListGridFieldType.INTEGER);
        distanceField.setCellFormatter(new CellFormatter() {
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
                return val + "km";
            }
        });
        ListGridField cargoSalePriceField = new ListGridField(PlayerOpportunitiesViewImpl.CARGO_SALE_PRICE, "Sale Price");
        cargoSalePriceField.setCanEdit(false);
        cargoSalePriceField.setType(ListGridFieldType.INTEGER);
        cargoSalePriceField.setCellFormatter(new CellFormatter() {

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
        ListGridField profitField = new ListGridField(PlayerOpportunitiesViewImpl.CARGO_PROFIT, "Profit");
        profitField.setCanEdit(false);
        profitField.setType(ListGridFieldType.INTEGER);
        profitField.setCellFormatter(new CellFormatter() {

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
        opportunityGrid.setFields(planetNameField, distanceField, cargoSalePriceField, profitField);

        opportunitySection.setItems(opportunityGrid);
        opportunitySectionStack.setSections(opportunitySection);

        /******************************* button bar ****************************/
        HLayout buttonBar = new HLayout();
        buttonBar.setHeight(25);
        buttonBar.setWidth100();
        shipPropertiesButton.disable();
        planetPropertiesButton.disable();
        launchShipButton.disable();
        refreshButton.setIcon("icons/16/arrow_refresh.png");

        buttonBar.addMember(shipPropertiesButton);
        buttonBar.addMember(planetPropertiesButton);
        buttonBar.addMember(launchShipButton);
        buttonBar.addMember(refreshButton);

        /******************************** job stack *****************************/
        SectionStack jobSectionStack = new SectionStack();
        jobSectionStack.setWidth100();
        jobSectionStack.setHeight(180);

        String jobTitle = Canvas.imgHTML("silk/world.png") + " Job Board";
        jobSection.setTitle(jobTitle);
        jobSection.setCanCollapse(false);
        jobSection.setExpanded(true);

        jobGrid.setWidth100();
        jobGrid.setHeight100();
        jobGrid.setShowAllRecords(true);
        jobGrid.setCanEdit(false);
        ListGridField jobNameField = new ListGridField("jobName", "Name");
        ListGridField jobTypeField = new ListGridField("jobType", "Type");
        ListGridField jobDescriptionField = new ListGridField("jobDescription", "Description");
        ListGridField jobPayField = new ListGridField("jobPay", "Pay");
        ListGridField jobDurationField = new ListGridField("jobDuration", "Duration");
        jobGrid.setFields(jobNameField, jobTypeField, jobDescriptionField, jobPayField, jobDurationField);

        jobSection.setItems(jobGrid);
        jobSectionStack.setSections(jobSection);

        baseLayout.addMember(loginBarPresenter.getView().asWidget());
        baseLayout.addMember(header);
        baseLayout.addMember(cargoSectionStack);
        baseLayout.addMember(buttonBar);
        baseLayout.addMember(opportunitySectionStack);
        baseLayout.addMember(jobSectionStack);
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerOpportunitiesView#setOpportunityGridTitle(java.lang.String)
	 */
    @Override
	public final void setOpportunityGridTitle(final String cargo) {
        String title = Canvas.imgHTML("silk/world.png") + " Opportunities for " + cargo;
        opportunitySection.setTitle(title);

    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.PlayerOpportunitiesView#getJobGrid()
	 */
    @Override
	public ListGrid getJobGrid() {
        return jobGrid;
    }

}
