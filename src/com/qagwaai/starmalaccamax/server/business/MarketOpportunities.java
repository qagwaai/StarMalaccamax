/**
 * MarketOpportunities.java
 * Created by pgirard at 10:48:23 AM on Jan 7, 2011
 * in the com.qagwaai.starmalaccamax.server.business package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.business;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.qagwaai.starmalaccamax.client.Application;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.DAOFactory;
import com.qagwaai.starmalaccamax.server.dao.SolarSystemDAO;
import com.qagwaai.starmalaccamax.shared.ServiceException;
import com.qagwaai.starmalaccamax.shared.model.MarketCommodityDTO;
import com.qagwaai.starmalaccamax.shared.model.MarketDistanceDTO;
import com.qagwaai.starmalaccamax.shared.model.MarketOpportunityForShipDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipCargoDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;

/**
 * @author pgirard
 * 
 */
public final class MarketOpportunities {
    /**
	 * 
	 */
    private static Logger log = Logger.getLogger(MarketOpportunities.class.getName());

    /**
	 * 
	 */
    private MarketOpportunities() {

    }

    /**
     * 
     * @param ships
     *            the ships to query on
     * @return a list of market opportunities
     * @throws ServiceException
     *             if the query fails
     */
    public static ArrayList<MarketOpportunityForShipDTO> getMarketOpportunitiesForShips(final ArrayList<ShipDTO> ships)
        throws ServiceException {
        DAOFactory factory = DAOFactory.getDAOFactory(Application.getInstance().getDAOFactory());
        SolarSystemDAO ssDAO = factory.getSolarSystemDAO();

        ArrayList<MarketOpportunityForShipDTO> opportunities = new ArrayList<MarketOpportunityForShipDTO>();
        for (ShipDTO ship : ships) {
            if (ship.getLocation() != null) {
                try {
                    ArrayList<MarketDistanceDTO> markets = ssDAO.getClosestMarketsForShip(ship);
                    for (MarketDistanceDTO market : markets) {
                        for (ShipCargoDTO cargo : ship.getCargo()) {
                            MarketCommodityDTO marketCommodity =
                                market.getMarket().getCommodities().get(cargo.getCommodity());
                            if (marketCommodity.getPurchasePrice() <= cargo.getPurchasePrice()) {
                                MarketOpportunityForShipDTO opportunity = new MarketOpportunityForShipDTO();
                                opportunity.setCommodity(cargo.getCommodity());
                                opportunity.setCargo(cargo);
                                opportunity.setShip(ship);
                                opportunity.setMarket(market.getMarket());
                                opportunity.setDistance(market.getDistance());
                                opportunity.setSalePrice(marketCommodity.getPurchasePrice());
                                opportunity.setMarker("!!!");
                                opportunities.add(opportunity);
                            }
                        }
                    }
                } catch (DAOException e) {
                    log.severe(e.toString());
                    throw new ServiceException("Could not complete command GetPlayerSummaryPage.");
                }

            }
        }

        return opportunities;
    }
}
