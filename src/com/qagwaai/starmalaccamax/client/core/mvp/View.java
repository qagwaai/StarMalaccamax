/**
 * View.java
 * Created by pgirard at 9:16:27 AM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.client.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.core.mvp;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author pgirard
 * 
 */
public interface View {
    /**
     * 
     * @return the view as a widget
     */
    Widget asWidget();

    /**
	 * 
	 */
    void destroy();

    /**
	 * 
	 */
    void hide();

    /**
     * notify the view to layout components
     */
    void layout();

    /**
	 * 
	 */
    void render();

    /**
	 * 
	 */
    void show();

    /**
     * Present an alert dialog
     * 
     * @param message
     *            the message to display
     */
    void alert(final String message);
    
    void say(final String title, final String message);

}
