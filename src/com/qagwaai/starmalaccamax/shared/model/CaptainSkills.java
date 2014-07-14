/**
 * Skills.java
 * Created by pgirard at 8:07:44 PM on Aug 29, 2010
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
public final class CaptainSkills implements IsSerializable, Serializable, Model {
    /**
	 * 
	 */
    public static final String ATTACK = "Attack";
    /**
	 * 
	 */
    public static final String DEFENSE = "Defense";
    /**
	 * 
	 */
    public static final String ENGINES = "Engines";
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
    public static final String NAVIGATION = "Navigation";
    /**
	 * 
	 */
    public static final String NEGOTIATION = "Negotiation";
    /**
	 * 
	 */
    public static final String REPAIR = "Repair";
    /**
	 * 
	 */
    public static final String SHIELDS = "Sheilds";
    /**
	 * 
	 */
    public static final String STORAGE = "Storage";
    /**
	 * 
	 */
    private int defense;
    /**
	 * 
	 */
    private int engines;
    /**
	 * 
	 */
    private int lasers;
    /**
	 * 
	 */
    private int missiles;
    /**
	 * 
	 */
    private int navigation;
    /**
	 * 
	 */
    private int negotiation;
    /**
	 * 
	 */
    private int repair;
    /**
	 * 
	 */
    private int shields;
    /**
	 * 
	 */
    private int storage;

    /**
	 * 
	 */
    private int attack;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CaptainSkills other = (CaptainSkills) obj;
        if (attack != other.attack) {
            return false;
        }
        if (defense != other.defense) {
            return false;
        }
        if (engines != other.engines) {
            return false;
        }
        if (lasers != other.lasers) {
            return false;
        }
        if (missiles != other.missiles) {
            return false;
        }
        if (navigation != other.navigation) {
            return false;
        }
        if (negotiation != other.negotiation) {
            return false;
        }
        if (repair != other.repair) {
            return false;
        }
        if (shields != other.shields) {
            return false;
        }
        if (storage != other.storage) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @param skills
     *            the skills in a map
     */
    public void fromMap(final Map<String, Integer> skills) {
        for (String key : skills.keySet()) {
            if (key.equalsIgnoreCase(CaptainSkills.ATTACK)) {
                setAttack(skills.get(key));
            }
            if (key.equalsIgnoreCase(CaptainSkills.DEFENSE)) {
                setDefense(skills.get(key));
            }
            if (key.equalsIgnoreCase(CaptainSkills.ENGINES)) {
                setEngines(skills.get(key));
            }
            if (key.equalsIgnoreCase(CaptainSkills.LASERS)) {
                setLasers(skills.get(key));
            }
            if (key.equalsIgnoreCase(CaptainSkills.MISSILES)) {
                setMissiles(skills.get(key));
            }
            if (key.equalsIgnoreCase(CaptainSkills.NAVIGATION)) {
                setNavigation(skills.get(key));
            }
            if (key.equalsIgnoreCase(CaptainSkills.NEGOTIATION)) {
                setNegotiation(skills.get(key));
            }
            if (key.equalsIgnoreCase(CaptainSkills.REPAIR)) {
                setRepair(skills.get(key));
            }
            if (key.equalsIgnoreCase(CaptainSkills.SHIELDS)) {
                setShields(skills.get(key));
            }
            if (key.equalsIgnoreCase(CaptainSkills.STORAGE)) {
                setStorage(skills.get(key));
            }
        }
    }

    /**
     * @return the attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * @return the defense
     */
    public int getDefense() {
        return defense;
    }

    /**
     * @return the engines
     */
    public int getEngines() {
        return engines;
    }

    /**
     * @return the lasers
     */
    public int getLasers() {
        return lasers;
    }

    /**
     * @return the missiles
     */
    public int getMissiles() {
        return missiles;
    }

    /**
     * @return the navigation
     */
    public int getNavigation() {
        return navigation;
    }

    /**
     * @return the negotiation
     */
    public int getNegotiation() {
        return negotiation;
    }

    /**
     * @return the repair
     */
    public int getRepair() {
        return repair;
    }

    /**
     * @return the shields
     */
    public int getShields() {
        return shields;
    }

    /**
     * @return the storage
     */
    public int getStorage() {
        return storage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + attack;
        result = prime * result + defense;
        result = prime * result + engines;
        result = prime * result + lasers;
        result = prime * result + missiles;
        result = prime * result + navigation;
        result = prime * result + negotiation;
        result = prime * result + repair;
        result = prime * result + shields;
        result = prime * result + storage;
        return result;
    }

    /**
     * @param attack
     *            the attack to set
     */
    public void setAttack(final int attack) {
        this.attack = attack;
    }

    /**
     * @param defense
     *            the defense to set
     */
    public void setDefense(final int defense) {
        this.defense = defense;
    }

    /**
     * @param engines
     *            the engines to set
     */
    public void setEngines(final int engines) {
        this.engines = engines;
    }

    /**
     * @param lasers
     *            the lasers to set
     */
    public void setLasers(final int lasers) {
        this.lasers = lasers;
    }

    /**
     * @param missiles
     *            the missiles to set
     */
    public void setMissiles(final int missiles) {
        this.missiles = missiles;
    }

    /**
     * @param navigation
     *            the navigation to set
     */
    public void setNavigation(final int navigation) {
        this.navigation = navigation;
    }

    /**
     * @param negotiation
     *            the negotiation to set
     */
    public void setNegotiation(final int negotiation) {
        this.negotiation = negotiation;
    }

    /**
     * @param repair
     *            the repair to set
     */
    public void setRepair(final int repair) {
        this.repair = repair;
    }

    /**
     * @param shields
     *            the shields to set
     */
    public void setShields(final int shields) {
        this.shields = shields;
    }

    /**
     * @param storage
     *            the storage to set
     */
    public void setStorage(final int storage) {
        this.storage = storage;
    }

    /**
     * 
     * @return all skills in a map
     */
    public Map<String, Integer> toMap() {
        Map<String, Integer> values = new HashMap<String, Integer>();
        values.put(CaptainSkills.ATTACK, getAttack());
        values.put(CaptainSkills.DEFENSE, getDefense());
        values.put(CaptainSkills.ENGINES, getEngines());
        values.put(CaptainSkills.LASERS, getLasers());
        values.put(CaptainSkills.MISSILES, getMissiles());
        values.put(CaptainSkills.NAVIGATION, getNavigation());
        values.put(CaptainSkills.NEGOTIATION, getNegotiation());
        values.put(CaptainSkills.REPAIR, getRepair());
        values.put(CaptainSkills.SHIELDS, getShields());
        values.put(CaptainSkills.STORAGE, getStorage());

        return values;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Skills [attack=");
        builder.append(attack);
        builder.append(", defense=");
        builder.append(defense);
        builder.append(", engines=");
        builder.append(engines);
        builder.append(", lasers=");
        builder.append(lasers);
        builder.append(", missiles=");
        builder.append(missiles);
        builder.append(", navigation=");
        builder.append(navigation);
        builder.append(", negotiation=");
        builder.append(negotiation);
        builder.append(", repair=");
        builder.append(repair);
        builder.append(", shields=");
        builder.append(shields);
        builder.append(", storage=");
        builder.append(storage);
        builder.append("]");
        return builder.toString();
    }
}
