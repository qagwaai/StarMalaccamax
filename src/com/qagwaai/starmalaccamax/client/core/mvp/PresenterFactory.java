/**
 * PresenterFactory.java
 * Created by pgirard at 9:45:23 AM on Dec 3, 2010
 * in the com.qagwaai.starmalaccamax.client.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.core.mvp;

import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.client.admin.mvp.CaptainAdminPresenterImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.CaptainAdminViewImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.ClosestAdminPresenterImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.ClosestAdminViewImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.GameEventAdminPresenterImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.GameEventAdminViewImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.JobAdminPresenterImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.JobAdminViewImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.JumpGateAdminPresenterImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.JumpGateAdminViewImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.MarketAdminPresenterImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.MarketAdminViewImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.PlanetAdminPresenterImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.PlanetAdminViewImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.ShipAdminPresenterImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.ShipAdminViewImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.ShipTypeAdminPresenterImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.ShipTypeAdminViewImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.SolarSystemAdminPresenterImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.SolarSystemAdminViewImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.StarAdminPresenterImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.StarAdminViewImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.UserAdminPresenterImpl;
import com.qagwaai.starmalaccamax.client.admin.mvp.UserAdminViewImpl;
import com.qagwaai.starmalaccamax.client.core.Locations;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.LandingPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.LandingViewImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerCalendarPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerCalendarViewImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerCommunicationsViewImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerOpportunitiesPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerOpportunitiesViewImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.PlayerSummaryViewImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.PrimerPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.PrimerViewImpl;
import com.qagwaai.starmalaccamax.shared.model.Captain;
import com.qagwaai.starmalaccamax.shared.model.Closest;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.GameEvent;
import com.qagwaai.starmalaccamax.shared.model.Job;
import com.qagwaai.starmalaccamax.shared.model.JumpGate;
import com.qagwaai.starmalaccamax.shared.model.Market;
import com.qagwaai.starmalaccamax.shared.model.Model;
import com.qagwaai.starmalaccamax.shared.model.Planet;
import com.qagwaai.starmalaccamax.shared.model.Ship;
import com.qagwaai.starmalaccamax.shared.model.ShipType;
import com.qagwaai.starmalaccamax.shared.model.SolarSystem;
import com.qagwaai.starmalaccamax.shared.model.Star;
import com.qagwaai.starmalaccamax.shared.model.User;

/**
 * @author pgirard
 * 
 */
public final class PresenterFactory {
    /**
     * 
     * @param token
     *            the token (unique) of the presenter to present
     * @param eventBus
     *            the bus for the presenter to publish onto
     * @param model
     *            any model used in the presenter
     * @param filter
     *            an optional filter
     * @return a fully instantiated presenter
     */
    public static Presenter create(final String token, final EventBus eventBus, final Model model, final Filter filter) {
        Presenter presenter = null;
        GWT.log("creating presenter for " + token);
        if (token.equals(Locations.getPlayerSummaryPage())) {
            presenter = new PlayerSummaryPresenterImpl(eventBus, new PlayerSummaryViewImpl(token), (User) model);
        }
        if (token.equals(Locations.getLandingPage())) {
            presenter = new LandingPresenterImpl(eventBus, new LandingViewImpl(token), (User) model);
        }
        if (token.equals(Locations.getUserAdminPage())) {
            presenter = new UserAdminPresenterImpl(eventBus, new UserAdminViewImpl(token), (User) model);
        }
        if (token.equals(Locations.getSolarSystemAdminPage())) {
            presenter =
                new SolarSystemAdminPresenterImpl(eventBus, new SolarSystemAdminViewImpl(token), (SolarSystem) model);
        }
        if (token.equals(Locations.getPlanetAdminPage())) {
            if (filter != null) {
                presenter =
                    new PlanetAdminPresenterImpl(eventBus, new PlanetAdminViewImpl(token), (Planet) model, filter);
            } else {
                presenter = new PlanetAdminPresenterImpl(eventBus, new PlanetAdminViewImpl(token), (Planet) model);
            }
        }
        if (token.equals(Locations.getPlayerCalendarPage())) {
            presenter = new PlayerCalendarPresenterImpl(eventBus, new PlayerCalendarViewImpl(token), (User) model);
        }
        if (token.equals(Locations.getJumpGateAdminPage())) {
            presenter = new JumpGateAdminPresenterImpl(eventBus, new JumpGateAdminViewImpl(token), (JumpGate) model);
        }
        if (token.equals(Locations.getStarAdminPage())) {
            presenter = new StarAdminPresenterImpl(eventBus, new StarAdminViewImpl(token), (Star) model);
        }
        if (token.equals(Locations.getJobAdminPage())) {
            presenter = new JobAdminPresenterImpl(eventBus, new JobAdminViewImpl(token), (Job) model);
        }
        if (token.equals(Locations.getShipTypeAdminPage())) {
            presenter = new ShipTypeAdminPresenterImpl(eventBus, new ShipTypeAdminViewImpl(token), (ShipType) model);
        }
        if (token.equals(Locations.getMarketAdminPage())) {
            presenter = new MarketAdminPresenterImpl(eventBus, new MarketAdminViewImpl(token), (Market) model);
        }
        if (token.equals(Locations.getShipAdminPage())) {
            presenter = new ShipAdminPresenterImpl(eventBus, new ShipAdminViewImpl(token), (Ship) model);
        }
        if (token.equals(Locations.getGameEventAdminPage())) {
            presenter = new GameEventAdminPresenterImpl(eventBus, new GameEventAdminViewImpl(token), (GameEvent) model);
        }
        if (token.equals(Locations.getCaptainAdminPage())) {
            presenter = new CaptainAdminPresenterImpl(eventBus, new CaptainAdminViewImpl(token), (Captain) model);
        }
        if (token.equals(Locations.getClosestAdminPage())) {
            presenter = new ClosestAdminPresenterImpl(eventBus, new ClosestAdminViewImpl(token), (Closest) model);
        }
        if (token.equals(Locations.getPlayerOpportunitiesPage())) {
            presenter = new PlayerOpportunitiesPresenterImpl(eventBus, new PlayerOpportunitiesViewImpl(token), (User) model);
        }
        if (token.equals(Locations.getPlayerCommuncationsPage())) {
            presenter = new PlayerCommunicationsPresenterImpl(eventBus, new PlayerCommunicationsViewImpl(token), (User) model);
        }
        if (token.equals(Locations.getPrimerPage())) {
            presenter = new PrimerPresenterImpl(eventBus, new PrimerViewImpl(token), (User) model);
        }
        return presenter;
    }

    /**
	 * 
	 */
    private PresenterFactory() {

    }
}
