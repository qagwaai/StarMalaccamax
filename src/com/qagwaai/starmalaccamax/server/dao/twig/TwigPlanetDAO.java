/**
 * DataNucleusUserDAO.java
 * Created by pgirard at 3:25:05 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server.dao.datanucleus package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao.twig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Transaction;
import com.google.code.twig.FindCommand.RootFindCommand;
import com.google.code.twig.ObjectDatastore;
import com.google.code.twig.annotation.AnnotationObjectDatastore;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.PlanetDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.Planet;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;

/**
 * @author pgirard
 * 
 */
public final class TwigPlanetDAO implements PlanetDAO {

    private static Logger log = Logger.getLogger(TwigPlanetDAO.class.getName());
    /**
	 * 
	 */
    public TwigPlanetDAO() {

    }

    /**
     * 
     * @param command
     *            the command to add filters to
     * @param criteria
     *            the to add to the command
     * @return the filtered command
     */
    private RootFindCommand<PlanetDTO> addFilterToCommand(final RootFindCommand<PlanetDTO> command,
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
                        if (item.getValue() != null) {
                            command.addFilter(item.getField(), FilterOperator.EQUAL, Integer.valueOf(item.getValue()));
                        }
                    } else if (item.getField().equals("hydrographics")) {
                        if (item.getValue() != null) {
                            command.addFilter(item.getField(), FilterOperator.EQUAL, Double.valueOf(item.getValue()));
                        }
                    } else if (item.getField().equals("solarSystemId")) {
                        if (item.getValue() != null) {
                            command.addFilter(item.getField(), FilterOperator.EQUAL, Long.valueOf(item.getValue()));
                        }
                    } else {
                        if (item.getValue() != null) {
                            command.addFilter(item.getField(), FilterOperator.EQUAL, item.getValue());
                        }
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
    public PlanetDTO addPlanet(final PlanetDTO newPlanet) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        Transaction trans = datastore.beginTransaction();
        Key key = datastore.store(newPlanet);
        newPlanet.setId(key.getId());
        trans.commit();

        return newPlanet;
    }

    /**
     * 
     * @param command
     *            the command to add sort criteria to
     * @param fullSort
     *            the full sort string ("name, -field")
     * @return the augmented command
     */
    private RootFindCommand<PlanetDTO>
        addSortsToCommand(final RootFindCommand<PlanetDTO> command, final String fullSort) {
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
    public ArrayList<PlanetDTO> bulkAddPlanet(final ArrayList<PlanetDTO> newPlanets) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        for (Planet planet : newPlanets) {
            datastore.storeOrUpdate((PlanetDTO) planet);
        }

        return newPlanets;
    }

    /**
     * 
     * @param itPlanets
     *            the Planets to convert
     * @return the converted Planets
     */
    private ArrayList<PlanetDTO> convertToInterface(final Iterator<PlanetDTO> itPlanets) {
        ArrayList<PlanetDTO> list = new ArrayList<PlanetDTO>();

        while (itPlanets.hasNext()) {
            list.add(itPlanets.next());
        }
        return list;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAllPlanets() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.deleteAll(PlanetDTO.class);

        return true;
    }

    /**
     * 
     * @param itPlanets
     *            the list of Planets to filter
     * @param nameFilter
     *            the name filter
     * @return the converted list of Planets
     */
    private ArrayList<PlanetDTO> filterOnName(final Iterator<PlanetDTO> itPlanets, final SimpleFilterItem nameFilter) {
        ArrayList<PlanetDTO> list = new ArrayList<PlanetDTO>();

        while (itPlanets.hasNext()) {
            PlanetDTO ss = itPlanets.next();
            if (nameFilter.getValue() != null) {
                if (ss.getName().contains((String) nameFilter.getValue())) {
                    list.add(ss);
                }
            }
        }
        return list;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<PlanetDTO> getAllPlanets() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        Iterator<PlanetDTO> itPlanets = datastore.find().type(PlanetDTO.class).now();

        return convertToInterface(itPlanets);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<PlanetDTO> getAllPlanets(final int startRow, final int endRow, final ArrayList<Filter> criteria,
        final String sortBy) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<PlanetDTO> command =
            datastore.find().type(PlanetDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addFilterToCommand(command, criteria);
        command = addSortsToCommand(command, sortBy);
        Iterator<PlanetDTO> itPlanets = command.now();
        if (getNameFilter(criteria) != null) {
            return filterOnName(itPlanets, getNameFilter(criteria));
        } else {
            return convertToInterface(itPlanets);
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<PlanetDTO> getAllPlanets(final int startRow, final int endRow, final String sortBy)
        throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<PlanetDTO> command =
            datastore.find().type(PlanetDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addSortsToCommand(command, sortBy);
        Iterator<PlanetDTO> itPlanets = command.now();

        return convertToInterface(itPlanets);
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

    @Override
    public ArrayList<PlanetDTO> getNonGasGiantPlanetsForSolarSystem(final Long solarSystemId) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<PlanetDTO> command = datastore.find().type(PlanetDTO.class);
        command.addFilter("solarSystemId", FilterOperator.EQUAL, solarSystemId);
        command.addFilter("isGasGiant", FilterOperator.EQUAL, false);

        Iterator<PlanetDTO> itPlanets = command.now();
        return convertToInterface(itPlanets);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public PlanetDTO getPlanet(final Long id) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        return datastore.load(PlanetDTO.class, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<PlanetDTO> getPlanetsForSolarSystem(final Long solarSystemId) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<PlanetDTO> command = datastore.find().type(PlanetDTO.class);
        command.addFilter("solarSystemId", FilterOperator.EQUAL, solarSystemId);

        Iterator<PlanetDTO> itPlanets = command.now();
        ArrayList<PlanetDTO> retVal = convertToInterface(itPlanets);
        return retVal;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalPlanets() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        return datastore.find().type(PlanetDTO.class).returnCount().now();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalPlanetsWithFilter(final ArrayList<Filter> criteria) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<PlanetDTO> command = datastore.find().type(PlanetDTO.class);
        command = addFilterToCommand(command, criteria);

        Iterator<PlanetDTO> itPlanets = command.now();
        if (getNameFilter(criteria) != null) {
            ArrayList<PlanetDTO> filteredResult = filterOnName(itPlanets, getNameFilter(criteria));
            return filteredResult.size() - 1;
        } else {
            return convertToInterface(itPlanets).size() - 1;
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean removePlanet(final PlanetDTO planet) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.associate(planet);
        datastore.delete(planet);
        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public PlanetDTO updatePlanet(final PlanetDTO planet) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.storeOrUpdate((PlanetDTO) planet);

        return planet;
    }
}
