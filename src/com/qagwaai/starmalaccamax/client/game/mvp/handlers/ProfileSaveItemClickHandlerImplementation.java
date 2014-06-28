package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowViewImpl;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.UpdateUser;
import com.qagwaai.starmalaccamax.client.service.action.UpdateUserResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.model.User;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;



/**
 * @author pgirard
 * 
 */
public class ProfileSaveItemClickHandlerImplementation implements
    com.smartgwt.client.widgets.form.fields.events.ClickHandler {
    /**
     * 
     */
    private final ProfileWindowViewImpl view;
    /**
     * 
     */
    private final User model;

    /**
     * @param view
     *            the view associated with this handler
     * @param model
     *            the data model
     */
    public ProfileSaveItemClickHandlerImplementation(final ProfileWindowViewImpl view, final User model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onClick(final com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
        model.setActive(view.getIsUserActive().getValueAsBoolean());
        model.setNickname(view.getUsernameItem().getValueAsString());
        model.setTimezone(view.getTimezoneItem().getValueAsString());

        ServiceFactory.getUserService().execute(new UpdateUser((UserDTO) model), new BaseAsyncCallback<UpdateUserResponse>() {

            @Override
            public void onFailure(final Throwable caught) {
                super.onFailure(caught);
                view.say("Error", "Could not update user: " + caught.getMessage());

            }

            @Override
            public void onSuccess(final UpdateUserResponse result) {
                super.onSuccess(result);
                view.closeWindow();

            }
        });

    }
}
