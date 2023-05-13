package local.rps.pracenje;

public class PracenjeDto {
	private int aktivniKorisnikId;
	private int pasivniKorisnikId;
	
	public PracenjeDto(int aktivniKorisnikId, int pasivniKorisnikId) {
		super();
		this.aktivniKorisnikId = aktivniKorisnikId;
		this.pasivniKorisnikId = pasivniKorisnikId;
	}

	public int getAktivniKorisnikId() {
		return aktivniKorisnikId;
	}

	public void setAktivniKorisnikId(int aktivniKorisnikId) {
		this.aktivniKorisnikId = aktivniKorisnikId;
	}

	public int getPasivniKorisnikId() {
		return pasivniKorisnikId;
	}

	public void setPasivniKorisnikId(int pasivniKorisnikId) {
		this.pasivniKorisnikId = pasivniKorisnikId;
	}
	
	
}
