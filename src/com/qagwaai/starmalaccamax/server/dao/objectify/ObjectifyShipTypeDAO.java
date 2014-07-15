package com.qagwaai.starmalaccamax.server.dao.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.ShipTypeDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.ShipTypeDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;

public class ObjectifyShipTypeDAO implements ShipTypeDAO {
	/**
	 * 
	 * @param command
	 *            the command to add filters to
	 * @param criteria
	 *            the to add to the command
	 * @return the filtered command
	 */
	private Query<ShipTypeDTO> addFilterToCommand(Query<ShipTypeDTO> command,
			final ArrayList<Filter> criteria) {
		if (criteria == null) {
			return command;
		}
		for (Filter criterion : criteria) {
			if (criterion instanceof SimpleFilterItem) {
				SimpleFilterItem item = (SimpleFilterItem) criterion;
				if (item.getField().equals("name")) {
					command = command.filter(item.getField(), item.getValue());
				} else if ( item.getField().equals("id")) {
					command = (Query<ShipTypeDTO>) command.filterKey(Key.create(ShipTypeDTO.class, Long.valueOf(item.getValue()).longValue()));
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
	private Query<ShipTypeDTO> addSortsToCommand(
			Query<ShipTypeDTO> command, final String fullSort) {
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
     *            the ShipType to convert
     * @return the converted ShipType
     */
    private ArrayList<ShipTypeDTO> convertToInterface(final Iterator<ShipTypeDTO> itShip) {
        ArrayList<ShipTypeDTO> list = new ArrayList<ShipTypeDTO>();

        while (itShip.hasNext()) {
            list.add(itShip.next());
        }
        return list;
    }


	@Override
	public ShipTypeDTO addShipType(ShipTypeDTO newShipType) throws DAOException {
		Key<ShipTypeDTO> pId = ofy().save().entity(newShipType).now();
		newShipType.setId(pId.getId());
		return newShipType;
	}

	@Override
	public ArrayList<ShipTypeDTO> bulkAddShipType(
			ArrayList<ShipTypeDTO> newShipTypes) throws DAOException {
        for (ShipTypeDTO shipType : newShipTypes) {
            addShipType((ShipTypeDTO) shipType);
        }

        return newShipTypes;
	}

	@Override
	public boolean deleteAllShipTypes() throws DAOException {
		List<Key<ShipTypeDTO>> keys = ofy().load().type(ShipTypeDTO.class).keys().list();
		ofy().delete().keys(keys);
		return true;
	}

	@Override
	public ArrayList<ShipTypeDTO> getAllShipTypes() throws DAOException {
		return getAllShipTypes(0, getTotalShipTypes(), null, "");
	}

	@Override
	public ArrayList<ShipTypeDTO> getAllShipTypes(int startRow, int endRow,
			ArrayList<Filter> criteria, String sortBy) throws DAOException {
		Query<ShipTypeDTO> command = ofy().load().type(ShipTypeDTO.class)
				.limit(endRow);
		command = addFilterToCommand(command, criteria);
		command = addSortsToCommand(command, sortBy);
		command.chunkAll();
		Iterator<ShipTypeDTO> itShips = command.iterator();
		return convertToInterface(itShips);
	}

	@Override
	public ArrayList<ShipTypeDTO> getAllShipTypes(int startRow, int endRow,
			String sortBy) throws DAOException {
		Query<ShipTypeDTO> command = ofy().load().type(ShipTypeDTO.class)
				.limit(endRow);
		command = addSortsToCommand(command, sortBy);
		Iterator<ShipTypeDTO> itShip = command.iterator();
		return convertToInterface(itShip);
	}

	@Override
	public ShipTypeDTO getShipType(Long id) throws DAOException {
		return ofy().load().type(ShipTypeDTO.class).id(id).now();
	}

	@Override
	public int getTotalShipTypes() throws DAOException {
		return ofy().load().type(ShipTypeDTO.class).count();
	}

	@Override
	public int getTotalShipTypesWithFilter(ArrayList<Filter> criteria)
			throws DAOException {
		Query<ShipTypeDTO> command = ofy().load().type(ShipTypeDTO.class);
		command = addFilterToCommand(command, criteria);
		return command.count();
	}

	@Override
	public boolean removeShipType(ShipTypeDTO shipType) throws DAOException {
		ofy().delete().type(ShipTypeDTO.class).id(shipType.getId());
		return true;
	}

	@Override
	public ShipTypeDTO updateShipType(ShipTypeDTO shipType) throws DAOException {
		Key<ShipTypeDTO> jumpGateId = ofy().save().entity(shipType).now();
		shipType.setId(jumpGateId.getId());
		return shipType;
	}

}
