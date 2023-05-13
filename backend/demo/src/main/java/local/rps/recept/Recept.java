package local.rps.recept;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import local.rps.korisnik.Korisnik;

@Entity
public class Recept {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String naziv;
	private int vrijemePripreme;
	private String fotografija;
	private int brojPorcija;
	@ManyToOne
	private Korisnik korisnik;
	@Column(name = "datum")
	private java.sql.Timestamp datumObjave;
	private String tezinaPripreme;
	
	public Recept(int id, String naziv, int vrijemePripreme, String fotografija,
			int brojPorcija, Korisnik korisnik, java.sql.Timestamp datumObjave, String tezinaPripreme) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.vrijemePripreme = vrijemePripreme;
		this.fotografija = fotografija;
		this.brojPorcija = brojPorcija;
		this.korisnik = korisnik;
		this.datumObjave = datumObjave;
		this.tezinaPripreme = tezinaPripreme;
	}
	
	public Recept() {}
	
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

	public int getVrijemePripreme() {
		return vrijemePripreme;
	}

	public void setVrijemePripreme(int vrijemePripreme) {
		this.vrijemePripreme = vrijemePripreme;
	}

	public String getFotografija() {
		return fotografija;
	}

	public void setFotografija(String fotografija) {
		this.fotografija = fotografija;
	}

	public int getBrojPorcija() {
		return brojPorcija;
	}

	public void setBrojPorcija(int brojPorcija) {
		this.brojPorcija = brojPorcija;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public java.sql.Timestamp getDatumObjave() {
		return datumObjave;
	}

	public void setDatumObjave(java.sql.Timestamp datumObjave) {
		this.datumObjave = datumObjave;
	}

	public String getTezinaPripreme() {
		return tezinaPripreme;
	}

	public void setTezinaPripreme(String tezinaPripreme) {
		this.tezinaPripreme = tezinaPripreme;
	}
	
	
	
}