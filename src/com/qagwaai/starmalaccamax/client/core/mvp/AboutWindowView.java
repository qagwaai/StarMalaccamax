/**
 * ShipWindowView.java
 * Created by pgirard at 11:50:58 AM on Oct 26, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.core.mvp;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.qagwaai.starmalaccamax.client.Application;
import com.qagwaai.starmalaccamax.client.core.util.ClientUtil;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author pgirard
 * 
 */
public class AboutWindowView extends AbstractView<AboutWindowPresenter> {
    private Window rootWindow = new Window();

    /**
	 * 
	 */
    public AboutWindowView() {

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Widget asWidget() {
        return rootWindow;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Canvas getRootCanvas() {
        return rootWindow;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void layout() {
        VLayout baseLayout = new VLayout();
        rootWindow.setWidth(600);
        rootWindow.setHeight(450);
        rootWindow.setTitle("About Star Malaccamax");
        rootWindow.setIsModal(true);
        rootWindow.setShowModalMask(true);
        rootWindow.setCanDragResize(true);
        rootWindow.setCanDragReposition(true);
        rootWindow.setShowMinimizeButton(false);

        baseLayout.setWidth100();
        baseLayout.setHeight100();

        baseLayout.addMember(new Label("Version " + Application.VERSION));
        baseLayout.addMember(new Label("Your Browser: " + ClientUtil.getUserAgent()));

        rootWindow.addItem(baseLayout);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void render() {
        rootWindow.setAutoCenter(true);
        rootWindow.show();
    }

    /**
     * 
     * @param windowTitle
     *            the title of the window
     */
    public final void setTitle(final String windowTitle) {
        rootWindow.setTitle(windowTitle);
    }

}
