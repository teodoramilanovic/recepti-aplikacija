package local.rps.grupa_jela_recepta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;

import local.rps.grupa_jela.GrupaJela;
import local.rps.recept.Recept;
import local.rps.security.Verification;

@Service
public class GrupaJelaReceptaService {

	@Autowired
	GrupaJelaReceptaRepository grupaJelaReceptaRepository;

	public GrupaJelaReceptaService() {
	}

	public List<GrupaJelaRecepta> getAllGrupeJelaRecepta() {
		return this.grupaJelaReceptaRepository.findAll();
	}
	
	public GrupaJelaRecepta getGrupaJelaReceptaById(int id) {
		return this.grupaJelaReceptaRepository.findById(id).get();
	}
	
	public void addGrupaJelaRecepta(GrupaJelaReceptaDto gjr,String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			GrupaJelaRecepta grupaJelaRecepta = new GrupaJelaRecepta();
	    	grupaJelaRecepta.setGrupaJela(new GrupaJela(gjr.getGrupaJelaId(), ""));
	    	grupaJelaRecepta.setRecept(new Recept(gjr.getReceptId(), "", 0, "", 0, null, null, ""));
	        this.grupaJelaReceptaRepository.save(grupaJelaRecepta);
		}
	}
	
	public void deleteGrupaJelaReceptaById(int id, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			GrupaJelaRecepta s = this.getGrupaJelaReceptaById(id);
			this.grupaJelaReceptaRepository.delete(s);
		}
	}

	public void deleteGrupaJelaReceptaByReceptId(int id, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
            this.grupaJelaReceptaRepository.deleteByReceptId(id);
        }
	}

	public List<GrupaJelaRecepta> getGrupaJelaReceptaByGrupaJelaId(int id) {
		return this.grupaJelaReceptaRepository.findByGrupaJelaId(id);
	}

	public List<GrupaJelaRecepta> getGrupaJelaReceptaByReceptId(int id) {
		return this.grupaJelaReceptaRepository.findByReceptId(id);
	}

}