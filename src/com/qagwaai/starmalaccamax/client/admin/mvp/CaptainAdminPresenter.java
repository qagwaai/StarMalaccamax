package com.qagwaai.starmalaccamax.client.admin.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.Presenter;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.shared.model.Captain;
import com.smartgwt.client.data.DataSource;

public interface CaptainAdminPresenter extends Presenter<CaptainAdminView, Captain> {

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

    void showDetails(AdminView view, EventBus eventBus);

}