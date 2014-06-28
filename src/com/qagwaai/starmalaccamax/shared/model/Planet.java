/**
 * Planet.java
 * Created by pgirard at 1:49:22 PM on Sep 9, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;


/**
 * @author pgirard
 * 
 */
public interface Planet extends Model {
    /**
     * <ul>
     * <li>0: None</li>
     * <li>1: Trace</li>
     * <li>2: Very thin, Tainted</li>
     * <li>3: Very thin</li>
     * <li>4: Thin, Tainted</li>
     * <li>5: Thin</li>
     * <li>6: Standard</li>
     * <li>7: Standard, Tainted</li>
     * <li>8: Dense</li>
     * <li>9: Dense, Tainted</li>
     * <li>10: Exotic</li>
     * <li>11: Corrosive</li>
     * <li>12: Insidious</li>
     * <li>13: Dense, high</li>
     * <li>14: Ellipsoid</li>
     * <li>15: Thin, low</li>
     * </ul>
     * 
     * @return the atmosphere of the planet
     */
    int getAtmosphere();

    /**
     * <ul>
     * <li>0 None</li>
     * <li>1 Very low quality</li>
     * <li>2 Low quality</li>
     * <li>3 Low quality</li>
     * <li>4 Standard</li>
     * <li>5 Standard</li>
     * <li>6 Standard</li>
     * <li>7 High quality</li>
     * <li>8 High quality</li>
     * <li>9 Very high quality</li>
     * <li>10 The best</li>
     * </ul>
     * 
     * @return the quality of the dock
     */
    int getDockQuality();

    /**
     * <ul>
     * <li>0 No government structure.</li>
     * <li>1 Company/Corporate.</li>
     * <li>2 Participating Democracy.</li>
     * <li>3 Self-Perpetuating Oligarchy.</li>
     * <li>4 Representative Democracy.</li>
     * <li>5 Feudal Technocracy.</li>
     * <li>6 Captive Government</li>
     * <li>7 Balkanization</li>
     * <li>8 Civil Service Bureaucracy</li>
     * <li>9 Impersonal Bureaucracy</li>
     * <li>10 Charismatic Dictator</li>
     * <li>11 Non-Charismatic Leader</li>
     * <li>12 Charismatic Oligarchy</li>
     * <li>13 Religious Dictatorship</li>
     * </ul>
     * 
     * @return the government type
     */
    int getGovernment();

    /**
     * 
     * @return the percentage of the surface covered in water
     */
    Double getHydrographics();

    /**
     * @return the id
     */
    Long getId();

    /**
     * <ul>
     * <li>0 No prohibitions.</li>
     * <li>1 Body pistols undetectable by standard detectors, explosives (bombs, grenades), and poison gas prohibited</li>
     * <li>2 Portable energy weapons (laser carbine, laser rifle) prohibited. Ship's gunnery not affected.</li>
     * <li>3 Weapons of a strict military nature (machine guns, automatic rifles) prohibited.</li>
     * <li>4 Light assault weapons (sub-machineguns) prohibited.</li>
     * <li>5 Personal concealable firearms (pistols, revolvers) prohibited.</li>
     * <li>6 Most firearms (all except shotguns) prohibited. The carrying of any type of weapon openly is discouraged.</li>
     * <li>7 Shotguns are prohibited.</li>
     * <li>8 Long bladed weapons (all but daggers) are controlled, and open posession is prohibited.</li>
     * <li>9 Posession of any weapon outside one's residence is prohibited.</li>
     * </ul>
     * 
     * @return the law level
     */
    int getLawLevel();

    /**
     * 
     * @return the location of the planet
     */
    LocationDTO getLocation();

    /**
     * 
     * @return the name of the planet
     */
    String getName();

    /**
     * 
     * @return the orbit of the planet in the solarsystem
     */
    int getOrbit();

    /**
     * 
     * @return the orbital distance
     */
    Double getOrbitalDistance();

    /**
     * 
     * @return the population of the planet
     */
    Long getPopulation();

    /**
     * 
     * @return if the planet is a satellite, the unique identifier of the main
     *         planet
     */
    Long getSatelliteParentId();

    /**
     * Size of the planet in kilometers.
     * 
     * Small worlds are around 200km Huge planets are around 16,000km Gas Giants
     * start at 25,000km and end around 100,000km
     * 
     * @return the size of the planet
     */
    int getSize();

    /**
     * 
     * @return the parent solar system id
     */
    Long getSolarSystemId();

    /**
     * 0 - rocks 20 - god like
     * 
     * @return the tech level
     */
    int getTechLevel();

    /**
     * 
     * @return true if this planet is a gas giant
     */
    boolean isGasGiant();

    /**
     * 
     * @return true if this is the main planet for the solar system
     */
    boolean isMainWorld();

    /**
     * 
     * @return true if the planet is a satellite of another planet
     */
    boolean isSatellite();

    /**
     * 
     * @param atmosphere
     *            the atmosphere of the planet
     */
    void setAtmosphere(int atmosphere);

    /**
     * 
     * @param dockQuality
     *            the quality of the dock
     */
    void setDockQuality(int dockQuality);

    /**
     * 
     * @param isGasGiant
     *            true if this planet is a gas giant
     */
    void setGasGiant(boolean isGasGiant);

    /**
     * 
     * @param government
     *            the government type
     */
    void setGovernment(int government);

    /**
     * 
     * @param hydrographics
     *            the percentage of the surface covered in water
     */
    void setHydrographics(Double hydrographics);

    /**
     * @param id
     *            the id to set
     */
    void setId(final Long id);

    /**
     * 
     * @param lawLevel
     *            the law level
     */
    void setLawLevel(int lawLevel);

    /**
     * 
     * @param location
     *            the location of the planet
     */
    void setLocation(LocationDTO location);

    /**
     * 
     * @param isMainWorld
     *            true if this is the main planet for the solar system
     */
    void setMainWorld(boolean isMainWorld);

    /**
     * 
     * @param name
     *            the name of the planet
     */
    void setName(String name);

    /**
     * 
     * @param orbit
     *            the orbit
     */
    void setOrbit(int orbit);

    /**
     * 
     * @param orbitalDistance
     *            the orbital distance
     */
    void setOrbitalDistance(Double orbitalDistance);

    /**
     * 
     * @param population
     *            the population of the planet
     */
    void setPopulation(Long population);

    /**
     * 
     * @param isSatellite
     *            true if the planet is a satellite of another planet
     */
    void setSatellite(boolean isSatellite);

    /**
     * 
     * @param planetId
     *            if the planet is a satellite, the unique id of the main planet
     */
    void setSatelliteParentId(Long planetId);

    /**
     * 
     * @param size
     *            the size of the planet in km
     */
    void setSize(int size);

    /**
     * 
     * @param solarSystemId
     *            the solar system id
     */
    void setSolarSystemId(Long solarSystemId);

    /**
     * 
     * @param techLevel
     *            the tech level
     */
    void setTechLevel(int techLevel);

    int getOrbitCenterX();

    void setOrbitCenterX(int x);

    int getOrbitCenterY();

    void setOrbitCenterY(int y);

    Double getOrbitalEccentricity();

    void setOrbitalEccentricity(Double eccentricity);

    int getOrbitRotation();

    void setOrbitRotation(int angle);

    int getOrbitXRel();

    void setOrbitXRel(int xrel);

    int getOrbitYRel();

    void setOrbitYRel(int yrel);

}
