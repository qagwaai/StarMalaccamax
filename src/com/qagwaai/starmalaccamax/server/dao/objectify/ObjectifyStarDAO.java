package com.qagwaai.starmalaccamax.server.dao.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.StarDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;
import com.qagwaai.starmalaccamax.shared.model.Star;
import com.qagwaai.starmalaccamax.shared.model.StarDTO;

public class ObjectifyStarDAO implements StarDAO {
	/**
	 * 
	 * @param command
	 *            the command to add filters to
	 * @param criteria
	 *            the to add to the command
	 * @return the filtered command
	 */
	private Query<StarDTO> addFilterToCommand(Query<StarDTO> command,
			final ArrayList<Filter> criteria) {
		if (criteria == null) {
			return command;
		}
		for (Filter criterion : criteria) {
			if (criterion instanceof SimpleFilterItem) {
				SimpleFilterItem item = (SimpleFilterItem) criterion;
				if (item.getField().equals("solarSystemId")) {
					command = command.filter(item.getField(), Long.valueOf(item.getValue()).longValue());
				} else if ( item.getField().equals("id")) {
					command = (Query<StarDTO>) command.filterKey(Key.create(StarDTO.class, Long.valueOf(item.getValue()).longValue()));
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
	private Query<StarDTO> addSortsToCommand(
			Query<StarDTO> command, final String fullSort) {
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
     * @param itStar
     *            the Star to convert
     * @return the converted Star
     */
    private ArrayList<StarDTO> convertToInterface(final Iterator<StarDTO> itStar) {
        ArrayList<StarDTO> list = new ArrayList<StarDTO>();

        while (itStar.hasNext()) {
            list.add(itStar.next());
        }
        return list;
    }
	@Override
	public StarDTO addStar(StarDTO newStar) throws DAOException {
		Key<StarDTO> pId = ofy().save().entity(newStar).now();
		newStar.setId(pId.getId());
		return newStar;
	}

	@Override
	public ArrayList<StarDTO> bulkAddStar(ArrayList<StarDTO> newStars)
			throws DAOException {
        for (Star star : newStars) {
            addStar((StarDTO) star);
        }

        return newStars;
	}

	@Override
	public boolean deleteAllStars() throws DAOException {
		List<Key<StarDTO>> keys = ofy().load().type(StarDTO.class).keys().list();
		ofy().delete().keys(keys);
		return true;
	}

	@Override
	public ArrayList<StarDTO> getAllStars() throws DAOException {
		return getAllStars(0, getTotalStars(), null, "");
	}

	@Override
	public ArrayList<StarDTO> getAllStars(int startRow, int endRow,
			ArrayList<Filter> criteria, String sortBy) throws DAOException {
		Query<StarDTO> command = ofy().load().type(StarDTO.class)
				.limit(endRow);
		command = addFilterToCommand(command, criteria);
		command = addSortsToCommand(command, sortBy);
		command.chunkAll();
		Iterator<StarDTO> itStars = command.iterator();
		return convertToInterface(itStars);
	}

	@Override
	public ArrayList<StarDTO> getAllStars(int startRow, int endRow,
			String sortBy) throws DAOException {
		Query<StarDTO> command = ofy().load().type(StarDTO.class)
				.limit(endRow);
		command = addSortsToCommand(command, sortBy);
		Iterator<StarDTO> itStar = command.iterator();
		return convertToInterface(itStar);
	}

	@Override
	public StarDTO getStar(Long id) throws DAOException {
		return ofy().load().type(StarDTO.class).id(id).now();
	}

	@Override
	public ArrayList<StarDTO> getStarsForSolarSystem(Long id)
			throws DAOException {
		Query<StarDTO> command = ofy().load().type(StarDTO.class).limit(2000);
		command = command.filter("solarSystemId", id);
		command = addSortsToCommand(command, "solarSystemId");
		Iterator<StarDTO> itStar = command.iterator();
		ArrayList<StarDTO> found = convertToInterface(itStar);
		return found;
	}

	@Override
	public int getTotalStars() throws DAOException {
		return ofy().load().type(StarDTO.class).count();
	}

	@Override
	public int getTotalStarsWithFilter(ArrayList<Filter> criteria)
			throws DAOException {
		Query<StarDTO> command = ofy().load().type(StarDTO.class);
		command = addFilterToCommand(command, criteria);
		return command.count();
	}

	@Override
	public boolean removeStar(StarDTO star) throws DAOException {
		ofy().delete().type(StarDTO.class).id(star.getId());
		return true;
	}

	@Override
	public StarDTO updateStar(StarDTO star) throws DAOException {
		Key<StarDTO> jumpGateId = ofy().save().entity(star).now();
		star.setId(jumpGateId.getId());
		return star;
	}

}
