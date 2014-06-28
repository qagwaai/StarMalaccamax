package com.qagwaai.starmalaccamax.client.admin.mvp;

import com.google.gwt.event.dom.client.ChangeHandler;

/**
 * 
 * @author ehldxae
 *
 */
public interface AdminFileUpload {
    /**
     * 
     * @param handler add a handler to the browse control
     */
    void addBrowseChangeHandler(ChangeHandler handler);

    /**
     * 
     * @param filename set the name of the file selected so that the view can show it
     */
    void setSelectedFile(String filename);

    /**
     * 
     * @param status add a status string to the progress
     */
    void addStatus(String status);

    /**
     * 
     * @return the number of records to pull per tick
     */
    int getRecordsPerTick();
}
