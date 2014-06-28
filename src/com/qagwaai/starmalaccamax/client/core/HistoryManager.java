/**
 * HistoryManager.java
 * Created by pgirard at 1:02:17 PM on Sep 1, 2010
 * in the com.qagwaai.starmalaccamax.client package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.core;

import java.util.LinkedHashMap;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.qagwaai.starmalaccamax.client.core.mvp.Presenter;
import com.qagwaai.starmalaccamax.client.core.mvp.PresenterFactory;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.Model;

/**
 * @author pgirard
 * 
 */
public final class HistoryManager {

    /**
	 * 
	 */
    private static LinkedHashMap<String, Presenter> presenterCache = new LinkedHashMap<String, Presenter>();

    /**
     * 
     * @param token
     *            the name of the presenter to look for
     * @return a presenter if one has been stored or null
     */
    public static Presenter getFromCache(final String token) {
        History.newItem(token, true);
        return presenterCache.get(token);
    }

    /**
     * hide the current page/view
     */
    public static void hideCurrentPresenter() {
        Presenter presenter = presenterCache.get(History.getToken());
        if (presenter != null) {
            presenter.hideView();
        } else {
            Window.alert("Could not hide [" + History.getToken() + "]. not stored?");
        }
        RootPanel.get().clear();
    }

    /**
     * 
     * @param token
     *            the token (unique) of the presenter to present
     * @param eventBus
     *            the bus for the presenter to publish onto
     * @param model
     *            any model used in the presenter
     * @param filter
     *            an optional filter
     */
    public static void present(final String token, final EventBus eventBus, final Model model, final Filter filter) {
        HistoryManager.hideCurrentPresenter();
        Presenter presenter = HistoryManager.getFromCache(token);
        if (presenter == null) {
            presenter = PresenterFactory.create(token, eventBus, model, filter);
            presenter.renderView();
        } else {
            presenter.setFilter(filter);
            presenter.showView();
        }
        HistoryManager.pushHistory(presenter, token);
    }

    /**
     * 
     * @param p
     *            the fully created presenter
     * @param token
     *            the name of location
     */
    public static void pushHistory(final Presenter p, final String token) {
        presenterCache.put(token, p);
    }

    /**
	 * 
	 */
    private HistoryManager() {
    }
}
