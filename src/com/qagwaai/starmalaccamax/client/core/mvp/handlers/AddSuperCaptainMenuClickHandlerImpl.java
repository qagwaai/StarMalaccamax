package com.qagwaai.starmalaccamax.client.core.mvp.handlers;

import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.client.core.LoginWatcher;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.AddCaptain;
import com.qagwaai.starmalaccamax.client.service.action.AddCaptainResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * 
 * @author pgirard
 * 
 */
public final class AddSuperCaptainMenuClickHandlerImpl implements com.smartgwt.client.widgets.menu.events.ClickHandler {
    /**
	 * 
	 * 
	 */
    public AddSuperCaptainMenuClickHandlerImpl() {
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final MenuItemClickEvent event) {
        CaptainDTO superCaptain = new CaptainDTO();
        superCaptain.setName("Super");
        superCaptain.setColor("#FF0000");
        superCaptain.getCaptainAttributes().setIntelligence(20);
        superCaptain.getCaptainAttributes().setKnowledge(20);
        superCaptain.getSkills().setAttack(20);
        superCaptain.getSkills().setDefense(20);
        superCaptain.getSkills().setEngines(20);
        superCaptain.getSkills().setLasers(20);
        superCaptain.getSkills().setMissiles(20);
        superCaptain.getSkills().setNavigation(20);
        superCaptain.getSkills().setNegotiation(20);
        superCaptain.getSkills().setRepair(20);
        superCaptain.getSkills().setShields(20);
        superCaptain.getSkills().setStorage(20);
        superCaptain.setOwnerId(LoginWatcher.getInstance().getLastEvent().getCurrentUser().getId());
        ServiceFactory.getCaptainService().execute(new AddCaptain(superCaptain),
            new BaseAsyncCallback<AddCaptainResponse>() {

                @Override
                public void onFailure(final Throwable caught) {
                    super.onFailure(caught);
                    GWT.log("Could not add captain: " + caught.getMessage());
                }

                @Override
                public void onSuccess(final AddCaptainResponse result) {
                    super.onSuccess(result);
                }
            });
    }
}