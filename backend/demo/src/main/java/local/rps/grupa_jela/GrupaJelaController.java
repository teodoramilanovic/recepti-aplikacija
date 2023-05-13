package local.rps.grupa_jela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class GrupaJelaController {
	
	@Autowired
	GrupaJelaService grupaJelaService;

	@RequestMapping("/grupeJela")
	public List<GrupaJela> getAllGrupeJela() {
		return grupaJelaService.getAllGrupeJela();
	}
	
	@RequestMapping("/grupeJela/{id}")
	public GrupaJela getGrupaJelaById(@PathVariable int id) {
		return grupaJelaService.getGrupaJelaById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/grupeJela")
	public void addGrupaJela(@RequestBody GrupaJela grupaJela) {
		grupaJelaService.addGrupaJela(grupaJela);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/grupeJela")
	public void updateGrupaJela(@RequestBody GrupaJela grupaJela) {
		grupaJelaService.updateGrupaJela(grupaJela);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/grupeJela/{id}")
	public void deleteGrupaJelaById(@PathVariable int id) {
		grupaJelaService.deleteGrupaJelaById(id);
	}

}