package local.rps.sastojak_recepta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import local.rps.recept.Recept;
import local.rps.sastojak.Sastojak;

@Entity
public class SastojakRecepta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double kolicina;
	private String mjernaJedinica;
	@ManyToOne
	private Sastojak sastojak;
	@ManyToOne
	private Recept recept;
	
	public SastojakRecepta(int id, double kolicina, String mjernaJedinica, Sastojak sastojak, Recept recept) {
		super();
		this.id = id;
		this.kolicina = kolicina;
		this.mjernaJedinica = mjernaJedinica;
		this.sastojak = sastojak;
		this.recept = recept;
	}
	
	public SastojakRecepta() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getKolicina() {
		return kolicina;
	}
	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}
	public String getMjernaJedinica() {
		return mjernaJedinica;
	}
	public void setMjernaJedinica(String mjernaJedinica) {
		this.mjernaJedinica = mjernaJedinica;
	}
	public Sastojak getSastojak() {
		return sastojak;
	}
	public void setSastojak(Sastojak sastojak) {
		this.sastojak = sastojak;
	}
	public Recept getRecept() {
		return recept;
	}
	public void setRecept(Recept recept) {
		this.recept = recept;
	}
	
	
}