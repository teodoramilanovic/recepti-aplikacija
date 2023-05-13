package local.rps.komentar;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;

import local.rps.korisnik.Korisnik;
import local.rps.recept.Recept;
import local.rps.security.Verification;

@Service
public class KomentarService {

	@Autowired
	KomentarRepository komentarRepository;

	public KomentarService() {
	}
	
	public Komentar getKomentarById(int id) {
		return this.komentarRepository.findById(id).get();
	}
	
	public void addKomentar(KomentarDto k, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			Komentar komentar=new Komentar();
			komentar.setKorisnik(new Korisnik(k.getKorisnikId(), "", "", "", "", "", ""));
			komentar.setRecept(new Recept(k.getReceptId(), "", 0, "", 0, null, null, ""));
			komentar.setDatumObjave(new Timestamp(System.currentTimeMillis()));
			komentar.setTekst(k.getTekst());
			this.komentarRepository.save(komentar);
		}
	}
	
	public void deleteKomentarById(int id, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			Komentar s = this.komentarRepository.findById(id).get();
			this.komentarRepository.delete(s);
		}
	}

	public List<Komentar> getAllKomentariByReceptId(int id) {
		return this.komentarRepository.findByReceptId(id);
	}

}
