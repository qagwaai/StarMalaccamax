/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.JumpGateDTO;

/**
 * @author pgirard
 * 
 */
public final class AddJumpGate implements IsSerializable, Action<AddJumpGateResponse> {

    /**
	 * 
	 */
    private JumpGateDTO jumpGate;

    /**
	 * 
	 */
    public AddJumpGate() {

    }

    /**
     * @param jumpGate
     *            the JumpGate to add
     */
    public AddJumpGate(final JumpGateDTO jumpGate) {
        this.jumpGate = jumpGate;
    }

    /**
     * @return the jumpGate
     */
    public JumpGateDTO getJumpGate() {
        return jumpGate;
    }

    /**
     * @param jumpGate
     *            the JumpGate to set
     */
    public void setJumpGate(final JumpGateDTO jumpGate) {
        this.jumpGate = jumpGate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "AddJumpGate [jumpGate=" + jumpGate + "]";
    }

}
