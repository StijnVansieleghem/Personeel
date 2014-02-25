package be.vdab.services;

import java.math.BigDecimal;

import be.vdab.dao.WerknemerDAO;
import be.vdab.entities.Werknemer;

public class WerknemerService {
	private final WerknemerDAO werknemerDAO = new WerknemerDAO();

	public Werknemer read(long werknemerID) {
		return werknemerDAO.read(werknemerID);
	}

	public Werknemer opslag(long id, BigDecimal bedrag) {
		werknemerDAO.beginTransaction();
		Werknemer werknemer = werknemerDAO.opslag(id, bedrag);
		werknemerDAO.commit();
		return werknemer;
	}
	
	public Werknemer findPresident(){
		return werknemerDAO.findPresident();
	}
}
