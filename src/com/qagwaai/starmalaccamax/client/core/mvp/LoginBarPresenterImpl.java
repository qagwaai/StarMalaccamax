/**
 * LoginBarPresenter.java
 * Created by pgirard at 11:47:49 AM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.client.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.core.mvp;

import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.ManageCaptainsMenuClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.ManageClosestMenuClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.ManageGameEventsMenuClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.ManageJobsMenuClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.ManageJumpGatesMenuClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.ManageMarketsMenuClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.ManagePlanetsMenuClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.ManageShipTypesMenuClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.ManageShipsMenuClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.ManageSolarSystemMenuClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.ManageStarMenuClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.ManageUsersMenuClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.RemoveAllCaptainsClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.RemoveAllClosestClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.RemoveAllGameEventsClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.RemoveAllJobsClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.RemoveAllJumpGatesClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.RemoveAllMarketsClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.RemoveAllPlanetsClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.RemoveAllShipTypesClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.RemoveAllShipsClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.RemoveAllSolarSystemsClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.RemoveAllSunsClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.handlers.RemoveAllUsersClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.core.LoginWatcher;
import com.qagwaai.starmalaccamax.client.core.mvp.handlers.AboutButtonClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.core.mvp.handlers.AddSuperCaptainMenuClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.core.mvp.handlers.LogoutLinkClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.core.mvp.handlers.ProfileClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.core.mvp.handlers.SetUserAsNewClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.core.mvp.handlers.ViewSolarSystem1049MenuClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.event.CurrentUserChangedEvent;
import com.qagwaai.starmalaccamax.client.event.CurrentUserChangedHandler;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.CommunicationsClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.FleetSummaryMenuClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.NewCaptainWizardButtonClickHandlerImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerOpportunitiesClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PrimerClickHandlerImpl;
import com.qagwaai.starmalaccamax.shared.model.User;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public final class LoginBarPresenterImpl extends AbstractPresenter<LoginBarView, User> implements
    CurrentUserChangedHandler, LoginBarPresenter {

    private ProfileClickHandlerImpl profileHandler;

    /**
     * 
     * @param eventBus
     *            the bus to publish/register events
     * @param view
     *            the associated view
     * @param model
     *            the user
     */
    public LoginBarPresenterImpl(final EventBus eventBus, final LoginBarViewImpl view, final User model) {
        super(eventBus, view, model);
        view.setPresenter(this);
        view.layout();
        eventBus.addHandler(CurrentUserChangedEvent.getType(), this);

        view.addLogoutButtonClickHandler(new LogoutLinkClickHandlerImpl(eventBus));
        view.setLogoutButtonDisabled(true);
        profileHandler = new ProfileClickHandlerImpl(eventBus, (UserDTO) model);
        view.addProfileButtonClickHandler(profileHandler);
        view.setProfileButtonDisabled(true);
        view.addManageUsersMenuClickHandler(new ManageUsersMenuClickHandlerImpl(eventBus));
        view.addManageSolarSystemsMenuClickHandler(new ManageSolarSystemMenuClickHandlerImpl(eventBus));
        view.addManagePlanetsMenuClickHandler(new ManagePlanetsMenuClickHandlerImpl(eventBus));
        view.addManageJumpGatesMenuClickHandler(new ManageJumpGatesMenuClickHandlerImpl(eventBus));
        view.addManageSunsMenuClickHandler(new ManageStarMenuClickHandlerImpl(eventBus));
        view.addManageJobsMenuClickHandler(new ManageJobsMenuClickHandlerImpl(eventBus));
        if (LoginWatcher.getInstance().getLastEvent() != null) {
            // update ui with latest
            onCurrentUserChanged(LoginWatcher.getInstance().getLastEvent());
        }
        view.addAddSuperCaptainButtonClickHandler(new AddSuperCaptainMenuClickHandlerImpl());
        view.addManageShipTypesMenuClickHandler(new ManageShipTypesMenuClickHandlerImpl(eventBus));
        view.addManageMarketsMenuClickHandler(new ManageMarketsMenuClickHandlerImpl(eventBus));
        view.addManageShipsMenuClickHandler(new ManageShipsMenuClickHandlerImpl(eventBus));
        view.addManageGameEventsMenuClickHandler(new ManageGameEventsMenuClickHandlerImpl(eventBus));
        view.addManageCaptainsMenuClickHandler(new ManageCaptainsMenuClickHandlerImpl(eventBus));
        view.addFleetSummaryButtonClickHandler(new FleetSummaryMenuClickHandlerImpl(eventBus));
        view.addManageClosestMenuClickHandler(new ManageClosestMenuClickHandlerImpl(eventBus));
        view.addCommunicationsButtonClickHandler(new CommunicationsClickHandlerImpl(eventBus));
        view.addOpportunitiesButtonClickHandler(new PlayerOpportunitiesClickHandlerImplementation(eventBus));
        view.addRemoveAllCaptainsMenuClickHandler(new RemoveAllCaptainsClickHandlerImpl(view));
        view.addRemoveAllClosestMenuClickHandler(new RemoveAllClosestClickHandlerImpl(view));
        view.addRemoveAllGameEventsMenuClickHandler(new RemoveAllGameEventsClickHandlerImpl(view));
        view.addRemoveAllJobsMenuClickHandler(new RemoveAllJobsClickHandlerImpl(view));
        view.addRemoveAllJumpGatesMenuClickHandler(new RemoveAllJumpGatesClickHandlerImpl(view));
        view.addRemoveAllMarketsMenuClickHandler(new RemoveAllMarketsClickHandlerImpl(view));
        view.addRemoveAllPlanetsMenuClickHandler(new RemoveAllPlanetsClickHandlerImpl(view));
        view.addRemoveAllShipsMenuClickHandler(new RemoveAllShipsClickHandlerImpl(view));
        view.addRemoveAllShipTypesMenuClickHandler(new RemoveAllShipTypesClickHandlerImpl(view));
        view.addRemoveAllSolarSystemsMenuClickHandler(new RemoveAllSolarSystemsClickHandlerImpl(view));
        view.addRemoveAllSunsMenuClickHandler(new RemoveAllSunsClickHandlerImpl(view));
        view.addRemoveAllUsersMenuClickHandler(new RemoveAllUsersClickHandlerImpl(view));
        view.addViewSolarSystem1049MenuClickHandler(new ViewSolarSystem1049MenuClickHandlerImpl(eventBus));
        view.addSetUserAsNewMenuClickHandler(new SetUserAsNewClickHandlerImpl(view));
        view.addNewCaptainWizardButtonClickHandler(new NewCaptainWizardButtonClickHandlerImpl(eventBus));
        view.addAboutButtonClickHandler(new AboutButtonClickHandlerImpl(eventBus));
        view.addPrimerButtonClickHandler(new PrimerClickHandlerImpl(eventBus));
    }

    /**
     * 
     * {@inheritDoc}
     */

    @Override
    public void destroyView() {
        getView().destroy();

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void hideView() {
        getView().hide();

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onCurrentUserChanged(final CurrentUserChangedEvent event) {

        UserDTO user = event.getCurrentUser();
        setModel(user);
        if (profileHandler != null) {
            profileHandler.setUser(user);
        }
        if (user != null) {
            getView().setUser(user, event.getLogoutUrl());
            getView().setProfileButtonDisabled(false);
            getView().setFleetSummaryButtonDisabled(false);
            // getView().getCalendarSummaryButton().setDisabled(false);
            getView().setOpportunitiesButtonDisabled(false);
            getView().setCommunicationsButtonDisabled(false);
        } else {
            getView().setLogin(event.getLoginUrl());
        }
        getView().setLogoutButtonDisabled(false);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void renderView() {
        getView().render();
    }

    /**
     * 
     * {@inheritDoc}
     */

    @Override
    public void showView() {
        getView().show();

    }
}
