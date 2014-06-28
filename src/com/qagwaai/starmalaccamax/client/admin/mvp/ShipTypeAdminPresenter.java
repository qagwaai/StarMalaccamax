package com.qagwaai.starmalaccamax.client.admin.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.Presenter;
import com.qagwaai.starmalaccamax.shared.model.ShipType;
import com.smartgwt.client.data.DataSource;

public interface ShipTypeAdminPresenter extends Presenter<ShipTypeAdminView, ShipType> {

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
     * 
     * {@inheritDoc}
     */
    void showView();

}