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
import com.qagwaai.starmalaccamax.server.dao.ShipTypeDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.ShipTypeDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;

/**
 * @author pgirard
 * 
 */
public final class TwigShipTypeDAO implements ShipTypeDAO {

    /**
	 * 
	 */
    public TwigShipTypeDAO() {

    }

    /**
     * 
     * @param command
     *            the command to add filters to
     * @param criteria
     *            the to add to the command
     * @return the filtered command
     */
    private RootFindCommand<ShipTypeDTO> addFilterToCommand(final RootFindCommand<ShipTypeDTO> command,
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
    public ShipTypeDTO addShipType(final ShipTypeDTO newShipType) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        Transaction trans = datastore.beginTransaction();
        Key key = datastore.store(newShipType);
        newShipType.setId(key.getId());
        trans.commit();

        return newShipType;
    }

    /**
     * 
     * @param command
     *            the command to add sort criteria to
     * @param fullSort
     *            the full sort string ("name, -field")
     * @return the augmented command
     */
    private RootFindCommand<ShipTypeDTO> addSortsToCommand(final RootFindCommand<ShipTypeDTO> command,
        final String fullSort) {
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
    public ArrayList<ShipTypeDTO> bulkAddShipType(final ArrayList<ShipTypeDTO> newShipTypes) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        for (ShipTypeDTO shipType : newShipTypes) {
            datastore.storeOrUpdate((ShipTypeDTO) shipType);
        }

        return newShipTypes;
    }

    /**
     * 
     * @param itShipTypes
     *            the ShipTypes to convert
     * @return the converted ShipTypes
     */
    private ArrayList<ShipTypeDTO> convertToInterface(final Iterator<ShipTypeDTO> itShipTypes) {
        ArrayList<ShipTypeDTO> list = new ArrayList<ShipTypeDTO>();

        while (itShipTypes.hasNext()) {
            list.add(itShipTypes.next());
        }
        return list;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAllShipTypes() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.deleteAll(ShipTypeDTO.class);

        return true;
    }

    /**
     * 
     * @param itShipTypes
     *            the list of ShipTypes to filter
     * @param nameFilter
     *            the name filter
     * @return the converted list of ShipTypes
     */
    private ArrayList<ShipTypeDTO>
        filterOnName(final Iterator<ShipTypeDTO> itShipTypes, final SimpleFilterItem nameFilter) {
        ArrayList<ShipTypeDTO> list = new ArrayList<ShipTypeDTO>();

        while (itShipTypes.hasNext()) {
            ShipTypeDTO ss = itShipTypes.next();
            if (ss.getName().contains((String) nameFilter.getValue())) {
                list.add(ss);
            }
        }
        return list;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<ShipTypeDTO> getAllShipTypes() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        Iterator<ShipTypeDTO> itShipTypes = datastore.find().type(ShipTypeDTO.class).now();

        return convertToInterface(itShipTypes);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<ShipTypeDTO> getAllShipTypes(final int startRow, final int endRow, final ArrayList<Filter> criteria,
        final String sortBy) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<ShipTypeDTO> command =
            datastore.find().type(ShipTypeDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addFilterToCommand(command, criteria);
        command = addSortsToCommand(command, sortBy);
        Iterator<ShipTypeDTO> itShipTypes = command.now();
        if (getNameFilter(criteria) != null) {
            return filterOnName(itShipTypes, getNameFilter(criteria));
        } else {
            return convertToInterface(itShipTypes);
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<ShipTypeDTO> getAllShipTypes(final int startRow, final int endRow, final String sortBy)
        throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<ShipTypeDTO> command =
            datastore.find().type(ShipTypeDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addSortsToCommand(command, sortBy);
        Iterator<ShipTypeDTO> itShipTypes = command.now();

        return convertToInterface(itShipTypes);
    }

    /**
     * 
     * @param criteria
     *            the full list of criteria to search
     * @return the filterItem if found or null
     */
    private SimpleFilterItem getNameFilter(final ArrayList<Filter> criteria) {
        for (Filter criterion : criteria) {
            if (criterion instanceof SimpleFilterItem) {
                SimpleFilterItem item = (SimpleFilterItem) criterion;
                if (item.getField().equals("name")) {
                    return item;
                }
            }
        }
        return null;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ShipTypeDTO getShipType(final Long id) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        return datastore.load(ShipTypeDTO.class, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalShipTypes() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        return datastore.find().type(ShipTypeDTO.class).returnCount().now();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalShipTypesWithFilter(final ArrayList<Filter> criteria) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<ShipTypeDTO> command = datastore.find().type(ShipTypeDTO.class);
        command = addFilterToCommand(command, criteria);

        Iterator<ShipTypeDTO> itShipTypes = command.now();
        if (getNameFilter(criteria) != null) {
            ArrayList<ShipTypeDTO> filteredResult = filterOnName(itShipTypes, getNameFilter(criteria));
            return filteredResult.size() - 1;
        } else {
            return convertToInterface(itShipTypes).size() - 1;
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean removeShipType(final ShipTypeDTO shipType) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.associate(shipType);
        datastore.delete(shipType);
        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ShipTypeDTO updateShipType(final ShipTypeDTO shipType) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.storeOrUpdate((ShipTypeDTO) shipType);

        return shipType;
    }
}
