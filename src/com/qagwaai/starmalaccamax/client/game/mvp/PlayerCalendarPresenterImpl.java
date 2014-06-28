/**
 * PlayerCalendarPresenter.java
 * Created by pgirard at 1:12:07 PM on Dec 2, 2010
 * in the com.qagwaai.starmalaccamax.client.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.LoginWatcher;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractPresenter;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.callbacks.GetPlayerSummaryCallback;
import com.qagwaai.starmalaccamax.client.game.callbacks.PlayerCalendarZoomClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.PlayerCalendarDayBodyClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.GetPlayerSummaryPage;
import com.qagwaai.starmalaccamax.shared.model.User;

/**
 * @author pgirard
 * 
 */
public class PlayerCalendarPresenterImpl extends AbstractPresenter<PlayerCalendarView, User> implements PlayerCalendarPresenter {


    /**
     * 
     * @param eventBus
     *            where to publish/listen for events
     * @param view
     *            the associated view
     * @param model
     *            the user data
     */
    public PlayerCalendarPresenterImpl(final EventBus eventBus, final PlayerCalendarViewImpl view, final User model) {
        super(eventBus, view, model);
        view.setPresenter(this);
        view.layout();

        loadSummaryListGrid();
        view.getEventCalendar().addDayBodyClickHandler(new PlayerCalendarDayBodyClickHandlerImplementation(view));

        view.getZoomCalendarMenu().addClickHandler(new PlayerCalendarZoomClickHandlerImplementation(eventBus, view));
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void destroyView() {
        getView().destroy();

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void hideView() {
        getView().hide();

    }

    /**
	 * 
	 */
    private void loadSummaryListGrid() {
        ServiceFactory.getPlayerSummaryService().execute(
            new GetPlayerSummaryPage(LoginWatcher.getInstance().getLastEvent().getCurrentUser()),
            new GetPlayerSummaryCallback(getView()));
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void renderView() {
        getView().render();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void showView() {
        getView().show();

    }

}
