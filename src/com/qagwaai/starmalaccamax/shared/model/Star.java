/**
 * Star.java
 * Created by pgirard at 11:17:07 AM on Sep 1, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

/**
 * @author pgirard
 * 
 */
public interface Star extends Model {
    /**
     * 
     * @return the component identifier
     */
    String getComponentIdentifier();

    /**
     * @return the id
     */
    Long getId();

    /**
     * 
     * @return the orbit
     */
    int getOrbit();

    /**
     * 
     * @return the size
     */
    String getSize();

    /**
     * 
     * @return the parent solar system
     */
    Long getSolarSystemId();

    /**
     * 
     * @return the spectral type
     */
    String getSpectralType();

    /**
     * 
     * @return the spectral type decimal
     */
    double getSpectralTypeDec();

    /**
     * 
     * @return true if is a companion star
     */
    boolean isCompanion();

    /**
     * 
     * @param isCompanion
     *            if is a companion
     */
    void setCompanion(boolean isCompanion);

    /**
     * 
     * @param componentId
     *            the component identifier
     */
    void setComponentIdentifier(String componentId);

    /**
     * @param id
     *            the id to set
     */
    void setId(final Long id);

    /**
     * 
     * @param orbit
     *            the orbit
     */
    void setOrbit(int orbit);

    /**
     * 
     * @param size
     *            the size
     */
    void setSize(String size);

    /**
     * 
     * @param solarSystemId
     *            the parent solar system
     */
    void setSolarSystemId(Long solarSystemId);

    /**
     * 
     * @param spectralType
     *            the spectral type
     */
    void setSpectralType(String spectralType);

    /**
     * 
     * @param spectralTypeDec
     *            the spectral type decimal
     */
    void setSpectralTypeDec(double spectralTypeDec);
}
