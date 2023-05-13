package local.rps.korisnik;

public class PromjenaLozinkeDto {
	private String staraLozinka;
	private String novaLozinka;
	
	public PromjenaLozinkeDto(String staraLozinka, String novaLozinka) {
		super();
		this.staraLozinka = staraLozinka;
		this.novaLozinka = novaLozinka;
	}

	public String getStaraLozinka() {
		return staraLozinka;
	}

	public void setStaraLozinka(String staraLozinka) {
		this.staraLozinka = staraLozinka;
	}

	public String getNovaLozinka() {
		return novaLozinka;
	}

	public void setNovaLozinka(String novaLozinka) {
		this.novaLozinka = novaLozinka;
	}
	
	
}
