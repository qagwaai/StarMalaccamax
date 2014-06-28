/**
 * Distance.java
 * Created by pgirard at 9:50:08 AM on Dec 17, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

/**
 * @author pgirard
 * 
 */
public interface Distance extends Model {

    /**
     * 
     * @return the distance in AU
     */
    Double getDistanceInAU();

    /**
     * 
     * @return the distance in km
     */
    Long getDistanceInKM();

    /**
     * 
     * @return the number of jumps
     */
    int getJumps();

    /**
     * 
     * @param distance
     *            the distance in AU
     */
    void setDistanceInAU(Double distance);

    /**
     * 
     * @param distance
     *            the distance in km
     */
    void setDistanceInKM(Long distance);

    /**
     * 
     * @param jumps
     *            the number of jumps
     */
    void setJumps(int jumps);

}
