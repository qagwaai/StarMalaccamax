/**
 * TickCommand.java
 * com.qagwaai.starmalaccamax.server.tick
 * StarMalaccamax
 * Created Mar 11, 2011 at 2:36:02 PM
 */
package com.qagwaai.starmalaccamax.server.business.tick;

import com.qagwaai.starmalaccamax.server.dao.DAOException;

/**
 * @author pgirard
 * 
 */
public interface TickCommand {
    /**
     * 
     * @return true of the execution completed successfully
     * @throws TickException
     *             if there was a non-data exception
     * @throws DAOException
     *             if there was a data exception
     */
    boolean execute() throws TickException, DAOException;
}
