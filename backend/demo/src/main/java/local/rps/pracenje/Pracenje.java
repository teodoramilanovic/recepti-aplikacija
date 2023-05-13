package local.rps.pracenje;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import local.rps.korisnik.Korisnik;

@Entity
public class Pracenje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Korisnik aktivniKorisnik;
	@ManyToOne
	private Korisnik pasivniKorisnik;
	
	public Pracenje(int id, Korisnik aktivniKorisnik, Korisnik pasivniKorisnik) {
		super();
		this.id = id;
		this.aktivniKorisnik = aktivniKorisnik;
		this.pasivniKorisnik = pasivniKorisnik;
	}
	
	public Pracenje() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
}