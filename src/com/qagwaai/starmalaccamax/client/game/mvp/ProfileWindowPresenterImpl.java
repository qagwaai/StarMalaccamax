/**
 * ProfileWindowPresenter.java
 * Created by pgirard at 11:10:38 AM on Jul 26, 2010
 * in the com.qagwaai.starmalaccamax.client.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import java.util.LinkedHashMap;

import com.qagwaai.starmalaccamax.client.core.LoginWatcher;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractPresenter;
import com.qagwaai.starmalaccamax.client.event.CurrentUserChangedEvent;
import com.qagwaai.starmalaccamax.client.event.CurrentUserChangedHandler;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.ProfileSaveItemClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.ProfileUploadButtonClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.ProfileUploadSubmitCompleteHandlerImplementation;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.GetAllCountries;
import com.qagwaai.starmalaccamax.client.service.action.GetAllCountriesResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllTimezones;
import com.qagwaai.starmalaccamax.client.service.action.GetAllTimezonesResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetBlobstoreTarget;
import com.qagwaai.starmalaccamax.client.service.action.GetBlobstoreTargetResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.model.Country;
import com.qagwaai.starmalaccamax.shared.model.User;

/**
 * @author pgirard
 * 
 */
public class ProfileWindowPresenterImpl extends AbstractPresenter<ProfileWindowView, User> implements
    CurrentUserChangedHandler, ProfileWindowPresenter {


    /**
     * 
     * @param eventBus
     *            the bus to publish events on
     * @param view
     *            the view that this presenter will attach to
     * @param model
     *            the model to present, if any
     */
    public ProfileWindowPresenterImpl(final EventBus eventBus, final ProfileWindowViewImpl view, final User model) {
        super(eventBus, view, model);

        view.setPresenter(this);
        view.layout();

        ServiceFactory.getUtilityService().execute(new GetBlobstoreTarget("/profileImageUpload"),
            new BaseAsyncCallback<GetBlobstoreTargetResponse>() {

                @Override
                public void onFailure(final Throwable caught) {
                    super.onFailure(caught);
                    view.say("Error", "Could not get blobstore url: " + caught.getMessage());
                }

                @Override
                public void onSuccess(final GetBlobstoreTargetResponse result) {
                    super.onSuccess(result);
                    view.getUploadButton().setEnabled(true);
                    view.getUploadForm().setAction(result.getBlobStoreUrl());
                }
            });

        ServiceFactory.getUtilityService().execute(new GetAllCountries(),
            new BaseAsyncCallback<GetAllCountriesResponse>() {

                @Override
                public void onFailure(final Throwable caught) {
                    super.onFailure(caught);
                    view.say("Error", "Could not load all countries: " + caught.getMessage());
                }

                @Override
                public void onSuccess(final GetAllCountriesResponse result) {
                    super.onSuccess(result);
                    LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
                    // LinkedHashMap<String, String> valueMapIcons = new
                    // LinkedHashMap<String, String>();
                    for (Country country : result.getCountries()) {
                        if (!country.getAbbreviation().isEmpty()) {
                            valueMap.put(country.getAbbreviation(), country.getName());
                            // valueMapIcons.put(country.getAbbreviation(),
                            // country.getAbbreviation());
                        }
                    }
                    view.getRegionItem().setValueMap(valueMap);
                    // view.getRegionItem().setValueIcons(valueMapIcons);
                }
            });

        ServiceFactory.getUtilityService().execute(new GetAllTimezones(),
            new BaseAsyncCallback<GetAllTimezonesResponse>() {

                @Override
                public void onFailure(final Throwable caught) {
                    super.onFailure(caught);
                    view.say("Error", "Could not load all timezones: " + caught.getMessage());
                }

                @Override
                public void onSuccess(final GetAllTimezonesResponse result) {
                    super.onSuccess(result);
                    LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
                    for (String timezone : result.getTimezones()) {
                        if (!timezone.isEmpty()) {
                            valueMap.put(timezone, timezone);
                        }
                    }
                    view.getTimezoneItem().setValueMap(valueMap);

                }
            });

        view.getUploadForm().addSubmitCompleteHandler(new ProfileUploadSubmitCompleteHandlerImplementation(view, this));

        view.getUploadButton().addClickHandler(new ProfileUploadButtonClickHandlerImplementation(view));

        view.getSaveItem().addClickHandler(new ProfileSaveItemClickHandlerImplementation(view, model));

        if (view.getUsernameItem() != null) {
            view.getUsernameItem().setDefaultValue(model.getNickname());
        }
        if (view.getEmailItem() != null) {
            view.getEmailItem().setDefaultValue(model.getEmail());
        }
        if (view.getIsNewItem() != null) {
            view.getIsNewItem().setDefaultValue(model.isNoob());
        }
        view.getLastLoggedIn().setDefaultValue(model.getLastLoggedin());
        view.getIsNPCItem().setDefaultValue(model.isNPC());
        view.getRatingItem().setDefaultValue(model.getRating());
        if (model.getTimezone() != null) {
            view.getTimezoneItem().setDefaultValue(model.getTimezone());
        }
        if (model.getProfileImageBlobKey() != null) {
            if (!model.getProfileImageBlobKey().isEmpty()) {
                view.getProfileImage().setSrc("/profileImage?blob-key=" + getModel().getProfileImageBlobKey());
            }
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void destroyView() {
        getView().closeWindow();

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void hideView() {
        getView().hide();

    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowPresenter#onCurrentUserChanged(com.qagwaai.starmalaccamax.client.event.CurrentUserChangedEvent)
	 */
	@Override
    public final void onCurrentUserChanged(final CurrentUserChangedEvent event) {
        getView().closeWindow();

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void renderView() {
        getView().render();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void showView() {
        getView().show();

    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowPresenter#isCurrentUserAdmin()
	 */
    @Override
	public final boolean isCurrentUserAdmin() {
        return LoginWatcher.getInstance().getLastEvent().getCurrentUser().isAdmin();
    }

}
