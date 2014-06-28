/**
 * GameEventWindowPresenter.java
 * Created by pgirard at 11:50:16 AM on Oct 26, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.AbstractPresenter;
import com.qagwaai.starmalaccamax.client.data.DataSourceFactory;
import com.qagwaai.starmalaccamax.client.data.GameEventDS;
import com.qagwaai.starmalaccamax.client.data.GameEventRecord;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.GameEventCloseClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.GameEventSaveClickHandlerImplementation;
import com.qagwaai.starmalaccamax.shared.InvalidParameterException;
import com.qagwaai.starmalaccamax.shared.model.GameEvent;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;
import com.smartgwt.client.data.DataSource;

/**
 * @author pgirard
 * 
 */
public final class GameEventWindowPresenterImpl extends AbstractPresenter<GameEventWindowView, GameEvent> implements GameEventWindowPresenter {


    /**
	 * 
	 */
    private DataSource dataSource;

    /**
     * 
     * @param eventBus
     *            the bus to publish events on
     * @param view
     *            the view that this presenter will attach to
     * @param model
     *            the model to present, if any
     */
    public GameEventWindowPresenterImpl(final EventBus eventBus, final GameEventWindowViewImpl view, final GameEvent model) {
        super(eventBus, view, model);
        view.setPresenter(this);
        try {
            dataSource = DataSourceFactory.get(GameEventDS.DS_TYPE, eventBus);
            if (model != null) {
                GameEventRecord[] records = new GameEventRecord[1];
                records[0] = new GameEventRecord((GameEventDTO) model);
                dataSource.setCacheData(records);
            }
        } catch (InvalidParameterException e) {
            view.alert("Could not load data source");
            return;
        }
        view.layout();

        view.getSaveButton().addClickHandler(new GameEventSaveClickHandlerImplementation(model, view));

        view.getCloseButton().addClickHandler(new GameEventCloseClickHandlerImplementation(view));

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void destroyView() {
        getView().destroy();

    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.GameEventWindowPresenter#getDataSource()
	 */
    @Override
	public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void hideView() {
        getView().hide();

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void renderView() {
        getView().render();

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void showView() {
        getView().show();
    }
}
