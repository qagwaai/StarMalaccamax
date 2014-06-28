/**
 * BulkPlanetLoadCommand.java
 * Created by pgirard at 12:38:45 PM on Oct 4, 2010
 * in the com.qagwaai.starmalaccamax.client.util package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.admin.util;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.client.admin.mvp.AdminFileUpload;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.BulkAddShipType;
import com.qagwaai.starmalaccamax.client.service.action.BulkAddShipTypeResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.shared.TransformationException;
import com.qagwaai.starmalaccamax.shared.model.ShipType;
import com.smartgwt.client.util.SC;

/**
 * @author pgirard
 * 
 */
public final class HTML5BulkShipTypeLoadCommand extends BaseThreadedHTML5LoadCommand implements HTML5BulkLoadCommand {

    /**
     * @param event
     *            the event object from Google Gears
     * @param view
     *            the view to pass status updates to
     * @param recordsPerTick
     *            the number of records to pull per tick
     */
    public HTML5BulkShipTypeLoadCommand(final AdminFileUpload view) {
        super(view);
    }

    @Override
    public void add(final ArrayList toAdd, final int fileLine) {
        ServiceFactory.getSolarSystemService().execute(new BulkAddShipType(toAdd),
            new BaseAsyncCallback<BulkAddShipTypeResponse>() {

                @Override
                public void onFailure(final Throwable caught) {
                    super.onFailure(caught);
                    SC.say("data load", "Bulk load of market failed on line " + fileLine);
                    view.addStatus("Bulk load of market failed on line " + fileLine + "\n");
                }

                @Override
                public void onSuccess(final BulkAddShipTypeResponse result) {
                    super.onSuccess(result);
                }
            });
        toAdd.clear();
    }

    @Override
    public ArrayList createCache() {
        return new ArrayList<ShipType>();
    }

    @Override
    public Object parseLine(final String buffer) throws TransformationException {
        return DelimitedToRecord.toShipType(buffer);
    }

}
