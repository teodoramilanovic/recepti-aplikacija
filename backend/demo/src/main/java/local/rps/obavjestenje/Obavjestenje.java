package local.rps.obavjestenje;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import local.rps.korisnik.Korisnik;

@Entity
public class Obavjestenje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String opis;
	@Column(name = "datum")
	private java.sql.Timestamp datum;
	@ManyToOne
	private Korisnik aktivniKorisnik;
	@ManyToOne
	private Korisnik pasivniKorisnik;
	
	public Obavjestenje(int id, String opis, java.sql.Timestamp datum, Korisnik aktivniKorisnik, Korisnik pasivniKorisnik) {
		super();
		this.id = id;
		this.opis = opis;
		this.datum = datum;
		this.aktivniKorisnik = aktivniKorisnik;
		this.pasivniKorisnik = pasivniKorisnik;
	}
	
	public Obavjestenje() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Korisnik getAktivniKorisnik() {
		return aktivniKorisnik;
	}

	public void setAktivniKorisnik(Korisnik aktivniKorisnik) {
		this.aktivniKorisnik = aktivniKorisnik;
	}

	public Korisnik getPasivniKorisnik() {
		return pasivniKorisnik;
	}

	public void setPasivniKorisnik(Korisnik pasivniKorisnik) {
		this.pasivniKorisnik = pasivniKorisnik;
	}

	public java.sql.Timestamp getDatum() {
		return datum;
	}

	public void setDatum(java.sql.Timestamp datum) {
		this.datum = datum;
	}

	
	
}