package com.qagwaai.starmalaccamax.client.core.mvp.handlers;

import com.qagwaai.starmalaccamax.client.core.LoginWatcher;
import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarView;
import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarViewImpl;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.UpdateUser;
import com.qagwaai.starmalaccamax.client.service.action.UpdateUserResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

public final class SetUserAsNewClickHandlerImpl implements com.smartgwt.client.widgets.menu.events.ClickHandler {
    private final LoginBarView view;

    public SetUserAsNewClickHandlerImpl(LoginBarViewImpl view) {
        this.view = view;
    }

    @Override
    public void onClick(MenuItemClickEvent event) {
        UserDTO user = LoginWatcher.getInstance().getLastEvent().getCurrentUser();
        user.setNoob(true);
        ServiceFactory.getUserService().execute(new UpdateUser(user), new BaseAsyncCallback<UpdateUserResponse>() {

            @Override
            public void onFailure(Throwable caught) {
                super.onFailure(caught);
                SC.say("Error", "could not update user: " + caught.getMessage());
            }

            @Override
            public void onSuccess(UpdateUserResponse result) {
                super.onSuccess(result);
                SC.say("Success", "user updated");
            }
        });
    }
}
