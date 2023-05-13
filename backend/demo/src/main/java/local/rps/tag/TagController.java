package local.rps.tag;

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
public class TagController {
	
	@Autowired
	TagService tagService;

	@RequestMapping("/tagovi")
	public List<Tag> getAllTagovi() {
		return tagService.getAllTagovi();
	}
	
	@RequestMapping("/tagovi/{id}")
	public Tag getTagById(@PathVariable int id) {
		return tagService.getTagById(id);
	}
	
	@RequestMapping("/tagovi/trazi/{naziv}")
	public List<Tag> getAllTagoviByNaziv(@PathVariable String naziv) {
		return tagService.getAllTagoviByNaziv(naziv);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/tagovi")
	public Tag addTag(@RequestBody Tag tag, @RequestHeader(name="authorization") String header) {
		return tagService.addTag(tag, header);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/tagovi/{id}")
	public void deleteTagById(@PathVariable int id, @RequestHeader(name="authorization") String header) {
		tagService.deleteTagById(id, header);
	}

}