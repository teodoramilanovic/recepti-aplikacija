package local.rps.tag_recepta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import local.rps.recept.Recept;
import local.rps.tag.Tag;

@Entity
public class TagRecepta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Tag tag;
	@ManyToOne
	private Recept recept;
	
	public TagRecepta(int id, Tag tag, Recept recept) {
		super();
		this.id = id;
		this.tag = tag;
		this.recept = recept;
	}
	
	public TagRecepta() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Recept getRecept() {
		return recept;
	}

	public void setRecept(Recept recept) {
		this.recept = recept;
	}
	
	
	
}