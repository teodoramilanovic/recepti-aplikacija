package local.rps.sastojak;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;

import local.rps.security.Verification;

@Service
public class SastojakService {

	@Autowired
	SastojakRepository sastojakRepository;

	public SastojakService() {
	}

	public List<Sastojak> getAllSastojci() {
		return this.sastojakRepository.findAll();
	}
	
	public Sastojak getSastojakById(int id) {
		return this.sastojakRepository.findById(id).get();
	}
	
	public Sastojak addSastojak(Sastojak sastojak, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			return this.sastojakRepository.save(sastojak);
		}
		return null;
	}
	
	public void deleteSastojakById(int id, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			Sastojak s = this.getSastojakById(id);
			this.sastojakRepository.delete(s);
		}
	}

	public List<Sastojak> getAllSastojciByNaziv(String naziv) {
		return this.sastojakRepository.getAllSastojciByNaziv(naziv);
	}

}