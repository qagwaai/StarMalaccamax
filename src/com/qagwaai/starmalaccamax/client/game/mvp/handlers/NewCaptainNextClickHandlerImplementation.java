package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainPresenter;
import com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;



/**
 * 
 * @author pgirard
 * 
 */
public final class NewCaptainNextClickHandlerImplementation implements ClickHandler {
    /**
	 * 
	 */
    private final NewCaptainView view;
    private final NewCaptainPresenter presenter;

    /**
     * 
     * @param view
     *            the associated view
     */
    public NewCaptainNextClickHandlerImplementation(final NewCaptainView view, final NewCaptainPresenter presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    @Override
    public void onClick(final ClickEvent event) {
        int currentPage = view.getCurrentPage();
        if (view.getNextButton().getTitle().equals("Save")) {
            presenter.saveNewCaptain();
        }
        if (currentPage < view.getTotalPages()) {
            view.displayPage(++currentPage);
            view.getPreviousButton().enable();
            if (currentPage + 1 == view.getTotalPages()) {
                view.copyValues();
                view.getNextButton().setTitle("Save");
            }
        }

    }
}
