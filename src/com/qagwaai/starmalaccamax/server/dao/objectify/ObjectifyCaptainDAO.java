package com.qagwaai.starmalaccamax.server.dao.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;
import com.qagwaai.starmalaccamax.server.dao.CaptainDAO;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

public class ObjectifyCaptainDAO implements CaptainDAO {

	/**
	 * 
	 * @param command
	 *            the command to add filters to
	 * @param criteria
	 *            the to add to the command
	 * @return the filtered command
	 */
	private Query<CaptainDTO> addFilterToCommand(
			Query<CaptainDTO> command, final ArrayList<Filter> criteria) {
		if (criteria == null) {
			return command;
		}
		for (Filter criterion : criteria) {
			if (criterion instanceof SimpleFilterItem) {
				SimpleFilterItem item = (SimpleFilterItem) criterion;
				if (!item.getField().equals("name")) {
					// this is using the equal operator - any text searching
					// will be impled below
					if (item.getField().equals("ownerId")) {
						command = command.filter(item.getField(),
								Long.valueOf(item.getValue()));
						// command.addFilter(item.getField(),
						// FilterOperator.EQUAL, Long.valueOf(item.getValue()));
					} else {
						command = command.filter(item.getField(), item.getValue());
						// command.addFilter(item.getField(),
						// FilterOperator.EQUAL, item.getValue());
					}
				} else {
					command = command.filter(item.getField(), item.getValue());
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
	private Query<CaptainDTO> addSortsToCommand(
			Query<CaptainDTO> command, final String fullSort) {
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

	@Override
	public CaptainDTO addCaptain(CaptainDTO newCaptain) throws DAOException {
		Key<CaptainDTO> captainId = ofy().save().entity(newCaptain).now();
		newCaptain.setId(captainId.getId());
		return newCaptain;
	}

	@Override
	public boolean deleteAllCaptains() throws DAOException {
		List<Key<CaptainDTO>> keys = ofy().load().type(CaptainDTO.class).keys().list();
		ofy().delete().keys(keys);
		return true;
	}

	@Override
	public ArrayList<CaptainDTO> getAllCaptains() throws DAOException {
		return getAllCaptains(0, getTotalCaptains(), null, "");
	}

	@Override
	public ArrayList<CaptainDTO> getAllCaptains(int startRow, int endRow,
			ArrayList<Filter> criteria, String sortBy) throws DAOException {
		ObjectifyService.ofy().load();
		Query<CaptainDTO> command = ofy().load().type(CaptainDTO.class)
				.limit(endRow);
		command = addFilterToCommand(command, criteria);
		command = addSortsToCommand(command, sortBy);
		Iterator<CaptainDTO> itCaptains = command.iterator();
		return convertToInterface(itCaptains);
	}

	@Override
	public ArrayList<CaptainDTO> getAllCaptains(int startRow, int endRow,
			String sortBy) throws DAOException {
		ObjectifyService.ofy().load();
		Query<CaptainDTO> command = ofy().load().type(CaptainDTO.class)
				.limit(endRow);
		command = addSortsToCommand(command, sortBy);
		Iterator<CaptainDTO> itCaptains = command.iterator();
		return convertToInterface(itCaptains);
	}

	@Override
	public CaptainDTO getCaptain(Long id) throws DAOException {
		return ofy().load().type(CaptainDTO.class).id(id).now();
	}

	@Override
	public ArrayList<CaptainDTO> getCaptainsForUser(UserDTO user)
			throws DAOException {
		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("ownerId");
		filter.setValue(String.valueOf(user.getId()));
		ArrayList<Filter> criteria = new ArrayList<Filter>();
		criteria.add(filter);
		
		ObjectifyService.ofy().load();
		Query<CaptainDTO> command = ofy().load().type(CaptainDTO.class);
		command = addFilterToCommand(command, criteria);

		Iterator<CaptainDTO> itCaptains = command.iterator();
		return convertToInterface(itCaptains);
	}

	@Override
	public int getTotalCaptains() throws DAOException {
		ObjectifyService.ofy().load();
		return ofy().load().type(CaptainDTO.class).count();
	}

	@Override
	public int getTotalCaptainsWithFilter(ArrayList<Filter> criteria)
			throws DAOException {
		ObjectifyService.ofy().load();
		Query<CaptainDTO> command = ofy().load().type(CaptainDTO.class);
		command = addFilterToCommand(command, criteria);
		return command.count();
	}

	@Override
	public boolean removeCaptain(CaptainDTO captain) throws DAOException {
		ObjectifyService.ofy().load();
		ofy().delete().type(CaptainDTO.class).id(captain.getId());
		return true;
	}

	@Override
	public CaptainDTO updateCaptain(CaptainDTO captain) throws DAOException {
		Key<CaptainDTO> captainId = ofy().save().entity(captain).now();
		captain.setId(captainId.getId());
		return captain;
	}

	@Override
	public boolean captainNameExists(String name) throws DAOException {
		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("name");
		filter.setValue(name);
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);
		
		if (getTotalCaptainsWithFilter(filterAll) > 0) {
			return true;
		}
		return false;
		
	}

}
