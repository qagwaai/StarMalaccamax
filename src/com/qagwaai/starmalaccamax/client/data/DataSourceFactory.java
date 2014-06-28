/**
 * DataSourceFactory.java
 * Created by pgirard at 3:57:19 PM on Nov 15, 2010
 * in the com.qagwaai.starmalaccamax.client.data package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import java.util.HashMap;

import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.shared.InvalidParameterException;

/**
 * @author pgirard
 * 
 */
public final class DataSourceFactory {
    /**
	 * 
	 */
    private static HashMap<String, GwtRpcDataSource> instances = new HashMap<String, GwtRpcDataSource>();

    /**
     * 
     * @param dsIdentifier
     *            the data source identifier
     * @param eventBus
     *            the bus to publish events on
     * @return a hydrated data source or null
     * @throws InvalidParameterException
     *             if the datasource requested is not configured
     */
    public static GwtRpcDataSource get(final String dsIdentifier, final EventBus eventBus)
        throws InvalidParameterException {
        if (instances.containsKey(dsIdentifier)) {
            return instances.get(dsIdentifier);
        }

        if (dsIdentifier.equals(CaptainDS.DS_TYPE)) {
            instances.put(CaptainDS.DS_TYPE, new CaptainDS(CaptainDS.DS_TYPE, ServiceFactory.getCaptainService(),
                eventBus));
            return instances.get(dsIdentifier);
        }
        if (dsIdentifier.equals(JobDS.DS_TYPE)) {
            instances.put(JobDS.DS_TYPE, new JobDS(JobDS.DS_TYPE, ServiceFactory.getJobService(), eventBus));
            return instances.get(dsIdentifier);
        }
        if (dsIdentifier.equals(JumpGateDS.DS_TYPE)) {
            instances.put(JumpGateDS.DS_TYPE, new JumpGateDS(JumpGateDS.DS_TYPE, ServiceFactory.getJumpGateService(),
                eventBus));
            return instances.get(dsIdentifier);
        }

        if (dsIdentifier.equals(MarketDS.DS_TYPE)) {
            instances
                .put(MarketDS.DS_TYPE, new MarketDS(MarketDS.DS_TYPE, ServiceFactory.getMarketService(), eventBus));
            return instances.get(dsIdentifier);
        }
        if (dsIdentifier.equals(PlanetDS.DS_TYPE)) {
            instances
                .put(PlanetDS.DS_TYPE, new PlanetDS(PlanetDS.DS_TYPE, ServiceFactory.getPlanetService(), eventBus));
            return instances.get(dsIdentifier);
        }
        if (dsIdentifier.equals(ShipDS.DS_TYPE)) {
            instances.put(ShipDS.DS_TYPE, new ShipDS(ShipDS.DS_TYPE, ServiceFactory.getShipService(), eventBus));
            return instances.get(dsIdentifier);
        }
        if (dsIdentifier.equals(ShipTypeDS.DS_TYPE)) {
            instances.put(ShipTypeDS.DS_TYPE, new ShipTypeDS(ShipTypeDS.DS_TYPE, ServiceFactory.getShipService(),
                eventBus));
            return instances.get(dsIdentifier);
        }
        if (dsIdentifier.equals(SolarSystemDS.DS_TYPE)) {
            instances.put(SolarSystemDS.DS_TYPE,
                new SolarSystemDS(SolarSystemDS.DS_TYPE, ServiceFactory.getSolarSystemService(), eventBus));
            return instances.get(dsIdentifier);
        }
        if (dsIdentifier.equals(StarDS.DS_TYPE)) {
            instances.put(StarDS.DS_TYPE, new StarDS(StarDS.DS_TYPE, ServiceFactory.getStarService(), eventBus));
            return instances.get(dsIdentifier);
        }
        if (dsIdentifier.equals(UserDS.DS_TYPE)) {
            instances.put(UserDS.DS_TYPE, new UserDS(UserDS.DS_TYPE, ServiceFactory.getUserService(), eventBus));
            return instances.get(dsIdentifier);
        }
        if (dsIdentifier.equals(GameEventDS.DS_TYPE)) {
            instances.put(GameEventDS.DS_TYPE,
                new GameEventDS(GameEventDS.DS_TYPE, ServiceFactory.getGameEventService(), eventBus));
            return instances.get(dsIdentifier);
        }
        if (dsIdentifier.equals(ClosestDS.DS_TYPE)) {
            instances.put(ClosestDS.DS_TYPE, new ClosestDS(ClosestDS.DS_TYPE, ServiceFactory.getSolarSystemService(),
                eventBus));
            return instances.get(dsIdentifier);
        }
        throw new InvalidParameterException("Could not get data source for " + dsIdentifier);
    }

    /**
	 * 
	 */
    private DataSourceFactory() {

    }
}
