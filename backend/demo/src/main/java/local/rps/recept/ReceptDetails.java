package local.rps.recept;

import java.util.List;

import local.rps.grupa_jela_recepta.GrupaJelaRecepta;
import local.rps.komentar.Komentar;
import local.rps.korisnik.Korisnik;
import local.rps.postupak_pripreme.PostupakPripreme;
import local.rps.sastojak_recepta.SastojakRecepta;
import local.rps.tag_recepta.TagRecepta;

public class ReceptDetails {
	private int id;
	private String naziv;
	private int vrijemePripreme;
	private int brojSvidjanja;
	private String fotografija;
	private int brojPorcija;
	private Korisnik korisnik;
	private java.sql.Timestamp datumObjave;
	private String tezinaPripreme;
	private List<SastojakRecepta> sastojci;
	private List<TagRecepta> tagovi;
	private List<PostupakPripreme> postupciPripreme;
	private List<GrupaJelaRecepta> grupeJela;
	private List<Komentar> komentari;
	
	public ReceptDetails(Recept r) {
		super();
		this.id = r.getId();
		this.naziv = r.getNaziv();
		this.vrijemePripreme = r.getVrijemePripreme();
		this.brojSvidjanja = 0;
		this.fotografija = r.getFotografija();
		this.brojPorcija = r.getBrojPorcija();
		this.korisnik = r.getKorisnik();
		this.datumObjave = r.getDatumObjave();
		this.tezinaPripreme = r.getTezinaPripreme();
		this.sastojci = null;
		this.tagovi = null;
		this.postupciPripreme = null;
		this.grupeJela = null;
		this.komentari = null;
	}

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

	public int getBrojSvidjanja() {
		return brojSvidjanja;
	}

	public void setBrojSvidjanja(int brojSvidjanja) {
		this.brojSvidjanja = brojSvidjanja;
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

	public List<SastojakRecepta> getSastojci() {
		return sastojci;
	}

	public void setSastojci(List<SastojakRecepta> sastojci) {
		this.sastojci = sastojci;
	}

	public List<TagRecepta> getTagovi() {
		return tagovi;
	}

	public void setTagovi(List<TagRecepta> tagovi) {
		this.tagovi = tagovi;
	}

	public List<PostupakPripreme> getPostupciPripreme() {
		return postupciPripreme;
	}

	public void setPostupciPripreme(List<PostupakPripreme> postupciPripreme) {
		this.postupciPripreme = postupciPripreme;
	}

	public List<Komentar> getKomentari() {
		return komentari;
	}

	public void setKomentari(List<Komentar> komentari) {
		this.komentari = komentari;
	}

	public List<GrupaJelaRecepta> getGrupeJela() {
		return grupeJela;
	}

	public void setGrupeJela(List<GrupaJelaRecepta> grupeJela) {
		this.grupeJela = grupeJela;
	}
}
