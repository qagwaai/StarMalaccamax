/**
 * AbstractView.java
 * Created by pgirard at 9:13:02 AM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.client.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.core.mvp;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;

/**
 * @author pgirard
 * @param <P>
 *            the presenter associated with this view
 */
public abstract class AbstractView<P extends Presenter> implements View {
    /**
	 * 
	 */
    private P presenter;

    /**
	 * 
	 */
    private String location;

    /**
     * Present an alert dialog
     * 
     * @param message
     *            the message to display
     */
    public final void alert(final String message) {
        Window.alert(message);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        getRootCanvas().destroy();

    }

    /**
     * @return the location
     */
    public final String getLocation() {
        return location;
    }

    /**
     * @return the presenter
     */
    public final P getPresenter() {
        return presenter;
    }

    /**
     * 
     * @return the root container
     */
    public abstract Canvas getRootCanvas();

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        getRootCanvas().hide();
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void render() {
        if (getRootCanvas().isDrawn()) {
            getRootCanvas().redraw();
        } else {
            getRootCanvas().draw();
        }
        if (!getRootCanvas().isVisible()) {
            show();
        }
    }

    /**
     * 
     * @param title
     *            the title of the dialog
     * @param message
     *            the message in the dialog
     */
    public final void say(final String title, final String message) {
        SC.say(title, message);
    }

    /**
     * @param location
     *            the location to set
     */
    public final void setLocation(final String location) {
        this.location = location;
    }

    /**
     * @param presenter
     *            the presenter to set
     */
    public final void setPresenter(final P presenter) {
        this.presenter = presenter;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void show() {
        getRootCanvas().show();
    }

    /**
     * 
     * @param element the element to modify
     * @param attr the attribute to modify
     * @param value the value of attribute
     */
    public void setStyleAttribute(final Element element, final String attr, final String value) {
        DOM.setStyleAttribute(element, attr, value);
    }
}
