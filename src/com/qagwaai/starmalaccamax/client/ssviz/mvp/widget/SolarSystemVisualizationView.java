package com.qagwaai.starmalaccamax.client.ssviz.mvp.widget;

import com.qagwaai.starmalaccamax.client.core.mvp.View;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;

public interface SolarSystemVisualizationView extends View {

    String getVizElement();

    void setVizElement(String vizElement);

    void addWindowCloseHandler(CloseClickHandler handler);

    void updateDetailsWithPlanetName(String name);

    void addStopStartRotationButtonClickHandler(ClickHandler handler);

    void addExportSceneButtonClickHandler(ClickHandler handler);

    void setRotationButtontitle(String text);

    int getRootWidth();

    int getRootHeight();

    void resize();

    void setPresenter(SolarSystemVisualizationPresenterImpl solarSystemVisualizationPresenterImpl);
}