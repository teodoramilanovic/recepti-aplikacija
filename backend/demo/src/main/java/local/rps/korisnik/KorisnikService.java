package local.rps.korisnik;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import local.rps.pracenje.PracenjeService;
import local.rps.recept.ReceptService;
import local.rps.security.Verification;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class KorisnikService {

	@Autowired
	KorisnikRepository korisnikRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	PracenjeService pracenjeService;
	
	@Autowired
	ReceptService receptService;

	public KorisnikService() {
	}
	
	public Korisnik getKorisnikById(int id, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			return this.korisnikRepository.findById(id).get();
		}
		return null;
	}
	
	public boolean jeDostupan(String email) {
		return this.korisnikRepository.findByEmail(email).size()==0;
	}
	
	public Resp addKorisnik(Korisnik korisnik) {
		if(jeDostupan(korisnik.getEmail())) {
			korisnik.setLozinka(passwordEncoder.encode(korisnik.getLozinka()));
			Korisnik k = this.korisnikRepository.save(korisnik);
			
			Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            String access_token = JWT.create()
                    .withSubject(Integer.toString(k.getId()))
                    .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                    .sign(algorithm);

            return new Resp(k, access_token);
		}
		return null;
	}
	
	public Resp loginKorisnik(Korisnik korisnik) {
		List<Korisnik> k = this.korisnikRepository.findByEmail(korisnik.getEmail());
		if(k.size()>0 && passwordEncoder.matches(korisnik.getLozinka(), k.get(0).getLozinka())) {
			Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            String access_token = JWT.create()
                    .withSubject(Integer.toString(k.get(0).getId()))
                    .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                    .sign(algorithm);
            
            return new Resp(k.get(0), access_token);
		}
		return new Resp(null,"");
	}
	
	public void updateKorisnik(Korisnik korisnik, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			this.korisnikRepository.save(korisnik);
		}
	}
	
	public void deleteKorisnikById(String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			String decodedId = jwt.getSubject();
			Korisnik s = this.korisnikRepository.findById(Integer.parseInt(decodedId)).get();
			this.korisnikRepository.delete(s);
		}
	}

	public KorisnikDetails getKorisnikByEmail(String email, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
            Korisnik k = this.korisnikRepository.findByEmail(email).get(0);
            KorisnikDetails kd = new KorisnikDetails(k);
            kd.setBrojPrati(this.pracenjeService.getPratiByKorisnikId(k.getId(), header).size());
            kd.setBrojPratioci(this.pracenjeService.getPratiociByKorisnikId(k.getId(), header).size());
            kd.setRecepti(this.receptService.getReceptiByKorisnikId(k.getId(), header));
            
            return kd;
        }	
		return null;
	}

	public boolean promijeniLozinku(PromjenaLozinkeDto pl, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
            String decodedId = jwt.getSubject();
            Korisnik k = this.korisnikRepository.findById(Integer.parseInt(decodedId)).get();
            if(passwordEncoder.matches(pl.getStaraLozinka(), k.getLozinka())) {
            	k.setLozinka(passwordEncoder.encode(pl.getNovaLozinka()));
            	this.korisnikRepository.save(k);
            	return true;
            }
            return false;
        }	
		return false;
	}


}