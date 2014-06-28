/**
 * Resources.java
 * Created by pgirard at 10:36:12 PM on Jul 26, 2010
 * in the com.qagwaai.starmalaccamax.client package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author pgirard
 * 
 */
public interface Resources extends ClientBundle {

    /**
     * 
     * @return the blank logo
     */
    @Source("s1_blank.jpg")
    ImageResource logo();

}
