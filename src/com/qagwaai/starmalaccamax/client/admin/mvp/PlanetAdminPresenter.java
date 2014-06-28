package com.qagwaai.starmalaccamax.client.admin.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.Presenter;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.Planet;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.smartgwt.client.data.DataSource;

public interface PlanetAdminPresenter extends Presenter<PlanetAdminView, Planet> {

    /**
     * 
     * @param ship
     *            the ship to update
     * @param planet
     *            the planet location to assign
     */
    void assignLocationToShip(final ShipDTO ship, final PlanetDTO planet);

    /**
     * 
     * {@inheritDoc}
     */
    void destroyView();

    /**
     * @return the dataSource
     */
    DataSource getDataSource();

    /**
     * 
     * {@inheritDoc}
     */
    void hideView();

    /**
     * 
     * {@inheritDoc}
     */
    void renderView();

    /**
     * {@inheritDoc}
     */
    void setFilter(final Filter filter);

    /**
     * 
     * {@inheritDoc}
     */
    void showView();

}