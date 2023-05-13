package local.rps.sastojak;

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
public class SastojakController {
	
	@Autowired
	SastojakService sastojakService;

	@RequestMapping("/sastojci")
	public List<Sastojak> getAllSastojci() {
		return sastojakService.getAllSastojci();
	}
	
	@RequestMapping("/sastojci/{id}")
	public Sastojak getSastojakById(@PathVariable int id) {
		return sastojakService.getSastojakById(id);
	}
	
	@RequestMapping("/sastojci/trazi/{naziv}")
	public List<Sastojak> getAllSastojciByNaziv(@PathVariable String naziv) {
		return sastojakService.getAllSastojciByNaziv(naziv);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/sastojci")
	public Sastojak addSastojak(@RequestBody Sastojak sastojak, @RequestHeader(name="authorization") String header) {
		return sastojakService.addSastojak(sastojak, header);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/sastojci/{id}")
	public void deleteSastojakById(@PathVariable int id, @RequestHeader(name="authorization") String header) {
		sastojakService.deleteSastojakById(id, header);
	}

}
