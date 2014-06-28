package com.qagwaai.starmalaccamax.client.admin.mvp.delegates;

import com.qagwaai.starmalaccamax.client.admin.mvp.AdminView;
import com.qagwaai.starmalaccamax.client.event.EventBus;

public interface ShowDetailsDelegate {
    void showDetails(AdminView view, EventBus eventBus);
}
