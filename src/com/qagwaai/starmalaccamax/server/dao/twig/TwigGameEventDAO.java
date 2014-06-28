/**
 * DataNucleusUserDAO.java
 * Created by pgirard at 3:25:05 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server.dao.datanucleus package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao.twig;

import java.util.ArrayList;
import java.util.Date;
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
import com.qagwaai.starmalaccamax.server.dao.GameEventDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;

/**
 * @author pgirard
 * 
 */
public final class TwigGameEventDAO implements GameEventDAO {
    /**
	 * 
	 */
    private static Logger log = Logger.getLogger(TwigGameEventDAO.class.getName());

    /**
	 * 
	 */
    public TwigGameEventDAO() {

    }

    /**
     * 
     * @param command
     *            the command to add filters to
     * @param criteria
     *            the to add to the command
     * @return the filtered command
     */
    private RootFindCommand<GameEventDTO> addFilterToCommand(final RootFindCommand<GameEventDTO> command,
        final ArrayList<Filter> criteria) {
        for (Filter criterion : criteria) {
            if (criterion instanceof SimpleFilterItem) {
                SimpleFilterItem item = (SimpleFilterItem) criterion;
                if (!item.getField().equals("name")) {
                    // this is using the equal operator - any text searching
                    // will be impled below
                    if (item.getField().equals("playerId")) {
                        command.addFilter(item.getField(), FilterOperator.EQUAL, Long.valueOf(item.getValue()));
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
    public GameEventDTO addGameEvent(final GameEventDTO newGameEvent) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        Transaction trans = datastore.beginTransaction();
        // newGameEvent.setActivityClass(serializeActivity(newGameEvent.getActivity()));
        Key key = datastore.store(newGameEvent);
        newGameEvent.setId(key.getId());
        trans.commit();

        return newGameEvent;
    }

    /**
     * 
     * @param command
     *            the command to add sort criteria to
     * @param fullSort
     *            the full sort string ("name, -field")
     * @return the augmented command
     */
    private RootFindCommand<GameEventDTO> addSortsToCommand(final RootFindCommand<GameEventDTO> command,
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
    public ArrayList<GameEventDTO> bulkAddGameEvent(final ArrayList<GameEventDTO> newGameEvents) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        for (GameEventDTO gameEvent : newGameEvents) {
            // gameEvent.setActivityClass(serializeActivity(gameEvent.getActivity()));
            datastore.storeOrUpdate(gameEvent);
        }

        return newGameEvents;
    }

    /*
     * private Text serializeActivity(GameActivity activity) {
     * ByteArrayOutputStream fos = null; ObjectOutputStream out = null; try {
     * fos = new ByteArrayOutputStream(); out = new ObjectOutputStream(fos);
     * out.writeObject(activity); out.close(); } catch(IOException ex) {
     * log.severe("Could not serialize activity: " + ex.getMessage()); return
     * new Text(""); }
     * 
     * return new Text(fos.toString()); }
     */

    /*
     * private GameActivity deserializeActivity(Text activity) {
     * ByteArrayInputStream fos = null; ObjectInputStream in = null;
     * 
     * try { fos = new ByteArrayInputStream(activity.getValue().getBytes()); in
     * = new ObjectInputStream(fos);
     * 
     * Object obj = in.readObject(); in.close(); if (obj instanceof
     * GameActivity) { return (GameActivity) obj; } else {
     * log.severe("Deserialize return non-GameActivity"); } } catch (IOException
     * e) { log.severe("Could not deserialize activity: " + e.getMessage()); }
     * catch (ClassNotFoundException e) {
     * log.severe("Could not deserialize activity: " + e.getMessage()); } return
     * null; }
     */

    /**
     * 
     * @param itGameEvents
     *            the GameEvents to convert
     * @return the converted GameEvents
     */
    private ArrayList<GameEventDTO> convertToInterfaceAndDeserialize(final Iterator<GameEventDTO> itGameEvents) {
        ArrayList<GameEventDTO> list = new ArrayList<GameEventDTO>();

        while (itGameEvents.hasNext()) {
            GameEventDTO ge = itGameEvents.next();
            // ge.setActivity(deserializeActivity(ge.getActivityClass()));
            list.add(ge);
        }
        return list;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAllGameEvents() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.deleteAll(GameEventDTO.class);

        return true;
    }

    /**
     * 
     * @param itGameEvents
     *            the list of GameEvents to filter
     * @param nameFilter
     *            the name filter
     * @return the converted list of GameEvents
     */
    private ArrayList<GameEventDTO> filterOnName(final Iterator<GameEventDTO> itGameEvents,
        final SimpleFilterItem nameFilter) {
        ArrayList<GameEventDTO> list = new ArrayList<GameEventDTO>();

        while (itGameEvents.hasNext()) {
            GameEventDTO ss = itGameEvents.next();
            if (ss.getShortDescription().contains(nameFilter.getValue())) {
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
    public ArrayList<GameEventDTO> getAllGameEvents() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        Iterator<GameEventDTO> itGameEvents = datastore.find().type(GameEventDTO.class).now();

        return convertToInterfaceAndDeserialize(itGameEvents);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<GameEventDTO> getAllGameEvents(final int startRow, final int endRow,
        final ArrayList<Filter> criteria, final String sortBy) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<GameEventDTO> command =
            datastore.find().type(GameEventDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addFilterToCommand(command, criteria);
        command = addSortsToCommand(command, sortBy);
        Iterator<GameEventDTO> itGameEvents = command.now();
        if (getNameFilter(criteria) != null) {
            return filterOnName(itGameEvents, getNameFilter(criteria));
        } else {
            return convertToInterfaceAndDeserialize(itGameEvents);
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<GameEventDTO> getAllGameEvents(final int startRow, final int endRow, final String sortBy)
        throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<GameEventDTO> command =
            datastore.find().type(GameEventDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addSortsToCommand(command, sortBy);
        Iterator<GameEventDTO> itGameEvents = command.now();

        return convertToInterfaceAndDeserialize(itGameEvents);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public GameEventDTO getGameEvent(final Long id) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        return datastore.load(GameEventDTO.class, id);
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
    public int getTotalGameEvents() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        return datastore.find().type(GameEventDTO.class).returnCount().now();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalGameEventsWithFilter(final ArrayList<Filter> criteria) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<GameEventDTO> command = datastore.find().type(GameEventDTO.class);
        command = addFilterToCommand(command, criteria);

        Iterator<GameEventDTO> itGameEvents = command.now();
        if (getNameFilter(criteria) != null) {
            ArrayList<GameEventDTO> filteredResult = filterOnName(itGameEvents, getNameFilter(criteria));
            return filteredResult.size() - 1;
        } else {
            return convertToInterfaceAndDeserialize(itGameEvents).size() - 1;
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean removeGameEvent(final GameEventDTO gameEvent) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.associate(gameEvent);
        datastore.delete(gameEvent);
        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public GameEventDTO updateGameEvent(final GameEventDTO gameEvent) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        // gameEvent.setActivityClass(serializeActivity(gameEvent.getActivity()));
        datastore.storeOrUpdate(gameEvent);

        return gameEvent;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<GameEventDTO> getEndedGameEvents() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<GameEventDTO> command = datastore.find().type(GameEventDTO.class);
        command.addFilter("endDateTime", FilterOperator.LESS_THAN, new Date(System.currentTimeMillis()));
	command.addFilter("eventEnabled", FilterOperator.EQUAL, true);
        Iterator<GameEventDTO> itGameEvents = command.now();

        return convertToInterfaceAndDeserialize(itGameEvents);
    }
}
