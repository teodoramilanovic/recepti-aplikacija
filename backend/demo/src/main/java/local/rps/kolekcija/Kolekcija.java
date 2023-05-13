package local.rps.kolekcija;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import local.rps.korisnik.Korisnik;

@Entity
public class Kolekcija {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String naziv;
	@ManyToOne
	private Korisnik korisnik;
	
	public Kolekcija(int id, String naziv, Korisnik korisnik) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.korisnik = korisnik;
	}
	
	public Kolekcija() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	
}