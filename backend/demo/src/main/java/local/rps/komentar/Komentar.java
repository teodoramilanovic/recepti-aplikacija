package local.rps.komentar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import local.rps.korisnik.Korisnik;
import local.rps.recept.Recept;

@Entity
public class Komentar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String tekst;
	@Column(name = "datum")
	private java.sql.Timestamp datumObjave;
	@ManyToOne
	private Korisnik korisnik;
	@ManyToOne
	private Recept recept;
	
	public Komentar(int id, String tekst, java.sql.Timestamp datumObjave, Korisnik korisnik, Recept recept) {
		super();
		this.id = id;
		this.tekst = tekst;
		this.datumObjave = datumObjave;
		this.korisnik = korisnik;
		this.recept = recept;
	}
	
	public Komentar() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public java.sql.Timestamp getDatumObjave() {
		return datumObjave;
	}

	public void setDatumObjave(java.sql.Timestamp datum) {
		this.datumObjave = datum;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Recept getRecept() {
		return recept;
	}

	public void setRecept(Recept recept) {
		this.recept = recept;
	}
	
	
}
