package local.rps.grupa_jela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrupaJelaService {

	@Autowired
	GrupaJelaRepository grupaJelaRepository;

	public GrupaJelaService() {
	}

	public List<GrupaJela> getAllGrupeJela() {
		return this.grupaJelaRepository.findAll();
	}
	
	public GrupaJela getGrupaJelaById(int id) {
		return this.grupaJelaRepository.findById(id).get();
	}
	
	public void addGrupaJela(GrupaJela grupaJela) {
		this.grupaJelaRepository.save(grupaJela);
	}
	
	public void updateGrupaJela(GrupaJela grupaJela) {
		this.grupaJelaRepository.save(grupaJela);
	}
	
	public void deleteGrupaJelaById(int id) {
		GrupaJela s = this.getGrupaJelaById(id);
		this.grupaJelaRepository.delete(s);
	}

}