package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowView;



/**
 * @author pgirard
 * 
 */
public final class ProfileUploadButtonClickHandlerImplementation implements ClickHandler {
    /**
     * 
     */
    private final ProfileWindowView view;

    /**
     * @param view
     *            the view associated with this handler
     */
    public ProfileUploadButtonClickHandlerImplementation(final ProfileWindowView view) {
        this.view = view;
    }

    @Override
    public void onClick(final ClickEvent event) {
        view.getUploadForm().submit();
    }
}