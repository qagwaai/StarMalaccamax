/**
 * GetAllUsersResponse.java
 * Created by pgirard at 2:07:29 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.JumpGateDTO;

/**
 * @author pgirard
 * 
 */
public final class GetAllJumpGatesResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ArrayList<JumpGateDTO> jumpGates;
    /**
	 * 
	 */
    private int totalJumpGates;

    /**
     * @return the users
     */
    public ArrayList<JumpGateDTO> getJumpGates() {
        return jumpGates;
    }

    /**
     * @return the totalJumpGates
     */
    public int getTotalJumpGates() {
        return totalJumpGates;
    }

    /**
     * @param jumpGates
     *            the users to set
     */
    public void setJumpGates(final ArrayList<JumpGateDTO> jumpGates) {
        this.jumpGates = jumpGates;
    }

    /**
     * @param totalJumpGates
     *            the totalJumpGates to set
     */
    public void setTotalJumpGates(final int totalJumpGates) {
        this.totalJumpGates = totalJumpGates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetAllJumpGatesResponse [jumpGates=" + jumpGates + ", totalJumpGates=" + totalJumpGates + "]";
    }

}
