package local.rps.pracenje;

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
public class PracenjeController {
	
	@Autowired
	PracenjeService pracenjeService;

	@RequestMapping("/pracenja")
	public List<Pracenje> getAllPracenja() {
		return pracenjeService.getAllPracenja();
	}
	
	@RequestMapping("/pracenja/{id}")
	public Pracenje getPracenjeById(@PathVariable int id, @RequestHeader(name="authorization") String header) {
		return pracenjeService.getPracenjeById(id, header);
	}
	
	@RequestMapping("/pratioci/{id}")
	public List<Pracenje> getPratiociByKorisnikId(@PathVariable int id, @RequestHeader(name="authorization") String header) {
		return pracenjeService.getPratiociByKorisnikId(id,header);
	}
	
	@RequestMapping("/prati/{id}")
	public List<Pracenje> getPratibyKorisnikId(@PathVariable int id, @RequestHeader(name="authorization") String header) {
		return pracenjeService.getPratiByKorisnikId(id, header);
	}
	
	@RequestMapping("/postojiPracenje/{id}")
	public boolean postojiPracenje(@PathVariable int id, @RequestHeader(name="authorization") String header) {
		return pracenjeService.postojiPracenje(id, header);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/pracenja")
	public void addPracenje(@RequestBody PracenjeDto p, @RequestHeader(name="authorization") String header) {
		pracenjeService.addPracenje(p, header);
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/pracenja/{id}")
	public void deletePracenjeById(@PathVariable int id, @RequestHeader(name="authorization") String header) {
		pracenjeService.deletePracenjeById(id, header);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/pracenja/obrisi")
	public void deletePracenjeByIds(@RequestBody PracenjeDto p, @RequestHeader(name="authorization") String header) {
		pracenjeService.deletePracenjeByIds(p, header);
	}

}
