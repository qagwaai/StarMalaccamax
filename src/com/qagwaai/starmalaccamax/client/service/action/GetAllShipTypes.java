/**
 * GetAllUsers.java
 * Created by pgirard at 2:06:53 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.Filter;

/**
 * @author pgirard
 * 
 */
public final class GetAllShipTypes implements IsSerializable, Action<GetAllShipTypesResponse> {

    /**
	 * 
	 */
    private int startRow;
    /**
	 * 
	 */
    private int endRow;
    /**
	 * 
	 */
    private boolean usePaging;
    /**
	 * 
	 */
    private ArrayList<Filter> criteria;
    /**
	 * 
	 */
    private String sortBy;

    /**
	 * 
	 */
    public GetAllShipTypes() {

    }

    /**
     * @param startRow
     *            the row to start on
     * @param endRow
     *            the row to end on
     */
    public GetAllShipTypes(final int startRow, final int endRow) {
        usePaging = true;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    /**
     * @param startRow
     *            the row to start on
     * @param endRow
     *            the row to end on
     * @param criteria
     *            any criteria to filter on
     * @param sortBy
     *            any sorts
     */
    public GetAllShipTypes(final int startRow, final int endRow, final ArrayList<Filter> criteria, final String sortBy) {
        this(startRow, endRow);
        this.criteria = criteria;
        this.sortBy = sortBy;
    }

    /**
     * @return the criteria
     */
    public ArrayList<Filter> getCriteria() {
        return criteria;
    }

    /**
     * @return the endRow
     */
    public int getEndRow() {
        return endRow;
    }

    /**
     * @return the sortBy
     */
    public String getSortBy() {
        return sortBy;
    }

    /**
     * @return the startRow
     */
    public int getStartRow() {
        return startRow;
    }

    /**
     * @return the usePaging
     */
    public boolean isUsePaging() {
        return usePaging;
    }

    /**
     * @param criteria
     *            the criteria to set
     */
    public void setCriteria(final ArrayList<Filter> criteria) {
        this.criteria = criteria;
    }

    /**
     * @param endRow
     *            the endRow to set
     */
    public void setEndRow(final int endRow) {
        usePaging = true;
        this.endRow = endRow;
    }

    /**
     * @param sortBy
     *            the sortBy to set
     */
    public void setSortBy(final String sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * @param startRow
     *            the startRow to set
     */
    public void setStartRow(final int startRow) {
        usePaging = true;
        this.startRow = startRow;
    }

    /**
     * @param usePaging
     *            the usePaging to set
     */
    public void setUsePaging(final boolean usePaging) {
        this.usePaging = usePaging;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final int maxLen = 10;
        return "GetAllShipTypes [criteria="
            + (criteria != null ? criteria.subList(0, Math.min(criteria.size(), maxLen)) : null) + ", endRow=" + endRow
            + ", sortBy=" + sortBy + ", startRow=" + startRow + ", usePaging=" + usePaging + "]";
    }

    /**
     * @return the usePaging
     */
    public boolean usePaging() {
        return usePaging;
    }

}
