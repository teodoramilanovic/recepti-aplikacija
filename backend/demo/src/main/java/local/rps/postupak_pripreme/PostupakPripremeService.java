package local.rps.postupak_pripreme;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;

import local.rps.recept.Recept;
import local.rps.security.Verification;

@Service
public class PostupakPripremeService {

	@Autowired
	PostupakPripremeRepository postupakPripremeRepository;

	public PostupakPripremeService() {
	}

	public List<PostupakPripreme> getAllPostupciPripreme() {
		return this.postupakPripremeRepository.findAll();
	}
	
	public PostupakPripreme getPostupakPripremeById(int id) {
		return this.postupakPripremeRepository.findById(id).get();
	}
	
	public List<PostupakPripreme> getAllPostupciPripremeByReceptId(int id) {
		return this.postupakPripremeRepository.findByReceptId(id);
	}
	
	public void addPostupakPripreme(int id, PostupakPripreme postupakPripreme, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			postupakPripreme.setRecept(new Recept(id, "", 0, "", 0, null, null, ""));
			this.postupakPripremeRepository.save(postupakPripreme);
		}
	}
	
	public void updatePostupakPripreme(PostupakPripreme postupakPripreme) {
		this.postupakPripremeRepository.save(postupakPripreme);
	}
	
	public void deletePostupakPripremeById(int id, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			PostupakPripreme s = this.getPostupakPripremeById(id);
			this.postupakPripremeRepository.delete(s);
		}
	}

}
