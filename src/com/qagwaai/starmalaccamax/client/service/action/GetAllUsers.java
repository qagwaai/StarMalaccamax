/**
 * GetAllUsers.java
 * Created by pgirard at 2:06:53 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * 
 */
public final class GetAllUsers implements IsSerializable, Action<GetAllUsersResponse> {

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
    private ArrayList<String> sort;
    /**
	 * 
	 */
    private boolean usePaging;

    /**
	 * 
	 */
    public GetAllUsers() {

    }

    /**
     * @param startRow
     *            the row to start on
     * @param endRow
     *            the row to end on
     */
    public GetAllUsers(final int startRow, final int endRow) {
        usePaging = true;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    /**
     * @return the endRow
     */
    public int getEndRow() {
        return endRow;
    }

    /**
     * @return the sort
     */
    public ArrayList<String> getSort() {
        return sort;
    }

    /**
     * @return the startRow
     */
    public int getStartRow() {
        return startRow;
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
     * @param sort
     *            the sort to set
     */
    public void setSort(final ArrayList<String> sort) {
        this.sort = sort;
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
        return "GetAllUsers [endRow=" + endRow + ", sort="
            + (sort != null ? sort.subList(0, Math.min(sort.size(), maxLen)) : null) + ", startRow=" + startRow
            + ", usePaging=" + usePaging + "]";
    }

    /**
     * @return the usePaging
     */
    public boolean usePaging() {
        return usePaging;
    }

}
