package local.rps.tag_recepta;

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
public class TagReceptaController {
	
	@Autowired
	TagReceptaService tagReceptaService;

	@RequestMapping("/tagoviRecepta")
	public List<TagRecepta> getAllTagoviOdRecepta() {
		return tagReceptaService.getAllTagoviOdRecepta();
	}
	
	@RequestMapping("/tagoviRecepta/{id}")
	public TagRecepta getTagReceptaById(@PathVariable int id) {
		return tagReceptaService.getTagReceptaById(id);
	}
	
	@RequestMapping("/tagoviRecepta/recept/{id}")
	public List<TagRecepta> getAllTagoviByReceptId(@PathVariable int id) {
		return tagReceptaService.getAllTagoviByReceptId(id);
	}
	
	@RequestMapping("/tagoviRecepta/tag/{id}")
	public List<TagRecepta> getAllTagoviByTagId(@PathVariable int id) {
		return tagReceptaService.getAllTagoviByTagId(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/tagoviRecepta")
	public void addTagRecepta(@RequestBody TagReceptaDto tr, @RequestHeader(name="authorization") String header) {
		tagReceptaService.addTagRecepta(tr, header);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/tagoviRecepta")
	public void updateTagRecepta(@RequestBody TagRecepta tagRecepta) {
		tagReceptaService.updateTagRecepta(tagRecepta);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/tagoviRecepta/{id}")
	public void deleteTagReceptaById(@PathVariable int id, @RequestHeader(name="authorization") String header) {
		tagReceptaService.deleteTagReceptaById(id, header);
	}

}