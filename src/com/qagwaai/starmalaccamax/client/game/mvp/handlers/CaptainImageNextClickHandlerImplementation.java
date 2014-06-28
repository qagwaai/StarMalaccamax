package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainPresenter;
import com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;


/**
 * @author pgirard
 * 
 */
public final class CaptainImageNextClickHandlerImplementation implements ClickHandler {
    /**
	 * 
	 */
    private final NewCaptainView view;
    private final NewCaptainPresenter presenter;

    /**
     * @param view the view associated with this handler
     */
    public CaptainImageNextClickHandlerImplementation(final NewCaptainView view, final NewCaptainPresenter presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    @Override
    public void onClick(ClickEvent event) {
        if (presenter.getCaptainImage() < 7) {
        	presenter.setCaptainImage(presenter.getCaptainImage() + 1);
            view.getCaptainImage().setSrc("captains/captain" + (presenter.getCaptainImage()) + ".png");
        }
    }
}
