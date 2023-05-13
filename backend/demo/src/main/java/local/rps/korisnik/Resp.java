package local.rps.korisnik;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Resp {

    private Korisnik result;
    private String token;
    
    public Resp(Korisnik result, String token) {
		super();
		this.result = result;
		this.token = token;
	}

	public Korisnik getResult() {
		return result;
	}

	public void setResult(Korisnik result) {
		this.result = result;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
    
}
