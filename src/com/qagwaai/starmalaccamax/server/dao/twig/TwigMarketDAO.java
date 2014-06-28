/**
 * DataNucleusUserDAO.java
 * Created by pgirard at 3:25:05 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server.dao.datanucleus package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao.twig;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Transaction;
import com.google.code.twig.FindCommand.RootFindCommand;
import com.google.code.twig.ObjectDatastore;
import com.google.code.twig.annotation.AnnotationObjectDatastore;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.MarketDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.Market;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;

/**
 * @author pgirard
 * 
 */
public final class TwigMarketDAO implements MarketDAO {

    /**
	 * 
	 */
    public TwigMarketDAO() {

    }

    /**
     * 
     * @param command
     *            the command to add filters to
     * @param criteria
     *            the to add to the command
     * @return the filtered command
     */
    private RootFindCommand<MarketDTO> addFilterToCommand(final RootFindCommand<MarketDTO> command,
        final ArrayList<Filter> criteria) {
        for (Filter criterion : criteria) {
            if (criterion instanceof SimpleFilterItem) {
                SimpleFilterItem item = (SimpleFilterItem) criterion;
                if (!item.getField().equals("name")) {
                    // this is using the equal operator - any text searching
                    // will be impled below
                    if (item.getField().equals("orbit") || item.getField().equals("size")
                        || item.getField().equals("atmosphere") || item.getField().equals("dockQuality")
                        || item.getField().equals("government") || item.getField().equals("lawLevel")
                        || item.getField().equals("techLevel")) {
                        command.addFilter(item.getField(), FilterOperator.EQUAL, Integer.valueOf(item.getValue()));
                    } else if (item.getField().equals("hydrographics")) {
                        command.addFilter(item.getField(), FilterOperator.EQUAL, Double.valueOf(item.getValue()));
                    } else {
                        command.addFilter(item.getField(), FilterOperator.EQUAL, item.getValue());
                    }
                }
            }
        }
        return command;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public MarketDTO addMarket(final MarketDTO newMarket) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        Transaction trans = datastore.beginTransaction();
        Key key = datastore.store(newMarket);
        newMarket.setId(key.getId());
        trans.commit();

        return newMarket;
    }

    /**
     * 
     * @param command
     *            the command to add sort criteria to
     * @param fullSort
     *            the full sort string ("name, -field")
     * @return the augmented command
     */
    private RootFindCommand<MarketDTO>
        addSortsToCommand(final RootFindCommand<MarketDTO> command, final String fullSort) {
        if (fullSort == null) {
            return command;
        }
        if (fullSort.equals("")) {
            return command;
        }
        String[] sortFields = fullSort.split(",");

        for (int i = 0; i < sortFields.length; i++) {
            if (sortFields[i].startsWith("-")) {
                command.addSort(sortFields[i].substring(1, sortFields[i].length()), SortDirection.DESCENDING);
            } else {
                command.addSort(sortFields[i], SortDirection.ASCENDING);
            }
        }

        return command;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<MarketDTO> bulkAddMarket(final ArrayList<MarketDTO> newMarkets) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        for (Market market : newMarkets) {
            datastore.storeOrUpdate((MarketDTO) market);
        }

        return newMarkets;
    }

    /**
     * 
     * @param itMarkets
     *            the Markets to convert
     * @return the converted Markets
     */
    private ArrayList<MarketDTO> convertToInterface(final Iterator<MarketDTO> itMarkets) {
        ArrayList<MarketDTO> list = new ArrayList<MarketDTO>();

        while (itMarkets.hasNext()) {
            list.add(itMarkets.next());
        }
        return list;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAllMarkets() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.deleteAll(MarketDTO.class);

        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<MarketDTO> getAllMarkets() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        Iterator<MarketDTO> itMarkets = datastore.find().type(MarketDTO.class).now();

        return convertToInterface(itMarkets);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<MarketDTO> getAllMarkets(final int startRow, final int endRow, final ArrayList<Filter> criteria,
        final String sortBy) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<MarketDTO> command =
            datastore.find().type(MarketDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addFilterToCommand(command, criteria);
        command = addSortsToCommand(command, sortBy);
        Iterator<MarketDTO> itMarkets = command.now();
        return convertToInterface(itMarkets);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<MarketDTO> getAllMarkets(final int startRow, final int endRow, final String sortBy)
        throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<MarketDTO> command =
            datastore.find().type(MarketDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addSortsToCommand(command, sortBy);
        Iterator<MarketDTO> itMarkets = command.now();

        return convertToInterface(itMarkets);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public MarketDTO getMarket(final Long id) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        return datastore.load(MarketDTO.class, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public MarketDTO getMarketForPlanet(final PlanetDTO planet) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        Iterator<MarketDTO> itMarkets =
            datastore.find().type(MarketDTO.class).addFilter("planetId", FilterOperator.EQUAL, planet.getId()).now();

        ArrayList<MarketDTO> found = convertToInterface(itMarkets);

        if (found.size() == 1) {
            return found.get(0);
        } else if (found.size() == 0) {
            return null;
        } else {
            throw new DAOException("Found " + found.size() + " markets for planet " + planet.getId());
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<MarketDTO> getMarketsForPlanets(final ArrayList<Long> planetIds) {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<MarketDTO> command = datastore.find().type(MarketDTO.class);
        command.addFilter("planetId", FilterOperator.IN, planetIds);

        Iterator<MarketDTO> itMarkets = command.now();
        return convertToInterface(itMarkets);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalMarkets() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        return datastore.find().type(MarketDTO.class).returnCount().now();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalMarketsWithFilter(final ArrayList<Filter> criteria) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<MarketDTO> command = datastore.find().type(MarketDTO.class);
        command = addFilterToCommand(command, criteria);

        Iterator<MarketDTO> itMarkets = command.now();
        return convertToInterface(itMarkets).size() - 1;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean removeMarket(final MarketDTO market) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.associate(market);
        datastore.delete(market);
        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public MarketDTO updateMarket(final MarketDTO market) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.storeOrUpdate((MarketDTO) market);

        return market;
    }
}
