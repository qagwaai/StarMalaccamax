/**
 * UserDS.java
 * Created by pgirard at 2:49:07 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.data package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Window;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.event.MarketAddedEvent;
import com.qagwaai.starmalaccamax.client.event.MarketRemovedEvent;
import com.qagwaai.starmalaccamax.client.event.MarketUpdatedEvent;
import com.qagwaai.starmalaccamax.client.event.MarketsLoadedEvent;
import com.qagwaai.starmalaccamax.client.service.MarketServiceAsync;
import com.qagwaai.starmalaccamax.client.service.action.AddMarket;
import com.qagwaai.starmalaccamax.client.service.action.AddMarketResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllMarkets;
import com.qagwaai.starmalaccamax.client.service.action.GetAllMarketsResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveMarket;
import com.qagwaai.starmalaccamax.client.service.action.RemoveMarketResponse;
import com.qagwaai.starmalaccamax.client.service.action.UpdateMarket;
import com.qagwaai.starmalaccamax.client.service.action.UpdateMarketResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.AddedMarket;
import com.qagwaai.starmalaccamax.client.service.helpers.GotAllMarkets;
import com.qagwaai.starmalaccamax.client.service.helpers.RemovedMarket;
import com.qagwaai.starmalaccamax.client.service.helpers.UpdatedMarket;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.MarketCommodity;
import com.qagwaai.starmalaccamax.shared.model.MarketCommodityDTO;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public final class MarketDS extends GwtRpcDataSource {

    /**
	 * 
	 */
    public static final String DS_TYPE = "Market";

    /**
     * 
     * @param from
     *            to copy from
     * @param to
     *            to copy to
     * @throws DataException
     *             if the operation failed
     */
    private static void copyValues(final ListGridRecord from, final MarketRecord to) throws DataException {
        to.setId(LongConverter.fromJavaScript(from, ShipTypeRecord.ID));
        to.setLastVisited(from.getAttributeAsDate(MarketRecord.LAST_VISITED));
        to.setPlanetId(LongConverter.fromJavaScript(from, MarketRecord.PLANET_ID));
    }

    /**
     * 
     * @param commodity
     *            the commodity to be transformed
     * @return a list grid record
     */
    private static ListGridRecord createRow(final MarketCommodity commodity) {
        ListGridRecord record = new ListGridRecord();
        record.setAttribute("commodityName", commodity.getName());
        record.setAttribute("sellPrice", commodity.getSellPrice());
        record.setAttribute("purchasePrice", commodity.getPurchasePrice());
        record.setAttribute("sellAvailable", commodity.getSellAmountAvailable());
        record.setAttribute("purchaseWanted", commodity.getPurchaseAmountWanted());
        return record;

    }

    /**
     * 
     * @param data
     *            the record to extract from
     * @param toReturn
     *            the instance to build into
     * @return the hydrated commodity
     */
    private static MarketCommodityDTO extractData(final ListGridRecord data, final MarketCommodityDTO toReturn) {
        toReturn.setName(data.getAttributeAsString("commodityName"));
        toReturn.setSellPrice(Short.valueOf(data.getAttribute("sellPrice")));
        toReturn.setPurchasePrice(Short.valueOf(data.getAttribute("purchasePrice")));
        toReturn.setSellAmountAvailable(Short.valueOf(data.getAttribute("sellAvailable")));
        toReturn.setPurchaseAmountWanted(Short.valueOf(data.getAttribute("purchaseWanted")));

        return toReturn;
    }

    /**
     * 
     * @param market
     *            the market object to append
     * @param data
     *            the list grid records
     * @return the updated market object
     */
    public static MarketDTO fromGrid(final MarketDTO market, final ListGridRecord[] data) {
        for (ListGridRecord record : data) {
            String name = record.getAttributeAsString("commodityName");
            MarketCommodityDTO previous = market.getCommodities().get(name);
            market.getCommodities().put(name, extractData(record, previous));
        }
        return market;
    }

    /**
     * 
     * @param bucket
     *            the bucket of commodities
     * @return an array of commodity records
     */
    public static ListGridRecord[] toGrid(final Map<String, MarketCommodityDTO> bucket) {
        return transformIt(bucket);
    }

    /**
     * 
     * @param market
     *            the market that holds the commodities
     * @return an array of commodity records
     */
    public static ListGridRecord[] toGrid(final MarketDTO market) {
        Map<String, MarketCommodityDTO> commodities = market.getCommodities();
        return transformIt(commodities);
    }

    /**
     * 
     * @param commodities
     *            the commodities to be transformed
     * @return an array of commodity records
     */
    private static ListGridRecord[] transformIt(final Map<String, MarketCommodityDTO> commodities) {
        ListGridRecord[] data = new ListGridRecord[commodities.size()];
        int index = 0;
        for (Entry<String, MarketCommodityDTO> commodity : commodities.entrySet()) {
            data[index++] = createRow(commodity.getValue());
        }

        return data;
    }

    /**
     * 
     */
    private MarketServiceAsync dataService = null;
    /**
     * 
     */
    private EventBus eventBus = null;

    /**
     * constructor and pull initial data set
     * 
     * @param id
     *            id of the data source
     * @param dataService
     *            the service to use to interact with the data store - this is a
     *            GWT RPC data source
     * @param eventBus
     *            the bus to publish events on
     */
    public MarketDS(final String id, final MarketServiceAsync dataService, final EventBus eventBus) {
        super();
        setID(id);

        this.dataService = dataService;
        this.eventBus = eventBus;
        this.setCanMultiSort(true);

        DataSourceIntegerField pkField = new DataSourceIntegerField(MarketRecord.ID);
        pkField.setPrimaryKey(true);
        pkField.setCanEdit(false);

        DataSourceIntegerField fkPlanetIdField = new DataSourceIntegerField(MarketRecord.PLANET_ID);
        DataSourceDateField lastVisitedField = new DataSourceDateField(MarketRecord.LAST_VISITED);

        setFields(pkField, fkPlanetIdField, lastVisitedField);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void executeAdd(final String requestId, final DSRequest request, final DSResponse response)
        throws DataException {

        GWT.log(DS_TYPE + "DS.transformRequest.ADD");
        JavaScriptObject data = request.getData();
        ListGridRecord rec = new ListGridRecord(data);
        MarketRecord marketRecord = new MarketRecord();
        copyValues(rec, marketRecord);

        dataService.execute(new AddMarket(marketRecord.toMarket()), new AddedMarket() {
            @Override
            public void got(final MarketDTO market) {
                MarketAddedEvent.fire(eventBus, market);

            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onFailure(final Throwable caught) {
                Window.alert("Add " + DS_TYPE + " failed");
                response.setStatus(-1);
                processResponse(requestId, response);
                super.onFailure(caught);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onSuccess(final AddMarketResponse result) {
                // DSResponse response = new DSResponse();

                if (result.getMarket() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    MarketRecord[] records = new MarketRecord[1];
                    records[0] = new MarketRecord(result.getMarket());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    MarketRecord[] records = new MarketRecord[0];
                    response.setData(records);
                }
                processResponse(requestId, response);
                super.onSuccess(result);
            }
        });
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void executeFetch(final String requestId, final DSRequest request, final DSResponse response)
        throws DataException {
        ArrayList<Filter> criterionArray = getCriterionArray(request.getCriteria());
        String fullSortString = request.getAttributeAsString("sortBy");
        final int startRow = getStartRow(request);
        final int endRow = getEndRow(request);
        GWT.log(DS_TYPE + "DS.transformRequest.FETCH Starting:" + startRow + ", ending:" + endRow + " sort: ["
            + fullSortString + "], criteria: [" + criterionArray + "]");

        dataService.execute(new GetAllMarkets(startRow, endRow, criterionArray, fullSortString), new GotAllMarkets() {

            public void got(final ArrayList<MarketDTO> markets) {
                MarketsLoadedEvent.fire(eventBus, markets);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onFailure(final Throwable caught) {
                Window.alert("Get " + DS_TYPE + " failed");
                response.setStatus(-1);
                processResponse(requestId, response);
                super.onFailure(caught);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onSuccess(final GetAllMarketsResponse result) {
                GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess");
                // DSResponse response = new DSResponse();
                ArrayList<MarketDTO> markets = result.getMarkets();
                if (result.getMarkets().size() > 0) {
                    response.setTotalRows(result.getTotalMarkets());
                    response.setStartRow(startRow);
                    if (endRow > result.getTotalMarkets()) {
                        response.setEndRow(result.getTotalMarkets());
                    } else {
                        response.setEndRow(endRow);
                    }
                    MarketRecord[] records = new MarketRecord[markets.size() + 1];
                    for (int i = 0; i < markets.size(); i++) {
                        records[i] = new MarketRecord(markets.get(i));
                        // records[i].setAttribute(MarketRecord.COMMODITIES,
                        // (Object) markets.get(i).getCommodities());
                    }
                    GWT.log(DS_TYPE + "DS.transformRequest.FETCH.onSuccess(start, end, total, size) = "
                        + response.getStartRow() + ", " + response.getEndRow() + ", " + result.getTotalMarkets() + ", "
                        + markets.size());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    MarketRecord[] records = new MarketRecord[0];
                    response.setData(records);
                }
                processResponse(requestId, response);
                super.onSuccess(result);
            }
        });

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void executeRemove(final String requestId, final DSRequest request, final DSResponse response)
        throws DataException {
        GWT.log(DS_TYPE + "DS.transformRequest.REMOVE");
        // Retrieve record which should be removed.
        JavaScriptObject data = request.getData();
        final ListGridRecord rec = new ListGridRecord(data);
        MarketRecord testRec = new MarketRecord();
        copyValues(rec, testRec);

        dataService.execute(new RemoveMarket(testRec.toMarket()), new RemovedMarket() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void onFailure(final Throwable caught) {
                Window.alert("Remove " + DS_TYPE + " failed");
                response.setStatus(-1);
                processResponse(requestId, response);
                super.onFailure(caught);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onSuccess(final RemoveMarketResponse result) {
                ListGridRecord[] list = new ListGridRecord[1];
                // We do not receive removed record from server.
                // Return record from request.
                list[0] = rec;
                response.setData(list);
                processResponse(requestId, response);
                super.onSuccess(result);
            }

            @Override
            public void removed(final MarketDTO market) {
                MarketRemovedEvent.fire(eventBus, market);
            }
        });
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void executeUpdate(final String requestId, final DSRequest request, final DSResponse response)
        throws DataException {
        GWT.log(DS_TYPE + "DS.transformRequest.UPDATE");
        // Retrieve record which should be updated.
        JavaScriptObject data = request.getData();
        ListGridRecord rec = new ListGridRecord(data);
        // Find grid
        Canvas obj = Canvas.getById(request.getComponentId());
        if (obj instanceof DynamicForm) {
            // DynamicForm form = (DynamicForm) obj;
            // rec = (ListGridRecord) form.
        } else {
            ListGrid grid = (ListGrid) obj;
            // Get record with old and new values combined
            int index = grid.getRecordIndex(rec);
            rec = (ListGridRecord) grid.getEditedRecord(index);
        }
        MarketRecord marketRecord = new MarketRecord();
        copyValues(rec, marketRecord);

        dataService.execute(new UpdateMarket(marketRecord.toMarket()), new UpdatedMarket() {
            @Override
            public void got(final MarketDTO market) {
                MarketUpdatedEvent.fire(eventBus, market);

            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onFailure(final Throwable caught) {
                Window.alert("Update " + DS_TYPE + " failed");
                response.setStatus(-1);
                processResponse(requestId, response);
                super.onFailure(caught);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onSuccess(final UpdateMarketResponse result) {
                // DSResponse response = new DSResponse();

                if (result.getMarket() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    MarketRecord[] records = new MarketRecord[1];
                    records[0] = new MarketRecord(result.getMarket());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    MarketRecord[] records = new MarketRecord[0];
                    response.setData(records);
                }
                processResponse(requestId, response);
                super.onSuccess(result);
            }
        });

    }

    /**
     * @return the dataService
     */
    public MarketServiceAsync getDataService() {
        return dataService;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected EventBus getEventBus() {
        return this.eventBus;
    }

    /**
     * @param dataService
     *            the dataService to set
     */
    public void setDataService(final MarketServiceAsync dataService) {
        this.dataService = dataService;
    }

}
