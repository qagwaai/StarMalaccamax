package com.qagwaai.starmalaccamax.server.dao.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.MarketDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.Market;
import com.qagwaai.starmalaccamax.shared.model.MarketDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;

public class ObjectifyMarketDAO implements MarketDAO {

	/**
	 * 
	 * @param command
	 *            the command to add filters to
	 * @param criteria
	 *            the to add to the command
	 * @return the filtered command
	 */
	private Query<MarketDTO> addFilterToCommand(Query<MarketDTO> command,
			final ArrayList<Filter> criteria) {
		if (criteria == null) {
			return command;
		}
		for (Filter criterion : criteria) {
			if (criterion instanceof SimpleFilterItem) {
				SimpleFilterItem item = (SimpleFilterItem) criterion;
				if (item.getField().equals("planetId")) {
					command = command.filter(item.getField(), Integer.valueOf(item.getValue()).intValue());
				} else if ( item.getField().equals("id")) {
					command = (Query<MarketDTO>) command.filterKey(Key.create(MarketDTO.class, Long.valueOf(item.getValue()).longValue()));
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
	private Query<MarketDTO> addSortsToCommand(
			Query<MarketDTO> command, final String fullSort) {
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
     * @param itMarket
     *            the Market to convert
     * @return the converted Market
     */
    private ArrayList<MarketDTO> convertToInterface(final Iterator<MarketDTO> itMarket) {
        ArrayList<MarketDTO> list = new ArrayList<MarketDTO>();

        while (itMarket.hasNext()) {
            list.add(itMarket.next());
        }
        return list;
    }
    
	@Override
	public MarketDTO addMarket(MarketDTO newMarket) throws DAOException {
		Key<MarketDTO> mId = ofy().save().entity(newMarket).now();
		newMarket.setId(mId.getId());
		return newMarket;
	}

	@Override
	public ArrayList<MarketDTO> bulkAddMarket(ArrayList<MarketDTO> newMarkets)
			throws DAOException {
        for (Market market : newMarkets) {
            addMarket((MarketDTO) market);
        }

        return newMarkets;
	}

	@Override
	public boolean deleteAllMarkets() throws DAOException {
		List<Key<MarketDTO>> keys = ofy().load().type(MarketDTO.class).keys().list();
		ofy().delete().keys(keys);
		return true;
	}

	@Override
	public ArrayList<MarketDTO> getAllMarkets() throws DAOException {
		return getAllMarkets(0, getTotalMarkets(), null, "");
	}

	@Override
	public ArrayList<MarketDTO> getAllMarkets(int startRow, int endRow,
			ArrayList<Filter> criteria, String sortBy) throws DAOException {
		Query<MarketDTO> command = ofy().load().type(MarketDTO.class)
				.limit(endRow);
		command = addFilterToCommand(command, criteria);
		command = addSortsToCommand(command, sortBy);
		command.chunkAll();
		Iterator<MarketDTO> itMarkets = command.iterator();
		return convertToInterface(itMarkets);
	}

	@Override
	public ArrayList<MarketDTO> getAllMarkets(int startRow, int endRow,
			String sortBy) throws DAOException {
		Query<MarketDTO> command = ofy().load().type(MarketDTO.class)
				.limit(endRow);
		command = addSortsToCommand(command, sortBy);
		Iterator<MarketDTO> itMarket = command.iterator();
		return convertToInterface(itMarket);
	}

	@Override
	public MarketDTO getMarket(Long id) throws DAOException {
		return ofy().load().type(MarketDTO.class).id(id).now();
	}

	@Override
	public MarketDTO getMarketForPlanet(PlanetDTO planet) throws DAOException {
		SimpleFilterItem filter = new SimpleFilterItem();
		filter.setField("planetId");
		filter.setValue(String.valueOf(planet.getId()));
		ArrayList<Filter> filterAll = new ArrayList<Filter>();
		filterAll.add(filter);
		
		ArrayList<MarketDTO> found = getAllMarkets(0, 2000, filterAll, "planetId");

        if (found.size() == 1) {
            return found.get(0);
        } else if (found.size() == 0) {
            return null;
        } else {
            throw new DAOException("Found " + found.size() + " markets for planet " + planet.getId());
        }
	}

	@Override
	public ArrayList<MarketDTO> getMarketsForPlanets(ArrayList<Long> planetIds) throws DAOException {
		
		Query<MarketDTO> command = ofy().load().type(MarketDTO.class).limit(2000);
		command = command.filter("planetId in", planetIds);
		command = addSortsToCommand(command, "planetId");
		command.chunkAll();
		Iterator<MarketDTO> itMarkets = command.iterator();
		return convertToInterface(itMarkets);
	}

	@Override
	public int getTotalMarkets() throws DAOException {
		return ofy().load().type(MarketDTO.class).count();
	}

	@Override
	public int getTotalMarketsWithFilter(ArrayList<Filter> criteria)
			throws DAOException {
		Query<MarketDTO> command = ofy().load().type(MarketDTO.class);
		command = addFilterToCommand(command, criteria);
		return command.count();
	}

	@Override
	public boolean removeMarket(MarketDTO market) throws DAOException {
		ofy().delete().type(MarketDTO.class).id(market.getId());
		return true;
	}

	@Override
	public MarketDTO updateMarket(MarketDTO market) throws DAOException {
		Key<MarketDTO> jumpGateId = ofy().save().entity(market).now();
		market.setId(jumpGateId.getId());
		return market;
	}

}
