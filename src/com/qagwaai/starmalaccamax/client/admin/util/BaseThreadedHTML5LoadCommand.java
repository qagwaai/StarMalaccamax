package com.qagwaai.starmalaccamax.client.admin.util;

import java.util.ArrayList;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.qagwaai.starmalaccamax.client.admin.mvp.AdminFileUpload;
import com.qagwaai.starmalaccamax.client.html5.File;
import com.qagwaai.starmalaccamax.client.html5.FileReader;
import com.qagwaai.starmalaccamax.client.html5.ProgressCallback;
import com.qagwaai.starmalaccamax.client.html5.ProgressEvent;
import com.qagwaai.starmalaccamax.shared.TransformationException;

/**
 * 
 * @author ehldxae
 *
 */
public abstract class BaseThreadedHTML5LoadCommand implements Command {

    protected File file;
    protected AdminFileUpload view;
    protected int recordsPerTick;
    protected int line = 0;

    /**
     * 
     */
    public BaseThreadedHTML5LoadCommand() {
        super();
    }

    /**
     * 
     * @param view
     */
    public BaseThreadedHTML5LoadCommand(final AdminFileUpload view) {
        super();
        this.view = view;
    }

    /**
     * 
     * @param view
     * @param recordsPerTick
     */
    public BaseThreadedHTML5LoadCommand(final AdminFileUpload view, final int recordsPerTick) {
        super();
        this.view = view;
        this.recordsPerTick = recordsPerTick;
    }

    /**
     * 
     * @param file
     * @param view
     * @param recordsPerTick
     */
    public BaseThreadedHTML5LoadCommand(final File file, final AdminFileUpload view, final int recordsPerTick) {
        super();
        this.file = file;
        this.view = view;
        this.recordsPerTick = recordsPerTick;
    }

    /**
     * 
     * @return
     */
    public int getRecordsPerTick() {
        return recordsPerTick;
    }

    /**
     * 
     * @param recordsPerTick
     */
    public void setRecordsPerTick(final int recordsPerTick) {
        this.recordsPerTick = recordsPerTick;
    }

    /**
     * 
     * @return
     */
    public File getFile() {
        return file;
    }

    /**
     * 
     * @param file
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * 
     * @param toAdd
     * @param fileLine
     */
    public abstract void add(ArrayList toAdd, int fileLine);

    /**
     * 
     */
    public void execute() {
        final String fileName = file.getName();
        final String fileType = file.getType();
        FileReader reader = FileReader.create();
        reader.readAsBinaryString(file, new ProgressCallback() {

            @Override
            public void onError(final ProgressEvent e) {

            }

            @Override
            public void onLoad(ProgressEvent e) {
                String result = e.getResult();
                String[] records = result.split("\r\n");

                ArrayList bulkCache = createCache();
                while (line < records.length) {
                    view.addStatus("starting at " + line + "\n");
                    while ((line < records.length) && ((bulkCache.size() + 1) < recordsPerTick)) {
                        String buffer = records[line];
                        try {
                            if (line > 0) {
                                // skip first line
                                // bulkCache.add(DelimitedToRecord.toPlanet(buffer));
                                bulkCache.add(parseLine(buffer));
                            }
                            line++;
                        } catch (TransformationException te) {
                            view.addStatus(line + ":" + te.getMessage() + "\n");
                        }
                        if (bulkCache.size() == recordsPerTick) {
                            add(bulkCache, line);
                        }
                    }
                    view.addStatus("\n");
                    if (bulkCache.size() > 0) {
                        // add the last bit
                        add(bulkCache, line);
                    }
                    bulkCache.clear();
                }
                Window.alert("Loaded file: " + fileName + " with " + records.length + " records");
                // listener.onLoadFile(fileName, bytes);
            }
        });
    }

    /**
     * 
     * @return
     */
    public abstract ArrayList createCache();

    /**
     * 
     * @param buffer
     * @return
     * @throws TransformationException
     */
    public abstract Object parseLine(String buffer) throws TransformationException;
}