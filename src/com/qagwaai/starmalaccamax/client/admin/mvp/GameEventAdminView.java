package com.qagwaai.starmalaccamax.client.admin.mvp;

import com.smartgwt.client.widgets.grid.events.RowContextClickEvent;

public interface GameEventAdminView extends AdminView {

    void showContextMenu(RowContextClickEvent event);

}