/**
 * 
 */
package com.qagwaai.starmalaccamax.client.data;

import java.util.ArrayList;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSProtocol;

/**
 * Data source with ability to communicate with server by GWT RPC.
 * <p/>
 * SmartClient natively supports data protocol "clientCustom". This protocol means that communication with server should
 * be implemented in <code>transformRequest (DSRequest request)</code> method. Here is a few things to note on
 * <code>transformRequest</code> implementation:
 * <ul>
 * <li><code>DSResponse</code> object has to be created and <code>processResponse (requestId, response)</code> must be
 * called to finish data request. <code>requestId</code> should be taken from original
 * <code>DSRequest.getRequestId ()</code>.</li>
 * <li>"clientContext" attribute from <code>DSRequest</code> should be copied to <code>DSResponse</code>.</li>
 * <li>In case of failure <code>DSResponse</code> should contain at least "status" attribute with error code (&lt;0).</li>
 * <li>In case of success <code>DSResponse</code> should contain at least "data" attribute with operation type specific
 * data:
 * <ul>
 * <li>FETCH - <code>ListGridRecord[]</code> retrieved records.</li>
 * <li>ADD - <code>ListGridRecord[]</code> with single added record. Operation is called on every newly added record.</li>
 * <li>UPDATE - <code>ListGridRecord[]</code> with single updated record. Operation is called on every updated record.</li>
 * <li>REMOVE - <code>ListGridRecord[]</code> with single removed record. Operation is called on every removed record.</li>
 * </ul>
 * </li>
 * </ul>
 * 
 * @author qagwaai
 * @version 1.0
 */
public abstract class GwtRpcDataSource extends DataSource {

    private static final int CR = 10;

    /**
     * Creates new data source which communicates with server by GWT RPC. It is
     * normal server side SmartClient data source with data protocol set to <code>DSProtocol.CLIENTCUSTOM</code>
     * ("clientCustom" - natively supported
     * by SmartClient but should be added to smartGWT) and with data format <code>DSDataFormat.CUSTOM</code>.
     */
    public GwtRpcDataSource() {
        setDataProtocol(DSProtocol.CLIENTCUSTOM);
        setDataFormat(DSDataFormat.CUSTOM);
        setClientOnly(false);
    }

    /**
     * Executed on <code>ADD</code> operation. <code>processResponse (requestId, response)</code> should be called when
     * operation completes (either successful or failure).
     * 
     * @param requestId
     *            <code>String</code> extracted from <code>DSRequest.getRequestId ()</code>.
     * @param request
     *            <code>DSRequest</code> being processed. <code>request.getData ()</code> contains record should be
     *            added.
     * @param response
     *            <code>DSResponse</code>. <code>setData (list)</code> should be
     *            called on successful execution of this method. Array should
     *            contain single element representing added row. <code>setStatus (&lt;0)</code> should be called on
     *            failure.
     * @throws DataException
     *             if the operation failed
     */
    protected abstract void executeAdd(String requestId, DSRequest request, DSResponse response) throws DataException;

    /**
     * Executed on <code>FETCH</code> operation. <code>processResponse (requestId, response)</code> should be called
     * when
     * operation completes (either successful or failure).
     * 
     * @param requestId
     *            <code>String</code> extracted from <code>DSRequest.getRequestId ()</code>.
     * @param request
     *            <code>DSRequest</code> being processed.
     * @param response
     *            <code>DSResponse</code>. <code>setData (list)</code> should be
     *            called on successful execution of this method. <code>setStatus (&lt;0)</code> should be called on
     *            failure.
     * @throws DataException
     *             if the operation failed
     */
    protected abstract void executeFetch(String requestId, DSRequest request, DSResponse response) throws DataException;

    /**
     * Executed on <code>REMOVE</code> operation. <code>processResponse (requestId, response)</code> should be called
     * when
     * operation completes (either successful or failure).
     * 
     * @param requestId
     *            <code>String</code> extracted from <code>DSRequest.getRequestId ()</code>.
     * @param request
     *            <code>DSRequest</code> being processed. <code>request.getData ()</code> contains record should be
     *            removed.
     * @param response
     *            <code>DSResponse</code>. <code>setData (list)</code> should be
     *            called on successful execution of this method. Array should
     *            contain single element representing removed row. <code>setStatus (&lt;0)</code> should be called on
     *            failure.
     * @throws DataException
     *             if the operation failed
     */
    protected abstract void executeRemove(String requestId, DSRequest request, DSResponse response)
        throws DataException;

    /**
     * Executed on <code>UPDATE</code> operation. <code>processResponse (requestId, response)</code> should be called
     * when
     * operation completes (either successful or failure).
     * 
     * @param requestId
     *            <code>String</code> extracted from <code>DSRequest.getRequestId ()</code>.
     * @param request
     *            <code>DSRequest</code> being processed. <code>request.getData ()</code> contains record should be
     *            updated.
     * @param response
     *            <code>DSResponse</code>. <code>setData (list)</code> should be
     *            called on successful execution of this method. Array should
     *            contain single element representing updated row. <code>setStatus (&lt;0)</code> should be called on
     *            failure.
     * @throws DataException
     *             if the operation failed
     */
    protected abstract void executeUpdate(String requestId, DSRequest request, DSResponse response)
        throws DataException;

    /**
     * 
     * @param criteria
     *            the criteria to check
     * @return the arraylist of filters
     */
    @SuppressWarnings("unchecked")
    protected ArrayList<Filter> getCriterionArray(final Criteria criteria) {
        ArrayList<Filter> criterionArray = new ArrayList<Filter>();
        if (criteria != null) {
            Map<String, Object> values = (Map<String, Object>) criteria.getValues();
            if (!values.isEmpty()) {
                for (String key : values.keySet()) {
                    if (!key.startsWith("__gwt")) {
                        if (values.get(key) != null) {
                            SimpleFilterItem criterion = new SimpleFilterItem(key, (String) values.get(key));
                            criterionArray.add(criterion);
                        }
                    }
                }
            }
        }
        return criterionArray;
    }

    /**
     * 
     * @param request
     *            the request to inspect
     * @return to end row if found, or 10
     */
    protected Integer getEndRow(final DSRequest request) {
        if (request.getEndRow() == null) {
            // arbitrary
            return Integer.valueOf(CR);
        }
        return request.getEndRow();
    }

    /**
     * 
     * @return the bus that the data source will publish events on
     */
    protected abstract EventBus getEventBus();

    /**
     * 
     * @param request
     *            the request to inspect
     * @return the start row if found, or 0
     */
    protected Integer getStartRow(final DSRequest request) {
        if (request.getStartRow() == null) {
            return Integer.valueOf(0);
        }
        return request.getStartRow();
    }

    /**
     * Executes request to server.
     * 
     * @param request
     *            <code>DSRequest</code> being processed.
     * @return <code>Object</code> data from original request.
     */
    @Override
    protected Object transformRequest(final DSRequest request) {
        String requestId = request.getRequestId();
        DSResponse response = new DSResponse();
        response.setAttribute("clientContext", request.getAttributeAsObject("clientContext"));
        // Assume success
        response.setStatus(0);
        switch (request.getOperationType()) {
            case FETCH:
                try {
                    executeFetch(requestId, request, response);
                } catch (DataException e) {
                    GWT.log("Execute Fetch failed", e);
                    response.setStatus(-1);
                }
                break;
            case ADD:
                try {
                    executeAdd(requestId, request, response);
                } catch (DataException e) {
                    GWT.log("Execute Add failed", e);
                    response.setStatus(-1);
                }
                break;
            case UPDATE:
                try {
                    executeUpdate(requestId, request, response);
                } catch (DataException e) {
                    GWT.log("Execute Update failed", e);
                    response.setStatus(-1);
                }
                break;
            case REMOVE:
                try {
                    executeRemove(requestId, request, response);
                } catch (DataException e) {
                    GWT.log("Execute Remove failed", e);
                    response.setStatus(-1);
                }
                break;
            default:
                // Operation not implemented.
                break;
        }
        return request.getData();
    }
}
