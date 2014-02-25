package be.vdab.dao;

import java.math.BigDecimal;

import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import be.vdab.entities.Werknemer;

public class WerknemerDAO extends AbstractDAO {
	public Werknemer read(long id) {
		return getEntityManager().find(Werknemer.class, id);
	}

	public Werknemer readWithLock(long id) {
		return getEntityManager().find(Werknemer.class, id,
				LockModeType.PESSIMISTIC_WRITE);
	}

	public Werknemer opslag(Long id, BigDecimal bedrag) {
		Werknemer werknemer = readWithLock(id);
		werknemer.opslag(bedrag);
		return werknemer;
	}
	
	public Werknemer findPresident() {
		TypedQuery<Werknemer> query = getEntityManager().createNamedQuery(
				"Werknemer.findPresident", Werknemer.class);
		return query.getSingleResult();
	}
}
