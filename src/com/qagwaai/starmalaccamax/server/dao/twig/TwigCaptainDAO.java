/**
 * DataNucleusCaptainDAO.java
 * Created by pgirard at 3:14:58 PM on Aug 11, 2010
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
import com.qagwaai.starmalaccamax.server.dao.CaptainDAO;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public final class TwigCaptainDAO implements CaptainDAO {

    /**
	 * 
	 */
    public TwigCaptainDAO() {

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CaptainDTO addCaptain(final CaptainDTO newCaptain) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        Transaction trans = datastore.beginTransaction();
        Key key = datastore.store(newCaptain);
        newCaptain.setId(key.getId());
        trans.commit();

        return newCaptain;
    }

    /**
     * 
     * @param command
     *            the command to add filters to
     * @param criteria
     *            the to add to the command
     * @return the filtered command
     */
    private RootFindCommand<CaptainDTO> addFilterToCommand(final RootFindCommand<CaptainDTO> command,
        final ArrayList<Filter> criteria) {
        for (Filter criterion : criteria) {
            if (criterion instanceof SimpleFilterItem) {
                SimpleFilterItem item = (SimpleFilterItem) criterion;
                if (!item.getField().equals("name")) {
                    // this is using the equal operator - any text searching
                    // will be impled below
                    if (item.getField().equals("ownerId")) {
                        command.addFilter(item.getField(), FilterOperator.EQUAL, Long.valueOf(item.getValue()));
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
     * @param command
     *            the command to add sort criteria to
     * @param fullSort
     *            the full sort string ("name, -field")
     * @return the augmented command
     */
    private RootFindCommand<CaptainDTO> addSortsToCommand(final RootFindCommand<CaptainDTO> command,
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
     * @param itCaptains
     *            the Captains to convert
     * @return the converted Captains
     */
    private ArrayList<CaptainDTO> convertToInterface(final Iterator<CaptainDTO> itCaptains) {
        ArrayList<CaptainDTO> list = new ArrayList<CaptainDTO>();

        while (itCaptains.hasNext()) {
            list.add(itCaptains.next());
        }
        return list;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAllCaptains() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.deleteAll(CaptainDTO.class);

        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<CaptainDTO> getAllCaptains() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        Iterator<CaptainDTO> itCaptains = datastore.find().type(CaptainDTO.class).now();

        return convertToInterface(itCaptains);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<CaptainDTO> getAllCaptains(final int startRow, final int endRow, final ArrayList<Filter> criteria,
        final String sortBy) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<CaptainDTO> command =
            datastore.find().type(CaptainDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addFilterToCommand(command, criteria);
        command = addSortsToCommand(command, sortBy);
        Iterator<CaptainDTO> itCaptains = command.now();
        return convertToInterface(itCaptains);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<CaptainDTO> getAllCaptains(final int startRow, final int endRow, final String sortBy)
        throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<CaptainDTO> command =
            datastore.find().type(CaptainDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addSortsToCommand(command, sortBy);
        Iterator<CaptainDTO> itCaptains = command.now();

        return convertToInterface(itCaptains);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CaptainDTO getCaptain(final Long id) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        return datastore.load(CaptainDTO.class, id);
    }

    @Override
    public ArrayList<CaptainDTO> getCaptainsForUser(final UserDTO user) throws DAOException {
        ArrayList<CaptainDTO> foundCaptains = null;
        ArrayList<Filter> userFilter = new ArrayList<Filter>();
        userFilter.add(new SimpleFilterItem("ownerId", String.valueOf(user.getId())));
        foundCaptains = getAllCaptains(0, 50, userFilter, "");
        return foundCaptains;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalCaptains() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        return datastore.find().type(CaptainDTO.class).returnCount().now();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalCaptainsWithFilter(final ArrayList<Filter> criteria) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<CaptainDTO> command = datastore.find().type(CaptainDTO.class);
        command = addFilterToCommand(command, criteria);

        Iterator<CaptainDTO> itCaptains = command.now();
        return convertToInterface(itCaptains).size() - 1;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean removeCaptain(final CaptainDTO captain) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.associate(captain);
        datastore.delete(captain);
        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CaptainDTO updateCaptain(final CaptainDTO captain) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.storeOrUpdate((CaptainDTO) captain);

        return captain;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean captainNameExists(String name) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<CaptainDTO> command = datastore.find().type(CaptainDTO.class);
        command.addFilter("name", FilterOperator.EQUAL, name);

        Iterator<CaptainDTO> itCaptains = command.now();

        if (itCaptains.hasNext()) {
            return true;
        }

        return false;
    }

}
