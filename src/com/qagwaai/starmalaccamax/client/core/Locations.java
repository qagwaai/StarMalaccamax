/**
 * Locations.java
 * Created by pgirard at 10:01:58 AM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.client package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.core;

/**
 * @author pgirard
 * 
 */
public final class Locations {

    /**
	 * 
	 */
    private static final String LANDING_PAGE = "!landing";

    /**
	 * 
	 */
    private static final String USER_ADMIN = "!useradmin";

    /**
	 * 
	 */
    private static final String SOLARSYSTEM_ADMIN = "!solarsystemadmin";
    /**
	 * 
	 */
    private static final String PLANET_ADMIN = "!planetadmin";

    /**
	 * 
	 */
    private static final String PLAYER_SUMMARY = "!playersummary";
    /**
	 * 
	 */
    private static final String PLAYER_CALENDAR = "!playercalendar";
    /**
	 * 
	 */
    private static final String PLAYER_COMMUNICATIONS = "!playercommunications";

    /**
	 * 
	 */
    private static final String JUMPGATE_ADMIN = "!jumpgateadmin";
    /**
	 * 
	 */
    private static final String STAR_ADMIN = "!staradmin";
    /**
	 * 
	 */
    private static final String JOB_ADMIN = "!jobadmin";
    /**
	 * 
	 */
    private static final String SHIP_TYPE_ADMIN = "!shiptypeadmin";
    /**
	 * 
	 */
    private static final String MARKET_ADMIN = "!marketadmin";
    /**
	 * 
	 */
    private static final String SHIP_ADMIN = "!shipadmin";
    /**
	 * 
	 */
    private static final String GAME_EVENT_ADMIN = "!gameeventadmin";
    /**
	 * 
	 */
    private static final String CAPTAIN_ADMIN = "!captainadmin";
    /**
	 * 
	 */
    private static final String CLOSEST_ADMIN = "!closestadmin";

    /**
	 * 
	 */
    private static final String PLAYER_OPPORTUNITIES = "!playeropportunities";

    /**
	 * 
	 */
    private static final String SOLAR_SYSTEM_VISUALIZATION = "solarSystemViz";

    private static final String PRIMER = "primer";

    /**
     * 
     * @return the captain admin page identifier
     */
    public static String getCaptainAdminPage() {
        return CAPTAIN_ADMIN;
    }

    /**
     * 
     * @return the closest admin page identifier
     */
    public static String getClosestAdminPage() {
        return CLOSEST_ADMIN;
    }

    /**
     * 
     * @return the game event admin page identifier
     */
    public static String getGameEventAdminPage() {
        return GAME_EVENT_ADMIN;
    }

    /**
     * 
     * @return the job admin page identifier
     */
    public static String getJobAdminPage() {
        return JOB_ADMIN;
    }

    /**
     * 
     * @return the jump gate admin page identifier
     */
    public static String getJumpGateAdminPage() {
        return JUMPGATE_ADMIN;
    }

    /**
     * @return the landingPage
     */
    public static String getLandingPage() {
        return LANDING_PAGE;
    }

    /**
     * 
     * @return the market admin page identifier
     */
    public static String getMarketAdminPage() {
        return MARKET_ADMIN;
    }

    /**
     * 
     * @return the planet admin page identifier
     */
    public static String getPlanetAdminPage() {
        return PLANET_ADMIN;
    }

    /**
     * 
     * @return the player calendar page identifier
     */
    public static String getPlayerCalendarPage() {
        return PLAYER_CALENDAR;
    }

    /**
     * 
     * @return the player communications page
     */
    public static String getPlayerCommuncationsPage() {
        return PLAYER_COMMUNICATIONS;
    }

    /**
     * 
     * @return the player opportunities page
     */
    public static String getPlayerOpportunitiesPage() {
        return PLAYER_OPPORTUNITIES;
    }

    /**
     * 
     * @return the player summary page identifier
     */
    public static String getPlayerSummaryPage() {
        return PLAYER_SUMMARY;
    }

    /**
     * 
     * @return the ship admin page identifier
     */
    public static String getShipAdminPage() {
        return SHIP_ADMIN;
    }

    /**
     * 
     * @return the ship type admin page identifier
     */
    public static String getShipTypeAdminPage() {
        return SHIP_TYPE_ADMIN;
    }

    /**
     * @return the solarsystemAdmin page identifier
     */
    public static String getSolarSystemAdminPage() {
        return SOLARSYSTEM_ADMIN;
    }

    /**
     * 
     * @return the solarsystemVisualzation page identifier
     */
    public static String getSolarSystemVisualizationPage() {
        return SOLAR_SYSTEM_VISUALIZATION;
    }

    /**
     * 
     * @return the star admin page identifier
     */
    public static String getStarAdminPage() {
        return STAR_ADMIN;
    }

    /**
     * 
     * @return the admin page
     */
    public static String getUserAdminPage() {
        return USER_ADMIN;
    }

    public static String getPrimerPage() {
        return PRIMER;
    }

    /**
	 * 
	 */
    private Locations() {

    }
}
