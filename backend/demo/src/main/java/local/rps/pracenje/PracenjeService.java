package local.rps.pracenje;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;

import local.rps.korisnik.Korisnik;
import local.rps.security.Verification;

@Service
public class PracenjeService {

	@Autowired
	PracenjeRepository pracenjeRepository;

	public PracenjeService() {
	}

	public List<Pracenje> getAllPracenja() {
		return this.pracenjeRepository.findAll();
	}
	
	public Pracenje getPracenjeById(int id, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			return this.pracenjeRepository.findById(id).get();
		}
		return null;
	}
	
	public void addPracenje(PracenjeDto p, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			Pracenje pracenje = new Pracenje();
			pracenje.setAktivniKorisnik(new Korisnik(p.getAktivniKorisnikId(), "", "", "", "", "", ""));
			pracenje.setPasivniKorisnik(new Korisnik(p.getPasivniKorisnikId(), "", "", "", "", "", ""));
			this.pracenjeRepository.save(pracenje);
		}
	}
	
	public void deletePracenjeById(int id, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			Pracenje s = this.pracenjeRepository.findById(id).get();
			this.pracenjeRepository.delete(s);
		}
	}

	public List<Pracenje> getPratioci(String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
            String decodedId = jwt.getSubject();
            return this.pracenjeRepository.findByPasivniKorisnikId(Integer.parseInt(decodedId));
        }	
		return null;
	}
	
	public List<Pracenje> getPrati(String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
            String decodedId = jwt.getSubject();
            return this.pracenjeRepository.findByAktivniKorisnikId(Integer.parseInt(decodedId));
        }	
		return null;
	}

	public List<Pracenje> getPratiociByKorisnikId(int id, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
            return this.pracenjeRepository.findByPasivniKorisnikId(id);
        }	
		return null;
	}

	public List<Pracenje> getPratiByKorisnikId(int id, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
            return this.pracenjeRepository.findByAktivniKorisnikId(id);
        }	
		return null;
	}

	public boolean postojiPracenje(int id, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
            String decodedId = jwt.getSubject();
            return this.pracenjeRepository.postojiPracenje(Integer.parseInt(decodedId), id).size()>0;
        }	
		return false;
	}

	public void deletePracenjeByIds(PracenjeDto p, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			int aki = p.getAktivniKorisnikId();
			int pki = p.getPasivniKorisnikId();
			this.pracenjeRepository.deleteByIds(aki,pki);
		}
	}

}