package local.rps.komentar;

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
public class KomentarController {
	
	@Autowired
	KomentarService komentarService;
	
	@RequestMapping("/komentari/{id}")
	public Komentar getKomentarById(@PathVariable int id) {
		return komentarService.getKomentarById(id);
	}
	
	@RequestMapping("/komentari/recept/{id}")
	public List<Komentar> getAllKomentariByReceptId(@PathVariable int id) {
		return komentarService.getAllKomentariByReceptId(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/komentari")
	public void addKomentar(@RequestBody KomentarDto k, @RequestHeader(name="authorization") String header) {
		komentarService.addKomentar(k, header);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/komentari/{id}")
	public void deleteKomentarById(@PathVariable int id, @RequestHeader(name="authorization") String header) {
		komentarService.deleteKomentarById(id, header);
	}

}