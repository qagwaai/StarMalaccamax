/**
 * CommoditiesList.java
 * Created by pgirard at 1:48:20 PM on Nov 8, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class ShipAttributes implements IsSerializable, Serializable, Model {
    /**
	 * 
	 */
    public static final String SHIELDS = "Shields";
    /**
	 * 
	 */
    public static final String COMPUTER = "Computer";
    /**
	 * 
	 */
    public static final String STORAGE = "Storage";
    /**
	 * 
	 */
    public static final String LASERS = "Lasers";
    /**
	 * 
	 */
    public static final String MISSILES = "Missiles";
    /**
	 * 
	 */
    public static final String REACTIVEENGINES = "Reactive Engines";
    /**
	 * 
	 */
    public static final String JUMPENGINES = "Jump Engines";

    private int shields;
    private int computer;
    private int storage;
    private int lasers;
    private int missiles;
    private int reactiveEngines;
    private int jumpEngines;

    /**
	 * 
	 */
    public ShipAttributes() {
        super();
    }

    /**
     * @param shields shields value
     * @param computer computer value
     * @param storage storage value
     * @param lasers lasers value
     * @param missiles missles value
     * @param reactiveEngines reactiveEngines value
     * @param jumpEngines jumpEngines value
     */
    public ShipAttributes(final int shields, final int computer, final int storage, final int lasers, final int missiles, final int reactiveEngines,
        final int jumpEngines) {
        super();
        this.shields = shields;
        this.computer = computer;
        this.storage = storage;
        this.lasers = lasers;
        this.missiles = missiles;
        this.reactiveEngines = reactiveEngines;
        this.jumpEngines = jumpEngines;
    }

    /**
     * @return the shields
     */
    public int getShields() {
        return shields;
    }

    /**
     * @param shields
     *            the shields to set
     */
    public void setShields(int shields) {
        this.shields = shields;
    }

    /**
     * @return the computer
     */
    public int getComputer() {
        return computer;
    }

    /**
     * @param computer
     *            the computer to set
     */
    public void setComputer(int computer) {
        this.computer = computer;
    }

    /**
     * @return the storage
     */
    public int getStorage() {
        return storage;
    }

    /**
     * @param storage
     *            the storage to set
     */
    public void setStorage(int storage) {
        this.storage = storage;
    }

    /**
     * @return the lasers
     */
    public int getLasers() {
        return lasers;
    }

    /**
     * @param lasers
     *            the lasers to set
     */
    public void setLasers(int lasers) {
        this.lasers = lasers;
    }

    /**
     * @return the missiles
     */
    public int getMissiles() {
        return missiles;
    }

    /**
     * @param missiles
     *            the missiles to set
     */
    public void setMissiles(int missiles) {
        this.missiles = missiles;
    }

    /**
     * @return the reactiveEngines
     */
    public int getReactiveEngines() {
        return reactiveEngines;
    }

    /**
     * @param reactiveEngines
     *            the reactiveEngines to set
     */
    public void setReactiveEngines(int reactiveEngines) {
        this.reactiveEngines = reactiveEngines;
    }

    /**
     * @return the jumpEngines
     */
    public int getJumpEngines() {
        return jumpEngines;
    }

    /**
     * @param jumpEngines
     *            the jumpEngines to set
     */
    public void setJumpEngines(int jumpEngines) {
        this.jumpEngines = jumpEngines;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ShipAttributes [shields=" + shields + ", computer=" + computer + ", storage=" + storage + ", lasers="
            + lasers + ", missiles=" + missiles + ", reactiveEngines=" + reactiveEngines + ", jumpEngines="
            + jumpEngines + "]";
    }

    public void fromMap(final Map<String, Integer> skills) {
        for (String key : skills.keySet()) {
            if (key.equalsIgnoreCase(ShipAttributes.SHIELDS)) {
                setShields(skills.get(key));
            }
            if (key.equalsIgnoreCase(ShipAttributes.COMPUTER)) {
                setComputer(skills.get(key));
            }
            if (key.equalsIgnoreCase(ShipAttributes.STORAGE)) {
                setStorage(skills.get(key));
            }
            if (key.equalsIgnoreCase(ShipAttributes.LASERS)) {
                setLasers(skills.get(key));
            }
            if (key.equalsIgnoreCase(ShipAttributes.MISSILES)) {
                setMissiles(skills.get(key));
            }
            if (key.equalsIgnoreCase(ShipAttributes.REACTIVEENGINES)) {
                setReactiveEngines(skills.get(key));
            }
            if (key.equalsIgnoreCase(ShipAttributes.JUMPENGINES)) {
                setJumpEngines(skills.get(key));
            }
        }
    }

    public Map<String, Integer> toMap() {
        Map<String, Integer> values = new HashMap<String, Integer>();
        values.put(ShipAttributes.SHIELDS, getShields());
        values.put(ShipAttributes.COMPUTER, getComputer());
        values.put(ShipAttributes.STORAGE, getStorage());
        values.put(ShipAttributes.LASERS, getLasers());
        values.put(ShipAttributes.MISSILES, getMissiles());
        values.put(ShipAttributes.REACTIVEENGINES, getReactiveEngines());
        values.put(ShipAttributes.JUMPENGINES, getJumpEngines());

        return values;
    }
}
