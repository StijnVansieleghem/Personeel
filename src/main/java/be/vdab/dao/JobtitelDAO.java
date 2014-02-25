package be.vdab.dao;

import javax.persistence.TypedQuery;

import be.vdab.entities.Jobtitel;

public class JobtitelDAO extends AbstractDAO {
	public Jobtitel read(long id) {
		return getEntityManager().find(Jobtitel.class, id);
	}

	public Iterable<Jobtitel> findAll() {
		TypedQuery<Jobtitel> query = getEntityManager().createNamedQuery(
				"Jobtitel.findAll", Jobtitel.class);
		return query.getResultList();
	}

}
