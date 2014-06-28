package com.qagwaai.starmalaccamax.server.dao.objectify;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.server.dao.DAOException;
import com.qagwaai.starmalaccamax.server.dao.JobDAO;
import com.qagwaai.starmalaccamax.shared.model.Filter;
import com.qagwaai.starmalaccamax.shared.model.JobDTO;

public class ObjectifyJobDAO implements JobDAO {

	@Override
	public JobDTO addJob(JobDTO newJob) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<JobDTO> bulkAddJob(ArrayList<JobDTO> newJobs)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAllJobs() throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<JobDTO> getAllJobs() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<JobDTO> getAllJobs(int startRow, int endRow,
			ArrayList<Filter> criteria, String sortBy) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<JobDTO> getAllJobs(int startRow, int endRow, String sortBy)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobDTO getJob(Long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalJobs() throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalJobsWithFilter(ArrayList<Filter> criteria)
			throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeJob(JobDTO job) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JobDTO updateJob(JobDTO job) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
