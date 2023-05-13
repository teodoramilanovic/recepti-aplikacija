package local.rps.tag_recepta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;

import local.rps.recept.Recept;
import local.rps.security.Verification;
import local.rps.tag.Tag;

@Service
public class TagReceptaService {

	@Autowired
	TagReceptaRepository tagReceptaRepository;

	public TagReceptaService() {
	}

	public List<TagRecepta> getAllTagoviOdRecepta() {
		return this.tagReceptaRepository.findAll();
	}
	
	public TagRecepta getTagReceptaById(int id) {
		return this.tagReceptaRepository.findById(id).get();
	}
	
	public List<TagRecepta> getAllTagoviByReceptId(int id) {
		return this.tagReceptaRepository.findByReceptId(id);
	}
	
	public List<TagRecepta> getAllTagoviByTagId(int id) {
		return this.tagReceptaRepository.findByTagId(id);
	}
	
	public void addTagRecepta(TagReceptaDto tr, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			TagRecepta tagRecepta = new TagRecepta();
			tagRecepta.setTag(new Tag(tr.getTagId(), ""));
			tagRecepta.setRecept(new Recept(tr.getReceptId(), "", 0, "", 0, null, null, ""));
			this.tagReceptaRepository.save(tagRecepta);
		}
	}
	
	public void updateTagRecepta(TagRecepta tagRecepta) {
		this.tagReceptaRepository.save(tagRecepta);
	}
	
	public void deleteTagReceptaById(int id, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			TagRecepta s = this.getTagReceptaById(id);
			this.tagReceptaRepository.delete(s);
		}
	}

}