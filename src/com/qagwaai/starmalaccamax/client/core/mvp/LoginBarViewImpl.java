/**
 * LoginBarWidget.java
 * Created by pgirard at 3:40:22 PM on Jul 22, 2010
 * in the com.qagwaai.starmalaccamax.client.widgets package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.core.mvp;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.qagwaai.starmalaccamax.client.ssviz.mvp.widget.SolarSystemVisualizationPresenterImpl;
import com.qagwaai.starmalaccamax.client.ssviz.mvp.widget.SolarSystemVisualizationViewImpl;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.smartgwt.client.widgets.toolbar.ToolStripMenuButton;

/**
 * @author pgirard
 * 
 */
public class LoginBarViewImpl extends AbstractView<LoginBarPresenter> implements LoginBarView {

    /**
	 * 
	 */
    private ToolStrip toolStrip;
    /**
	 * 
	 */
    private ToolStripButton userButton;

    /**
	 * 
	 */
    private ToolStripButton linkButton;
    /**
	 * 
	 */
    private ToolStripButton profileButton;

    private ToolStripButton aboutButton = new ToolStripButton();

    /**
	 * 
	 */
    private ToolStripButton fleetSummaryButton;

    private MenuItem newCaptainWizardButton = new MenuItem("New Captain Wizard");

    /**
	 * 
	 */
    // private ToolStripButton calendarSummaryButton;

    /**
	 * 
	 */
    private ToolStripButton opportunitiesButton;

    /**
	 * 
	 */
    private ToolStripButton communicationsButton;

    private ToolStripButton primerButton = new ToolStripButton("Primer");

    /**
     * init here so we can assign a click handler
     */
    private MenuItem manageUsersItem = new MenuItem("Manage Users", "icons/16/person.png");
    /**
	 * 
	 */
    private MenuItem manageItem = new MenuItem("Manage");
    /**
	 * 
	 */
    private MenuItem removeItem = new MenuItem("Remove");

    /**
     * 
     */
    private MenuItem manageSolarSystemsItem = new MenuItem("Manage Solar Systems", "icons/16/solar_system.png", "");
    /**
     * 
     */
    private MenuItem manageJumpGatesItem = new MenuItem("Manage Jump Gates", "icons/16/solar_system.png", "");
    /**
     * 
     */
    private MenuItem manageSunsItem = new MenuItem("Manage Suns", "icons/16/solar_system.png", "");
    /**
     * 
     */
    private MenuItem managePlanetsItem = new MenuItem("Manage Planets", "icons/16/solar_system.png", "");
    /**
     * 
     */
    private MenuItem manageMarketsItem = new MenuItem("Manage Markets", "icons/16/solar_system.png", "");
    /**
     * 
     */
    private MenuItem manageJobsItem = new MenuItem("Manage Jobs", "icons/16/solar_system.png", "");
    /**
     * 
     */
    private MenuItem manageShipTypesItem = new MenuItem("Manage ShipTypes", "icons/16/solar_system.png", "");
    /**
     * 
     */
    private MenuItem manageCaptainsItem = new MenuItem("Manage Captains", "icons/16/solar_system.png", "");
    /**
     * 
     */
    private MenuItem manageShipsItem = new MenuItem("Manage Ships", "icons/16/solar_system.png", "");
    /**
     * 
     */
    private MenuItem manageGameEventsItem = new MenuItem("Manage Game Events", "icons/16/solar_system.png", "");
    /**
     * 
     */
    private MenuItem manageClosestItem = new MenuItem("Manage Closest", "icons/16/solar_system.png", "");

    /**
	 * 
	 */
    private MenuItem removeAllClosest = new MenuItem("Remove all Closest", "icons/16/close.png", "");
    /**
	 * 
	 */
    private MenuItem removeAllJumpGates = new MenuItem("Remove all Jump Gates", "icons/16/close.png", "");
    /**
	 * 
	 */
    private MenuItem removeAllShips = new MenuItem("Remove all Ships", "icons/16/close.png", "");
    /**
	 * 
	 */
    private MenuItem removeAllCaptains = new MenuItem("Remove all Captains", "icons/16/close.png", "");
    /**
	 * 
	 */
    private MenuItem removeAllGameEvents = new MenuItem("Remove all GameEvents", "icons/16/close.png", "");
    /**
	 * 
	 */
    private MenuItem removeAllJobs = new MenuItem("Remove all Jobs", "icons/16/close.png", "");
    /**
	 * 
	 */
    private MenuItem removeAllMarkets = new MenuItem("Remove all Markets", "icons/16/close.png", "");
    /**
	 * 
	 */
    private MenuItem removeAllPlanets = new MenuItem("Remove all Planets", "icons/16/close.png", "");
    /**
	 * 
	 */
    private MenuItem removeAllShipTypes = new MenuItem("Remove all Ship Types", "icons/16/close.png", "");
    /**
	 * 
	 */
    private MenuItem removeAllSolarSystems = new MenuItem("Remove all Solar Systems", "icons/16/close.png", "");
    /**
	 * 
	 */
    private MenuItem removeAllSuns = new MenuItem("Remove all Suns", "icons/16/close.png", "");
    /**
	 * 
	 */
    private MenuItem removeAllUsers = new MenuItem("Remove all Users", "icons/16/close.png", "");

    /**
	 * 
	 */
    private MenuItem addSuperCaptain = new MenuItem("Add Super Captain");

    private MenuItem jumpToOtherPage = new MenuItem("Jump To Other Page");

    /**
	 * 
	 */
    private MenuItem viewSolarSystem1049 = new MenuItem("Visualize Solar System 1049");

    private MenuItem menuSetUserAsNew = new MenuItem("Set User As New");

    /**
	 * 
	 */
    public LoginBarViewImpl() {
        super();

        toolStrip = new ToolStrip();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Widget asWidget() {

        return toolStrip;
    }

    public void addAddSuperCaptainButtonClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        addSuperCaptain.addClickHandler(handler);
    }

    /**
     * 
     * @return the visable item
     */
    private ToolStripMenuButton getAdminToolStripMenuButton() {
        Menu menu = new Menu();
        menu.setShowShadow(true);
        menu.setShadowDepth(3);

        Menu manageSubMenu = new Menu();
        manageSubMenu.setItems(manageCaptainsItem, manageClosestItem, manageGameEventsItem, manageJobsItem,
            manageJumpGatesItem, manageMarketsItem, managePlanetsItem, manageShipsItem, manageShipTypesItem,
            manageSolarSystemsItem, manageSunsItem, manageUsersItem);
        manageItem.setSubmenu(manageSubMenu);

        Menu removeSubMenu = new Menu();
        removeSubMenu.setItems(removeAllCaptains, removeAllClosest, removeAllGameEvents, removeAllJobs,
            removeAllJumpGates, removeAllMarkets, removeAllPlanets, removeAllShips, removeAllShipTypes,
            removeAllSolarSystems, removeAllSuns, removeAllUsers);
        removeItem.setSubmenu(removeSubMenu);

        menu.setItems(manageItem, removeItem);

        ToolStripMenuButton menuButton = new ToolStripMenuButton("Admin", menu);
        menuButton.setWidth(100);
        return menuButton;
    }

    /**
     * @return the calendarSummaryButton
     */
    /*
     * public final ToolStripButton getCalendarSummaryButton() { return
     * calendarSummaryButton; }
     */

    public void addCommunicationsButtonClickHandler(com.smartgwt.client.widgets.events.ClickHandler handler) {
        communicationsButton.addClickHandler(handler);
    }

    public void setCommunicationsButtonDisabled(boolean disabled) {
        communicationsButton.setDisabled(disabled);
    }

    public void addFleetSummaryButtonClickHandler(com.smartgwt.client.widgets.events.ClickHandler handler) {
        fleetSummaryButton.addClickHandler(handler);
    }

    public void setFleetSummaryButtonDisabled(boolean disabled) {
        fleetSummaryButton.setDisabled(disabled);
    }

    public void addLogoutButtonClickHandler(com.smartgwt.client.widgets.events.ClickHandler handler) {
        linkButton.addClickHandler(handler);
    }

    public void setLogoutButtonDisabled(boolean disabled) {
        linkButton.setDisabled(disabled);
    }

    public void addManageCaptainsMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        manageCaptainsItem.addClickHandler(handler);
    }

    public void addManageClosestMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        manageClosestItem.addClickHandler(handler);
    }

    public void addManageGameEventsMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        manageGameEventsItem.addClickHandler(handler);
    }

    public void addManageJobsMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        manageJobsItem.addClickHandler(handler);
    }

    public void addManageJumpGatesMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        manageJumpGatesItem.addClickHandler(handler);
    }

    public void addManageMarketsMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        manageMarketsItem.addClickHandler(handler);
    }

    public void addManagePlanetsMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        managePlanetsItem.addClickHandler(handler);
    }

    public void addManageShipsMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        manageShipsItem.addClickHandler(handler);
    }

    public void addManageShipTypesMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        manageShipTypesItem.addClickHandler(handler);
    }

    public void addManageSolarSystemsMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        manageSolarSystemsItem.addClickHandler(handler);
    }

    public void addManageSunsMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        manageSunsItem.addClickHandler(handler);
    }

    public void addManageUsersMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        manageUsersItem.addClickHandler(handler);
    }

    public void addOpportunitiesButtonClickHandler(com.smartgwt.client.widgets.events.ClickHandler handler) {
        opportunitiesButton.addClickHandler(handler);
    }

    public void setOpportunitiesButtonDisabled(boolean disabled) {
        opportunitiesButton.setDisabled(disabled);
    }

    public void addProfileButtonClickHandler(com.smartgwt.client.widgets.events.ClickHandler handler) {
        profileButton.addClickHandler(handler);
    }

    public void setProfileButtonDisabled(boolean disabled) {
        profileButton.setDisabled(disabled);
    }

    public void addRemoveAllCaptainsMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        removeAllCaptains.addClickHandler(handler);
    }

    public void addRemoveAllClosestMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        removeAllClosest.addClickHandler(handler);
    }

    public void addRemoveAllGameEventsMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        removeAllGameEvents.addClickHandler(handler);
    }

    public void addRemoveAllJobsMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        removeAllJobs.addClickHandler(handler);
    }

    public void addRemoveAllJumpGatesMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        removeAllJumpGates.addClickHandler(handler);
    }

    public void addRemoveAllMarketsMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        removeAllMarkets.addClickHandler(handler);
    }

    public void addRemoveAllPlanetsMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        removeAllPlanets.addClickHandler(handler);
    }

    public void addRemoveAllShipsMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        removeAllShips.addClickHandler(handler);
    }

    public void addRemoveAllShipTypesMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        removeAllShipTypes.addClickHandler(handler);
    }

    public void addRemoveAllSolarSystemsMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        removeAllSolarSystems.addClickHandler(handler);
    }

    public void addRemoveAllSunsMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        removeAllSuns.addClickHandler(handler);
    }

    public void addRemoveAllUsersMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        removeAllUsers.addClickHandler(handler);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Canvas getRootCanvas() {
        return toolStrip;
    }

    /**
     * 
     * @return the visable item
     */
    private ToolStripMenuButton getTestToolStripMenuButton() {
        Menu menu = new Menu();
        menu.setShowShadow(true);
        menu.setShadowDepth(3);

        jumpToOtherPage.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(MenuItemClickEvent event) {
                SolarSystemVisualizationPresenterImpl presenter =
                    new SolarSystemVisualizationPresenterImpl(getPresenter().getEventBus(),
                        new SolarSystemVisualizationViewImpl(), new SolarSystemDTO(), new SimpleFilterItem());
                presenter.renderView();
            }
        });

        menu.setItems(addSuperCaptain, viewSolarSystem1049, menuSetUserAsNew, newCaptainWizardButton, jumpToOtherPage);

        ToolStripMenuButton menuButton = new ToolStripMenuButton("Test", menu);
        menuButton.setWidth(100);
        return menuButton;
    }

    public void addViewSolarSystem1049MenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        viewSolarSystem1049.addClickHandler(handler);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void layout() {

        toolStrip.setWidth100();

        fleetSummaryButton = new ToolStripButton();
        fleetSummaryButton.setTitle("Fleet Summary");
        fleetSummaryButton.setDisabled(true);
        toolStrip.addButton(fleetSummaryButton);

        /*
         * calendarSummaryButton = new ToolStripButton();
         * calendarSummaryButton.setTitle("Calendar");
         * calendarSummaryButton.setDisabled(true);
         * toolStrip.addButton(calendarSummaryButton);
         */

        opportunitiesButton = new ToolStripButton();
        opportunitiesButton.setTitle("Opportunities");
        opportunitiesButton.setDisabled(true);
        toolStrip.addButton(opportunitiesButton);

        communicationsButton = new ToolStripButton();
        communicationsButton.setTitle("Communications");
        communicationsButton.setDisabled(true);
        toolStrip.addButton(communicationsButton);

        toolStrip.addButton(primerButton);

        // push all buttons to the right
        toolStrip.addFill();

        userButton = new ToolStripButton();
        userButton.setIcon("silk/emoticon.png");

        userButton.setTitle("Hello <name>");
        toolStrip.addButton(userButton);

        linkButton = new ToolStripButton();
        linkButton.setIcon("silk/connect.png");
        linkButton.setTitle("Checking...");

        toolStrip.addSeparator();
        toolStrip.addButton(linkButton);

        profileButton = new ToolStripButton();
        profileButton.setIcon("silk/vcard_edit.png");
        profileButton.setTitle("Profile");

        toolStrip.addSeparator();
        toolStrip.addMember(profileButton);

        aboutButton.setIcon("application.png");
        aboutButton.setTitle("About");
        toolStrip.addSeparator();
        toolStrip.addMember(aboutButton);

    }

    /**
     * 
     * @param url
     *            the url of the login form
     */
    public final void login(final String url) {
        Window.Location.assign(url);
    }

    /**
     * 
     * @param url
     *            where to go after the logout
     */
    public final void logout(final String url) {
        Window.Location.assign(url);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void render() {
        // needs to be added to container

    }

    /**
     * 
     * @param url
     *            the login url
     */
    public final void setLogin(final String url) {
        linkButton.setTitle("Login...");
    }

    /**
     * 
     * @param user
     *            a non-null user
     * @param logoutUrl
     *            the url for the user to logout
     */
    public final void setUser(final UserDTO user, final String logoutUrl) {
        userButton.setTitle("Hello " + user.getNickname());

        linkButton.setTitle("Logout");

        if (user.isAdmin()) {
            toolStrip.addMenuButton(getAdminToolStripMenuButton(), 0);
            toolStrip.addMenuButton(getTestToolStripMenuButton(), 1);
        }
    }

    public void addSetUserAsNewMenuClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        menuSetUserAsNew.addClickHandler(handler);
    }

    public void addPrimerButtonClickHandler(com.smartgwt.client.widgets.events.ClickHandler handler) {
        primerButton.addClickHandler(handler);
    }

    public void addNewCaptainWizardButtonClickHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        newCaptainWizardButton.addClickHandler(handler);
    }

    public void addAboutButtonClickHandler(com.smartgwt.client.widgets.events.ClickHandler handler) {
        aboutButton.addClickHandler(handler);
    }

}
