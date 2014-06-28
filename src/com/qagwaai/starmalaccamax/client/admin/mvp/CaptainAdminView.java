package com.qagwaai.starmalaccamax.client.admin.mvp;

import com.smartgwt.client.widgets.grid.events.RowContextClickEvent;

public interface CaptainAdminView extends AdminView {

    void showContextMenu(final RowContextClickEvent event);

}