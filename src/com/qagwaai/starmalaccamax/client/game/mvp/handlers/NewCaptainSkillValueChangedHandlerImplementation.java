package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainPresenter;
import com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView;
import com.smartgwt.client.widgets.Slider;
import com.smartgwt.client.widgets.events.ValueChangedEvent;
import com.smartgwt.client.widgets.events.ValueChangedHandler;



/**
 * @author pgirard
 * 
 */
public final class NewCaptainSkillValueChangedHandlerImplementation implements ValueChangedHandler {
    /**
	 * 
	 */
    private final NewCaptainView view;
    private final NewCaptainPresenter presenter;

    /**
     * @param view the view associated with this handler
     */
    public NewCaptainSkillValueChangedHandlerImplementation(final NewCaptainView view, final NewCaptainPresenter presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    @Override
    public void onValueChanged(ValueChangedEvent event) {
        int skillsSpent = presenter.sumSkillsSpent();
        if (skillsSpent <= NewCaptainPresenterImpl.TOTAL_SKILL_POINTS) {
            view.getTotalSkillAvailable().setContents(
                "Total Points Available: " + (NewCaptainPresenterImpl.TOTAL_SKILL_POINTS - skillsSpent));
        } else {
            Slider slider = (Slider) event.getSource();
            if (event.getValue() > 0) {
                slider.setValue(event.getValue() - 1);
            } else {
                slider.setValue(0);
            }
        }
    }
}