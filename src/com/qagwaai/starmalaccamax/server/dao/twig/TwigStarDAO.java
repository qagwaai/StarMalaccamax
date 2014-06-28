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
import com.qagwaai.starmalaccamax.server.dao.StarDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;
import com.qagwaai.starmalaccamax.shared.model.Star;
import com.qagwaai.starmalaccamax.shared.model.StarDTO;

/**
 * @author pgirard
 * 
 */
public final class TwigStarDAO implements StarDAO {

    /**
	 * 
	 */
    public TwigStarDAO() {

    }

    /**
     * 
     * @param command
     *            the command to add filters to
     * @param criteria
     *            the to add to the command
     * @return the filtered command
     */
    private RootFindCommand<StarDTO> addFilterToCommand(final RootFindCommand<StarDTO> command,
        final ArrayList<Filter> criteria) {
        for (Filter criterion : criteria) {
            if (criterion instanceof SimpleFilterItem) {
                SimpleFilterItem item = (SimpleFilterItem) criterion;
                if (!item.getField().equals("name")) {
                    // this is using the equal operator - any text searching
                    // will be impled below
                    if (item.getField().equals("numberOfComponents") || item.getField().equals("maximumOrbits")
                        || item.getField().equals("habitableOrbit") || item.getField().equals("gasGiants")
                        || item.getField().equals("planetoidBelts") || item.getField().equals("hip")) {
                        command.addFilter(item.getField(), FilterOperator.EQUAL, Integer.valueOf(item.getValue()));
                    } else if (item.getField().equals("alpha") || item.getField().equals("delta")
                        || item.getField().equals("parallax") || item.getField().equals("x")
                        || item.getField().equals("y") || item.getField().equals("z")) {
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
     * @param command
     *            the command to add sort criteria to
     * @param fullSort
     *            the full sort string ("name, -field")
     * @return the augmented command
     */
    private RootFindCommand<StarDTO> addSortsToCommand(final RootFindCommand<StarDTO> command, final String fullSort) {
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
    public StarDTO addStar(final StarDTO newStar) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        Transaction trans = datastore.beginTransaction();
        Key key = datastore.store(newStar);
        newStar.setId(key.getId());
        trans.commit();

        return newStar;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<StarDTO> bulkAddStar(final ArrayList<StarDTO> newStars) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        for (Star star : newStars) {
            datastore.storeOrUpdate((StarDTO) star);
        }

        return newStars;
    }

    /**
     * 
     * @param itStars
     *            the Stars to convert
     * @return the converted Stars
     */
    private ArrayList<StarDTO> convertToInterface(final Iterator<StarDTO> itStars) {
        ArrayList<StarDTO> list = new ArrayList<StarDTO>();

        while (itStars.hasNext()) {
            list.add(itStars.next());
        }
        return list;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAllStars() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.deleteAll(StarDTO.class);

        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<StarDTO> getAllStars() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        Iterator<StarDTO> itStars = datastore.find().type(StarDTO.class).now();

        return convertToInterface(itStars);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<StarDTO> getAllStars(final int startRow, final int endRow, final ArrayList<Filter> criteria,
        final String sortBy) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<StarDTO> command =
            datastore.find().type(StarDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addFilterToCommand(command, criteria);
        command = addSortsToCommand(command, sortBy);
        Iterator<StarDTO> itStars = command.now();
        return convertToInterface(itStars);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<StarDTO> getAllStars(final int startRow, final int endRow, final String sortBy) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<StarDTO> command =
            datastore.find().type(StarDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addSortsToCommand(command, sortBy);
        Iterator<StarDTO> itStars = command.now();

        return convertToInterface(itStars);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public StarDTO getStar(final Long id) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        return datastore.load(StarDTO.class, id);
    }

    @Override
    public ArrayList<StarDTO> getStarsForSolarSystem(final Long id) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<StarDTO> command = datastore.find().type(StarDTO.class);
        command.addFilter("solarSystemId", FilterOperator.EQUAL, id);

        Iterator<StarDTO> itStars = command.now();
        return convertToInterface(itStars);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalStars() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        return datastore.find().type(StarDTO.class).returnCount().now();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalStarsWithFilter(final ArrayList<Filter> criteria) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<StarDTO> command = datastore.find().type(StarDTO.class);
        command = addFilterToCommand(command, criteria);

        Iterator<StarDTO> itStars = command.now();
        return convertToInterface(itStars).size() - 1;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean removeStar(final StarDTO star) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.associate(star);
        datastore.delete(star);
        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public StarDTO updateStar(final StarDTO star) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.storeOrUpdate((StarDTO) star);

        return star;
    }
}
