/**
 * 
 */
package com.qagwaai.starmalaccamax.client.ssviz.mvp.handlers;

import com.qagwaai.starmalaccamax.client.ssviz.jsni.Bridge;
import com.qagwaai.starmalaccamax.client.ssviz.mvp.widget.SolarSystemVisualizationView;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author ehldxae
 *
 */
public class StopStartAnimationClickHandler implements ClickHandler {

    private boolean isAnimationPaused = false;
    private final SolarSystemVisualizationView view;
    
    
    public StopStartAnimationClickHandler(final SolarSystemVisualizationView view) {
	super();
	this.view = view;
    }


    /* (non-Javadoc)
     * @see com.smartgwt.client.widgets.events.ClickHandler#onClick(com.smartgwt.client.widgets.events.ClickEvent)
     */
    @Override
    public void onClick(final ClickEvent event) {
	isAnimationPaused = !isAnimationPaused;
	Bridge.setRotation(isAnimationPaused);
	if (isAnimationPaused) {
	    view.setRotationButtontitle("Start rotation");
	} else {
	    view.setRotationButtontitle("Pause rotation");
	}
    }

}
