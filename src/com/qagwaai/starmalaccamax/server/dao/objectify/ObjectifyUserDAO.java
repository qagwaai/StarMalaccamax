package com.qagwaai.starmalaccamax.server.dao.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.code.twig.ObjectDatastore;
import com.google.code.twig.annotation.AnnotationObjectDatastore;
import com.google.common.collect.Lists;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.UserDAO;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

public class ObjectifyUserDAO implements UserDAO {

	@Override
	public UserDTO addUser(UserDTO newUser) throws DAOException {

		 Key<UserDTO> updatedUser = ofy().save().entity(newUser).now();
		 newUser.setId(updatedUser.getId());
		 return newUser;
	}

	@Override
	public boolean deleteAllUsers() throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserDTO findUserByAppworksId(String appworksUserId)
			throws DAOException {
		
		return ofy().load().type(UserDTO.class).id(appworksUserId).now();
	}

	@Override
	public UserDTO findUserByEmail(String email) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UserDTO> getAllUsers() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UserDTO> getAllUsers(int startRow, int endRow)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getUser(Long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeUser(UserDTO user) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserDTO updateUser(UserDTO user) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
