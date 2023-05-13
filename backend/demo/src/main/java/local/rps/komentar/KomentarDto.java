package local.rps.komentar;

public class KomentarDto {
	private String tekst;
	private int korisnikId;
	private int receptId;
	
	public KomentarDto(String tekst, int korisnikId, int receptId) {
		super();
		this.tekst = tekst;
		this.korisnikId = korisnikId;
		this.receptId = receptId;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public int getKorisnikId() {
		return korisnikId;
	}

	public void setKorisnikId(int korisnikId) {
		this.korisnikId = korisnikId;
	}

	public int getReceptId() {
		return receptId;
	}

	public void setReceptId(int receptId) {
		this.receptId = receptId;
	}
	
	
}
