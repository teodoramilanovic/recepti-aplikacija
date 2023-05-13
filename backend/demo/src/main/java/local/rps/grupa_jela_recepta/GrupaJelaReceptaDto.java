package local.rps.grupa_jela_recepta;

public class GrupaJelaReceptaDto {
	private int grupaJelaId;
	private int receptId;
	
	public GrupaJelaReceptaDto(int grupaJelaId, int receptId) {
		super();
		this.grupaJelaId = grupaJelaId;
		this.receptId = receptId;
	}

	public int getGrupaJelaId() {
		return grupaJelaId;
	}

	public void setGrupaJelaId(int grupaJelaId) {
		this.grupaJelaId = grupaJelaId;
	}

	public int getReceptId() {
		return receptId;
	}

	public void setReceptId(int receptId) {
		this.receptId = receptId;
	}
	
	
}
