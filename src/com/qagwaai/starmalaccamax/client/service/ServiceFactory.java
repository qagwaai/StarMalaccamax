/**
 * ServiceFactory.java
 * Created by pgirard at 11:37:55 AM on Aug 27, 2010
 * in the com.qagwaai.starmalaccamax.client.service package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service;

import com.google.gwt.core.client.GWT;

/**
 * @author pgirard
 * 
 */
public final class ServiceFactory {
    /**
	 * 
	 */
    private static UserServiceAsync userService;

    /**
	 * 
	 */
    private static LoginServiceAsync loginService;

    /**
	 * 
	 */
    private static CaptainServiceAsync captainService;

    /**
	 * 
	 */
    private static SolarSystemServiceAsync solarSystemService;

    /**
	 * 
	 */
    private static PlanetServiceAsync planetService;

    /**
	 * 
	 */
    private static JumpGateServiceAsync jumpGateService;

    /**
	 * 
	 */
    private static StarServiceAsync starService;
    /**
	 * 
	 */
    private static JobServiceAsync jobService;
    /**
	 * 
	 */
    private static MarketServiceAsync marketService;
    /**
	 * 
	 */
    private static ShipServiceAsync shipService;
    /**
	 * 
	 */
    private static PlayerSummaryServiceAsync playerSummaryService;

    /**
	 * 
	 */
    private static GameEventServiceAsync gameEventService;

    /**
	 * 
	 */
    private static ChannelServiceAsync channelService;

    /**
	 * 
	 */
    private static UtilityServiceAsync utilityService;

    /**
     * @return the captainService
     */
    public static CaptainServiceAsync getCaptainService() {
        if (captainService == null) {
            captainService = GWT.create(CaptainService.class);
        }
        return captainService;
    }

    /**
     * 
     * @return the handle to the channel service
     */
    public static ChannelServiceAsync getChannelService() {
        if (channelService == null) {
            channelService = GWT.create(ChannelService.class);
        }
        return channelService;
    }

    /**
     * 
     * @return the remote gameEvent service
     */
    public static GameEventServiceAsync getGameEventService() {
        if (gameEventService == null) {
            gameEventService = GWT.create(GameEventService.class);
        }
        return gameEventService;
    }

    /**
     * 
     * @return the remote job service
     */
    public static JobServiceAsync getJobService() {
        if (jobService == null) {
            jobService = GWT.create(JobService.class);
        }
        return jobService;
    }

    /**
     * 
     * @return the remote jump Gate service
     */
    public static JumpGateServiceAsync getJumpGateService() {
        if (jumpGateService == null) {
            jumpGateService = GWT.create(JumpGateService.class);
        }
        return jumpGateService;
    }

    /**
     * 
     * @return the handle to the login service
     */
    public static LoginServiceAsync getLoginService() {
        if (loginService == null) {
            loginService = GWT.create(LoginService.class);
        }
        return loginService;
    }

    /**
     * 
     * @return the remote job service
     */
    public static MarketServiceAsync getMarketService() {
        if (marketService == null) {
            marketService = GWT.create(MarketService.class);
        }
        return marketService;
    }

    /**
     * 
     * @return the remote planet service
     */
    public static PlanetServiceAsync getPlanetService() {
        if (planetService == null) {
            planetService = GWT.create(PlanetService.class);
        }
        return planetService;
    }

    /**
     * 
     * @return the handle to the player summary service
     */
    public static PlayerSummaryServiceAsync getPlayerSummaryService() {
        if (playerSummaryService == null) {
            playerSummaryService = GWT.create(PlayerSummaryService.class);
        }
        return playerSummaryService;
    }

    /**
     * 
     * @return the handle to the ship service
     */
    public static ShipServiceAsync getShipService() {
        if (shipService == null) {
            shipService = GWT.create(ShipService.class);
        }
        return shipService;
    }

    /**
     * 
     * @return the remote solar system service
     */
    public static SolarSystemServiceAsync getSolarSystemService() {
        if (solarSystemService == null) {
            solarSystemService = GWT.create(SolarSystemService.class);
        }
        return solarSystemService;
    }

    /**
     * 
     * @return the remote star service
     */
    public static StarServiceAsync getStarService() {
        if (starService == null) {
            starService = GWT.create(StarService.class);
        }
        return starService;
    }

    /**
     * 
     * @return the handle to the user service
     */
    public static UserServiceAsync getUserService() {
        if (userService == null) {
            userService = GWT.create(UserService.class);
        }
        return userService;
    }

    /**
     * 
     * @return the handle to the utility service
     */
    public static UtilityServiceAsync getUtilityService() {
        if (utilityService == null) {
            utilityService = GWT.create(UtilityService.class);
        }
        return utilityService;
    }

    /**
	 * 
	 */
    private ServiceFactory() {
    }
}
