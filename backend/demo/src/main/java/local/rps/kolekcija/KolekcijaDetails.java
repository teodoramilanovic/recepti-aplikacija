package local.rps.kolekcija;

import java.util.List;

import local.rps.omiljen_recept.OmiljenRecept;

public class KolekcijaDetails {
	private int id;
	private String naziv;
	private List<OmiljenRecept> recepti;
	
	public KolekcijaDetails(Kolekcija k) {
		super();
		this.id = k.getId();
		this.naziv = k.getNaziv();
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

	public List<OmiljenRecept> getRecepti() {
		return recepti;
	}

	public void setRecepti(List<OmiljenRecept> recepti) {
		this.recepti = recepti;
	}
	
	
	
}
