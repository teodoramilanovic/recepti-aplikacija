package local.rps.omiljen_recept;

public class OmiljenReceptDto {
	private int receptId;
	private int kolekcijaId;
	
	public OmiljenReceptDto(int receptId, int kolekcijaId) {
		super();
		this.receptId = receptId;
		this.kolekcijaId = kolekcijaId;
	}

	public int getReceptId() {
		return receptId;
	}

	public void setReceptId(int receptId) {
		this.receptId = receptId;
	}

	public int getKolekcijaId() {
		return kolekcijaId;
	}

	public void setKolekcijaId(int kolekcijaId) {
		this.kolekcijaId = kolekcijaId;
	}
	
	
}
