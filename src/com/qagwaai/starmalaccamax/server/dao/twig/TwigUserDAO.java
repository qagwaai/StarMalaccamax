/**
 * DataNucleusUserDAO.java
 * Created by pgirard at 3:25:05 PM on Jul 23, 2010
 * in the com.qagwaai.starmalaccamax.server.dao.datanucleus package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.server.dao.twig;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Transaction;
import com.google.code.twig.ObjectDatastore;
import com.google.code.twig.annotation.AnnotationObjectDatastore;
import com.google.common.collect.Lists;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.UserDAO;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;

/**
 * @author pgirard
 * 
 */
public final class TwigUserDAO implements UserDAO {

    /**
	 * 
	 */
    public TwigUserDAO() {

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public UserDTO addUser(final UserDTO newUser) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        Transaction trans = datastore.beginTransaction();
        Key key = datastore.store(newUser);
        newUser.setId(key.getId());
        trans.commit();

        return newUser;
    }

    /**
     * 
     * @param itUsers
     *            the Users to convert
     * @return the converted Users
     */
    private ArrayList<UserDTO> convertToInterface(final Iterator<UserDTO> itUsers) {
        ArrayList<UserDTO> list = new ArrayList<UserDTO>();

        while (itUsers.hasNext()) {
            list.add(itUsers.next());
        }
        return list;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAllUsers() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.deleteAll(UserDTO.class);

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDTO findUserByAppworksId(final String appworksUserId) throws DAOException {

        ObjectDatastore datastore = new AnnotationObjectDatastore();

        Iterator<UserDTO> itUsers =
            datastore.find().type(UserDTO.class).addFilter("appengineUniqueId", FilterOperator.EQUAL, appworksUserId)
                .now();
        ArrayList<UserDTO> list = Lists.newArrayList(itUsers);
        if (list.size() > 1) {
            throw new DAOException("Unique search found duplicates");
        }

        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public UserDTO findUserByEmail(final String email) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        Iterator<UserDTO> itUsers =
            datastore.find().type(UserDTO.class).addFilter("email", FilterOperator.EQUAL, email).now();
        ArrayList<UserDTO> list = Lists.newArrayList(itUsers);
        if (list.size() > 1) {
            throw new DAOException("Unique search found duplicates");
        }

        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<UserDTO> getAllUsers() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        Iterator<UserDTO> itUsers = datastore.find().type(UserDTO.class).now();

        return convertToInterface(itUsers);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<UserDTO> getAllUsers(final int startRow, final int endRow) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        Iterator<UserDTO> itUsers =
            datastore.find().type(UserDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow).now();

        return convertToInterface(itUsers);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public UserDTO getUser(final Long id) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        return datastore.load(UserDTO.class, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean removeUser(final UserDTO user) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.associate(user);
        datastore.delete(user);
        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public UserDTO updateUser(final UserDTO user) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.storeOrUpdate(user);

        return user;
    }

}
