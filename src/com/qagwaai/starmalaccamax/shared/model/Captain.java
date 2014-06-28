package com.qagwaai.starmalaccamax.shared.model;

/**
 * 
 * @author pgirard
 * 
 */
public interface Captain extends Model {

    /**
     * @return the attributes
     */
    CaptainAttributes getCaptainAttributes();

    /**
     * @return the color
     */
    String getColor();

    /**
     * @return the id
     */
    Long getId();

    /**
     * @return the name
     */
    String getName();

    /**
     * 
     * @return the unique identifier
     */
    Long getOwnerId();

    /**
     * @return the skills
     */
    CaptainSkills getSkills();

    /**
     * 
     * @param attributes
     *            the captain's attributes
     */
    void setCaptainAttributes(CaptainAttributes attributes);

    /**
     * @param color
     *            the color to set
     */
    void setColor(final String color);

    /**
     * @param id
     *            the id to set
     */
    void setId(final Long id);

    /**
     * @param name
     *            the name to set
     */
    void setName(final String name);

    /**
     * 
     * @param id
     *            the unique identifier
     */
    void setOwnerId(Long id);

    /**
     * 
     * @param skills
     *            the captain's skills
     */
    void setSkills(CaptainSkills skills);

    String getGender();

    void setGender(String gender);

    String getRace();

    void setRace(String race);
}