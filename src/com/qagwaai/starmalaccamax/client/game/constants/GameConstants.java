/**
 * GameConstants.java
 * Created by pgirard at 9:47:43 AM on Nov 24, 2010
 * in the com.qagwaai.starmalaccamax.client.constants package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.constants;

import com.google.gwt.i18n.client.Constants;

/**
 * @author pgirard
 * 
 */
public interface GameConstants extends Constants {
    // CAPTAIN
    /**
     * 
     * @return the captain color field label
     */
    @DefaultStringValue("Color")
    String captainColor();

    /**
     * @return the string
     */
    @DefaultStringValue("Name")
    String captainName();

    // CLOSEST
    /**
     * @return the string
     */
    @DefaultStringValue("Jumps")
    String closestNumberOfJumps();

    // ERRORS
    /**
     * @return the string
     */
    @DefaultStringValue("IdLongConversion")
    String idLongConversionError();

    /**
     * @return the string
     */
    @DefaultStringValue("Intro1")
    String intro1();

    /**
     * @return the string
     */
    @DefaultStringValue("WizardIntro")
    String wizardIntro();

}
