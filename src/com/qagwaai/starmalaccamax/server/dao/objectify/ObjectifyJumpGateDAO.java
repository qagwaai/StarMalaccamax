package com.qagwaai.starmalaccamax.server.dao.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.JumpGateDAO;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.JumpGate;
import com.qagwaai.starmalaccamax.shared.model.JumpGateDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;

public class ObjectifyJumpGateDAO implements JumpGateDAO {
	/**
	 * 
	 * @param command
	 *            the command to add filters to
	 * @param criteria
	 *            the to add to the command
	 * @return the filtered command
	 */
	private Query<JumpGateDTO> addFilterToCommand(Query<JumpGateDTO> command,
			final ArrayList<Filter> criteria) {
		if (criteria == null) {
			return command;
		}
		for (Filter criterion : criteria) {
			if (criterion instanceof SimpleFilterItem) {
				SimpleFilterItem item = (SimpleFilterItem) criterion;
				command = command.filter(item.getField(), item.getValue());
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
	private Query<JumpGateDTO> addSortsToCommand(
			Query<JumpGateDTO> command, final String fullSort) {
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
     * @param itJumpGates
     *            the JumpGates to convert
     * @return the converted JumpGates
     */
    private ArrayList<JumpGateDTO> convertToInterface(final Iterator<JumpGateDTO> itJumpGates) {
        ArrayList<JumpGateDTO> list = new ArrayList<JumpGateDTO>();

        while (itJumpGates.hasNext()) {
            list.add(itJumpGates.next());
        }
        return list;
    }
	@Override
	public JumpGateDTO addJumpGate(JumpGateDTO newJumpGate) throws DAOException {
		Key<JumpGateDTO> jgId = ofy().save().entity(newJumpGate).now();
		newJumpGate.setId(jgId.getId());
		return newJumpGate;
	}

	@Override
	public ArrayList<JumpGateDTO> bulkAddJumpGate(
			ArrayList<JumpGateDTO> newJumpGates) throws DAOException {
        for (JumpGate jumpGate : newJumpGates) {
            addJumpGate((JumpGateDTO) jumpGate);
        }

        return newJumpGates;
	}

	@Override
	public boolean deleteAllJumpGates() throws DAOException {
		List<Key<JumpGateDTO>> keys = ofy().load().type(JumpGateDTO.class).keys().list();
		ofy().delete().keys(keys);
		return true;
	}

	@Override
	public ArrayList<JumpGateDTO> getAllJumpGates() throws DAOException {
		return getAllJumpGates(0, getTotalJumpGates(), null, "");
	}

	@Override
	public ArrayList<JumpGateDTO> getAllJumpGates(int startRow, int endRow,
			ArrayList<Filter> criteria, String sortBy) throws DAOException {
		ObjectifyService.ofy().load();
		Query<JumpGateDTO> command = ofy().load().type(JumpGateDTO.class)
				.limit(endRow);
		command = addFilterToCommand(command, criteria);
		command = addSortsToCommand(command, sortBy);
		Iterator<JumpGateDTO> itJumpGates = command.iterator();
		return convertToInterface(itJumpGates);
	}

	@Override
	public ArrayList<JumpGateDTO> getAllJumpGates(int startRow, int endRow,
			String sortBy) throws DAOException {
		ObjectifyService.ofy().load();
		Query<JumpGateDTO> command = ofy().load().type(JumpGateDTO.class)
				.limit(endRow);
		command = addSortsToCommand(command, sortBy);
		Iterator<JumpGateDTO> itJumpGates = command.iterator();
		return convertToInterface(itJumpGates);
	}

	@Override
	public JumpGateDTO getJumpGate(Long id) throws DAOException {
		return ofy().load().type(JumpGateDTO.class).id(id).now();
	}

	@Override
	public int getTotalJumpGates() throws DAOException {
		ObjectifyService.ofy().load();
		return ofy().load().type(JumpGateDTO.class).count();
	}

	@Override
	public int getTotalJumpGatesWithFilter(ArrayList<Filter> criteria)
			throws DAOException {
		ObjectifyService.ofy().load();
		Query<JumpGateDTO> command = ofy().load().type(JumpGateDTO.class);
		command = addFilterToCommand(command, criteria);
		return command.count();
	}

	@Override
	public boolean removeJumpGate(JumpGateDTO jumpGate) throws DAOException {
		ObjectifyService.ofy().load();
		ofy().delete().type(JumpGateDTO.class).id(jumpGate.getId());
		return true;
	}

	@Override
	public JumpGateDTO updateJumpGate(JumpGateDTO jumpGate) throws DAOException {
		Key<JumpGateDTO> jumpGateId = ofy().save().entity(jumpGate).now();
		jumpGate.setId(jumpGateId.getId());
		return jumpGate;
	}

}
