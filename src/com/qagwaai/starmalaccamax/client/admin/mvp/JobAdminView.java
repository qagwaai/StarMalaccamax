package com.qagwaai.starmalaccamax.client.admin.mvp;

import com.smartgwt.client.widgets.grid.events.RowContextClickEvent;

public interface JobAdminView extends AdminView {

    void showContextMenu(RowContextClickEvent event);

}