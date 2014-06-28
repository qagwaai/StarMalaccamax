/**
 * ErrorDTO.java
 * Created by pgirard at 10:24:36 AM on Oct 1, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.annotation.Entity;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class AppErrorDTO implements IsSerializable, Serializable, AppError {

    /**
	 * 
	 */
    private String detail;
    /**
	 * 
	 */
    private int priority;
    /**
	 * 
	 */
    private String shortMessage;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDetail() {
        return detail;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPriority() {
        return priority;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getShortMessage() {
        return shortMessage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDetail(final String detail) {
        this.detail = detail;

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setDetail(final Throwable error) {
        this.detail = extractDetails(error);

    }

    /**
     * 
     * @param error the error to extract from
     * @return a string with the error details
     */
    private String extractDetails(final Throwable error) {
        String buffer = error.getMessage();
        buffer += "\n";
        for (StackTraceElement element : error.getStackTrace()) {
            buffer += element.toString() + "\n";
        }
        return buffer;
    }

    @Override
    public void setDetails(final Set<Throwable> errors) {
        String buffer = "Multiple Errors\n";
        for (Throwable t : errors) {
            buffer += extractDetails(t);
            buffer += "\n-----------------------------------------------------\n";
        }
        this.detail = buffer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPriority(final int priority) {
        this.priority = priority;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setShortMessage(final String message) {
        this.shortMessage = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AppErrorDTO [detail=");
        builder.append(detail);
        builder.append(", priority=");
        builder.append(priority);
        builder.append(", shortMessage=");
        builder.append(shortMessage);
        builder.append("]");
        return builder.toString();
    }

}
