package local.rps.obavjestenje;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;

import local.rps.korisnik.Korisnik;
import local.rps.security.Verification;

@Service
public class ObavjestenjeService {

	@Autowired
	ObavjestenjeRepository obavjestenjeRepository;

	public ObavjestenjeService() {
	}

	public List<Obavjestenje> getAllObavjestenja(String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
            String decodedId = jwt.getSubject();
            return obavjestenjeRepository.findByPasivniKorisnikId(Integer.parseInt(decodedId),Sort.by(Sort.Direction.DESC,"datum"));
        }
        return null;
	}
	
	public Obavjestenje getObavjestenjeById(int id, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			return this.obavjestenjeRepository.findById(id).get();
		}
		return null;
	}
	
	public void addObavjestenje(ObavjestenjeDto o, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			Obavjestenje obavjestenje=new Obavjestenje();
			obavjestenje.setAktivniKorisnik(new Korisnik(o.getAktivniKorisnikid(), "", "", "", "", "", ""));
			obavjestenje.setPasivniKorisnik(new Korisnik(o.getPasivniKorisnikId(), "", "", "", "", "", ""));
			obavjestenje.setDatum(new Timestamp(System.currentTimeMillis()));
			obavjestenje.setOpis(o.getOpis());
			this.obavjestenjeRepository.save(obavjestenje);
		}
	}
	
	
	public void deleteObavjestenjeById(int id, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			Obavjestenje s = this.obavjestenjeRepository.findById(id).get();
			this.obavjestenjeRepository.delete(s);
		}
	}

}