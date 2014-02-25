package be.vdab.services;

import be.vdab.dao.JobtitelDAO;
import be.vdab.entities.Jobtitel;

public class JobtitelService {
	private final JobtitelDAO jobtitelDAO = new JobtitelDAO();

	public Iterable<Jobtitel> findAll() {
		return jobtitelDAO.findAll();
	}
	

	public Jobtitel read(long jobtitelID) {
		return jobtitelDAO.read(jobtitelID);
	}
}