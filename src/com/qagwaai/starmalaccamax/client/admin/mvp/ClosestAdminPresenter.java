package com.qagwaai.starmalaccamax.client.admin.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.Presenter;
import com.qagwaai.starmalaccamax.shared.model.Closest;
import com.smartgwt.client.data.DataSource;

public interface ClosestAdminPresenter extends Presenter<ClosestAdminView, Closest> {

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