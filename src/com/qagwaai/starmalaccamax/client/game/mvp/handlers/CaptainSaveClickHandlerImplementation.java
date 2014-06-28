package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import java.util.HashMap;
import java.util.Map;

import com.qagwaai.starmalaccamax.client.event.CaptainUpdatedEvent;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.CaptainWindowViewImpl;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.UpdateCaptain;
import com.qagwaai.starmalaccamax.client.service.action.UpdateCaptainResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.model.Captain;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * 
 * @author pgirard
 * 
 */
public final class CaptainSaveClickHandlerImplementation implements ClickHandler {
    /**
	 * 
	 */
    private final EventBus eventBus;
    /**
	 * 
	 */
    private final CaptainWindowViewImpl view;
    /**
	 * 
	 */
    private final Captain model;

    /**
     * 
     * @param eventBus
     *            the bus to publish events on
     * @param view
     *            the view to get data from
     * @param model
     *            the model to save
     */
    public CaptainSaveClickHandlerImplementation(final EventBus eventBus, final CaptainWindowViewImpl view,
        final Captain model) {
        this.eventBus = eventBus;
        this.view = view;
        this.model = model;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final ClickEvent event) {
        model.setName((String) view.getNameItem().getValue());
        model.setColor((String) view.getColorPickerItem().getValue());
        ListGridRecord[] attributeData = view.getAttributesListGrid().getRecords();
        Map<String, Integer> attributes = new HashMap<String, Integer>();
        for (ListGridRecord skill : attributeData) {
            attributes.put(skill.getAttributeAsString("attributeName"), skill.getAttributeAsInt("attributeValue"));
        }
        model.getCaptainAttributes().fromMap(attributes);

        ListGridRecord[] skillData = view.getSkillsListGrid().getRecords();
        Map<String, Integer> skills = new HashMap<String, Integer>();
        for (ListGridRecord skill : skillData) {
            skills.put(skill.getAttributeAsString("skillName"), skill.getAttributeAsInt("skillValue"));
        }
        model.getSkills().fromMap(skills);
        ServiceFactory.getCaptainService().execute(new UpdateCaptain((CaptainDTO) model),
            new BaseAsyncCallback<UpdateCaptainResponse>() {

                @Override
                public void onFailure(final Throwable caught) {
                    super.onFailure(caught);
                    view.alert("Could not update captain: " + caught.getMessage());

                }

                @Override
                public void onSuccess(final UpdateCaptainResponse result) {
                    super.onSuccess(result);
                    view.closeWindow();
                    CaptainUpdatedEvent.fire(eventBus, result.getCaptain());
                }
            });
    }
}