/**
 * CalendarWindowPresenter.java
 * Created by pgirard at 11:50:16 AM on Oct 26, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.mvp.AbstractPresenter;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.CalendarCloseClickHandlerImplementation;
import com.qagwaai.starmalaccamax.shared.model.CalendarEvents;
import com.smartgwt.client.widgets.calendar.CalendarEvent;

/**
 * @author pgirard
 * 
 */
public class CalendarWindowPresenterImpl extends AbstractPresenter<CalendarWindowView, CalendarEvents> implements CalendarWindowPresenter {



    /**
     * 
     * @param eventBus
     *            the bus to publish events on
     * @param view
     *            the view that this presenter will attach to
     * @param model
     *            the model to present, if any
     */
    public CalendarWindowPresenterImpl(final EventBus eventBus, final CalendarWindowViewImpl view, final CalendarEvents model) {
        super(eventBus, view, model);
        view.setPresenter(this);
        view.layout();

        view.getCalendar().selectTab(2);
        view.getCalendar().setData(model.toArray(new CalendarEvent[model.size()]));
        // view.getCalendar().invalidateCache();
        view.getCloseButton().addClickHandler(new CalendarCloseClickHandlerImplementation(view));
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
