package com.qagwaai.starmalaccamax.server.dao.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.ShipDAO;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;

public class ObjectifyShipDAO implements ShipDAO {
	/**
	 * 
	 * @param command
	 *            the command to add filters to
	 * @param criteria
	 *            the to add to the command
	 * @return the filtered command
	 */
	private Query<ShipDTO> addFilterToCommand(Query<ShipDTO> command,
			final ArrayList<Filter> criteria) {
		if (criteria == null) {
			return command;
		}
		for (Filter criterion : criteria) {
			if (criterion instanceof SimpleFilterItem) {
				SimpleFilterItem item = (SimpleFilterItem) criterion;
				if (item.getField().equals("ownerId")) {
					command = command.filter(item.getField(), Long.valueOf(item.getValue()).longValue());
				} else if ( item.getField().equals("id")) {
					command = (Query<ShipDTO>) command.filterKey(Key.create(ShipDTO.class, Long.valueOf(item.getValue()).longValue()));
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
	private Query<ShipDTO> addSortsToCommand(
			Query<ShipDTO> command, final String fullSort) {
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
     * @param itShip
     *            the Ship to convert
     * @return the converted Ship
     */
    private ArrayList<ShipDTO> convertToInterface(final Iterator<ShipDTO> itShip) {
        ArrayList<ShipDTO> list = new ArrayList<ShipDTO>();

        while (itShip.hasNext()) {
            list.add(itShip.next());
        }
        return list;
    }

	@Override
	public ShipDTO addShip(ShipDTO newShip) throws DAOException {
		Key<ShipDTO> pId = ofy().save().entity(newShip).now();
		newShip.setId(pId.getId());
		return newShip;
	}

	@Override
	public boolean deleteAllShips() throws DAOException {
		List<Key<ShipDTO>> keys = ofy().load().type(ShipDTO.class).keys().list();
		ofy().delete().keys(keys);
		return true;
	}

	@Override
	public ArrayList<ShipDTO> getAllShips() throws DAOException {
		return getAllShips(0, getTotalShips(), null, "");
	}

	@Override
	public ArrayList<ShipDTO> getAllShips(int startRow, int endRow,
			ArrayList<Filter> criteria, String sortBy) throws DAOException {
		Query<ShipDTO> command = ofy().load().type(ShipDTO.class)
				.limit(endRow);
		command = addFilterToCommand(command, criteria);
		command = addSortsToCommand(command, sortBy);
		command.chunkAll();
		Iterator<ShipDTO> itShips = command.iterator();
		return convertToInterface(itShips);
	}

	@Override
	public ArrayList<ShipDTO> getAllShips(int startRow, int endRow,
			String sortBy) throws DAOException {
		Query<ShipDTO> command = ofy().load().type(ShipDTO.class)
				.limit(endRow);
		command = addSortsToCommand(command, sortBy);
		Iterator<ShipDTO> itShip = command.iterator();
		return convertToInterface(itShip);
	}

	@Override
	public ShipDTO getShip(Long id) throws DAOException {
		return ofy().load().type(ShipDTO.class).id(id).now();
	}

	@Override
	public ShipDTO getShipForCaptain(CaptainDTO captain) throws DAOException {
		Query<ShipDTO> command = ofy().load().type(ShipDTO.class).limit(2000);
		command = command.filter("ownerId", captain.getId());
		command = addSortsToCommand(command, "ownerId");
		Iterator<ShipDTO> itShip = command.iterator();
		ArrayList<ShipDTO> found = convertToInterface(itShip);

        if (found.size() == 1) {
            return found.get(0);
        } else if (found.size() == 0) {
            return null;
        } else {
            throw new DAOException("Found " + found.size() + " ships for captain " + captain.getId());
        }
	}

	@Override
	public int getTotalShips() throws DAOException {
		return ofy().load().type(ShipDTO.class).count();
	}

	@Override
	public int getTotalShipsWithFilter(ArrayList<Filter> criteria)
			throws DAOException {
		Query<ShipDTO> command = ofy().load().type(ShipDTO.class);
		command = addFilterToCommand(command, criteria);
		return command.count();
	}

	@Override
	public boolean removeShip(ShipDTO ship) throws DAOException {
		ofy().delete().type(ShipDTO.class).id(ship.getId());
		return true;
	}

	@Override
	public ShipDTO updateShip(ShipDTO ship) throws DAOException {
		Key<ShipDTO> jumpGateId = ofy().save().entity(ship).now();
		ship.setId(jumpGateId.getId());
		return ship;
	}

}
