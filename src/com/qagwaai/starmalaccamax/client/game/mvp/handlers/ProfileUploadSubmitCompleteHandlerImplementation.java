package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowViewImpl;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.GetUser;
import com.qagwaai.starmalaccamax.client.service.action.GetUserResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;


/**
 * @author pgirard
 * 
 */
public final class ProfileUploadSubmitCompleteHandlerImplementation implements SubmitCompleteHandler {
    /**
     * 
     */
    private final ProfileWindowViewImpl view;
    private final ProfileWindowPresenterImpl presenter;

    /**
     * @param view
     *            the view associated with this handler
     */
    public ProfileUploadSubmitCompleteHandlerImplementation(final ProfileWindowViewImpl view, final ProfileWindowPresenterImpl presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    @Override
    public void onSubmitComplete(final SubmitCompleteEvent event) {
        ServiceFactory.getUserService().execute(new GetUser(presenter.getModel().getId()),
            new BaseAsyncCallback<GetUserResponse>() {

                @Override
                public void onFailure(final Throwable caught) {
                    super.onFailure(caught);
                    view.say("Error", "Could not get updated user: " + caught.getMessage());
                }

                @Override
                public void onSuccess(final GetUserResponse result) {
                    super.onSuccess(result);
                    presenter.getModel().setProfileImageBlobKey(result.getUser().getProfileImageBlobKey());
                    view.getProfileImage().setSrc(
                        "/profileImage?blob-key=" + result.getUser().getProfileImageBlobKey());
                }
            });

    }
}
