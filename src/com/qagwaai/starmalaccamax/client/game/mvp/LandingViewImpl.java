/**
 * LandingView.java
 * Created by pgirard at 9:13:23 AM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.client.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractView;
import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarPresenterImpl;
import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarViewImpl;
import com.qagwaai.starmalaccamax.client.game.constants.GameConstants;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;
import com.smartgwt.client.types.ImageStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VStack;

/**
 * @author pgirard
 * 
 */
public class LandingViewImpl extends AbstractView<LandingPresenter> implements LandingView {

    /**
	 * 
	 */
    private GameConstants constants = GWT.create(GameConstants.class);

    /**
	 * 
	 */
    private VStack basePanel;

    /**
	 * 
	 */
    private HTMLPane wizard;

    /**
	 * 
	 */
    private Button startWizard;

    /**
	 * 
	 */
    private Button gotoPlayerSummaryPage;

    /**
	 * 
	 */
    private LoginBarPresenterImpl loginBarPresenter;

    /**
	 * 
	 */
    public LandingViewImpl() {
        super();
    }

    /**
     * 
     * @param location
     *            the location of this view
     */
    public LandingViewImpl(final String location) {
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

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.LandingView#getGotoPlayerSummaryPage()
	 */
    @Override
	public final Button getGotoPlayerSummaryPage() {
        return gotoPlayerSummaryPage;
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
    public final VStack getRootPanel() {
        return basePanel;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.LandingView#getStartWizard()
	 */
    @Override
	public final Button getStartWizard() {
        return startWizard;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.LandingView#gotUser(com.qagwaai.starmalaccamax.shared.model.UserDTO)
	 */
    @Override
	public final void gotUser(final UserDTO user) {
        if (user != null) {
            if (!user.hasCaptains()) {
                wizard.setVisible(true);
                startWizard.setVisible(true);
            }

            if (user.isNoob()) {
                SC.say("Welcome", "Welcome to Star Malaccamax <Insert Welcome Message Here>");
                // TODO create welcome message
            }
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void layout() {

        basePanel = new VStack();
        basePanel.setWidth("100%");
        loginBarPresenter =
            new LoginBarPresenterImpl(this.getPresenter().getEventBus(), new LoginBarViewImpl(), new UserDTO());
        basePanel.addMember(loginBarPresenter.getView().asWidget());
        HTML spacer = new HTML("&nbsp;");
        spacer.setWidth("50px");
        spacer.setHeight("50px");
        basePanel.addMember(spacer);

        Img starImg1 = new Img("s1_blank.jpg", 350, 280);
        starImg1.setImageType(ImageStyle.NORMAL);
        starImg1.setBorder("1px solid gray");

        HLayout contentLayout1 = new HLayout();
        contentLayout1.setWidth100();
        contentLayout1.setHeight("150px");
        HTMLPane content = new HTMLPane();
        // content.setContents(storyContent);
        content.setContents(constants.intro1());
        content.setWidth("*");
        contentLayout1.addMember(spacer);
        contentLayout1.addMember(content);
        contentLayout1.addMember(starImg1);

        HLayout contentLayout2 = new HLayout();
        contentLayout2.setWidth100();
        contentLayout2.setHeight("100px");
        wizard = new HTMLPane();
        wizard.setContents(constants.wizardIntro());
        wizard.setWidth("*");
        wizard.setVisible(false);
        contentLayout2.addMember(spacer);
        contentLayout2.addMember(wizard);

        HLayout contentLayout3 = new HLayout();
        contentLayout3.setWidth100();

        startWizard = new Button("New Captain");
        startWizard.setVisible(false);
        gotoPlayerSummaryPage = new Button("Goto Player Summary");
        contentLayout3.addMember(spacer);
        contentLayout3.addMember(startWizard);
        contentLayout3.addMember(gotoPlayerSummaryPage);

        basePanel.addMember(contentLayout1);
        basePanel.addMember(contentLayout2);
        basePanel.addMember(contentLayout3);

    }

}
