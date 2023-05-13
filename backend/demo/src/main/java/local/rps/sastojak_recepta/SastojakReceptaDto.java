package local.rps.sastojak_recepta;

public class SastojakReceptaDto {
	private double kolicina;
	private String mjernaJedinica;
	private int sastojakId;
	private int receptId;
	
	public SastojakReceptaDto(double kolicina, String mjernaJedinica, int sastojakId, int receptId) {
		super();
		this.kolicina = kolicina;
		this.mjernaJedinica = mjernaJedinica;
		this.sastojakId = sastojakId;
		this.receptId = receptId;
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

	public int getSastojakId() {
		return sastojakId;
	}

	public void setSastojakId(int sastojakId) {
		this.sastojakId = sastojakId;
	}

	public int getReceptId() {
		return receptId;
	}

	public void setReceptId(int receptId) {
		this.receptId = receptId;
	}
	
	
}
