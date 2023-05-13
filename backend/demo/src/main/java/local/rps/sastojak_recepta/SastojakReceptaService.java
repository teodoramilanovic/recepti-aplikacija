package local.rps.sastojak_recepta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;

import local.rps.recept.Recept;
import local.rps.sastojak.Sastojak;
import local.rps.security.Verification;

@Service
public class SastojakReceptaService {

	@Autowired
	SastojakReceptaRepository sastojakReceptaRepository;

	public SastojakReceptaService() {
	}

	public List<SastojakRecepta> getAllSastojciRecepta() {
		return this.sastojakReceptaRepository.findAll();
	}
	
	public SastojakRecepta getSastojakReceptaById(int id) {
		return this.sastojakReceptaRepository.findById(id).get();
	}
	
	public List<SastojakRecepta> getAllSastojciByReceptId(int id) {
		return this.sastojakReceptaRepository.findByReceptId(id);
	}
	
	public void addSastojakRecepta(SastojakReceptaDto sr, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			SastojakRecepta sastojakRecepta = new SastojakRecepta();
			sastojakRecepta.setSastojak(new Sastojak(sr.getSastojakId(), ""));
			sastojakRecepta.setRecept(new Recept(sr.getReceptId(), "", 0, "", 0, null, null, ""));
			sastojakRecepta.setKolicina(sr.getKolicina());
			sastojakRecepta.setMjernaJedinica(sr.getMjernaJedinica());
			this.sastojakReceptaRepository.save(sastojakRecepta);
		}
	}
	
	public void updateSastojakRecepta(SastojakRecepta sastojakRecepta) {
		this.sastojakReceptaRepository.save(sastojakRecepta);
	}
	
	public void deleteSastojakReceptaById(int id, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			SastojakRecepta s = this.getSastojakReceptaById(id);
			this.sastojakReceptaRepository.delete(s);
		}
	}

}
