/**
 * UserDAO.java
 * Created by pgirard at 3:21:33 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server.dao package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

/**
 * @author pgirard
 * 
 */
public interface MarketDAO {

    /**
     * 
     * @param newMarket
     *            the new market to add
     * @return the updated new market
     * @throws DAOException
     *             if the datastore fails
     */
    MarketDTO addMarket(MarketDTO newMarket) throws DAOException;

    /**
     * 
     * @param newMarkets
     *            the new markets to add
     * @return the updated new market
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<MarketDTO> bulkAddMarket(ArrayList<MarketDTO> newMarkets) throws DAOException;

    /**
     * 
     * @return true if the delete succeeds
     * @throws DAOException
     *             if the delete fails
     */
    boolean deleteAllMarkets() throws DAOException;

    /**
     * 
     * @return all markets in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<MarketDTO> getAllMarkets() throws DAOException;

    /**
     * 
     * @param startRow
     *            paging support - start row
     * @param endRow
     *            paging support - end row
     * @param criteria
     *            if there is any filter
     * @param sortBy
     *            the fully qualified sort string
     * @return all markets in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<MarketDTO> getAllMarkets(int startRow, int endRow, ArrayList<Filter> criteria, String sortBy)
        throws DAOException;

    /**
     * 
     * @param startRow
     *            paging support - start row
     * @param endRow
     *            paging support - end row
     * @param sortBy
     *            the complete sort string
     * @return all markets in the store
     * @throws DAOException
     *             if the datastore fails
     */
    ArrayList<MarketDTO> getAllMarkets(int startRow, int endRow, String sortBy) throws DAOException;

    /**
     * 
     * @param id
     *            the unique key
     * @return the found market or null
     * @throws DAOException
     *             if the datastore fails
     */
    MarketDTO getMarket(Long id) throws DAOException;

    /**
     * 
     * @param planet
     *            the market's planet to search for
     * @return the found market or null
     * @throws DAOException
     *             if the datastore fails
     */
    MarketDTO getMarketForPlanet(PlanetDTO planet) throws DAOException;

    /**
     * 
     * @param planetIds
     *            the planets to look for
     * @return a list of markets for those queried planets
     */
    ArrayList<MarketDTO> getMarketsForPlanets(ArrayList<Long> planetIds);

    /**
     * 
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalMarkets() throws DAOException;

    /**
     * @param criteria
     *            the filter criteria
     * @return the total number of solar systems
     * @throws DAOException
     *             if the query fails
     */
    int getTotalMarketsWithFilter(ArrayList<Filter> criteria) throws DAOException;

    /**
     * 
     * @param market
     *            the market to be removed
     * @return true if the removal was successful
     * @throws DAOException
     *             if the datastore fails
     */
    boolean removeMarket(MarketDTO market) throws DAOException;

    /**
     * 
     * @param market
     *            the market to update
     * @return the updated market
     * @throws DAOException
     *             if the datastore fails
     */
    MarketDTO updateMarket(MarketDTO market) throws DAOException;

}
