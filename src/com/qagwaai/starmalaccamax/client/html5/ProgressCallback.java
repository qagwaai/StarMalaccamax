package com.qagwaai.starmalaccamax.client.html5;

/**
 * 
 * @author ehldxae
 *
 */
public abstract class ProgressCallback {

    /**
     * When the read starts.
     * @param e the progress event
     */
    public void onLoadStart(final ProgressEvent e) {

    }

    /**
     * While reading (and decoding) file or fileBlob data, and reporting partial
     * file data (progess.loaded/progress.total)
     * 
     * @param e the progress event
     */
    public void onProgress(final ProgressEvent e) {

    }

    /**
     * When the read has been aborted. For instance, by invoking the abort()
     * method.
     * @param e the progress event
     */
    public void onAbort(final ProgressEvent e) {

    }

    /**
     * When the read has failed (see errors).
     * @param e the progress event
     */
    public abstract void onError(final ProgressEvent e);

    /**
     * When the read has successfully completed.
     * @param e the progress event
     */
    public abstract void onLoad(final ProgressEvent e);

    /**
     * When the request has completed (either in success or failure).
     * @param e the progress event
     */
    public void onLoadEnd(final ProgressEvent e) {

    }

}
