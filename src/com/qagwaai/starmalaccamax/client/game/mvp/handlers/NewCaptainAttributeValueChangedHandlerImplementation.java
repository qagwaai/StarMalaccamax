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
public final class NewCaptainAttributeValueChangedHandlerImplementation implements ValueChangedHandler {
    /**
	 * 
	 */
    private final NewCaptainView view;
    private final NewCaptainPresenter presenter;

    /**
     * @param view the view associated with this handler
     */
    public NewCaptainAttributeValueChangedHandlerImplementation(final NewCaptainView view, final NewCaptainPresenter presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    @Override
    public void onValueChanged(ValueChangedEvent event) {
        int attributesSpent = presenter.sumAttributesSpent();
        if (attributesSpent <= NewCaptainPresenterImpl.TOTAL_ATTRIBUTE_POINTS) {
            view.getTotalAttributeAvailable().setContents(
                "Total Points Available: " + (NewCaptainPresenterImpl.TOTAL_ATTRIBUTE_POINTS - attributesSpent));
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
