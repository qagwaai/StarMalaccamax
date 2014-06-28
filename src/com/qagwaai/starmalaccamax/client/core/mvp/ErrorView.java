/**
 * ErrorView.java
 * Created by pgirard at 10:28:08 AM on Oct 1, 2010
 * in the com.qagwaai.starmalaccamax.client.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.core.mvp;

import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

/**
 * @author pgirard
 * 
 */
public class ErrorView extends AbstractView<ErrorPresenter> {
    /**
	 * 
	 */
    private Window rootWindow;
    /**
	 * 
	 */
    private TextItem shortMessageItem;

    /**
	 * 
	 */
    private TextAreaItem detailItem;

    /**
	 * 
	 */
    private TextItem priorityItem;

    /**
	 * 
	 */
    public ErrorView() {
        rootWindow = new Window();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Widget asWidget() {
        return rootWindow;
    }

    /**
	 * 
	 */
    public final void closeWindow() {
        rootWindow.destroy();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void destroy() {
        closeWindow();

    }

    /**
     * @return the detailItem
     */
    public final TextAreaItem getDetailItem() {
        return detailItem;
    }

    /**
     * @return the priorityItem
     */
    public final TextItem getPriorityItem() {
        return priorityItem;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Canvas getRootCanvas() {
        return rootWindow;
    }

    /**
     * @return the shortMessageItem
     */
    public final TextItem getShortMessageItem() {
        return shortMessageItem;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void layout() {
        rootWindow.setAutoCenter(true);
        rootWindow.setIsModal(true);
        rootWindow.setShowModalMask(true);
        rootWindow.setCanDragResize(true);
        rootWindow.setCanDragReposition(true);
        rootWindow.setShowMinimizeButton(false);
        rootWindow.setWidth(400);
        rootWindow.setHeight(400);
        rootWindow.setTitle("Error");

        final DynamicForm form = new DynamicForm();
        form.setWidth100();
        form.setHeight100();

        shortMessageItem = new TextItem();
        shortMessageItem.setTitle("Short");

        detailItem = new TextAreaItem();
        detailItem.setTitle("Detail");
        detailItem.setWidth("100%");
        detailItem.setHeight(300);

        priorityItem = new TextItem();
        priorityItem.setTitle("Priority");

        form.setFields(new FormItem[] { shortMessageItem, priorityItem, detailItem });

        rootWindow.addItem(form);

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void render() {
        rootWindow.setAutoCenter(true);
        rootWindow.show();

    }

}
