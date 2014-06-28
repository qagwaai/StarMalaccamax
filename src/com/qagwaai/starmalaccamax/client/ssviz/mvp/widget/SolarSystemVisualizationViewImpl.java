/**
 * 
 */
package com.qagwaai.starmalaccamax.client.ssviz.mvp.widget;

import com.google.gwt.user.client.ui.Widget;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractView;
import com.qagwaai.starmalaccamax.client.core.mvp.View;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author ehldxae
 * 
 */
public class SolarSystemVisualizationViewImpl extends AbstractView<SolarSystemVisualizationPresenterImpl> implements
    View, SolarSystemVisualizationView {

    private final Window rootWindow = new Window();
    private final HTMLPane rootPane = new HTMLPane();
    private final Window detailsWindow = new Window(); 
    private String vizElement;
    private final Label planetNameLabel = new Label();  
    private final IButton stopStartRotationButton = new IButton("Pause Rotation");
    private final IButton exportSceneButton = new IButton("Export Scene");

    /*
     * (non-Javadoc)
     * 
     * @see com.qagwaai.starmalaccamax.client.core.mvp.View#asWidget()
     */
    @Override
    public Widget asWidget() {
        return rootWindow;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qagwaai.starmalaccamax.client.core.mvp.View#destroy()
     */
    @Override
    public void destroy() {
        rootWindow.destroy();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qagwaai.starmalaccamax.client.core.mvp.View#layout()
     */
    @Override
    public void layout() {
        rootWindow.setAutoCenter(true);
        rootWindow.setIsModal(true);
        rootWindow.setShowModalMask(true);
        rootWindow.setCanDragResize(true);
        rootWindow.setCanDragReposition(true);
        rootWindow.setShowMinimizeButton(false);
        rootWindow.setLeft(20);
        rootWindow.setTop(20);
        rootWindow.setWidth(com.google.gwt.user.client.Window.getClientWidth() - 40);
        rootWindow.setHeight(com.google.gwt.user.client.Window.getClientHeight() - 40);
        rootWindow.setTitle("Solar System Visualization");

        rootPane.setContents("<div id=\"" + vizElement + "\" style=\"margin:0;padding:0\"></div>");
        rootWindow.addItem(rootPane);
                 
        detailsWindow.setTitle("Details");  
        detailsWindow.setWidth(200);  
        detailsWindow.setHeight(200);  
        detailsWindow.setCanDragResize(true);  
        detailsWindow.setShowFooter(true);  
        
        VLayout detailsLayout = new VLayout();
  
        planetNameLabel.setContents("Planet Name:");  
        planetNameLabel.setAlign(Alignment.CENTER);  
        planetNameLabel.setPadding(5);  
        planetNameLabel.setHeight100();  
        
        detailsLayout.addMember(planetNameLabel);
        detailsLayout.addMember(stopStartRotationButton);
	detailsLayout.addMember(exportSceneButton);
        
        detailsWindow.addItem(detailsLayout);  
        rootPane.addChild(detailsWindow);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qagwaai.starmalaccamax.client.core.mvp.View#render()
     */
    @Override
    public void render() {
        rootWindow.setAutoCenter(true);
        rootWindow.show();
    }

    @Override
    public Canvas getRootCanvas() {
        return rootWindow;
    }

    @Override
    public void resize() {
        rootWindow.setLeft(20);
        rootWindow.setTop(20);
        rootWindow.setWidth(com.google.gwt.user.client.Window.getClientWidth() - 40);
        rootWindow.setHeight(com.google.gwt.user.client.Window.getClientHeight() - 40);
        
        detailsWindow.setTop(rootWindow.getHeight() - 250);
        detailsWindow.setLeft(0);
    }

    /* (non-Javadoc)
     * @see com.qagwaai.starmalaccamax.client.ssviz.mvp.widget.SolarSystemVisualizationView#getVizElement()
     */
    @Override
    public String getVizElement() {
        return vizElement;
    }

    /* (non-Javadoc)
     * @see com.qagwaai.starmalaccamax.client.ssviz.mvp.widget.SolarSystemVisualizationView#setVizElement(java.lang.String)
     */
    @Override
    public void setVizElement(final String vizElement) {
        this.vizElement = vizElement;
    }

    /* (non-Javadoc)
     * @see com.qagwaai.starmalaccamax.client.ssviz.mvp.widget.SolarSystemVisualizationView#addWindowCloseHandler(com.smartgwt.client.widgets.events.CloseClickHandler)
     */
    @Override
    public void addWindowCloseHandler(final CloseClickHandler handler) {
        rootWindow.addCloseClickHandler(handler);
    }

    @Override
    public int getRootWidth() {
        return rootPane.getWidth();
    }

    @Override
    public int getRootHeight() {
        return rootPane.getHeight();
    }
    
    /* (non-Javadoc)
     * @see com.qagwaai.starmalaccamax.client.ssviz.mvp.widget.SolarSystemVisualizationView#updateDetailsWithPlanetName(java.lang.String)
     */
    @Override
    public void updateDetailsWithPlanetName(final String name) {
	planetNameLabel.setContents("Planet Name: " + name);
    }
    
    /* (non-Javadoc)
     * @see com.qagwaai.starmalaccamax.client.ssviz.mvp.widget.SolarSystemVisualizationView#addStopStartRotationButtonClickHandler(com.smartgwt.client.widgets.events.ClickHandler)
     */
    @Override
    public void addStopStartRotationButtonClickHandler(final ClickHandler handler) {
	stopStartRotationButton.addClickHandler(handler);
    }
    
    /* (non-Javadoc)
     * @see com.qagwaai.starmalaccamax.client.ssviz.mvp.widget.SolarSystemVisualizationView#setRotationButtontitle(java.lang.String)
     */
    @Override
    public void setRotationButtontitle(final String text) {
	stopStartRotationButton.setTitle(text);
    }

    @Override
    public void addExportSceneButtonClickHandler(ClickHandler handler) {
	exportSceneButton.addClickHandler(handler);
    }
}
