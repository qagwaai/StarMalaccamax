/**
 * GetAllUsersResponse.java
 * Created by pgirard at 2:07:29 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.CaptainSolarSystemDTO;

/**
 * @author pgirard
 * 
 */
public final class GetSolarSystemsForCaptainsResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ArrayList<CaptainSolarSystemDTO> results;

    /**
     * @return the results
     */
    public ArrayList<CaptainSolarSystemDTO> getResults() {
        return results;
    }

    /**
     * @param results
     *            the results to set
     */
    public void setResults(final ArrayList<CaptainSolarSystemDTO> results) {
        this.results = results;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetSolarSystemsForCaptainsResponse [results=" + results + "]";
    }

}
