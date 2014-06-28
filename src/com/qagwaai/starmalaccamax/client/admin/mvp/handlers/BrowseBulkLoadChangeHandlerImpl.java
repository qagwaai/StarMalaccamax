package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.qagwaai.starmalaccamax.client.admin.mvp.AdminFileUpload;
import com.qagwaai.starmalaccamax.client.admin.util.HTML5BulkLoadCommand;
import com.qagwaai.starmalaccamax.client.html5.File;
import com.qagwaai.starmalaccamax.client.html5.FileList;

public final class BrowseBulkLoadChangeHandlerImpl implements ChangeHandler {
    private final HTML5BulkLoadCommand command;
    private final AdminFileUpload view;

    public BrowseBulkLoadChangeHandlerImpl(AdminFileUpload view, HTML5BulkLoadCommand command) {
        this.command = command;
        this.view = view;
    }

    @Override
    public void onChange(ChangeEvent event) {
        FileList fileList = FileList.fromEvent(event.getNativeEvent());
        for (int i = 0; i < fileList.length(); i++) {
            File file = fileList.get(i);
            if (file == null) {
                break;
            }
            // processFile(file);
            command.setFile(file);
            command.setRecordsPerTick(view.getRecordsPerTick());
            Scheduler.get().scheduleDeferred(command);
        }
    }
}
