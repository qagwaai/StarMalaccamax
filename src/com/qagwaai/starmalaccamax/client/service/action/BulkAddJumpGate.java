/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
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
public final class BulkAddJumpGate implements IsSerializable, Action<BulkAddJumpGateResponse> {

    /**
	 * 
	 */
    private ArrayList<JumpGateDTO> jumpGates;

    /**
	 * 
	 */
    public BulkAddJumpGate() {

    }

    /**
     * @param jumpGates
     *            the JumpGates to add
     */
    public BulkAddJumpGate(final ArrayList<JumpGateDTO> jumpGates) {
        this.jumpGates = jumpGates;
    }

    /**
     * @return the jumpGates
     */
    public ArrayList<JumpGateDTO> getJumpGates() {
        return jumpGates;
    }

    /**
     * @param jumpGates
     *            the JumpGates to set
     */
    public void setJumpGates(final ArrayList<JumpGateDTO> jumpGates) {
        this.jumpGates = jumpGates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final int maxLen = 10;
        return "BulkAddJumpGate [jumpGates="
            + (jumpGates != null ? jumpGates.subList(0, Math.min(jumpGates.size(), maxLen)) : null) + "]";
    }

}
