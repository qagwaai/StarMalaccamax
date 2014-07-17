package com.qagwaai.starmalaccamax.server.dao.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.GameEventDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.GameEvent;
import com.qagwaai.starmalaccamax.shared.model.GameEventDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;

public class ObjectifyGameEventDAO implements GameEventDAO {
	/**
	 * 
	 * @param command
	 *            the command to add filters to
	 * @param criteria
	 *            the to add to the command
	 * @return the filtered command
	 */
	private Query<GameEventDTO> addFilterToCommand(Query<GameEventDTO> command,
			final ArrayList<Filter> criteria) throws DAOException {
		if (criteria == null) {
			return command;
		}
		for (Filter criterion : criteria) {
			if (criterion instanceof SimpleFilterItem) {
				SimpleFilterItem item = (SimpleFilterItem) criterion;
				if (item.getField().equals("playerId")) {
					command = command.filter(item.getField(), Long.valueOf(item.getValue()).longValue());
				} else if (item.getField().equals("eventEnabled")) {
					command = command.filter(item.getField(), Boolean.valueOf(item.getValue()).booleanValue());
				} else if (item.getField().equals("endDateTime")) {
					DateFormat df =  DateFormat.getDateInstance();
					try {
						command = command.filter(item.getField(), df.parse(item.getValue()));
					} catch (ParseException e) {
						throw new DAOException("Invalid date format [" + item.getValue() + "]");
					}
				} else if ( item.getField().equals("id")) {
					command = (Query<GameEventDTO>) command.filterKey(Key.create(GameEventDTO.class, Long.valueOf(item.getValue()).longValue()));
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
	private Query<GameEventDTO> addSortsToCommand(
			Query<GameEventDTO> command, final String fullSort) {
		if (fullSort == null) {
			return command;
		}
		if (fullSort.equals("")) {
			return command;
		}
		String[] sortFields = fullSort.split(",");

		for (int i = 0; i < sortFields.length; i++) {
			command = command.order(sortFields[i]);
		}

		return command;
	}
	
    /**
     * 
     * @param itGameEvent
     *            the GameEvent to convert
     * @return the converted GameEvent
     */
    private ArrayList<GameEventDTO> convertToInterface(final Iterator<GameEventDTO> itGameEvent) {
        ArrayList<GameEventDTO> list = new ArrayList<GameEventDTO>();

        while (itGameEvent.hasNext()) {
            list.add(itGameEvent.next());
        }
        return list;
    }
	@Override
	public GameEventDTO addGameEvent(GameEventDTO newGameEvent)
			throws DAOException {
		Key<GameEventDTO> pId = ofy().save().entity(newGameEvent).now();
		newGameEvent.setId(pId.getId());
		return newGameEvent;
	}

	@Override
	public ArrayList<GameEventDTO> bulkAddGameEvent(
			ArrayList<GameEventDTO> newGameEvents) throws DAOException {
        for (GameEvent star : newGameEvents) {
            addGameEvent((GameEventDTO) star);
        }

        return newGameEvents;
	}

	@Override
	public boolean deleteAllGameEvents() throws DAOException {
		List<Key<GameEventDTO>> keys = ofy().load().type(GameEventDTO.class).keys().list();
		ofy().delete().keys(keys);
		return true;
	}

	@Override
	public ArrayList<GameEventDTO> getAllGameEvents() throws DAOException {
		return getAllGameEvents(0, getTotalGameEvents(), null, "");
	}

	@Override
	public ArrayList<GameEventDTO> getAllGameEvents(int startRow, int endRow,
			ArrayList<Filter> criteria, String sortBy) throws DAOException {
		Query<GameEventDTO> command = ofy().load().type(GameEventDTO.class)
				.limit(endRow);
		command = addFilterToCommand(command, criteria);
		command = addSortsToCommand(command, sortBy);
		command.chunkAll();
		Iterator<GameEventDTO> itGameEvents = command.iterator();
		return convertToInterface(itGameEvents);
	}

	@Override
	public ArrayList<GameEventDTO> getAllGameEvents(int startRow, int endRow,
			String sortBy) throws DAOException {
		Query<GameEventDTO> command = ofy().load().type(GameEventDTO.class)
				.limit(endRow);
		command = addSortsToCommand(command, sortBy);
		Iterator<GameEventDTO> itGameEvent = command.iterator();
		return convertToInterface(itGameEvent);
	}

	@Override
	public GameEventDTO getGameEvent(Long id) throws DAOException {
		return ofy().load().type(GameEventDTO.class).id(id).now();
	}

	@Override
	public int getTotalGameEvents() throws DAOException {
		return ofy().load().type(GameEventDTO.class).count();
	}

	@Override
	public int getTotalGameEventsWithFilter(ArrayList<Filter> criteria)
			throws DAOException {
		Query<GameEventDTO> command = ofy().load().type(GameEventDTO.class);
		command = addFilterToCommand(command, criteria);
		return command.count();
	}

	@Override
	public boolean removeGameEvent(GameEventDTO gameEvent) throws DAOException {
		ofy().delete().type(GameEventDTO.class).id(gameEvent.getId());
		return true;
	}

	@Override
	public GameEventDTO updateGameEvent(GameEventDTO gameEvent)
			throws DAOException {
		Key<GameEventDTO> jumpGateId = ofy().save().entity(gameEvent).now();
		gameEvent.setId(jumpGateId.getId());
		return gameEvent;
	}

	@Override
	public ArrayList<GameEventDTO> getEndedGameEvents() throws DAOException {
		
		Query<GameEventDTO> command = ofy().load().type(GameEventDTO.class);
        command.filter("endDateTime <", new Date(System.currentTimeMillis()));
        command.filter("eventEnabled", true);
        Iterator<GameEventDTO> itGameEvents = command.iterator();

        return convertToInterface(itGameEvents);
	}

}
