/**
 * JobDTO.java
 * Created by pgirard at 8:26:17 PM on Oct 20, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.google.code.twig.annotation.Id;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.annotation.Entity;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
@Entity
public final class JobDTO implements Job, IsSerializable, Serializable {

    /**
	 * 
	 */
    private String commandClass;
    /**
	 * 
	 */
    private String description;
    /**
	 * 
	 */
    @com.google.code.twig.annotation.Id
    @com.googlecode.objectify.annotation.Id
    private Long id;
    /**
	 * 
	 */
    private Date lastRun;
    /**
	 * 
	 */
    private Long recurrence;
    /**
	 * 
	 */
    private String status;
    /**
	 * 
	 */
    private boolean enabled;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        JobDTO other = (JobDTO) obj;
        if (commandClass == null) {
            if (other.commandClass != null) {
                return false;
            }
        } else if (!commandClass.equals(other.commandClass)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (enabled != other.enabled) {
            return false;
        }
        if (lastRun == null) {
            if (other.lastRun != null) {
                return false;
            }
        } else if (!lastRun.equals(other.lastRun)) {
            return false;
        }
        if (status == null) {
            if (other.status != null) {
                return false;
            }
        } else if (!status.equals(other.status)) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommandClass() {
        return commandClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getLastRun() {
        return lastRun;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getRecurrence() {
        return recurrence;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStatus() {
        return status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((commandClass == null) ? 0 : commandClass.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + (enabled ? 1231 : 1237);
        result = prime * result + ((lastRun == null) ? 0 : lastRun.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCommandClass(final String commandClass) {
        this.commandClass = commandClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLastRun(final Date lastRun) {
        this.lastRun = lastRun;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRecurrence(final Long milliseconds) {
        this.recurrence = milliseconds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("JobDTO [commandClass=");
        builder.append(commandClass);
        builder.append(", description=");
        builder.append(description);
        builder.append(", enabled=");
        builder.append(enabled);
        builder.append(", id=");
        builder.append(id);
        builder.append(", lastRun=");
        builder.append(lastRun);
        builder.append(", recurrence=");
        builder.append(recurrence);
        builder.append(", status=");
        builder.append(status);
        builder.append("]");
        return builder.toString();
    }

}
