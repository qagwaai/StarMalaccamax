/**
 * Presenter.java
 * Created by pgirard at 9:16:46 AM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.client.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.core.mvp;

import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.Model;

/**
 * @author pgirard
 * 
 */
public interface Presenter<V extends View, M extends Model> {
    /**
     * remove the view
     */
    void destroyView();

    /**
     * 
     * @return the event bus associated with the presenter
     */
    EventBus getEventBus();

    /**
     * 
     * @return the filter of the model
     */
    Filter getFilter();

    /**
	 * 
	 */
    void hideView();

    /**
     * render the associated view
     */
    void renderView();

    /**
     * 
     * @param filter
     *            the filter of the model
     */
    void setFilter(Filter filter);

    /**
	 * 
	 */
    void showView();

    M getModel();

    void setModel(M model);

    V getView();
}
