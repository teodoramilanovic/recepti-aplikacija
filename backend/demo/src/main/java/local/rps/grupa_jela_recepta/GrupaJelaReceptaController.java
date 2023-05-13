package local.rps.grupa_jela_recepta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class GrupaJelaReceptaController {
	
	@Autowired
	GrupaJelaReceptaService grupaJelaReceptaService;

	@RequestMapping("/grupeJelaRecepta")
	public List<GrupaJelaRecepta> getAllGrupeJelaRecepta() {
		return grupaJelaReceptaService.getAllGrupeJelaRecepta();
	}
	
	@RequestMapping("/grupeJelaRecepta/{id}")
	public GrupaJelaRecepta getGrupaJelaReceptaById(@PathVariable int id) {
		return grupaJelaReceptaService.getGrupaJelaReceptaById(id);
	}
	
	@RequestMapping("/grupeJelaRecepta/grupaJela/{id}")
	public List<GrupaJelaRecepta> getGrupaJelaReceptaByGrupaJelaId(@PathVariable int id) {
		return grupaJelaReceptaService.getGrupaJelaReceptaByGrupaJelaId(id);
	}
	
	@RequestMapping("/grupeJelaRecepta/recept/{id}")
	public List<GrupaJelaRecepta> getGrupaJelaReceptaByReceptId(@PathVariable int id) {
		return grupaJelaReceptaService.getGrupaJelaReceptaByReceptId(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/grupeJelaRecepta")
	public void addGrupaJelaRecepta(@RequestBody GrupaJelaReceptaDto gjr,  @RequestHeader(name="authorization") String header) {
		grupaJelaReceptaService.addGrupaJelaRecepta(gjr, header);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/grupeJelaRecepta/{id}")
	public void deleteGrupaJelaReceptaById(@PathVariable int id, @RequestHeader(name="authorization") String header) {
		grupaJelaReceptaService.deleteGrupaJelaReceptaById(id, header);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/grupeJelaRecepta/recept/{id}")
	public void deleteGrupaJelaReceptaByReceptId(@PathVariable int id, @RequestHeader(name="authorization") String header) {
		grupaJelaReceptaService.deleteGrupaJelaReceptaByReceptId(id, header);
	}
}
