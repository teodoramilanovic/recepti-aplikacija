package local.rps.recept;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;

import local.rps.postupak_pripreme.PostupakPripreme;
import local.rps.postupak_pripreme.PostupakPripremeController;
import local.rps.pracenje.Pracenje;
import local.rps.pracenje.PracenjeService;
import local.rps.sastojak_recepta.SastojakRecepta;
import local.rps.sastojak_recepta.SastojakReceptaController;
import local.rps.security.Verification;
import local.rps.tag_recepta.TagRecepta;
import local.rps.tag_recepta.TagReceptaController;
import local.rps.grupa_jela_recepta.GrupaJelaRecepta;
import local.rps.grupa_jela_recepta.GrupaJelaReceptaService;
import local.rps.komentar.Komentar;
import local.rps.komentar.KomentarController;
import local.rps.korisnik.Korisnik;
import local.rps.omiljen_recept.OmiljenRecept;
import local.rps.omiljen_recept.OmiljenReceptController;

@Service
public class ReceptService {

	@Autowired
	ReceptRepository receptRepository;
	
	@Autowired
	SastojakReceptaController sastojakReceptaController;
	
	@Autowired
	PostupakPripremeController postupakPripremeController;
	
	@Autowired
	TagReceptaController tagReceptaController;
	
	@Autowired
	OmiljenReceptController omiljenReceptController;
	
	@Autowired
	KomentarController komentarController;
	
	@Autowired
	PracenjeService pracenjeService;
	
	@Autowired
	GrupaJelaReceptaService grupaJelaReceptaService;
	
	public ReceptService() {
	}

	public List<Recept> getAllRecepti() {
		return this.receptRepository.findAll();
	}
	
	public List<Recept> getAllReceptiByNaziv(String naziv) {
		return this.receptRepository.getAllReceptiByNaziv(naziv);
	}
	
	public ReceptDetails getReceptById(int id) {
		Recept r = this.receptRepository.findById(id).get();
		ReceptDetails rd = new ReceptDetails(r);
		List<SastojakRecepta> sastojci = sastojakReceptaController.getAllSastojciByReceptId(id);
		rd.setSastojci(sastojci);
		List<PostupakPripreme> postupciPripreme = postupakPripremeController.getAllPostupciPripremeByReceptId(id);
		rd.setPostupciPripreme(postupciPripreme);
		List<TagRecepta> tagovi = tagReceptaController.getAllTagoviByReceptId(id);
		rd.setTagovi(tagovi);
		List<OmiljenRecept> omiljeniRecepti = omiljenReceptController.getOmiljeniReceptiByReceptId(id);
		rd.setBrojSvidjanja(omiljeniRecepti.size());
		List<GrupaJelaRecepta> grupeJelaRecepta = grupaJelaReceptaService.getGrupaJelaReceptaByReceptId(id);
		rd.setGrupeJela(grupeJelaRecepta);
		List<Komentar> komentari = komentarController.getAllKomentariByReceptId(id);
		rd.setKomentari(komentari);
		return rd;
	}
	
	public Recept addRecept(Recept recept, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
            String decodedId = jwt.getSubject();
            recept.setKorisnik(new Korisnik(Integer.parseInt(decodedId), "", "", "", "", "", ""));
    		recept.setDatumObjave(new Timestamp(System.currentTimeMillis()));
            return this.receptRepository.save(recept);
        }
		return null;
	}
	
	public void updateRecept(Recept recept) {
		this.receptRepository.save(recept);
	}
	
	public void deleteReceptById(int id, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
			ReceptDetails s = this.getReceptById(id);
			this.receptRepository.deleteById(s.getId());
		}
	}
	
	public List<Recept> getNoviRecepti() {
		 List<Recept> recepti = this.receptRepository.findAll();
		 Collections.sort(recepti, new Comparator<Recept>() {
			 public int compare(Recept r1, Recept r2) {
				 return r2.getDatumObjave().compareTo(r1.getDatumObjave());
			 }
		 });
		 return recepti;
	}
	
	public List<ReceptDetails> getPopularniRecepti() {
		List<Recept> recepti = this.receptRepository.findAll();
		List<ReceptDetails> rds= recepti.stream()
        .map(r ->  new ReceptDetails(r))
        .collect(Collectors.toList());
		for (ReceptDetails rd : rds) {
			List<OmiljenRecept> omiljeniRecepti = omiljenReceptController.getOmiljeniReceptiByReceptId(rd.getId());
			rd.setBrojSvidjanja(omiljeniRecepti.size());
		}
		Collections.sort(rds, new Comparator<ReceptDetails>() {
			 public int compare(ReceptDetails rd1, ReceptDetails rd2) {
				 return rd2.getBrojSvidjanja() - rd1.getBrojSvidjanja();
			 }
		 });
		 return rds;
	}

	public List<Recept> getMojiRecepti(String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
            String decodedId = jwt.getSubject();
            return receptRepository.findByKorisnikId(Integer.parseInt(decodedId),Sort.by(Sort.Direction.DESC,"datumObjave"));
        }
        return null;
	}

	public List<Recept> getReceptiByKorisnikId(int id, String header) {
		DecodedJWT jwt = Verification.verify(header);
		if(jwt != null) {
            return receptRepository.findByKorisnikId(id,Sort.by(Sort.Direction.DESC,"datumObjave"));
        }
        return null;
	}

	public List<Recept> getReceptiByTezinaPripreme(String tezina) {
		return receptRepository.findByTezinaPripreme(tezina);
	}

	public List<Recept> getReceptiLjudiKojePratim(String header) {
		List<Pracenje> listaPrati = this.pracenjeService.getPrati(header);
		List<Recept> recepti=new ArrayList<Recept>();
		for(Pracenje p: listaPrati) {
			List<Recept> temp = receptRepository.findByKorisnikId(p.getPasivniKorisnik().getId());
			recepti.addAll(temp);
		}
		Collections.sort(recepti, new Comparator<Recept>() {
			 public int compare(Recept r1, Recept r2) {
				 return r1.getDatumObjave().compareTo(r2.getDatumObjave());
			 }
		 });
		
		return recepti;
	}

}
