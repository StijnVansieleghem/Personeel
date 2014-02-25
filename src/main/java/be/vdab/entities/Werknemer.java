package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import be.vdab.valueobjects.Email;

@Entity
@Table(name = "werknemers")
public class Werknemer implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	private String familienaam;
	private String voornaam;
	@Embedded
	private Email email;
	private BigDecimal salaris;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jobtitelid")
	private Jobtitel jobtitel;

	public Jobtitel getJobtitel() {
		return jobtitel;
	}

	public void setJobtitel(Jobtitel jobtitel) {
		this.jobtitel = jobtitel;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chefid")
	private Werknemer chef;

	public Werknemer getChef() {
		return chef;
	}

	public void setChef(Werknemer werknemer) {
		if (this.chef != null && this.chef.getWerknemers().contains(this)) {
			this.chef.removeWerknemer(this);
		}
		this.chef = werknemer;
		if (werknemer != null && !werknemer.getWerknemers().contains(this)) {
			werknemer.addWerknemer(this);
		}
	}

	@OneToMany(mappedBy = "chef")
	private Set<Werknemer> werknemers;

	public Set<Werknemer> getWerknemers() {
		return werknemers;
	}

	public void setWerknemers(Set<Werknemer> werknemers) {
		this.werknemers = werknemers;
	}

	public void addWerknemer(Werknemer werknemer) {
		werknemers.add(werknemer);
		if (werknemer.getChef() != this) {
			werknemer.setChef(this);
		}
	}

	public void removeWerknemer(Werknemer werknemer) {
		werknemers.remove(werknemer);
		if (werknemer.getChef() == this) {
			werknemer.setChef(null);
		}
	}

	protected Werknemer() {

	}

	public Werknemer(String familienaam, String voornaam, Email email,
			BigDecimal salaris) {
		setFamilienaam(familienaam);
		setVoornaam(voornaam);
		setEmail(email);
		setSalaris(salaris);
		werknemers = new HashSet<Werknemer>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	
	public String getNaam() {
		return voornaam + ' ' + familienaam;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public BigDecimal getSalaris() {
		return salaris;
	}

	public void setSalaris(BigDecimal salaris) {
		this.salaris = salaris;
	}

	public void opslag(BigDecimal bedrag) {
		bedrag = bedrag.add(getSalaris());
		if (getSalaris().compareTo(bedrag) < 0) {
			setSalaris(bedrag);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Werknemer)) {
			return false;
		}
		return ((Werknemer) obj).id == this.id;
	}

	@Override
	public int hashCode() {
		return Long.toString(id).hashCode();
	}
}
