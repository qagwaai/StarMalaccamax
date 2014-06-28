/**
 * PleaseWaitView.java
 * Created by pgirard at 11:40:03 AM on Oct 4, 2010
 * in the com.qagwaai.starmalaccamax.client.util package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.admin.util;

import com.google.gwt.user.client.ui.Widget;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractView;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Progressbar;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author pgirard
 * 
 */

public class PleaseWaitView extends AbstractView {

    /**
	 * 
	 */
    private Window winModal = new Window();

    /**
	 * 
	 */
    private String title;
    /**
	 * 
	 */
    private String message;

    /**
	 * 
	 */
    private Label messageLabel;
    /**
	 * 
	 */
    private Progressbar bar = new Progressbar();

    /**
     * @param title
     *            the title of the window
     * @param message
     *            the message explaining the work to be done
     */
    public PleaseWaitView(final String title, final String message) {
        this.title = title;
        this.message = message;

        winModal.setWidth(360);
        winModal.setHeight(115);
        winModal.setTitle(this.title);
        winModal.setIsModal(true);
        winModal.setShowModalMask(true);
        winModal.centerInPage();

        VLayout contents = new VLayout();

        messageLabel = new Label(message);
        messageLabel.setTitle(message);
        messageLabel.setHeight(25);
        bar.setPercentDone(0);
        bar.setHeight(25);
        contents.setHeight(50);

        contents.addMember(messageLabel);
        contents.addMember(bar);

        winModal.addItem(contents);
        winModal.show();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Widget asWidget() {

        return winModal;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void destroy() {

    }

    /**
     * @return the bar
     */
    public final Progressbar getBar() {
        return bar;
    }

    /**
     * @return the message
     */
    public final String getMessage() {
        return message;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Canvas getRootCanvas() {
        return winModal;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void layout() {

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void render() {

    }

    /**
     * 
     * @param message
     *            the message to display
     */
    public final void setMessage(final String message) {
        messageLabel.setTitle(message);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void show() {
        winModal.show();
        winModal.redraw();

    }

}
