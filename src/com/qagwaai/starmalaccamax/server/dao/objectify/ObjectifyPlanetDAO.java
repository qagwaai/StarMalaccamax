package com.qagwaai.starmalaccamax.server.dao.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.PlanetDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.Planet;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;

public class ObjectifyPlanetDAO implements PlanetDAO {
	/**
	 * 
	 * @param command
	 *            the command to add filters to
	 * @param criteria
	 *            the to add to the command
	 * @return the filtered command
	 */
	private Query<PlanetDTO> addFilterToCommand(Query<PlanetDTO> command,
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
					command = (Query<PlanetDTO>) command.filterKey(Key.create(PlanetDTO.class, Long.valueOf(item.getValue()).longValue()));
				}  else if ( item.getField().equals("isGasGiant")) {
					command = command.filter(item.getField(), Boolean.valueOf(item.getValue()).booleanValue());
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
	private Query<PlanetDTO> addSortsToCommand(
			Query<PlanetDTO> command, final String fullSort) {
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
     * @param itPlanet
     *            the Planet to convert
     * @return the converted Planet
     */
    private ArrayList<PlanetDTO> convertToInterface(final Iterator<PlanetDTO> itPlanet) {
        ArrayList<PlanetDTO> list = new ArrayList<PlanetDTO>();

        while (itPlanet.hasNext()) {
            list.add(itPlanet.next());
        }
        return list;
    }
	@Override
	public PlanetDTO addPlanet(PlanetDTO newPlanet) throws DAOException {
		Key<PlanetDTO> pId = ofy().save().entity(newPlanet).now();
		newPlanet.setId(pId.getId());
		return newPlanet;
	}

	@Override
	public ArrayList<PlanetDTO> bulkAddPlanet(ArrayList<PlanetDTO> newPlanets)
			throws DAOException {
        for (Planet planet : newPlanets) {
            addPlanet((PlanetDTO) planet);
        }

        return newPlanets;
	}

	@Override
	public boolean deleteAllPlanets() throws DAOException {
		List<Key<PlanetDTO>> keys = ofy().load().type(PlanetDTO.class).keys().list();
		ofy().delete().keys(keys);
		return true;
	}

	@Override
	public ArrayList<PlanetDTO> getAllPlanets() throws DAOException {
		return getAllPlanets(0, getTotalPlanets(), null, "");
	}

	@Override
	public ArrayList<PlanetDTO> getAllPlanets(int startRow, int endRow,
			ArrayList<Filter> criteria, String sortBy) throws DAOException {
		Query<PlanetDTO> command = ofy().load().type(PlanetDTO.class)
				.limit(endRow);
		command = addFilterToCommand(command, criteria);
		command = addSortsToCommand(command, sortBy);
		command.chunkAll();
		Iterator<PlanetDTO> itPlanets = command.iterator();
		return convertToInterface(itPlanets);
	}

	@Override
	public ArrayList<PlanetDTO> getAllPlanets(int startRow, int endRow,
			String sortBy) throws DAOException {
		Query<PlanetDTO> command = ofy().load().type(PlanetDTO.class)
				.limit(endRow);
		command = addSortsToCommand(command, sortBy);
		Iterator<PlanetDTO> itPlanet = command.iterator();
		return convertToInterface(itPlanet);
	}

	@Override
	public ArrayList<PlanetDTO> getNonGasGiantPlanetsForSolarSystem(
			Long solarSystemId) throws DAOException {
		Query<PlanetDTO> command = ofy().load().type(PlanetDTO.class).limit(2000);
		command.filter("solarSystemId", solarSystemId);
		command.filter("isGasGiant", false);
		command.chunkAll();
		Iterator<PlanetDTO> itPlanets = command.iterator();
		return convertToInterface(itPlanets);
	}

	@Override
	public PlanetDTO getPlanet(Long id) throws DAOException {
		return ofy().load().type(PlanetDTO.class).id(id).now();
	}

	@Override
	public ArrayList<PlanetDTO> getPlanetsForSolarSystem(Long solarSystemId)
			throws DAOException {
		
		Query<PlanetDTO> command = ofy().load().type(PlanetDTO.class).limit(2000);
		command.filter("solarSystemId", solarSystemId);
		command.chunkAll();
		Iterator<PlanetDTO> itPlanets = command.iterator();
		return convertToInterface(itPlanets);
	}

	@Override
	public int getTotalPlanets() throws DAOException {
		return ofy().load().type(PlanetDTO.class).count();
	}

	@Override
	public int getTotalPlanetsWithFilter(ArrayList<Filter> criteria)
			throws DAOException {
		Query<PlanetDTO> command = ofy().load().type(PlanetDTO.class);
		command = addFilterToCommand(command, criteria);
		return command.count();
	}

	@Override
	public boolean removePlanet(PlanetDTO planet) throws DAOException {
		ofy().delete().type(PlanetDTO.class).id(planet.getId());
		return true;
	}

	@Override
	public PlanetDTO updatePlanet(PlanetDTO planet) throws DAOException {
		Key<PlanetDTO> jumpGateId = ofy().save().entity(planet).now();
		planet.setId(jumpGateId.getId());
		return planet;
	}

}
