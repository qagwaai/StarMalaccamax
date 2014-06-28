package com.qagwaai.starmalaccamax.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.qagwaai.starmalaccamax.client.core.HistoryManager;
import com.qagwaai.starmalaccamax.client.core.Locations;
import com.qagwaai.starmalaccamax.client.core.LoginWatcher;
import com.qagwaai.starmalaccamax.client.core.StatsManager;
import com.qagwaai.starmalaccamax.client.core.TimeoutManager;
import com.qagwaai.starmalaccamax.client.core.channel.LoginListenerForChannel;
import com.qagwaai.starmalaccamax.client.core.channel.PushMessageListener;
import com.qagwaai.starmalaccamax.client.core.events.WindowResizedEvent;
import com.qagwaai.starmalaccamax.client.core.util.UncaughtHandler;
import com.qagwaai.starmalaccamax.client.event.CurrentUserChangedEvent;
import com.qagwaai.starmalaccamax.client.event.DefaultEventBus;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.event.PushMessageReceivedEvent;
import com.qagwaai.starmalaccamax.client.game.mvp.LandingPresenterImpl;
import com.qagwaai.starmalaccamax.client.game.mvp.LandingViewImpl;
import com.qagwaai.starmalaccamax.client.ssviz.jsni.Bridge;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;
import com.smartgwt.client.core.KeyIdentifier;
import com.smartgwt.client.util.KeyCallback;
import com.smartgwt.client.util.Page;
import com.smartgwt.client.util.SC;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public final class StarMalaccamax implements EntryPoint {
    /**
     * spin up gin bindings
     */
    // private final StarMalaccamaxGinjector ginjector =
    // GWT.create(StarMalaccamaxGinjector.class);

    private EventBus eventBus;

    /**
	 * 
	 */
    private void launchApplication() {
        mapKeys();
        LandingPresenterImpl presenter =
            new LandingPresenterImpl(eventBus, new LandingViewImpl(Locations.getLandingPage()), new UserDTO());
        presenter.renderView();
        HistoryManager.pushHistory(presenter, Locations.getLandingPage());
        History.newItem(Locations.getLandingPage(), true);
    }

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {
	Bridge.exportStaticMethod();

        eventBus = new DefaultEventBus();
        Application.getInstance().setEventBus(eventBus);
        GWT.setUncaughtExceptionHandler(new UncaughtHandler(eventBus));
        Scheduler.get().scheduleDeferred(new Command() {

            @Override
            public void execute() {
                launchApplication();

            }
        });

        TimeoutManager.getInstance().initializeManager(0, 0);
        // start watching for login/logoff events
        LoginWatcher watcher = LoginWatcher.getInstance();
        watcher.start(eventBus);
        // watch for new login so that we can setup a push channel
        eventBus.addHandler(CurrentUserChangedEvent.getType(), new LoginListenerForChannel(eventBus));
        // TODO test for logging - disconnect
        eventBus.addHandler(PushMessageReceivedEvent.getType(), new PushMessageListener());

        RootPanel.getBodyElement().removeChild(RootPanel.get("loadingWrapper").getElement());
        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(final ResizeEvent arg0) {
                WindowResizedEvent.fire(eventBus);
            }
        });
    }

    private void mapKeys() {
        KeyIdentifier statsKey = new KeyIdentifier();

        statsKey.setCtrlKey(true);
        statsKey.setAltKey(true);
        statsKey.setKeyName("0");
        Page.registerKey(statsKey, new KeyCallback() {

            @Override
            public void execute(String keyName) {
                SC.say("Async Callback Status", StatsManager.getInstance().getCallbackStatsReport());
            }
        });
    }
}
