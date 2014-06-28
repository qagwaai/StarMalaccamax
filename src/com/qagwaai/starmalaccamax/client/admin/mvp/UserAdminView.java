package com.qagwaai.starmalaccamax.client.admin.mvp;

import com.smartgwt.client.widgets.grid.events.RowContextClickEvent;

public interface UserAdminView extends AdminView {

    void showContextMenu(RowContextClickEvent event);

}