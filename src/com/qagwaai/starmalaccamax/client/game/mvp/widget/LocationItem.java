/**
 * LocationItem.java
 * Created by pgirard at 3:39:14 PM on Nov 23, 2010
 * in the com.qagwaai.starmalaccamax.client.view.widget package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp.widget;

import com.google.gwt.core.client.JavaScriptObject;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author pgirard
 * 
 */
public final class LocationItem extends VLayout {

    /**
	 * 
	 */
    private LocationDTO location;
    /**
	 * 
	 */
    private DynamicForm form;
    /**
	 * 
	 */
    private TextItem solarSystemItem = new TextItem("solarSystem", "Solar System");
    /**
	 * 
	 */
    private TextItem planetItem = new TextItem("planet", "Planet");
    /**
	 * 
	 */
    private TextItem xItem = new TextItem("x", "X Coordinate");
    /**
	 * 
	 */
    private TextItem yItem = new TextItem("y", "Y Coordinate");
    /**
	 * 
	 */
    private TextItem zItem = new TextItem("z", "Z Coordinate");

    /**
	 * 
	 */
    public LocationItem() {
    }

    /**
     * @param membersMargin
     *            the margin between each member
     */
    public LocationItem(final int membersMargin) {
        super(membersMargin);
    }

    /**
     * @param jsObj
     *            build this object from a javascriptobject
     */
    public LocationItem(final JavaScriptObject jsObj) {
        super(jsObj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected JavaScriptObject create() {
        form = new DynamicForm();
        form.setFields(solarSystemItem, planetItem, xItem, yItem, zItem);
        this.addMember(form);
        return super.create();
    }

    /**
     * @return the planetItem
     */
    public TextItem getPlanetItem() {
        return planetItem;
    }

    /**
     * @return the solarSystemItem
     */
    public TextItem getSolarSystemItem() {
        return solarSystemItem;
    }

    /**
     * 
     * @return the location value
     */
    public LocationDTO getValue() {
        return location;
    }

    /**
     * @return the xItem
     */
    public TextItem getXItem() {
        return xItem;
    }

    /**
     * @return the yItem
     */
    public TextItem getYItem() {
        return yItem;
    }

    /**
     * @return the zItem
     */
    public TextItem getZItem() {
        return zItem;
    }

    /**
     * 
     * @param location
     *            the location to display
     */
    public void setValue(final LocationDTO location) {
        this.location = location;
        solarSystemItem.setValue(String.valueOf(location.getSolarSystemId()));
        planetItem.setValue(String.valueOf(location.getPlanetId()));
        xItem.setValue(String.valueOf(location.getX()));
        yItem.setValue(String.valueOf(location.getY()));
        zItem.setValue(String.valueOf(location.getZ()));
    }

}
