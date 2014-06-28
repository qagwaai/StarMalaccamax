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
import com.qagwaai.starmalaccamax.server.dao.ShipDAO;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;

/**
 * @author pgirard
 * 
 */
public final class TwigShipDAO implements ShipDAO {

    /**
	 * 
	 */
    public TwigShipDAO() {

    }

    /**
     * 
     * @param command
     *            the command to add filters to
     * @param criteria
     *            the to add to the command
     * @return the filtered command
     */
    private RootFindCommand<ShipDTO> addFilterToCommand(final RootFindCommand<ShipDTO> command,
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
    public ShipDTO addShip(final ShipDTO newShip) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        Transaction trans = datastore.beginTransaction();
        Key key = datastore.store(newShip);
        newShip.setId(key.getId());
        trans.commit();

        return newShip;
    }

    /**
     * 
     * @param command
     *            the command to add sort criteria to
     * @param fullSort
     *            the full sort string ("name, -field")
     * @return the augmented command
     */
    private RootFindCommand<ShipDTO> addSortsToCommand(final RootFindCommand<ShipDTO> command, final String fullSort) {
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
     * @param itShips
     *            the Ships to convert
     * @return the converted Ships
     */
    private ArrayList<ShipDTO> convertToInterface(final Iterator<ShipDTO> itShips) {
        ArrayList<ShipDTO> list = new ArrayList<ShipDTO>();

        while (itShips.hasNext()) {
            list.add(itShips.next());
        }
        return list;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAllShips() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.deleteAll(ShipDTO.class);

        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<ShipDTO> getAllShips() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        Iterator<ShipDTO> itShips = datastore.find().type(ShipDTO.class).now();

        return convertToInterface(itShips);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<ShipDTO> getAllShips(final int startRow, final int endRow, final ArrayList<Filter> criteria,
        final String sortBy) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<ShipDTO> command =
            datastore.find().type(ShipDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addFilterToCommand(command, criteria);
        command = addSortsToCommand(command, sortBy);
        Iterator<ShipDTO> itShips = command.now();
        return convertToInterface(itShips);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<ShipDTO> getAllShips(final int startRow, final int endRow, final String sortBy) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<ShipDTO> command =
            datastore.find().type(ShipDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addSortsToCommand(command, sortBy);
        Iterator<ShipDTO> itShips = command.now();

        return convertToInterface(itShips);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ShipDTO getShip(final Long id) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        return datastore.load(ShipDTO.class, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ShipDTO getShipForCaptain(final CaptainDTO captain) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        Iterator<ShipDTO> itShips =
            datastore.find().type(ShipDTO.class).addFilter("ownerId", FilterOperator.EQUAL, captain.getId()).now();

        ArrayList<ShipDTO> found = convertToInterface(itShips);

        if (found.size() == 1) {
            return found.get(0);
        } else if (found.size() == 0) {
            return null;
        } else {
            throw new DAOException("Found " + found.size() + " ships for captain " + captain.getId());
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalShips() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        return datastore.find().type(ShipDTO.class).returnCount().now();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalShipsWithFilter(final ArrayList<Filter> criteria) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<ShipDTO> command = datastore.find().type(ShipDTO.class);
        command = addFilterToCommand(command, criteria);

        Iterator<ShipDTO> itShips = command.now();
        return convertToInterface(itShips).size() - 1;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean removeShip(final ShipDTO ship) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.associate(ship);
        datastore.delete(ship);
        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ShipDTO updateShip(final ShipDTO ship) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.storeOrUpdate((ShipDTO) ship);

        return ship;
    }
}
