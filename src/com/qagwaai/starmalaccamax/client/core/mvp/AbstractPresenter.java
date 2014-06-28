/**
 * AbstractView.java
 * Created by pgirard at 9:30:48 AM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.client.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.core.mvp;

import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.Model;

/**
 * @author pgirard
 * @param <V>
 *            the view bound to the presenter
 * @param <M>
 *            the model bound to this presenter
 */
public abstract class AbstractPresenter<V extends View, M extends Model> implements Presenter<V, M> {
    /**
	 * 
	 */
    private final EventBus eventBus;
    /**
     * The view for the presenter.
     */
    private final V view;

    /**
	 * 
	 */
    private M model;

    /**
	 * 
	 */
    private Filter filter;

    /**
     * 
     * @param eventBus
     *            the eventbus to be used to public events
     * @param view
     *            the view bound to the presenter
     * @param model
     *            the main model bound to the presenter
     */
    public AbstractPresenter(final EventBus eventBus, final V view, final M model) {
        this.eventBus = eventBus;
        this.view = view;
        this.model = model;
    }

    /**
     * 
     * @param eventBus
     *            the eventbus to be used to public events
     * @param view
     *            the view bound to the presenter
     * @param model
     *            the main model bound to the presenter
     * @param filter
     *            create presenter with filter
     */
    public AbstractPresenter(final EventBus eventBus, final V view, final M model, final Filter filter) {
        this.eventBus = eventBus;
        this.view = view;
        this.model = model;
        this.filter = filter;
    }

    /**
     * @return the eventBus
     */
    public final EventBus getEventBus() {
        return eventBus;
    }

    /**
     * @return the filter
     */
    public Filter getFilter() {
        return filter;
    }

    /**
     * @return the model
     */
    public final M getModel() {
        return model;
    }

    /**
     * @return the view
     */
    public final V getView() {
        return view;
    }

    /**
     * Render the view, firing any events
     */
    public abstract void renderView();

    /**
     * @param filter
     *            the filter to set
     */
    public void setFilter(final Filter filter) {
        this.filter = filter;
    }

    /**
     * @param model
     *            the model to set
     */
    public final void setModel(final M model) {
        this.model = model;
    }

}
