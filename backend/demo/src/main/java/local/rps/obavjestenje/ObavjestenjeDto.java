package local.rps.obavjestenje;

public class ObavjestenjeDto {
	private String opis;
	private int aktivniKorisnikid;
	private int pasivniKorisnikId;
	
	public ObavjestenjeDto(String opis, int aktivniKorisnikid, int pasivniKorisnikId) {
		super();
		this.opis = opis;
		this.aktivniKorisnikid = aktivniKorisnikid;
		this.pasivniKorisnikId = pasivniKorisnikId;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getAktivniKorisnikid() {
		return aktivniKorisnikid;
	}

	public void setAktivniKorisnikid(int aktivniKorisnikid) {
		this.aktivniKorisnikid = aktivniKorisnikid;
	}

	public int getPasivniKorisnikId() {
		return pasivniKorisnikId;
	}

	public void setPasivniKorisnikId(int pasivniKorisnikId) {
		this.pasivniKorisnikId = pasivniKorisnikId;
	}
	
	
}
