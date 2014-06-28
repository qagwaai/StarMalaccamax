package com.qagwaai.starmalaccamax.client.admin.util;

import com.google.gwt.user.client.Command;
import com.qagwaai.starmalaccamax.client.html5.File;

public interface HTML5BulkLoadCommand extends Command {
    void setFile(File file);

    void setRecordsPerTick(int recordsPerTick);
}