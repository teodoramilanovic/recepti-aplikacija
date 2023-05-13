package local.rps.kolekcija;

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
public class KolekcijaController {
	
	@Autowired
	KolekcijaService kolekcijaService;
	
	@RequestMapping("/mojeKolekcije")
	public List<KolekcijaDetails> getAllKolekcije(@RequestHeader(name="authorization") String header) {
		return kolekcijaService.getMojeKolekcije(header);
	}
	
	@RequestMapping("/kolekcije/{id}")
	public Kolekcija getKolekcijaById(@PathVariable int id, @RequestHeader(name="authorization") String header) {
		return kolekcijaService.getKolekcijaById(id, header);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/kolekcije")
	public void addKolekcija(@RequestBody Kolekcija kolekcija, @RequestHeader(name="authorization") String header) {
		kolekcijaService.addKolekcija(kolekcija, header);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/kolekcije/{id}")
	public void Kolekcija(@PathVariable int id, @RequestHeader(name="authorization") String header) {
		kolekcijaService.deleteKolekcijaById(id, header);
	}

}
