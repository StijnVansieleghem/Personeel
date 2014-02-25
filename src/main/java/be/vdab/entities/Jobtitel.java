package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "jobtitels")
public class Jobtitel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	private String naam;

	@OneToMany(mappedBy = "jobtitel")
	private Set<Werknemer> werknemers;

	public Set<Werknemer> getWerknemers() {
		return Collections.unmodifiableSet(werknemers);
	}

	public void addWerknemer(Werknemer werknemer) {
		werknemers.add(werknemer);
	}

	public void removeWerknemer(Werknemer werknemer) {
		werknemers.remove(werknemer);
	}

	protected Jobtitel() {
	}

	public Jobtitel(long id, String naam) {
		setId(id);
		setNaam(naam);
	}

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public long getPresident() {
		for (Werknemer werknemer : werknemers) {
			if (String.valueOf(werknemer.getId()).isEmpty()) {
				return werknemer.getId();
			}
		}
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Jobtitel)) {
			return false;
		}
		return ((Jobtitel) obj).id == this.id;
	}

	@Override
	public int hashCode() {
		return naam.hashCode();
	}
}