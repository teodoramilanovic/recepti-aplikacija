package local.rps.omiljen_recept;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import local.rps.kolekcija.Kolekcija;
import local.rps.recept.Recept;

@Entity
public class OmiljenRecept {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Recept recept;
	@ManyToOne
	private Kolekcija kolekcija;
	
	public OmiljenRecept(int id, Recept recept, Kolekcija kolekcija) {
		super();
		this.id = id;
		this.recept = recept;
		this.kolekcija = kolekcija;
	}
	
	public OmiljenRecept() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Recept getRecept() {
		return recept;
	}

	public void setRecept(Recept recept) {
		this.recept = recept;
	}

	public Kolekcija getKolekcija() {
		return kolekcija;
	}

	public void setKolekcija(Kolekcija kolekcija) {
		this.kolekcija = kolekcija;
	}
	
	
}

