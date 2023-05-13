package local.rps.postupak_pripreme;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import local.rps.recept.Recept;

@Entity
public class PostupakPripreme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String opis;
	private int redniBroj;
	private String fotografija;
	@ManyToOne
	private Recept recept;
	
	public PostupakPripreme(int id, String opis, int redniBroj, String fotografija, Recept recept) {
		super();
		this.id = id;
		this.opis = opis;
		this.redniBroj = redniBroj;
		this.fotografija = fotografija;
		this.recept = recept;
	}
	
	public PostupakPripreme() {}

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

	public int getRedniBroj() {
		return redniBroj;
	}

	public void setRedniBroj(int redniBroj) {
		this.redniBroj = redniBroj;
	}

	public String getFotografija() {
		return fotografija;
	}

	public void setFotografija(String fotografija) {
		this.fotografija = fotografija;
	}

	public Recept getRecept() {
		return recept;
	}

	public void setRecept(Recept recept) {
		this.recept = recept;
	}
	
	
}
