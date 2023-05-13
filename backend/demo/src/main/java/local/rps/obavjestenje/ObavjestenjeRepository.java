package local.rps.obavjestenje;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObavjestenjeRepository extends JpaRepository <Obavjestenje, Integer> {	
	public List<Obavjestenje> findByPasivniKorisnikId(int id, Sort sort);
}

