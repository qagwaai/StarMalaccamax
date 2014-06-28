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
import com.qagwaai.starmalaccamax.server.dao.JumpGateDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.JumpGate;
import com.qagwaai.starmalaccamax.shared.model.JumpGateDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;

/**
 * @author pgirard
 * 
 */
public final class TwigJumpGateDAO implements JumpGateDAO {

    /**
	 * 
	 */
    public TwigJumpGateDAO() {

    }

    /**
     * 
     * @param command
     *            the command to add filters to
     * @param criteria
     *            the to add to the command
     * @return the filtered command
     */
    private RootFindCommand<JumpGateDTO> addFilterToCommand(final RootFindCommand<JumpGateDTO> command,
        final ArrayList<Filter> criteria) {
        for (Filter criterion : criteria) {
            if (criterion instanceof SimpleFilterItem) {
                SimpleFilterItem item = (SimpleFilterItem) criterion;
                command.addFilter(item.getField(), FilterOperator.EQUAL, Long.valueOf(item.getValue()));
            }
        }
        return command;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public JumpGateDTO addJumpGate(final JumpGateDTO newJumpGate) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        Transaction trans = datastore.beginTransaction();
        Key key = datastore.store(newJumpGate);
        newJumpGate.setId(key.getId());
        trans.commit();

        return newJumpGate;
    }

    /**
     * 
     * @param command
     *            the command to add sort criteria to
     * @param fullSort
     *            the full sort string ("name, -field")
     * @return the augmented command
     */
    private RootFindCommand<JumpGateDTO> addSortsToCommand(final RootFindCommand<JumpGateDTO> command,
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
    public ArrayList<JumpGateDTO> bulkAddJumpGate(final ArrayList<JumpGateDTO> newJumpGates) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        for (JumpGate jumpGate : newJumpGates) {
            datastore.storeOrUpdate((JumpGateDTO) jumpGate);
        }

        return newJumpGates;
    }

    /**
     * 
     * @param itJumpGates
     *            the JumpGates to convert
     * @return the converted JumpGates
     */
    private ArrayList<JumpGateDTO> convertToInterface(final Iterator<JumpGateDTO> itJumpGates) {
        ArrayList<JumpGateDTO> list = new ArrayList<JumpGateDTO>();

        while (itJumpGates.hasNext()) {
            list.add(itJumpGates.next());
        }
        return list;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAllJumpGates() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.deleteAll(JumpGateDTO.class);

        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<JumpGateDTO> getAllJumpGates() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        Iterator<JumpGateDTO> itJumpGates = datastore.find().type(JumpGateDTO.class).now();

        return convertToInterface(itJumpGates);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<JumpGateDTO> getAllJumpGates(final int startRow, final int endRow, final ArrayList<Filter> criteria,
        final String sortBy) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<JumpGateDTO> command =
            datastore.find().type(JumpGateDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addFilterToCommand(command, criteria);
        command = addSortsToCommand(command, sortBy);
        Iterator<JumpGateDTO> itJumpGates = command.now();
        return convertToInterface(itJumpGates);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<JumpGateDTO> getAllJumpGates(final int startRow, final int endRow, final String sortBy)
        throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<JumpGateDTO> command =
            datastore.find().type(JumpGateDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addSortsToCommand(command, sortBy);
        Iterator<JumpGateDTO> itJumpGates = command.now();

        return convertToInterface(itJumpGates);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public JumpGateDTO getJumpGate(final Long id) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        return datastore.load(JumpGateDTO.class, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalJumpGates() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        return datastore.find().type(JumpGateDTO.class).returnCount().now();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalJumpGatesWithFilter(final ArrayList<Filter> criteria) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<JumpGateDTO> command = datastore.find().type(JumpGateDTO.class);
        command = addFilterToCommand(command, criteria);

        Iterator<JumpGateDTO> itJumpGates = command.now();
        return convertToInterface(itJumpGates).size() - 1;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean removeJumpGate(final JumpGateDTO jumpGate) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.associate(jumpGate);
        datastore.delete(jumpGate);
        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public JumpGateDTO updateJumpGate(final JumpGateDTO jumpGate) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.storeOrUpdate((JumpGateDTO) jumpGate);

        return jumpGate;
    }

}
