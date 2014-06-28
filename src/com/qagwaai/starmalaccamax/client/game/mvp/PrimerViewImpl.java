/**
 * LandingView.java
 * Created by pgirard at 9:13:23 AM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.client.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractView;
import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarPresenterImpl;
import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarViewImpl;
import com.qagwaai.starmalaccamax.client.game.constants.GameConstants;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;
import com.smartgwt.client.types.ContentsType;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author pgirard
 * 
 */
public class PrimerViewImpl extends AbstractView<PrimerPresenterImpl> implements PrimerView {

    /**
	 * 
	 */
    private GameConstants constants = GWT.create(GameConstants.class);

    /**
	 * 
	 */
    private VLayout basePanel = new VLayout();

    /**
	 * 
	 */
    public PrimerViewImpl() {
        super();
    }

    /**
     * 
     * @param location
     *            the location of this view
     */
    public PrimerViewImpl(final String location) {
        this();
        setLocation(location);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Widget asWidget() {

        return basePanel;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Canvas getRootCanvas() {
        return basePanel;
    }

    /**
     * @return the rootPanel
     */
    public final VLayout getRootPanel() {
        return basePanel;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void layout() {
        basePanel.setWidth100();
        basePanel.setHeight100();

        LoginBarPresenterImpl loginBarPresenter =
            new LoginBarPresenterImpl(this.getPresenter().getEventBus(), new LoginBarViewImpl(), new UserDTO());

        final HTMLPane htmlPane = new HTMLPane();
        htmlPane.setShowEdges(true);
        htmlPane.setContentsURL("https://sites.google.com/site/starmalaccamax1/wiki");
        htmlPane.setContentsType(ContentsType.PAGE);

        basePanel.addMember(loginBarPresenter.getView().asWidget());
        basePanel.addMember(htmlPane);

    }

}
