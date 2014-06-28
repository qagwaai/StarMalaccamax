/**
 * CommoditiesList.java
 * Created by pgirard at 1:48:20 PM on Nov 8, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

/**
 * @author pgirard
 * 
 */
public enum Commodities {
        /**
	 * 
	 */
        AGRICULTURE("Natural Resources", "Agriculture"),
        /**
	 * 
	 */
        BASE_METALS("Natural Resources", "Base Metals"),
        /**
	 * 
	 */
        CARBON_COMPOUNDS("Natural Resources", "Carbon Compounds"),
        /**
	 * 
	 */
        CRYSTALLINES("Natural Resources", "Crystallines"),
        /**
	 * 
	 */
        HYDROGEN("Natural Resources", "Hydrogen"),
        /**
	 * 
	 */
        LIVESTOCK("Natural Resources", "Livestock"),
        /**
	 * 
	 */
        NOBLE_GASES("Natural Resources", "Noble Gases"),
        /**
	 * 
	 */
        ORGANIC_FIBERS("Natural Resources", "Organic Fibers"),
        /**
	 * 
	 */
        OXYGEN("Natural Resources", "Oxygen"),
        /**
	 * 
	 */
        PLANKTON("Natural Resources", "Plankton "),
        /**
	 * 
	 */
        RARE_METALS("Natural Resources", "Rare Metals"),
        /**
	 * 
	 */
        RADIOACTIVES("Natural Resources", "Radioactives"),
        /**
	 * 
	 */
        RARE_MINERALS("Natural Resources", "Rare Minerals"),
        /**
	 * 
	 */
        RARE_PROTEINS("Natural Resources", "Rare Proteins"),
        /**
	 * 
	 */
        REACTIVE_GASES("Natural Resources", "Reactive Gases"),
        /**
	 * 
	 */
        BIOTECH_ORGANISMS("Industrial Components", "BioTech Organisms"),
        /**
	 * 
	 */
        CHEMICALS("Industrial Components", "Chemicals "),
        /**
	 * 
	 */
        ELECTRONICS("Industrial Components", "Electronics"),
        /**
	 * 
	 */
        MACHINED_PARTS("Industrial Components", "Machined Parts"),
        /**
	 * 
	 */
        NANITES("Industrial Components", "Nanites "),
        /**
	 * 
	 */
        PLASTICS("Industrial Components", "Plastics"),
        /**
	 * 
	 */
        ROBOTICS("Industrial Components", "Robotics"),
        /**
	 * 
	 */
        STRUCTURAL_COMPONENTS("Industrial Components", "Structural Components"),
        /**
	 * 
	 */
        SUSPENDED_PLASMA("Industrial Components", "Suspended Plasma"),
        /**
	 * 
	 */
        TEXTILES("Industrial Components", "Textiles"),
        /**
	 * 
	 */
        BIOFUEL("Manufactured Goods", "Biofuel"),
        /**
	 * 
	 */
        BUN("Manufactured Goods", "BUN (Basic Unit Nutrition)"),
        /**
	 * 
	 */
        COMPUTERS("Manufactured Goods", "Computers"),
        /**
	 * 
	 */
        CONSUMER_ELECTRONICS("Manufactured Goods", "Consumer Electronics"),
        /**
	 * 
	 */
        CONSUMER_GOODS("Manufactured Goods", "Consumer Goods"),
        /**
	 * 
	 */
        ENERGY_GRIDS("Manufactured Goods", "Energy Grids"),
        /**
	 * 
	 */
        EXOTIC_FOOD("Manufactured Goods", "Exotic Food"),
        /**
	 * 
	 */
        MANUFACTORIES("Manufactured Goods", "Manufactories"),
        /**
	 * 
	 */
        MEDICINE("Manufactured Goods", "Medicine"),
        /**
	 * 
	 */
        PRE_FAB_STRUCTURES("Manufactured Goods", "Pre-Fab Structures"),
        /**
	 * 
	 */
        PROCESSED_FOOD("Manufactured Goods", "Processed Food"),
        /**
	 * 
	 */
        SATELLITES("Manufactured Goods", "Satellites"),
        /**
	 * 
	 */
        SHIPS("Manufactured Goods", "Ships"),
        /**
	 * 
	 */
        VEHICLES("Manufactured Goods", "Vehicles");

    /**
     * 
     * @param key
     *            the key to search for
     * @return the found commodity or null
     */
    public static final Commodities getFromKey(final String key) {
        for (Commodities entry : Commodities.values()) {
            if (entry.getKey().equals(key)) {
                return entry;
            }
        }
        return null;
    }

    /**
	 * 
	 */
    private final String key;

    /**
	 * 
	 */
    private final String category;

    /**
     * 
     * @param category
     *            the category for the commodity
     * @param key
     *            the commodity name
     */
    Commodities(final String category, final String key) {
        this.key = key;
        this.category = category;
    }

    /**
     * @return the category
     */
    public final String getCategory() {
        return category;
    }

    /**
     * @return the key
     */
    public final String getKey() {
        return key;
    }

}
