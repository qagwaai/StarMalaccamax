package com.qagwaai.starmalaccamax.client.ssviz.jsni;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.qagwaai.starmalaccamax.client.Application;
import com.qagwaai.starmalaccamax.client.event.MouseOverPlanetEvent;

public class Bridge {
    private String value = "bridgevalue";

    /**
     * Tester
     * 
     * @return
     */
    // TODO remove when done testing
    public String getValue() {
        return value;
    }

    // TODO remove when done testing
    public void setValue(final String value) {
        this.value = value;
    }
    
    public static void onMouseOverPlanet(final String planetName) {
	GWT.log("onMouseOverPlanet[" + planetName + "]");
	MouseOverPlanetEvent.fire(Application.getInstance().getEventBus(), planetName);
    }

    public static void sayHello(final String value) {
        Window.alert(value);
    }

    public static String getPlanetName() {
        return "asdfasdfasdf: " + new Date(System.currentTimeMillis()).toString();
    }

    public static native void exportStaticMethod() /*-{
        if ($wnd.logConsole) {
        	$wnd.logConsole.debug("registering static method");
        }
        $wnd.sayHello = $entry(@com.qagwaai.starmalaccamax.client.ssviz.jsni.Bridge::sayHello(Ljava/lang/String;));
        $wnd.getPlanetName = $entry(@com.qagwaai.starmalaccamax.client.ssviz.jsni.Bridge::getPlanetName());
        $wnd.onMouseOverPlanet = $entry(@com.qagwaai.starmalaccamax.client.ssviz.jsni.Bridge::onMouseOverPlanet(Ljava/lang/String;));
    }-*/;

    public static native void callStartup() /*-{
		$wnd.SSVIZ.startup();
    }-*/;

    public static native void callStartupInit(final String elementId, final int windowHeight, final int windowWidth) /*-{
		$wnd.logConsole.debug( "calling startupInit" );
		$wnd.SSVIZ.startupInit( elementId, windowHeight, windowWidth );
    }-*/;

    public static native void callShutdown() /*-{
		$wnd.SSVIZ.shutdown();
    }-*/;
    
    public static native void popupLogger() /*-{
    }-*/;
    
    public static native void setAPlanet(final String jsonObject) /*-{
		$wnd.SSVIZ.setAPlanet( jsonObject );
    }-*/;
    
    public static native void setAllPlanets(final String jsonArray) /*-{
		$wnd.SSVIZ.setAllPlanets( jsonArray );
    }-*/;
    
    public static native void setSolarSystem(final String jsonObject) /*-{
		$wnd.SSVIZ.setSolarSystem( jsonObject );
    }-*/;
    
    public static native void setStars(final String jsonArray) /*-{
		$wnd.SSVIZ.setStars( jsonArray );
    }-*/;
    
    public static native void setRotation( final boolean pause ) /*-{
		$wnd.SSVIZ.setRotation( pause );
    }-*/;

    public static native void exportScene() /*-{
		$wnd.SSVIZ.exportScene();
    }-*/;
}
