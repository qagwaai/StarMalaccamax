/**
 * 
 */
package com.qagwaai.starmalaccamax.client.ssviz.mvp.widget;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Command;
import com.qagwaai.starmalaccamax.client.core.events.WindowResizedEvent;
import com.qagwaai.starmalaccamax.client.core.events.WindowResizedHandler;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractPresenter;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.event.MouseOverPlanetEvent;
import com.qagwaai.starmalaccamax.client.event.MouseOverPlanetHandler;
import com.qagwaai.starmalaccamax.client.ssviz.jsni.Bridge;
import com.qagwaai.starmalaccamax.client.ssviz.mvp.commands.CommandInitImpl;
import com.qagwaai.starmalaccamax.client.ssviz.mvp.handlers.StopStartAnimationClickHandler;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.SolarSystem;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;

/**
 * @author ehldxae
 * 
 */
public class SolarSystemVisualizationPresenterImpl extends
    AbstractPresenter<SolarSystemVisualizationView, SolarSystem> implements WindowResizedHandler, CloseClickHandler, MouseOverPlanetHandler {

    private final String elementId;

    public SolarSystemVisualizationPresenterImpl(final EventBus eventBus, final SolarSystemVisualizationView view,
        final SolarSystem model, final Filter filter) {
        super(eventBus, view, model, filter);
        view.setPresenter(this);
        elementId = "vizContainer" + System.currentTimeMillis();
        view.setVizElement(elementId);
        view.layout();

        eventBus.addHandler(WindowResizedEvent.getType(), this);
        eventBus.addHandler(MouseOverPlanetEvent.getType(), this);

        Command initViz = new CommandInitImpl(elementId, getView());

        Scheduler.get().scheduleDeferred(initViz);

        view.addWindowCloseHandler(this);
        view.addStopStartRotationButtonClickHandler(new StopStartAnimationClickHandler(getView()));
	view.addExportSceneButtonClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		Bridge.exportScene();
	    }
	});
        view.resize();
    }

    @Override
    public void destroyView() {
        getView().destroy();
    }

    @Override
    public void hideView() {
        getView().hide();
    }

    @Override
    public void showView() {
        getView().show();
    }

    @Override
    public void renderView() {
        getView().render();
    }

    @Override
    public void onWindowResized(final WindowResizedEvent event) {
        getView().resize();
    }

    @Override
    public void onCloseClick(final CloseClickEvent event) {
        Bridge.callShutdown();
        getView().destroy();
    }

    @Override
    public void onMouseOverPlanet(final MouseOverPlanetEvent event) {
	getView().updateDetailsWithPlanetName(event.getPlanetName());
    }

}
