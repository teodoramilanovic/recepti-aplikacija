package local.rps.sastojak_recepta;

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
public class SastojakReceptaController {
	
	@Autowired
	SastojakReceptaService sastojakReceptaService;

	@RequestMapping("/sastojciRecepta")
	public List<SastojakRecepta> getAllSastojciRecepta() {
		return sastojakReceptaService.getAllSastojciRecepta();
	}
	
	@RequestMapping("/sastojciRecepta/{id}")
	public SastojakRecepta getSastojakReceptaById(@PathVariable int id) {
		return sastojakReceptaService.getSastojakReceptaById(id);
	}
	
	@RequestMapping("/sastojciRecepta/recept/{id}")
	public List<SastojakRecepta> getAllSastojciByReceptId(@PathVariable int id) {
		return sastojakReceptaService.getAllSastojciByReceptId(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/sastojciRecepta")
	public void addSastojakRecepta(@RequestBody SastojakReceptaDto sr, @RequestHeader(name="authorization") String header) {
		sastojakReceptaService.addSastojakRecepta(sr, header);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/sastojciRecepta")
	public void updateSastojakRecepta(@RequestBody SastojakRecepta sastojakRecepta) {
		sastojakReceptaService.updateSastojakRecepta(sastojakRecepta);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/sastojciRecepta/{id}")
	public void deleteSastojakReceptaById(@PathVariable int id, @RequestHeader(name="authorization") String header) {
		sastojakReceptaService.deleteSastojakReceptaById(id, header);
	}

}