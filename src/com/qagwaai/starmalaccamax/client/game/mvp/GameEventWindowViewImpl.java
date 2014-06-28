/**
 * GameEventWindowView.java
 * Created by pgirard at 11:50:58 AM on Oct 26, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import com.google.gwt.user.client.ui.Widget;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractView;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author pgirard
 * 
 */
public class GameEventWindowViewImpl extends AbstractView<GameEventWindowPresenter> implements GameEventWindowView {
    private static final int VIEW_HEIGHT = 450;

    private static final int VIEW_WIDTH = 600;

    private static final int VIEW_ROOT_MARGIN = 10;

    /**
	 * 
	 */
    private Window rootWindow;

    /**
	 * 
	 */
    private DynamicForm form = new DynamicForm();

    /**
     * 
     */
    private IButton saveButton = new IButton("Save");

    /**
     * 
     */
    private IButton closeButton = new IButton("Close");

    /**
	 * 
	 */
    public GameEventWindowViewImpl() {
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

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.GameEventWindowView#getCloseButton()
	 */
    @Override
	public final IButton getCloseButton() {
        return closeButton;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.GameEventWindowView#getForm()
	 */
    @Override
	public final DynamicForm getForm() {
        return form;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Canvas getRootCanvas() {
        return rootWindow;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.GameEventWindowView#getSaveButton()
	 */
    @Override
	public final IButton getSaveButton() {
        return saveButton;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void layout() {
        VLayout baseLayout = new VLayout(VIEW_ROOT_MARGIN);
        rootWindow.setWidth(VIEW_WIDTH);
        rootWindow.setHeight(VIEW_HEIGHT);
        rootWindow.setTitle("GameEvent for Planet ");
        rootWindow.setIsModal(true);
        rootWindow.setShowModalMask(true);
        rootWindow.setCanDragResize(true);
        rootWindow.setCanDragReposition(true);
        rootWindow.setShowMinimizeButton(false);

        form.setDataSource(getPresenter().getDataSource());

        HStack buttonBar = new HStack();
        buttonBar.addMember(saveButton);
        buttonBar.addMember(closeButton);

        baseLayout.addMember(form);
        baseLayout.addMember(buttonBar);

        rootWindow.addItem(baseLayout);
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

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.GameEventWindowView#setTitle(java.lang.String)
	 */
    @Override
	public final void setTitle(final String windowTitle) {
        rootWindow.setTitle(windowTitle);
    }
}
