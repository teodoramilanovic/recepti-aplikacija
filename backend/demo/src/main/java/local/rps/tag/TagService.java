package local.rps.tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;

import local.rps.security.Verification;

@Service
public class TagService {

	@Autowired
	TagRepository tagRepository;

	public TagService() {
	}

	public List<Tag> getAllTagovi() {
		return this.tagRepository.findAll();
	}
	
	public Tag getTagById(int id) {
		return this.tagRepository.findById(id).get();
	}
	
	public Tag addTag(Tag tag, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			return this.tagRepository.save(tag);
		}
		return null;
	}
	
	public void deleteTagById(int id, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			Tag s = this.getTagById(id);
			this.tagRepository.delete(s);
		}
	}

	public List<Tag> getAllTagoviByNaziv(String naziv) {
		return this.tagRepository.getAllTagoviByNaziv(naziv);
	}

}