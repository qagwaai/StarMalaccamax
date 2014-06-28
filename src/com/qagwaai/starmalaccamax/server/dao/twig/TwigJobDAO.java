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
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Transaction;
import com.google.code.twig.FindCommand.RootFindCommand;
import com.google.code.twig.ObjectDatastore;
import com.google.code.twig.annotation.AnnotationObjectDatastore;
import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.JobDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.Job;
import com.qagwaai.starmalaccamax.shared.model.JobDTO;
import com.qagwaai.starmalaccamax.shared.model.SimpleFilterItem;

/**
 * @author pgirard
 * 
 */
public final class TwigJobDAO implements JobDAO {

    /**
	 * 
	 */
    public TwigJobDAO() {

    }

    /**
     * 
     * @param command
     *            the command to add filters to
     * @param criteria
     *            the to add to the command
     * @return the filtered command
     */
    private RootFindCommand<JobDTO> addFilterToCommand(final RootFindCommand<JobDTO> command,
        final ArrayList<Filter> criteria) {
        for (Filter criterion : criteria) {
            if (criterion instanceof SimpleFilterItem) {
                SimpleFilterItem item = (SimpleFilterItem) criterion;
                if (!item.getField().equals("name")) {
                    // this is using the equal operator - any text searching
                    // will be impled below
                    if (item.getField().equals("orbit") || item.getField().equals("size")
                        || item.getField().equals("atmosphere") || item.getField().equals("dockQuality")
                        || item.getField().equals("government") || item.getField().equals("lawLevel")
                        || item.getField().equals("techLevel")) {
                        command.addFilter(item.getField(), FilterOperator.EQUAL, Integer.valueOf(item.getValue()));
                    } else if (item.getField().equals("hydrographics")) {
                        command.addFilter(item.getField(), FilterOperator.EQUAL, Double.valueOf(item.getValue()));
                    } else {
                        command.addFilter(item.getField(), FilterOperator.EQUAL, item.getValue());
                    }
                }
            }
        }
        return command;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public JobDTO addJob(final JobDTO newJob) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        Transaction trans = datastore.beginTransaction();
        Key key = datastore.store(newJob);
        newJob.setId(key.getId());
        trans.commit();

        return newJob;
    }

    /**
     * 
     * @param command
     *            the command to add sort criteria to
     * @param fullSort
     *            the full sort string ("name, -field")
     * @return the augmented command
     */
    private RootFindCommand<JobDTO> addSortsToCommand(final RootFindCommand<JobDTO> command, final String fullSort) {
        if (fullSort == null) {
            return command;
        }
        if (fullSort.equals("")) {
            return command;
        }
        String[] sortFields = fullSort.split(",");

        for (int i = 0; i < sortFields.length; i++) {
            if (sortFields[i].startsWith("-")) {
                command.addSort(sortFields[i].substring(1, sortFields[i].length()), SortDirection.DESCENDING);
            } else {
                command.addSort(sortFields[i], SortDirection.ASCENDING);
            }
        }

        return command;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<JobDTO> bulkAddJob(final ArrayList<JobDTO> newJobs) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        for (Job job : newJobs) {
            datastore.storeOrUpdate((JobDTO) job);
        }

        return newJobs;
    }

    /**
     * 
     * @param itJobs
     *            the Jobs to convert
     * @return the converted Jobs
     */
    private ArrayList<JobDTO> convertToInterface(final Iterator<JobDTO> itJobs) {
        ArrayList<JobDTO> list = new ArrayList<JobDTO>();

        while (itJobs.hasNext()) {
            list.add(itJobs.next());
        }
        return list;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAllJobs() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.deleteAll(JobDTO.class);

        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<JobDTO> getAllJobs() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        Iterator<JobDTO> itJobs = datastore.find().type(JobDTO.class).now();

        return convertToInterface(itJobs);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<JobDTO> getAllJobs(final int startRow, final int endRow, final ArrayList<Filter> criteria,
        final String sortBy) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<JobDTO> command =
            datastore.find().type(JobDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addFilterToCommand(command, criteria);
        command = addSortsToCommand(command, sortBy);
        Iterator<JobDTO> itJobs = command.now();
        return convertToInterface(itJobs);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ArrayList<JobDTO> getAllJobs(final int startRow, final int endRow, final String sortBy) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<JobDTO> command =
            datastore.find().type(JobDTO.class).startFrom(startRow).fetchMaximum(endRow - startRow);
        command = addSortsToCommand(command, sortBy);
        Iterator<JobDTO> itJobs = command.now();

        return convertToInterface(itJobs);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public JobDTO getJob(final Long id) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        return datastore.load(JobDTO.class, id);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalJobs() throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();

        return datastore.find().type(JobDTO.class).returnCount().now();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getTotalJobsWithFilter(final ArrayList<Filter> criteria) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        RootFindCommand<JobDTO> command = datastore.find().type(JobDTO.class);
        command = addFilterToCommand(command, criteria);

        Iterator<JobDTO> itJobs = command.now();
        return convertToInterface(itJobs).size() - 1;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean removeJob(final JobDTO job) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.associate(job);
        datastore.delete(job);
        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public JobDTO updateJob(final JobDTO job) throws DAOException {
        ObjectDatastore datastore = new AnnotationObjectDatastore();
        datastore.storeOrUpdate((JobDTO) job);

        return job;
    }
}
