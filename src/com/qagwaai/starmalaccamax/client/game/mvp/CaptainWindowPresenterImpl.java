/**
 * CaptainWindowPresenter.java
 * Created by pgirard at 2:18:11 PM on Nov 3, 2010
 * in the com.qagwaai.starmalaccamax.client.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import java.util.Map;

import com.qagwaai.starmalaccamax.client.core.mvp.AbstractPresenter;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.CaptainCancelClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.CaptainSaveClickHandlerImplementation;
import com.qagwaai.starmalaccamax.shared.model.Captain;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public class CaptainWindowPresenterImpl extends AbstractPresenter<CaptainWindowView, Captain> implements CaptainWindowPresenter {


    /**
     * 
     * @param eventBus
     *            the bus to publish events on
     * @param view
     *            the view that this presenter will attach to
     * @param model
     *            the model to present, if any
     */
    public CaptainWindowPresenterImpl(final EventBus eventBus, final CaptainWindowViewImpl view, final Captain model) {
        super(eventBus, view, model);
        view.setPresenter(this);
        view.layout();
        if (model != null) {
            view.getNameItem().setDefaultValue(model.getName());
            view.getColorPickerItem().setDefaultValue(model.getColor());

            ListGridRecord[] attributeData = new ListGridRecord[2];
            Map<String, Integer> attributes = model.getCaptainAttributes().toMap();
            int i = 0;
            for (String key : attributes.keySet()) {
                ListGridRecord record = new ListGridRecord();
                record.setAttribute("attributeName", key);
                record.setAttribute("attributeValue", attributes.get(key));
                attributeData[i++] = record;
            }
            view.getAttributesListGrid().setData(attributeData);

            Map<String, Integer> skills = model.getSkills().toMap();
            ListGridRecord[] skillData = new ListGridRecord[skills.size()];
            i = 0;
            for (String key : skills.keySet()) {
                ListGridRecord record = new ListGridRecord();
                record.setAttribute("skillName", key);
                record.setAttribute("skillValue", skills.get(key));
                skillData[i++] = record;
            }
            view.getSkillsListGrid().setData(skillData);

        }
        view.getCancelButton().addClickHandler(new CaptainCancelClickHandlerImplementation(view));
        view.getSaveButton().addClickHandler(new CaptainSaveClickHandlerImplementation(eventBus, view, model));
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void destroyView() {
        getView().destroy();

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void hideView() {
        getView().hide();

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

}
